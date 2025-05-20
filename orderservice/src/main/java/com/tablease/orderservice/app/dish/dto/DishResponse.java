package com.tablease.orderservice.app.dish.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishResponse (
        String name,
        String description,
        BigDecimal price,
        UUID dishTypeId,
        List<UUID> allergenIds,
        BigDecimal cost,
        String thumbnail) {}
