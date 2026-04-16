package org.example.servicetest.services;

import org.example.servicetest.model.Item;
import org.example.servicetest.model.ItemCreateRequest;
import org.example.servicetest.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    private final ItemRepository repository;

    public ItemService(ItemRepository repository) {
        this.repository = repository;
    }

    public Item create(ItemCreateRequest request) {
        if (repository.existsByCode(request.code())) {
            throw new IllegalArgumentException("Code already exists: " + request.code());
        }
        Item item = Item.builder()
                .name(request.name())
                .code(request.code())
                .description(request.description())
                .createDate(LocalDateTime.now())
                .updateDate(Optional.empty())
                .build();
        return repository.save(item);
    }

    public Optional<Item> update(String id, ItemCreateRequest request) {
        Optional<Item> existing = repository.findById(id);
        if (existing.isEmpty()) {
            return Optional.empty();
        }
        if (!existing.get().getCode().equals(request.code()) && repository.existsByCode(request.code())) {
            throw new IllegalArgumentException("Code already exists: " + request.code());
        }
        Item updated = Item.builder()
                .id(id)
                .name(request.name())
                .code(request.code())
                .description(request.description())
                .createDate(existing.get().getCreateDate())
                .updateDate(Optional.of(LocalDateTime.now()))
                .build();
        return Optional.of(repository.save(updated));
    }

    public Optional<Item> findById(String id) {
        return repository.findById(id);
    }

    public Optional<Item> findByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code));
    }

    public List<Item> findAll() {
        return repository.findAll();
    }

    public boolean delete(String id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}