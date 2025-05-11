package com.tablease.orderservice.domain.dish.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class DishTypeId {
    private final UUID value;

    public DishTypeId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }

}
