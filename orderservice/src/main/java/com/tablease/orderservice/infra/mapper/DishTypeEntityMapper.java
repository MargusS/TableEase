package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import com.tablease.orderservice.infra.persistence.entity.dish.DishTypeEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface DishTypeEntityMapper {

    @Mapping(source = "destination", target = "preparationArea", qualifiedByName = "entityToDomainDestination")
    DishType toDomain(DishTypeEntity entity);

    @InheritInverseConfiguration
    @Mapping(source = "preparationArea", target = "destination", qualifiedByName = "domainToEntityDestination")
    @Mapping(target = "dishes", ignore = true)
    DishTypeEntity toEntity(DishType domain);

    // Enum ↔ Enum (value object <-> entity)
    @Named("entityToDomainDestination")
    default Destination entityToDomainDestination(DishTypeEntity.Destination dest) {
        return Destination.valueOf(dest.name());
    }

    @Named("domainToEntityDestination")
    default DishTypeEntity.Destination domainToEntityDestination(Destination dest) {
        return DishTypeEntity.Destination.valueOf(dest.name());
    }
}
