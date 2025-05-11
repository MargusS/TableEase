package com.tablease.orderservice.domain.dish.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record DishId(UUID value) {
    public DishId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }
}