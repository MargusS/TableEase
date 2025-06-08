package com.tablease.orderservice.app.dish.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record DishResponse(
        UUID uuid,
        String name,
        String description,
        List<AllergenResponse> allergens,
        BigDecimal price,
        BigDecimal cost,
        DishTypeResponse dishType,
        String thumbnail,
        Instant createdAt
) {
}
