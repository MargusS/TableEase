package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class Dish {

    private final UUID uuid;
    private String name;
    private String description;
    private SellingPrice price;
    private CostPrice cost;
    private DishType dishType;
    private List<Allergen> allergens;
    private boolean isActive;
    private String thumbnail;
    private Instant createdAt;
    private Instant updatedAt;

    public Dish(UUID uuid, String name, String description, SellingPrice price, CostPrice cost, DishType dishType, List<Allergen> allergens, String thumbnail) {
        if (uuid == null) throw new IllegalArgumentException("Dish UUID cannot be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Dish name cannot be null or empty");
        if (description.length() > 500) throw new IllegalArgumentException("Description is too long");
        if (dishType == null) throw new IllegalArgumentException("Dish type is required");

        this.uuid = uuid;
        this.name = name.trim();
        this.description = (description != null) ? description.trim() : null;
        this.price = price;
        this.cost = cost;
        this.dishType = dishType;
        this.allergens = List.copyOf(allergens);
        this.thumbnail = (thumbnail != null && !thumbnail.isBlank()) ? thumbnail.trim() : null;
        this.isActive = true;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

}
