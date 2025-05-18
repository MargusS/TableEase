package com.tablease.orderservice.app.dish;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.repository.DishRepository;

public class DishInteractor implements DishBoundaries {

    private final DishFactoryProvider dishFactoryProvider;
    private final DishPresenter dishPresenter;
    private final DishRepository dishRepository;

    public DishInteractor(DishFactoryProvider dishFactoryProvider, DishPresenter dishPresenter, DishRepository dishRepository) {
        this.dishFactoryProvider = dishFactoryProvider;
        this.dishPresenter = dishPresenter;
        this.dishRepository = dishRepository;
    }


    @Override
    public DishResponse createDish(DishRequest request) {
        return null;
    }
}
