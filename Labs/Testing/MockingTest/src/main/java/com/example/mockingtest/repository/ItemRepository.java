package com.example.mockingtest.repository;

/*
  @author   grok
  @project   mockingtest
  @class    ItemRepository
  @version  1.0.0
  @since    26.05.25 - 22.00
*/

import com.example.mockingtest.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, String> {
}