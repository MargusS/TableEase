package com.tablease.orderservice.domain.dish.repository;

import com.tablease.orderservice.domain.dish.Dish;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishRepository {
    Dish save(Dish dish);

    Optional<Dish> findById(UUID dishId);

    List<Dish> findAll();

    Dish update(Dish dish);

    void delete(Dish dish);
}
