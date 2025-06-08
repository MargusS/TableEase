package com.tablease.orderservice.app.dish.ports;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;
import org.springframework.stereotype.Component;

@Component
public interface DishDtoMapperPort {
    DishResponse toResponse(Dish dish);
}
