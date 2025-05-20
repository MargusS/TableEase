package com.tablease.orderservice.app.dish;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Dish;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class DishPresenterImpl implements DishPresenter{

    private final DishMapperPort dishMapper;

    public DishPresenterImpl (DishMapperPort dishMapperPort){
        this.dishMapper = dishMapperPort;
    }

    @Override
    public DishResponse success(Dish dish) {
        return dishMapper.toResponse(dish);
    }

    @Override
    public DishResponse error(String error) {
        throw new ResponseStatusException(HttpStatus.CONFLICT, error);
    }
}
