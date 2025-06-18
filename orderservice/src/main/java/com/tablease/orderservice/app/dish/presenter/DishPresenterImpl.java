package com.tablease.orderservice.app.dish.presenter;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.ports.DishDtoMapperPort;
import com.tablease.orderservice.domain.dish.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class DishPresenterImpl implements DishPresenter {

    private final DishDtoMapperPort dishMapper;

    public DishPresenterImpl(DishDtoMapperPort dishMapperPort) {
        this.dishMapper = dishMapperPort;
    }

    @Override
    public DishResponse success(Dish dish) {
        return dishMapper.toResponse(dish);
    }
}
