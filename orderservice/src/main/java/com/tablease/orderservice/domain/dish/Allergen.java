package com.tablease.orderservice.model.dish;

import lombok.Getter;

@Getter
public class Allergen {
    private final String name;
    private final String symbolUrl; // path or full URL to image/icon
    private final String description;

    public Allergen(String name, String symbolUrl, String description) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Allergen name required");
        if (symbolUrl == null || symbolUrl.isBlank()) throw new IllegalArgumentException("Allergen symbol required");
        if (description == null || description.isBlank()) throw new IllegalArgumentException("Allergen description required");

        this.name = name;
        this.symbolUrl = symbolUrl;
        this.description = description;
    }
}
