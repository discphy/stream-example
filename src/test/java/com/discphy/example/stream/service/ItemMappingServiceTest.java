package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class ItemMappingServiceTest {

    static List<Item> items = Arrays.asList(
            new Item(1L, "책A", 10000, 10),
            new Item(2L, "책B", 20000, 20),
            new Item(3L, "자동차A", 1000000, 3),
            new Item(4L, "자동차B", 2000000, 5),
            new Item(5L, "음식A", 8000, 150),
            new Item(6L, "음식B", 9000, 200),
            new Item(7L, "음식C", 10000, 300),
            new Item(8L, "노트북A", 100000, 20),
            new Item(9L, "노트북B", 40000, 40)
    );

    @Test
    void 람다_상품명만_나열() {
    	//when
        List<String> itemNames = items.stream()
                .map(Item::getItemName)
                .collect(Collectors.toList());

        //then
        assertThat(itemNames).containsExactly("책A",
                "책B",
                "자동차A",
                "자동차B",
                "음식A",
                "음식B",
                "음식C",
                "노트북A",
                "노트북B");
    }

    @Test
    void foreach_상품명이_책인_상품을_수량을_0개로() {
    	//given
        List<Item> books = new ArrayList<>();

        //when
        for (Item item : items) {
            if (item.getItemName().contains("책")) {
                books.add(soldOutItem(item));
            }
        }

    	//then
        assertThat(books).hasSize(2);
        assertThat(books).extracting("quantity").containsOnly(0);
    }

    @Test
    void 람다_상품명이_책인_상품을_수량을_0개로() {
        //when
        List<Item> books = items.stream()
                .filter(item -> item.getItemName().contains("책"))
                .map(this::soldOutItem)
                .collect(Collectors.toList());

    	//then
        assertThat(books).hasSize(2);
        assertThat(books).extracting("quantity").containsOnly(0);
    }

    private Item soldOutItem(Item item) {
        item.setQuantity(0);
        return item;
    }
}
