package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class DishType {

    private final UUID uuid;
    private String name;
    private Destination preparationArea;
    private Instant createdAt;
    private Instant updatedAt;

    public DishType(UUID uuid, String name, Destination preparationArea) {
        if (uuid == null) throw new IllegalArgumentException("DishType UUID cannot be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("DishType name cannot be null or empty");
        if (preparationArea == null) throw new IllegalArgumentException("Preparation area is required");

        this.uuid = uuid;
        this.name = name.trim();
        this.preparationArea = preparationArea;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }
}
