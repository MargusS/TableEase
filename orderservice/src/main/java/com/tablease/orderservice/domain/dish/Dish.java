package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.DishId;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import lombok.Getter;

import java.time.Instant;
import java.util.List;

@Getter
public class Dish {
    private final DishId id;
    private final Instant createdAt;
    private Instant updatedAt;
    private final String name;
    private final String description;
    private final List<Allergen> allergens;
    private final boolean isActive;
    private final Price price;
    private final Price cost;
    private final String thumbnailUrl;
    private final DishType type;

    public Dish(DishId id, String name, String description, List<Allergen> allergens, boolean isActive, Price price, Price cost, DishType type, String thumbnailUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.allergens = allergens;
        this.isActive = isActive;
        this.price = price;
        this.cost = cost;
        this.type = type;
        this.createdAt = Instant.now();
        this.thumbnailUrl = thumbnailUrl;
        this.updatedAt = this.createdAt;
    }

}
