package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.ports.DishDtoMapperPort;
import com.tablease.orderservice.domain.dish.Dish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(
        componentModel = "spring",
        uses = {
                AllergenDtoMapperImpl.class,
                DishTypeDtoMapperImpl.class
        }
)
public interface DishDtoMapperImpl extends DishDtoMapperPort {

    @Override
    @Mapping(source = "thumbnailUrl", target = "thumbnail")
    @Mapping(source = "price.amount", target = "price")
    @Mapping(source = "cost.amount", target = "cost")
    DishResponse toResponse(Dish dish);
}
