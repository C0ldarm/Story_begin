package org.example.servicetest.service;

import org.example.servicetest.model.Item;
import org.example.servicetest.model.ItemCreateRequest;
import org.example.servicetest.repository.ItemRepository;
import org.example.servicetest.services.ItemService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class ItemServiceMockTests {

    @Mock
    private ItemRepository mockRepository;

    private ItemService underTest;

    @Captor
    private ArgumentCaptor<Item> argumentCaptor;

    private ItemCreateRequest request;
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest = new ItemService(mockRepository);
        request = new ItemCreateRequest("Till Lindemann", "Rammstein", "poet");
        item = Item.builder()
                .id("1")
                .name(request.name())
                .code(request.code())
                .description(request.description())
                .createDate(LocalDateTime.now())
                .updateDate(Optional.empty())
                .build();
    }

    @AfterEach
    void tearDown() {
        reset(mockRepository);
    }

    // Create Tests (5)
    @DisplayName("Create new Item - Happy Path")
    @Test
    void whenInsertNewItemAndCodeNotExistsThenOk() {
        given(mockRepository.existsByCode(request.code())).willReturn(false);
        given(mockRepository.save(any(Item.class))).willReturn(item);

        Item result = underTest.create(request);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getName()).isEqualTo(request.name());
        assertThat(itemToSave.getCode()).isEqualTo(request.code());
        assertThat(itemToSave.getDescription()).isEqualTo(request.description());
        assertNotNull(itemToSave.getCreateDate());
        assertTrue(itemToSave.getCreateDate().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertTrue(itemToSave.getUpdateDate().isEmpty());
        assertThat(result).isEqualTo(item);
        verify(mockRepository, times(1)).existsByCode(request.code());
        verify(mockRepository, times(1)).save(itemToSave);
    }

    @DisplayName("Create new Item - Code already exists")
    @Test
    void whenInsertNewItemAndCodeExistsThenThrowException() {
        given(mockRepository.existsByCode(request.code())).willReturn(true);

        assertThrows(IllegalArgumentException.class, () -> underTest.create(request));

        verify(mockRepository, times(1)).existsByCode(request.code());
        verify(mockRepository, never()).save(any());
    }

    @DisplayName("Create new Item - Null name")
    @Test
    void whenInsertNewItemWithNullNameThenSaveWithNullName() {
        ItemCreateRequest nullNameRequest = new ItemCreateRequest(null, "Rammstein", "poet");
        given(mockRepository.existsByCode(nullNameRequest.code())).willReturn(false);
        given(mockRepository.save(any(Item.class))).willReturn(item);

        underTest.create(nullNameRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getName()).isNull();
        verify(mockRepository, times(1)).existsByCode(nullNameRequest.code());
        verify(mockRepository, times(1)).save(itemToSave);
    }

    @DisplayName("Create new Item - Empty code")
    @Test
    void whenInsertNewItemWithEmptyCodeThenSave() {
        ItemCreateRequest emptyCodeRequest = new ItemCreateRequest("Till Lindemann", "", "poet");
        given(mockRepository.existsByCode(emptyCodeRequest.code())).willReturn(false);
        given(mockRepository.save(any(Item.class))).willReturn(item);

        underTest.create(emptyCodeRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getCode()).isEmpty();
        verify(mockRepository, times(1)).existsByCode(emptyCodeRequest.code());
        verify(mockRepository, times(1)).save(itemToSave);
    }

    @DisplayName("Create new Item - Null description")
    @Test
    void whenInsertNewItemWithNullDescriptionThenSaveWithNullDescription() {
        ItemCreateRequest nullDescRequest = new ItemCreateRequest("Till Lindemann", "Rammstein", null);
        given(mockRepository.existsByCode(nullDescRequest.code())).willReturn(false);
        given(mockRepository.save(any(Item.class))).willReturn(item);

        underTest.create(nullDescRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getDescription()).isNull();
        verify(mockRepository, times(1)).existsByCode(nullDescRequest.code());
        verify(mockRepository, times(1)).save(itemToSave);
    }

    // Update Tests (5)
    @DisplayName("Update Item - Happy Path")
    @Test
    void whenUpdateExistingItemThenOk() {
        ItemCreateRequest updateRequest = new ItemCreateRequest("Updated Name", "NewCode", "Updated poet");
        given(mockRepository.findById("1")).willReturn(Optional.of(item));
        given(mockRepository.existsByCode(updateRequest.code())).willReturn(false);
        given(mockRepository.save(any(Item.class))).willReturn(item);

        Optional<Item> result = underTest.update("1", updateRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getName()).isEqualTo(updateRequest.name());
        assertThat(itemToSave.getCode()).isEqualTo(updateRequest.code());
        assertThat(itemToSave.getDescription()).isEqualTo(updateRequest.description());
        assertThat(itemToSave.getUpdateDate()).isPresent();
        assertTrue(itemToSave.getUpdateDate().get().isBefore(LocalDateTime.now().plusSeconds(1)));
        assertThat(result).isPresent().contains(item);
        verify(mockRepository, times(1)).findById("1");
        verify(mockRepository, times(1)).existsByCode(updateRequest.code());
        verify(mockRepository, times(1)).save(itemToSave);
    }

    @DisplayName("Update Item - Item not found")
    @Test
    void whenUpdateNonExistingItemThenReturnEmpty() {
        given(mockRepository.findById("1")).willReturn(Optional.empty());

        Optional<Item> result = underTest.update("1", request);

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findById("1");
        verify(mockRepository, never()).existsByCode(any());
        verify(mockRepository, never()).save(any());
    }

    @DisplayName("Update Item - Code already exists")
    @Test
    void whenUpdateItemWithExistingCodeThenThrowException() {
        ItemCreateRequest updateRequest = new ItemCreateRequest("Updated Name", "ExistingCode", "Updated poet");
        given(mockRepository.findById("1")).willReturn(Optional.of(item));
        given(mockRepository.existsByCode(updateRequest.code())).willReturn(true);

        assertThrows(IllegalArgumentException.class, () -> underTest.update("1", updateRequest));

        verify(mockRepository, times(1)).findById("1");
        verify(mockRepository, times(1)).existsByCode(updateRequest.code());
        verify(mockRepository, never()).save(any());
    }

    @DisplayName("Update Item - Same code as existing")
    @Test
    void whenUpdateItemWithSameCodeThenOk() {
        ItemCreateRequest updateRequest = new ItemCreateRequest("Updated Name", "Rammstein", "Updated poet");
        given(mockRepository.findById("1")).willReturn(Optional.of(item));
        given(mockRepository.save(any(Item.class))).willReturn(item);

        Optional<Item> result = underTest.update("1", updateRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getName()).isEqualTo(updateRequest.name());
        assertThat(itemToSave.getCode()).isEqualTo(updateRequest.code());
        assertThat(itemToSave.getDescription()).isEqualTo(updateRequest.description());
        assertThat(itemToSave.getUpdateDate()).isPresent();
        assertThat(result).isPresent().contains(item);
        verify(mockRepository, times(1)).findById("1");
        verify(mockRepository, times(1)).save(itemToSave);
        verifyNoMoreInteractions(mockRepository);
    }
    @DisplayName("Update Item - Null fields")
    @Test
    void whenUpdateItemWithNullFieldsThenSaveWithNulls() {
        ItemCreateRequest updateRequest = new ItemCreateRequest(null, "Rammstein", null);
        given(mockRepository.findById("1")).willReturn(Optional.of(item));
        given(mockRepository.save(any(Item.class))).willReturn(item);

        Optional<Item> result = underTest.update("1", updateRequest);

        then(mockRepository).should().save(argumentCaptor.capture());
        Item itemToSave = argumentCaptor.getValue();
        assertThat(itemToSave.getName()).isNull();
        assertThat(itemToSave.getCode()).isEqualTo(updateRequest.code());
        assertThat(itemToSave.getDescription()).isNull();
        assertThat(itemToSave.getUpdateDate()).isPresent();
        assertThat(result).isPresent().contains(item);
        verify(mockRepository, times(1)).findById("1");
        verify(mockRepository, times(1)).save(itemToSave);
        verifyNoMoreInteractions(mockRepository);
    }

    // FindById Tests (3)
    @DisplayName("Find Item by ID - Happy Path")
    @Test
    void whenFindByIdExistingThenReturnItem() {
        given(mockRepository.findById("1")).willReturn(Optional.of(item));

        Optional<Item> result = underTest.findById("1");

        assertThat(result).isPresent().contains(item);
        verify(mockRepository, times(1)).findById("1");
    }

    @DisplayName("Find Item by ID - Not found")
    @Test
    void whenFindByIdNonExistingThenReturnEmpty() {
        given(mockRepository.findById("1")).willReturn(Optional.empty());

        Optional<Item> result = underTest.findById("1");

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findById("1");
    }

    @DisplayName("Find Item by ID - Null ID")
    @Test
    void whenFindByIdNullThenReturnEmpty() {
        given(mockRepository.findById(null)).willReturn(Optional.empty());

        Optional<Item> result = underTest.findById(null);

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findById(null);
    }

    // FindByCode Tests (3)
    @DisplayName("Find Item by Code - Happy Path")
    @Test
    void whenFindByCodeExistingThenReturnItem() {
        given(mockRepository.findByCode("Rammstein")).willReturn(item);

        Optional<Item> result = underTest.findByCode("Rammstein");

        assertThat(result).isPresent().contains(item);
        verify(mockRepository, times(1)).findByCode("Rammstein");
    }

    @DisplayName("Find Item by Code - Not found")
    @Test
    void whenFindByCodeNonExistingThenReturnEmpty() {
        given(mockRepository.findByCode("Unknown")).willReturn(null);

        Optional<Item> result = underTest.findByCode("Unknown");

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findByCode("Unknown");
    }

    @DisplayName("Find Item by Code - Null code")
    @Test
    void whenFindByCodeNullThenReturnEmpty() {
        given(mockRepository.findByCode(null)).willReturn(null);

        Optional<Item> result = underTest.findByCode(null);

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findByCode(null);
    }
    // FindAll Tests (2)
    @DisplayName("Find All Items - Multiple items")
    @Test
    void whenFindAllThenReturnList() {
        Item item2 = Item.builder().id("2").name("Other").code("OtherCode").build();
        given(mockRepository.findAll()).willReturn(Arrays.asList(item, item2));

        List<Item> result = underTest.findAll();

        assertThat(result).hasSize(2).contains(item, item2);
        verify(mockRepository, times(1)).findAll();
    }

    @DisplayName("Find All Items - Empty list")
    @Test
    void whenFindAllEmptyThenReturnEmptyList() {
        given(mockRepository.findAll()).willReturn(List.of());

        List<Item> result = underTest.findAll();

        assertThat(result).isEmpty();
        verify(mockRepository, times(1)).findAll();
    }

    // Delete Tests (3)
    @DisplayName("Delete Item - Happy Path")
    @Test
    void whenDeleteExistingItemThenReturnTrue() {
        given(mockRepository.existsById("1")).willReturn(true);

        boolean result = underTest.delete("1");

        assertThat(result).isTrue();
        verify(mockRepository, times(1)).existsById("1");
        verify(mockRepository, times(1)).deleteById("1");
    }

    @DisplayName("Delete Item - Not found")
    @Test
    void whenDeleteNonExistingItemThenReturnFalse() {
        given(mockRepository.existsById("1")).willReturn(false);

        boolean result = underTest.delete("1");

        assertThat(result).isFalse();
        verify(mockRepository, times(1)).existsById("1");
        verify(mockRepository, never()).deleteById(any());
    }
    @DisplayName("Delete Item - Null ID")
    @Test
    void whenDeleteNullIdThenReturnFalse() {
        given(mockRepository.existsById(null)).willReturn(false);

        boolean result = underTest.delete(null);

        assertThat(result).isFalse();
        verify(mockRepository, times(1)).existsById(null);
        verify(mockRepository, never()).deleteById(any());
    }
}