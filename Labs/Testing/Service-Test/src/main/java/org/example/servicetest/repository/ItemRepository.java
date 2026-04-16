package org.example.servicetest.repository;

import org.example.servicetest.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
    boolean existsByCode(String code);
    Item findByCode(String code);
}