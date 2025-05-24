package com.tablease.orderservice.app.dish.presenter;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;

public interface DishPresenter {
    DishResponse success(Dish dish);
    DishResponse error(String error);
}
