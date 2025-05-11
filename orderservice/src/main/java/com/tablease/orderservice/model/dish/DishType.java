package com.tablease.orderservice.model.dish;

import com.tablease.orderservice.model.dish.valueobjects.Destination;
import com.tablease.orderservice.model.dish.valueobjects.DishTypeId;
import lombok.Getter;

import java.time.Instant;

@Getter
public class DishType {
    private final DishTypeId id;
    private final String name; // "DRINK", "MAIN", etc.
    private final Destination destination; // BAR or KITCHEN
    private final Instant createdAt;
    private Instant updatedAt;

    public DishType(DishTypeId id, String name, Destination destination,Instant createdAt) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }
}
