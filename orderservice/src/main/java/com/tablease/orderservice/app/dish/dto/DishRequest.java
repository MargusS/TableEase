package com.tablease.orderservice.app.dish.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record DishRequest(String name,
                          String description,
                          List<UUID> allergenIds,
                          boolean isActive,
                          BigDecimal price,
                          BigDecimal cost,
                          UUID dishTypeId,
                          String thumbnailUrl) {
}
