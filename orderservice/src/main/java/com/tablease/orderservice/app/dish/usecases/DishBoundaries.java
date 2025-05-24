package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;

public interface DishBoundaries {
    DishResponse createDish(DishRequest request);
}
