package com.example.mockingtest.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item {

    @Id
    private String id;

    private String name;
    private String code;
    private String description;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @ElementCollection
    @CollectionTable(name = "item_update_dates", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "update_date")
    private List<LocalDateTime> updateDate = new ArrayList<>();

    // Constructor used in ItemService
    public Item(String name, String code, String description) {
        this.name = name;
        this.code = code;
        this.description = description;
    }
}
