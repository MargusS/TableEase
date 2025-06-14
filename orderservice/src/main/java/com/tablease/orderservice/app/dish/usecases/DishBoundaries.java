package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import java.util.List;
import java.util.UUID;


public interface DishBoundaries {
    DishResponse createDish(DishRequest request);
    DishResponse getDish(UUID dishId);
    List<DishResponse> getAllDishes();
    DishResponse updateDish(UUID dishId, DishRequest request);
    DishResponse deleteDish(UUID dishId);
    DishResponse updateDish(UUID dishId, DishRequest request);
    List<DishResponse> listDishes();
}
