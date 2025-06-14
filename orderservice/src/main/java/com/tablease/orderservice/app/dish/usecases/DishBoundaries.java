package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;

import java.util.UUID;

public interface DishBoundaries {
    DishResponse createDish(DishRequest request);
    DishResponse deleteDish(UUID dishId);
}
