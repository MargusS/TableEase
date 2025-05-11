package com.tablease.orderservice.domain.dish.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record DishTypeId(UUID value) {
    public DishTypeId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

}
