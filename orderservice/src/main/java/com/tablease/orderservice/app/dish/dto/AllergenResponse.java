package com.tablease.orderservice.app.dish.dto;

import java.util.UUID;

public record AllergenResponse(
        UUID uuid,
        String name,
        String symbolUrl,
        String description
) {
}
