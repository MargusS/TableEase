package com.tablease.orderservice.app.dish;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;

public interface DishMapperPort {
    DishResponse toResponse(Dish dish);
}