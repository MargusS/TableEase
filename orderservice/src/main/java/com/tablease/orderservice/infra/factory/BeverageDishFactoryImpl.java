package com.tablease.orderservice.infra.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.factory.BeverageDishFactory;
import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component("BEVERAGE")
public class BeverageDishFactoryImpl implements BeverageDishFactory {
    @Override
    public Dish create(String name, String description, SellingPrice price, CostPrice cost, DishType dishType, List<Allergen> allergens, String thumbnail) {
        return new Dish(UUID.randomUUID(), name, description, price, cost, dishType, allergens, thumbnail);
    }
}
