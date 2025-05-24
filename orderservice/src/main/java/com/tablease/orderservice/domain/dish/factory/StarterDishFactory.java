package com.tablease.orderservice.domain.dish.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.DishId;
import com.tablease.orderservice.domain.dish.valueobjects.Price;

import java.util.Set;
import java.util.UUID;

public class StarterDishFactory implements DishFactory {
    @Override
    public Dish create(String name, String description, Set<Allergen> allergens, boolean isActive, Price price, Price cost, DishType type, String thumbnailUrl) {
        return new Dish(new DishId(UUID.randomUUID()), name, description, allergens, isActive, price, cost, type, thumbnailUrl);
    }

    @Override
    public String getSupportedFactory() {
        return "STARTER";
    }
}
