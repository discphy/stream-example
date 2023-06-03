package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ItemPrintServiceTest {

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
    void 일반_아이템_출력_V_1() {
        for (int i = 0; i < items.size(); i++) {
            System.out.println("item = " + items.get(i));
        }
    }

    @Test
    void 일반_아이템_출력_V_2() {
        for (Item item : items) {
            System.out.println("item = " + item);
        }
    }

    @Test
    void 람다_아이템_출력_V_1() {
        items.forEach(item -> System.out.println("item = " + item));
    }

    @Test
    void 람다_아이템_출력_참조_V_2() {
        items.forEach(this::print);
    }

    void print(Item item) {
        System.out.println("item = " + item);
    }
}
