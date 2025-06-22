package com.tablease.orderservice.domain.dish.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;

import java.util.List;

public interface DishFactory {

    Dish create(String name,
                String description,
                List<Allergen> allergens,
                boolean isActive,
                SellingPrice price,
                CostPrice cost,
                DishType type,
                String thumbnailUrl);
}
