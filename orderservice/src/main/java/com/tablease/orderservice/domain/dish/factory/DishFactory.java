package com.tablease.orderservice.domain.dish.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.Price;

import java.util.Set;

public interface DishFactory {

    Dish create(String name,
                String description,
                Set<Allergen> allergens,
                boolean isActive,
                Price price,
                Price cost,
                DishType type,
                String thumbnailUrl);

    String getSupportedFactory();
}
