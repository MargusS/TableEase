package com.tablease.orderservice.app.dish.presenter;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;
import org.springframework.http.HttpStatus;

public interface DishPresenter {
    DishResponse success(Dish dish);
}
