package com.tablease.orderservice.model.dish.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class DishId {
    private final UUID value;

    public DishId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }
}