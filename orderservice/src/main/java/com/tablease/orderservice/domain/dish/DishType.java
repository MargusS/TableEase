package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.Destination;

import java.time.Instant;
import java.util.UUID;

public record DishType(
        UUID uuid,
        String name,
        Destination destination,
        Instant createdAt,
        Instant updatedAt
) {
    public DishType(UUID uuid, String name, Destination destination, Instant createdAt) {
        this(uuid, name, destination, createdAt, createdAt);
    }
}
