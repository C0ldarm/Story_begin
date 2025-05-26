package com.example.integrationtest.service;

import com.example.integrationtest.model.Item;
import com.example.integrationtest.repository.ItemRepository;
import com.example.integrationtest.request.ItemCreateRequest;
import com.example.integrationtest.request.ItemUpdateRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private List<Item> items = new ArrayList<>();
    {
        items.add(Item.builder()
                .id("1")
                .name("Freddie Mercury")
                .code("Queen")
                .description("vocal, piano")
                .createDate(LocalDateTime.now())
                .updateDate(new ArrayList<>())
                .build());
        items.add(Item.builder()
                .id("2")
                .name("Paul McCartney")
                .code("Beatles")
                .description("guitar")
                .createDate(LocalDateTime.now())
                .updateDate(new ArrayList<>())
                .build());
        items.add(Item.builder()
                .id("3")
                .name("Mick Jagger")
                .code("Rolling Stones")
                .description("vocal")
                .createDate(LocalDateTime.now())
                .updateDate(new ArrayList<>())
                .build());
    }

    @PostConstruct
    void init() {
        itemRepository.deleteAll();
        itemRepository.saveAll(items);
    }

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item create(Item item) {
        if (item.getId() == null) {
            item.setId(UUID.randomUUID().toString());
        }
        if (item.getCreateDate() == null) {
            item.setCreateDate(LocalDateTime.now());
        }
        return itemRepository.save(item);
    }

    public Item create(ItemCreateRequest request) {
        Item item = mapToItem(request);
        item.setId(UUID.randomUUID().toString());
        item.setCreateDate(LocalDateTime.now());
        item.setUpdateDate(new ArrayList<>());
        return itemRepository.save(item);
    }

    public Item update(Item item) {
        if (item.getId() == null) {
            throw new IllegalArgumentException("Item ID cannot be null for update");
        }
        return itemRepository.save(item);
    }

    public void delById(String id) {
        itemRepository.deleteById(id);
    }

    private Item mapToItem(ItemCreateRequest request) {
        return new Item(request.getName(), request.getCode(), request.getDescription());
    }

    public Item update(ItemUpdateRequest request) {
        Item itemPersisted = itemRepository.findById(request.getId()).orElse(null);
        if (itemPersisted != null) {
            List<LocalDateTime> updateDates = itemPersisted.getUpdateDate() != null ? new ArrayList<>(itemPersisted.getUpdateDate()) : new ArrayList<>();
            updateDates.add(LocalDateTime.now());
            Item itemToUpdate = Item.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .code(request.getCode())
                    .description(request.getDescription())
                    .createDate(itemPersisted.getCreateDate() != null ? itemPersisted.getCreateDate() : LocalDateTime.now())
                    .updateDate(updateDates)
                    .build();
            return itemRepository.save(itemToUpdate);
        }
        return null;
    }
}