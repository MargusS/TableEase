package com.tablease.orderservice.interfaces.presenter.dish;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.ports.DishDtoMapperPort;
import com.tablease.orderservice.app.dish.presenter.DishPresenter;
import com.tablease.orderservice.domain.dish.Dish;
import org.springframework.stereotype.Component;

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
