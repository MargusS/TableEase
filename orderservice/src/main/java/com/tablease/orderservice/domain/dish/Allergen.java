package com.tablease.orderservice.domain.dish;

import com.tablease.orderservice.domain.dish.valueobjects.AllergenId;
import lombok.Getter;

@Getter
public class Allergen {
    private final AllergenId id;
    private final String name;
    private final String symbolUrl; // path or full URL to image/icon
    private final String description;

    public Allergen(AllergenId id, String name, String symbolUrl, String description) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Allergen name required");
        if (symbolUrl == null || symbolUrl.isBlank()) throw new IllegalArgumentException("Allergen symbol required");
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("Allergen description required");

        this.id = id;
        this.name = name;
        this.symbolUrl = symbolUrl;
        this.description = description;
    }
}
