package com.tablease.orderservice.infra.mappers;

import com.tablease.orderservice.app.dish.DishMapperPort;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;
import org.modelmapper.ModelMapper;

public class DishMapper implements DishMapperPort {
    private final ModelMapper modelMapper;

    public DishMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DishResponse toResponse(Dish dish) {
        return modelMapper.map(dish, DishResponse.class);
    }
}
