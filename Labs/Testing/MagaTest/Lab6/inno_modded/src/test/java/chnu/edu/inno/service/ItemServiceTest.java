package chnu.edu.inno.service;

import chnu.edu.inno.dto.PageResponse;
import chnu.edu.inno.exception.EmptyListException;
import chnu.edu.inno.exception.InvalidRequestException;
import chnu.edu.inno.exception.PageNotFoundException;
import chnu.edu.inno.exception.ResourceNotFoundException;
import chnu.edu.inno.model.Item;
import chnu.edu.inno.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ItemServiceTest {

    private final ItemRepository repository = mock(ItemRepository.class);
    private final ItemService service = new ItemService(repository);

    // ---------- пагінація ----------

    @Test
    void negativePageIsBadRequest() {
        InvalidRequestException ex = assertThrows(InvalidRequestException.class,
                () -> service.getItemsPage(-1, 10));
        assertTrue(ex.getMessage().contains("від'ємним"));
        verifyNoInteractions(repository);
    }

    @Test
    void nonPositiveSizeIsBadRequest() {
        assertThrows(InvalidRequestException.class, () -> service.getItemsPage(0, 0));
        assertThrows(InvalidRequestException.class, () -> service.getItemsPage(0, -5));
    }

    @Test
    void tooLargeSizeIsBadRequest() {
        assertThrows(InvalidRequestException.class,
                () -> service.getItemsPage(0, ItemService.MAX_PAGE_SIZE + 1));
    }

    @Test
    void emptyCollectionThrowsEmptyListException() {
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(), PageRequest.of(0, 10), 0));
        assertThrows(EmptyListException.class, () -> service.getItemsPage(0, 10));
    }

    @Test
    void pageBeyondLastThrowsPageNotFound() {
        // 3 елементи, size=2 -> 2 сторінки (0 і 1); запитуємо 10-ту
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(List.of(), PageRequest.of(10, 2), 3));
        PageNotFoundException ex = assertThrows(PageNotFoundException.class,
                () -> service.getItemsPage(10, 2));
        assertTrue(ex.getMessage().contains("10"));
    }

    @Test
    void validPageIsReturned() {
        List<Item> items = List.of(new Item("Milk", "1", 1), new Item("Bread", "2", 2));
        when(repository.findAll(any(Pageable.class)))
                .thenReturn(new PageImpl<>(items, PageRequest.of(0, 2), 3));
        PageResponse<Item> result = service.getItemsPage(0, 2);
        assertEquals(2, result.content().size());
        assertEquals(2, result.totalPages());
        assertEquals(3, result.totalElements());
    }

    // ---------- create ----------

    @Test
    void createWithoutRequiredFieldsIsBadRequestWithDetails() {
        InvalidRequestException ex = assertThrows(InvalidRequestException.class,
                () -> service.createItem(new Item(null, " ", 1)));
        assertEquals(2, ex.getDetails().size());
        verify(repository, never()).save(any());
    }

    @Test
    void createIgnoresClientId() {
        Item input = new Item("Milk", "1", 1);
        input.setId("client-id");
        when(repository.save(any(Item.class))).thenAnswer(i -> i.getArgument(0));
        Item saved = service.createItem(input);
        assertNull(saved.getId());
    }

    // ---------- update ----------

    @Test
    void updateMissingItemThrowsNotFoundAndDoesNotSave() {
        Item item = new Item("Milk", "1", 1);
        item.setId("missing");
        when(repository.existsById("missing")).thenReturn(false);
        assertThrows(ResourceNotFoundException.class, () -> service.updateItem(item));
        verify(repository, never()).save(any());
    }

    @Test
    void updateExistingItemSaves() {
        Item item = new Item("Milk", "1", 1);
        item.setId("42");
        when(repository.existsById("42")).thenReturn(true);
        when(repository.save(item)).thenReturn(item);
        assertSame(item, service.updateItem(item));
    }

    @Test
    void updateWithoutIdIsBadRequest() {
        assertThrows(InvalidRequestException.class,
                () -> service.updateItem(new Item("Milk", "1", 1)));
    }

    // ---------- delete / get ----------

    @Test
    void deleteMissingItemThrowsNotFound() {
        when(repository.findById("x")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.deleteItemById("x"));
        verify(repository, never()).delete(any());
        verify(repository, never()).deleteById(any());
    }

    @Test
    void deleteExistingItemDeletesWholeObjectNotById() {
        // JaVers: deleteById кидає виняток, тому видаляємо лише цілим об'єктом
        Item item = new Item("Milk", "1", 1);
        item.setId("x");
        when(repository.findById("x")).thenReturn(Optional.of(item));
        service.deleteItemById("x");
        verify(repository).delete(item);
        verify(repository, never()).deleteById(any());
    }

    @Test
    void getMissingItemThrowsNotFound() {
        when(repository.findById("x")).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> service.getItemById("x"));
    }
}
