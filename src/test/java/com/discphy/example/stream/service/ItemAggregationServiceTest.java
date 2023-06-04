package com.discphy.example.stream.service;

import com.discphy.example.stream.domain.Category;
import com.discphy.example.stream.domain.ItemCategory;
import org.junit.jupiter.api.Test;

import java.util.*;

import static com.discphy.example.stream.domain.Category.*;
import static java.util.stream.Collectors.groupingBy;
import static org.assertj.core.api.Assertions.assertThat;

class ItemAggregationServiceTest {

    static List<ItemCategory> items = Arrays.asList(
            new ItemCategory(1L, "책A", BOOK, 10000, 10),
            new ItemCategory(2L, "책B", BOOK, 20000, 20),
            new ItemCategory(3L, "자동차A", CAR, 1000000, 3),
            new ItemCategory(4L, "자동차B", CAR, 2000000, 5),
            new ItemCategory(5L, "음식A", FOOD, 8000, 150),
            new ItemCategory(6L, "음식B", FOOD, 9000, 200),
            new ItemCategory(7L, "음식C", FOOD, 10000, 300),
            new ItemCategory(8L, "노트북A", LAPTOP, 100000, 20),
            new ItemCategory(9L, "노트북B", LAPTOP, 40000, 40)
    );

    @Test
    void 일반_상품_카테고리_그룹핑() {
        //given
        Map<Category, List<ItemCategory>> itemGroups = new HashMap<>();

        for (Category category : Category.values()) {
            itemGroups.put(category, new ArrayList<>());
        }

        //when
        for (ItemCategory item : items) {
            itemGroups.get(item.getCategory()).add(item);
        }

        //then
        assertThat(itemGroups.keySet()).contains(BOOK, CAR, FOOD, LAPTOP);
        assertThat(itemGroups.get(BOOK).size()).isEqualTo(2);
        assertThat(itemGroups.get(CAR).size()).isEqualTo(2);
        assertThat(itemGroups.get(FOOD).size()).isEqualTo(3);
        assertThat(itemGroups.get(BOOK).size()).isEqualTo(2);
    }

    @Test
    void 스트림_상품_카테고리_그룹핑() {
        //when
        Map<Category, List<ItemCategory>> itemGroups = items.stream().collect(groupingBy(ItemCategory::getCategory));

        //then
        assertThat(itemGroups.keySet()).contains(BOOK, CAR, FOOD, LAPTOP);
        assertThat(itemGroups.get(BOOK).size()).isEqualTo(2);
        assertThat(itemGroups.get(CAR).size()).isEqualTo(2);
        assertThat(itemGroups.get(FOOD).size()).isEqualTo(3);
        assertThat(itemGroups.get(BOOK).size()).isEqualTo(2);
    }
}
