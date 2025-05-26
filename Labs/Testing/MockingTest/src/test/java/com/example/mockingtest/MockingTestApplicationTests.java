package com.example.mockingtest;

/*
  @author   grok
  @project   mockingtest
  @class    ItemServiceTest
  @version  1.0.0
  @since    26.05.25 - 22.00
*/

import com.example.mockingtest.model.Item;
import com.example.mockingtest.repository.ItemRepository;
import com.example.mockingtest.request.ItemCreateRequest;
import com.example.mockingtest.request.ItemUpdateRequest;
import com.example.mockingtest.service.ItemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemService itemService;

    private Item item;
    private ItemCreateRequest createRequest;
    private ItemUpdateRequest updateRequest;

    @BeforeEach
    void setUp() {
        item = Item.builder()
                .id("1")
                .name("Freddie Mercury")
                .code("Queen")
                .description("vocal, piano")
                .createDate(LocalDateTime.now())
                .updateDate(new ArrayList<>())
                .build();

        createRequest = new ItemCreateRequest("John Lennon", "Beatles", "guitar, vocal");
        updateRequest = new ItemUpdateRequest("1", "Freddie Mercury Updated", "Queen", "vocal, piano");
    }

    @Test
    void givenItemsInRepository_whenGetAll_thenReturnAllItems() {
        // Given
        List<Item> items = Arrays.asList(item);
        when(itemRepository.findAll()).thenReturn(items);

        // When
        List<Item> result = itemService.getAll();

        // Then
        assertEquals(1, result.size());
        assertEquals(item, result.get(0));
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void givenEmptyRepository_whenGetAll_thenReturnEmptyList() {
        // Given
        when(itemRepository.findAll()).thenReturn(new ArrayList<>());

        // When
        List<Item> result = itemService.getAll();

        // Then
        assertTrue(result.isEmpty());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void givenValidId_whenGetById_thenReturnItem() {
        // Given
        when(itemRepository.findById("1")).thenReturn(Optional.of(item));

        // When
        Item result = itemService.getById("1");

        // Then
        assertNotNull(result);
        assertEquals(item, result);
        verify(itemRepository, times(1)).findById("1");
    }

    @Test
    void givenInvalidId_whenGetById_thenReturnNull() {
        // Given
        when(itemRepository.findById("999")).thenReturn(Optional.empty());

        // When
        Item result = itemService.getById("999");

        // Then
        assertNull(result);
        verify(itemRepository, times(1)).findById("999");
    }

    @Test
    void givenItemObject_whenCreate_thenReturnSavedItem() {
        // Given
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        // When
        Item result = itemService.create(item);

        // Then
        assertNotNull(result);
        assertEquals(item, result);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void givenNullCreateRequest_whenCreate_thenThrowException() {
        // Given
        ItemCreateRequest nullRequest = null;

        // When & Then
        assertThrows(NullPointerException.class, () -> itemService.create(nullRequest));
        verify(itemRepository, never()).save(any(Item.class));
    }


    @Test
    void givenItemObject_whenUpdate_thenReturnUpdatedItem() {
        // Given
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        // When
        Item result = itemService.update(item);

        // Then
        assertNotNull(result);
        assertEquals(item, result);
        verify(itemRepository, times(1)).save(item);
    }

    @Test
    void givenValidUpdateRequest_whenUpdate_thenReturnUpdatedItem() {
        // Given
        when(itemRepository.findById("1")).thenReturn(Optional.of(item));
        Item updatedItem = Item.builder()
                .id("1")
                .name("Freddie Mercury Updated")
                .code("Queen")
                .description("vocal, piano")
                .createDate(item.getCreateDate())
                .updateDate(new ArrayList<>(List.of(LocalDateTime.now())))
                .build();
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        // When
        Item result = itemService.update(updateRequest);

        // Then
        assertNotNull(result);
        assertEquals("Freddie Mercury Updated", result.getName());
        assertFalse(result.getUpdateDate().isEmpty());
        verify(itemRepository, times(1)).findById("1");
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void givenInvalidUpdateRequest_whenUpdate_thenReturnNull() {
        // Given
        when(itemRepository.findById("999")).thenReturn(Optional.empty());

        // When
        Item result = itemService.update(new ItemUpdateRequest("999", "Invalid", "Unknown", "Unknown"));

        // Then
        assertNull(result);
        verify(itemRepository, times(1)).findById("999");
        verify(itemRepository, never()).save(any(Item.class));
    }

    @Test
    void givenValidId_whenDeleteById_thenDeleteItem() {
        // Given
        doNothing().when(itemRepository).deleteById("1");

        // When
        itemService.delById("1");

        // Then
        verify(itemRepository, times(1)).deleteById("1");
    }

    @Test
    void givenMultipleItems_whenGetAll_thenReturnAllItems() {
        // Given
        Item item2 = new Item("John Lennon", "Beatles", "guitar, vocal");
        List<Item> items = Arrays.asList(item, item2);
        when(itemRepository.findAll()).thenReturn(items);

        // When
        List<Item> result = itemService.getAll();

        // Then
        assertEquals(2, result.size());
        verify(itemRepository, times(1)).findAll();
    }

    @Test
    void givenEmptyName_whenCreate_thenSaveItem() {
        // Given
        ItemCreateRequest request = new ItemCreateRequest("", "Code", "Description");
        Item newItem = new Item("", "Code", "Description");
        when(itemRepository.save(any(Item.class))).thenReturn(newItem);

        // When
        Item result = itemService.create(request);

        // Then
        assertNotNull(result);
        assertEquals("", result.getName());
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void givenUpdateRequestWithNullFields_whenUpdate_thenReturnUpdatedItem() {
        // Given
        ItemUpdateRequest nullFieldsRequest = new ItemUpdateRequest("1", null, null, null);
        when(itemRepository.findById("1")).thenReturn(Optional.of(item));
        Item updatedItem = Item.builder()
                .id("1")
                .name(null)
                .code(null)
                .description(null)
                .createDate(item.getCreateDate())
                .updateDate(new ArrayList<>(List.of(LocalDateTime.now())))
                .build();
        when(itemRepository.save(any(Item.class))).thenReturn(updatedItem);

        // When
        Item result = itemService.update(nullFieldsRequest);

        // Then
        assertNotNull(result);
        assertNull(result.getName());
        assertFalse(result.getUpdateDate().isEmpty());
        verify(itemRepository, times(1)).findById("1");
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void givenPostConstruct_whenInit_thenClearAndSaveItems() {
        // Given
        itemService = new ItemService(itemRepository);
        doNothing().when(itemRepository).deleteAll();
        when(itemRepository.saveAll(anyList())).thenReturn(new ArrayList<>());

        // When
        itemService.init();

        // Then
        verify(itemRepository, times(1)).deleteAll();
        verify(itemRepository, times(1)).saveAll(anyList());
    }
    
    @Test
    void givenEmptyId_whenGetById_thenReturnNull() {
        // Given
        when(itemRepository.findById("")).thenReturn(Optional.empty());

        // When
        Item result = itemService.getById("");

        // Then
        assertNull(result);
        verify(itemRepository, times(1)).findById("");
    }

    @Test
    void givenSameDataUpdateRequest_whenUpdate_thenReturnUpdatedItem() {
        // Given
        when(itemRepository.findById("1")).thenReturn(Optional.of(item));
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        // When
        Item result = itemService.update(new ItemUpdateRequest("1", "Freddie Mercury", "Queen", "vocal, piano"));

        // Then
        assertNotNull(result);
        assertEquals(item.getName(), result.getName());
        assertFalse(result.getUpdateDate().isEmpty());
        verify(itemRepository, times(1)).findById("1");
        verify(itemRepository, times(1)).save(any(Item.class));
    }

    @Test
    void givenNonExistentId_whenDeleteById_thenNoException() {
        // Given
        doNothing().when(itemRepository).deleteById("999");

        // When
        itemService.delById("999");

        // Then
        verify(itemRepository, times(1)).deleteById("999");
    }
}
