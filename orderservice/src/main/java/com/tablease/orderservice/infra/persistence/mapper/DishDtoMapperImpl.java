package com.tablease.orderservice.infra.persistence.mapper;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.ports.DishDtoMapperPort;
import com.tablease.orderservice.domain.dish.Dish;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DishDtoMapperImpl implements DishDtoMapperPort {
    private final ModelMapper modelMapper;

    public DishDtoMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public DishResponse toResponse(Dish dish) {
        return modelMapper.map(dish, DishResponse.class);
    }
}
