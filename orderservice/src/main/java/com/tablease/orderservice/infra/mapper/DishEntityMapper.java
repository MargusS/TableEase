package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import com.tablease.orderservice.infra.persistence.entity.dish.DishEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = {
        DishTypeEntityMapper.class,
        AllergenEntityMapper.class
})
public interface DishEntityMapper {
    
    @Mapping(source = "thumbnail", target = "thumbnailUrl")
    @Mapping(source = "active", target = "isActive")
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToPrice")
    @Mapping(source = "cost", target = "cost", qualifiedByName = "bigDecimalToPrice")
    Dish toDomain(DishEntity entity);

    @InheritInverseConfiguration
    @Mapping(source = "type", target = "dishType")
    @Mapping(source = "isActive", target = "active")
    @Mapping(source = "price", target = "price", qualifiedByName = "priceToBigDecimal")
    @Mapping(source = "cost", target = "cost", qualifiedByName = "priceToBigDecimal")
    DishEntity toEntity(Dish domain);

    @Named("bigDecimalToPrice")
    default Price toPrice(BigDecimal amount) {
        return new Price(amount);
    }

    @Named("priceToBigDecimal")
    default BigDecimal toBigDecimal(Price price) {
        return price.amount();
    }
}
