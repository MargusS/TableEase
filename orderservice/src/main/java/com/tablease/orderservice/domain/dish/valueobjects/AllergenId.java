package com.tablease.orderservice.domain.dish.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record AllergenId(UUID value) {
    public AllergenId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }
}
