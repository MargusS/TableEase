package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import java.util.List;
import java.util.UUID;

public interface DishBoundaries {
    DishResponse createDish(DishRequest request);

    DishResponse updateDish(UUID dishId, DishRequest request);

    List<DishResponse> listDishes();
}
