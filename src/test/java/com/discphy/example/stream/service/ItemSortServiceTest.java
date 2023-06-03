package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class ItemSortServiceTest {

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
    void 정렬_상품가격순_정렬_후_최솟값_V_1() {
    	//given
        items.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getPrice().compareTo(o2.getPrice());
            }
        });

        //when
        Item firstItem = items.get(0);
        System.out.println("firstItem = " + firstItem);

        //then
        assertThat(firstItem.getId()).isEqualTo(5L);
        assertThat(firstItem.getItemName()).isEqualTo("음식A");
        assertThat(firstItem.getPrice()).isEqualTo(8000);
    }

    @Test
    void 정렬_상품가격순_정렬_후_최솟값_V_2() {
    	//given
        items.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));

        //when
        Item firstItem = items.get(0);
        System.out.println("firstItem = " + firstItem);

        //then
        assertThat(firstItem.getId()).isEqualTo(5L);
        assertThat(firstItem.getItemName()).isEqualTo("음식A");
        assertThat(firstItem.getPrice()).isEqualTo(8000);
    }

    @Test
    void 정렬_상품가격순_정렬_후_최솟값_V_3() {
    	//given
        items.sort(Comparator.comparing(Item::getPrice));

        //when
        Item firstItem = items.get(0);
        System.out.println("firstItem = " + firstItem);

        //then
        assertThat(firstItem.getId()).isEqualTo(5L);
        assertThat(firstItem.getItemName()).isEqualTo("음식A");
        assertThat(firstItem.getPrice()).isEqualTo(8000);
    }

    @Test
    void 스트림_정렬_상품가격순_정렬_후_최솟값() {
        //when
        Item firstItem = items.stream().min(Comparator.comparing(Item::getPrice)).get();
        System.out.println("firstItem = " + firstItem);

        //then
        assertThat(firstItem.getId()).isEqualTo(5L);
        assertThat(firstItem.getItemName()).isEqualTo("음식A");
        assertThat(firstItem.getPrice()).isEqualTo(8000);
    }
}
