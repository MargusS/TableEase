package com.tablease.orderservice.domain.dish.repository;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.valueobjects.DishId;

import java.util.Optional;

public interface DishRepository {
    Dish save(Dish dish);
    Optional<Dish> findById(DishId id);
}
