package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Dish(
        UUID uuid,
        Instant createdAt,
        Instant updatedAt,
        String name,
        String description,
        List<Allergen> allergens,
        boolean isActive,
        SellingPrice price,
        CostPrice cost,
        String thumbnailUrl,
        DishType type
) {
    public Dish(UUID uuid, String name, String description, List<Allergen> allergens,
                boolean isActive, SellingPrice price, CostPrice cost, DishType type, String thumbnailUrl) {
        this(uuid, Instant.now(), Instant.now(), name, description, allergens, isActive, price, cost, thumbnailUrl, type);
    }
}
