package com.example.integrationtest.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemUpdateRequest {

    private String id;
    private String name;
    private String code;
    private String description;
}