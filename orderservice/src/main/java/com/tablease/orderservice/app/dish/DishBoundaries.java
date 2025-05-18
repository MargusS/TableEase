package com.tablease.orderservice.app.dish;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;

public interface DishBoundaries {
    DishResponse createDish(DishRequest request);
}
