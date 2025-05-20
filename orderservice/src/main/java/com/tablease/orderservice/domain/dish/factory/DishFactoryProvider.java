package com.tablease.orderservice.domain.dish.factory;

import com.tablease.orderservice.domain.dish.DishType;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DishFactoryProvider {
    private final Map<String, DishFactory> factories;
    public DishFactoryProvider(List<DishFactory> factoryList) {
        this.factories = factoryList.stream()
                .collect(Collectors.toMap(DishFactory::getSupportedFactory, Function.identity()));
    }

    public DishFactory getFactory(DishType type) {
        String key = type.getName().toUpperCase();
        DishFactory factory = factories.get(key);

        return factory;
    }
}
