package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.app.dish.dto.DishTypeResponse;
import com.tablease.orderservice.domain.dish.DishType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DishTypeDtoMapperImpl {
    
    @Mapping(source = "destination", target = "destination")
    DishTypeResponse toDto(DishType dishType);
}