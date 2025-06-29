package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.mapper.DishDtoMapperPort;
import com.tablease.orderservice.domain.dish.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {
                AllergenDtoMapperImpl.class,
                DishTypeDtoMapperImpl.class
        }
)
public interface DishDtoMapperImpl extends DishDtoMapperPort {

    @Override
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "cost.amount", target = "cost")
    DishResponse toResponse(Dish dish);

    @Override
    List<DishResponse> toResponseList(List<Dish> dishes);
}
