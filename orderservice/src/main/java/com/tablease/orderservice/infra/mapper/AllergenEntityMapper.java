package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.infra.persistence.entity.dish.AllergenEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface AllergenEntityMapper {

    Allergen toDomain(AllergenEntity entity);

    @InheritInverseConfiguration
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "dishes", ignore = true)
    AllergenEntity toEntity(Allergen domain);
    
}
