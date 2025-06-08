package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.app.dish.dto.AllergenResponse;
import com.tablease.orderservice.domain.dish.Allergen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AllergenDtoMapperImpl {
    AllergenResponse toDto(Allergen allergen);
}
