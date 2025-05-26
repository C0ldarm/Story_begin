package com.example.integrationtest.controller;

import com.example.integrationtest.model.Item;
import com.example.integrationtest.request.ItemCreateRequest;
import com.example.integrationtest.request.ItemUpdateRequest;
import com.example.integrationtest.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemService itemService;

    @GetMapping
    public List<Item> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getById(@PathVariable String id) {
        Item item = itemService.getById(id);
        return item != null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Item create(@RequestBody ItemCreateRequest request) {
        return itemService.create(request);
    }

    @PutMapping
    public ResponseEntity<Item> update(@RequestBody ItemUpdateRequest request) {
        Item updatedItem = itemService.update(request);
        return updatedItem != null ? ResponseEntity.ok(updatedItem) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        itemService.delById(id);
        return ResponseEntity.noContent().build();
    }
}