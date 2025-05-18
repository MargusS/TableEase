package com.tablease.orderservice.domain.dish.factory;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.Allergen;
import com.tablease.orderservice.domain.dish.valueobjects.DishId;
import com.tablease.orderservice.domain.dish.valueobjects.Price;

import java.time.Instant;
import java.util.Set;

public class SecondDishFactory implements DishFactory{
    @Override
    public Dish create(DishId id, String name, String description, Set<Allergen> allergens, boolean isActive, Price price, Price cost, DishType type, Instant createdAt, String thumbnailUrl) {
        return new Dish(id, name,description,allergens, isActive, price, cost, type, createdAt, thumbnailUrl);
    }


    @Override
    public String getSupportedFactory(){
        return "SECOND COURSE";
    }
}
