package com.tablease.orderservice.app.dish.ports;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;

public interface DishMapperPort {
    DishResponse toResponse(Dish dish);
}