package org.example.servicetest.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Builder
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String code;
    private String description;
    private LocalDateTime createDate;
    private Optional<LocalDateTime> updateDate;
}