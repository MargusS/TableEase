package com.tablease.orderservice.app.dish.dto;

import java.util.UUID;

public record DishTypeResponse(
        UUID uuid,
        String name,
        String destination
) {
}