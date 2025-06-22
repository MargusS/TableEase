package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.MonetaryValue;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;
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
    @Mapping(source = "price", target = "price", qualifiedByName = "bigDecimalToSellingPrice")
    @Mapping(source = "cost", target = "cost", qualifiedByName = "bigDecimalToCostPrice")
    Dish toDomain(DishEntity entity);

    @InheritInverseConfiguration
    @Mapping(source = "type", target = "dishType")
    @Mapping(source = "isActive", target = "active")
    @Mapping(source = "price", target = "price", qualifiedByName = "priceToBigDecimal")
    @Mapping(source = "cost", target = "cost", qualifiedByName = "priceToBigDecimal")
    DishEntity toEntity(Dish domain);

    @Named("bigDecimalToSellingPrice")
    default SellingPrice toSellingPrice(BigDecimal amount) {
        return new SellingPrice(amount);
    }
    @Named("bigDecimalToCostPrice")
    default CostPrice toCostPrice(BigDecimal amount) {
        return new CostPrice(amount);
    }

    @Named("priceToBigDecimal")
    default BigDecimal toBigDecimal(MonetaryValue price) {
        return price.getAmount();
    }
}
