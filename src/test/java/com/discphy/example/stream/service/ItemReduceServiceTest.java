package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.offset;

class ItemReduceServiceTest {

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
    void 일반_재고_X_가격_총액_구하기() {
        //given
        Integer allPrice = 0;

        for (Item item : items) {
            allPrice += item.getPrice() * item.getQuantity();
        }

        //then
        assertThat(allPrice).isEqualTo(23100000);
    }

    @Test
    void 스트림_재고_X_가격_총액_구하기() {
        //when
        Integer allPrice = items.stream()
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .reduce(0, Integer::sum);

        //then
        assertThat(allPrice).isEqualTo(23100000);
    }

    @Test
    void 일반_가격_평균_구하기() {
        //given
        Integer allPrice = 0;

        for (Item item : items) {
            allPrice += item.getPrice();
        }

        //when
        double averagePrice = (double) allPrice / items.size();
        System.out.println("averagePrice = " + averagePrice);

        //then
        assertThat(averagePrice).isEqualTo(355222, offset(1d));
    }

    @Test
    void 스트림_가격_평균_구하기() {
        //when
        double averagePrice = items.stream()
                .mapToInt(Item::getPrice)
                .average()
                .orElse(0.0);

        System.out.println("averagePrice = " + averagePrice);

        //then
        assertThat(averagePrice).isEqualTo(355222, offset(1d));
    }
}
