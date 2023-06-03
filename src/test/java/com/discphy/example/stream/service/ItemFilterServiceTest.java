package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Item;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;

class ItemFilterServiceTest {

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
    void foreach_상품명이_자동차인것들만_필터링() {
    	//given
        List<Item> cars = new ArrayList<>();

    	//when
        for (Item item : items) {
            if (item.getItemName().contains("자동차")) cars.add(item);
        }

        //then
        assertThat(cars).hasSize(2);
    }

    @Test
    void 스트림_상품명이_자동차인것들만_필터링() {
        //when
        List<Item> cars = items.stream()
                .filter(item -> item.getItemName().contains("자동차"))
                .collect(toList());

        //then
        assertThat(cars).hasSize(2);
    }
    
    @Test
    void 상품명이_음식이면서_가격이_8500원_이상인것들만_필터링() {
        //when
        List<Item> foods = items.stream()
                .filter(item -> item.getItemName().contains("음식"))
                .filter(item -> item.getPrice() > 8500)
                .collect(toList());
        /*
         * 아래와 같이 사용해도 된다.
         * .filter(item -> item.getItemName().contains("음식") && item.getPrice() > 8500))
         */

        //then
        assertThat(foods).hasSize(2);
    }

    @Test
    void 상품가격_필터링_중복_제거() {
    	//when
        List<Integer> prices = items.stream()
                .map(Item::getPrice) // 뒤에서 설명할 map() 메소드이며 참조를 이용하였다.
                .distinct()
                .collect(toList());

        //then
        assertThat(prices).hasSize(8); // 10000원 중복 제거 9 - 1 = 8
        assertThat(prices).containsExactly(10000, 20000, 1000000, 2000000, 8000, 9000, 100000, 40000);
    }
}
