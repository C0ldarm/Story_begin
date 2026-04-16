package com.example.mockingtest.service;

import com.example.mockingtest.model.Item;
import com.example.mockingtest.repository.ItemRepository;
import com.example.mockingtest.request.ItemCreateRequest;
import com.example.mockingtest.request.ItemUpdateRequest;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    private List<Item> items = new ArrayList<>();
    {
        items.add(new Item("Freddie Mercury", "Queen", "vocal, piano"));
        items.add(new Item("Paul McCartney", "Beatles", "guitar"));
        items.add(new Item("Mick Jagger", "Rolling Stones", "vocal"));
    }

    @PostConstruct
    public void init() {
        itemRepository.deleteAll();
        itemRepository.saveAll(items);
    }

    // CRUD operations

    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    public Item getById(String id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item create(Item item) {
        return itemRepository.save(item);
    }

    public Item create(ItemCreateRequest request) {
        Item item = mapToItem(request);
        item.setCreateDate(LocalDateTime.now());
        item.setUpdateDate(new ArrayList<LocalDateTime>());
        return itemRepository.save(item);
    }

    public Item update(Item item) {
        return itemRepository.save(item);
    }

    public void delById(String id) {
        itemRepository.deleteById(id);
    }

    private Item mapToItem(ItemCreateRequest request) {
        Item item = new Item(request.getName(), request.getCode(), request.getDescription());
        return item;
    }

    public Item update(ItemUpdateRequest request) {
        Item itemPersisted = itemRepository.findById(request.getId()).orElse(null);
        if (itemPersisted != null) {
            List<LocalDateTime> updateDates = itemPersisted.getUpdateDate();
            updateDates.add(LocalDateTime.now());
            Item itemToUpdate = Item.builder()
                    .id(request.getId())
                    .name(request.getName())
                    .code(request.getCode())
                    .description(request.getDescription())
                    .createDate(itemPersisted.getCreateDate())
                    .updateDate(updateDates)
                    .build();
            return itemRepository.save(itemToUpdate);
        }
        return null;
    }
}
