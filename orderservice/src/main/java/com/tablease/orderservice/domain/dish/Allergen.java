package com.tablease.orderservice.domain.dish;

import java.util.UUID;

public record Allergen(UUID uuid, String name, String symbolUrl, String description) {
    public Allergen {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Allergen name required");
    }
}
