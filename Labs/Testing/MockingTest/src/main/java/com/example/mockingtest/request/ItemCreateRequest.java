package com.example.mockingtest.request;

/*
  @author   grok
  @project   mockingtest
  @class    ItemCreateRequest
  @version  1.0.0
  @since    26.05.25 - 22.00
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemCreateRequest {

    private String name;
    private String code;
    private String description;
}