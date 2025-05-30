package com.tablease.orderservice.infra.factory;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.factory.SecondCourseDishFactory;
import com.tablease.orderservice.domain.dish.valueobjects.DishId;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
@Qualifier("SECOND_COURSE")
public class SecondCourseCourseDishFactoryImpl implements SecondCourseDishFactory {
    @Override
    public Dish create(String name, String description, List<Allergen> allergens, boolean isActive, Price price, Price cost, DishType type, String thumbnailUrl) {
        return new Dish(new DishId(UUID.randomUUID()), name, description, allergens, isActive, price, cost, type, thumbnailUrl);
    }
}
