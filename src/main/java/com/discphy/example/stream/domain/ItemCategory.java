package com.discphy.example.stream.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCategory {

    private Long id;
    private String itemName;
    private Category category;
    private Integer price;
    private Integer quantity;
}
