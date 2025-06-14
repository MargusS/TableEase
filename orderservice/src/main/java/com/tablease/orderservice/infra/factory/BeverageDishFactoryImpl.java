package com.tablease.orderservice.infra.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.factory.BeverageDishFactory;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("BEVERAGE")
public class BeverageDishFactoryImpl implements BeverageDishFactory {
    @Override
    public Dish create(String name, String description, List<Allergen> allergens, boolean isActive, Price price, Price cost, DishType type, String thumbnailUrl) {
        return new Dish(UUID.randomUUID(), name, description, allergens, isActive, price, cost, type, thumbnailUrl);
    }
}
