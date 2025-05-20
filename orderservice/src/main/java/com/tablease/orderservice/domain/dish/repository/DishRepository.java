package com.tablease.orderservice.domain.dish.repository;

import com.tablease.orderservice.domain.dish.Dish;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface DishRepository {
    Dish save(Dish dish);

    Optional<Dish> findById(UUID dishId);

    Set<Dish> findAll();

    Dish update(Dish dish);

    void delete(Dish dish);
}
