package com.tablease.orderservice.domain.dish.repository;

import com.tablease.orderservice.domain.dish.DishType;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DishTypeRepository {
    DishType save(DishType dishType);

    Optional<DishType> findById(UUID dishTypeId);

    List<DishType> findAll();

    DishType update(DishType dishType);

    void delete(DishType dishType);
}
