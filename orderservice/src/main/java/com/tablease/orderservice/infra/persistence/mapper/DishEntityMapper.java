package com.tablease.orderservice.infra.persistence.mapper;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.infra.persistence.entity.dish.DishEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DishEntityMapper {
    private final ModelMapper modelMapper;

    public DishEntityMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DishEntity toEntity(Dish dish) {
        return modelMapper.map(dish, DishEntity.class);
    }

    public Dish toDomain(DishEntity dishEntity) {
        return modelMapper.map(dishEntity, Dish.class);
    }
}
