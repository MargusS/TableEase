package com.tablease.orderservice.domain.dish;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class Allergen {

    private final UUID uuid;
    private String name;
    private String symbolUrl;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    public Allergen(UUID uuid, String name, String symbolUrl, String description) {
        if (uuid == null) throw new IllegalArgumentException("Allergen UUID cannot be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Allergen name cannot be null or empty");

        this.uuid = uuid;
        this.name = name.trim();
        this.symbolUrl = (symbolUrl != null && !symbolUrl.isBlank()) ? symbolUrl.trim() : null;
        this.description = (description != null && !description.isBlank()) ? description.trim() : null;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }
}
