package com.example.mockingtest.controller;

import com.example.mockingtest.model.Item;
import com.example.mockingtest.request.ItemCreateRequest;
import com.example.mockingtest.request.ItemUpdateRequest;
import com.example.mockingtest.service.ItemService;
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
