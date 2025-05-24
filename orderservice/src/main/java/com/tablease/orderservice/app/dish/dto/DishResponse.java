package com.tablease.orderservice.app.dish.dto;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.Price;

import java.time.Instant;
import java.util.List;

public record DishResponse(String name,
                           String description,
                           List<Allergen> allergens,
                           Price price,
                           Price cost,
                           DishType dishType,
                           String thumbnail,
                           Instant createdAt) {
}
