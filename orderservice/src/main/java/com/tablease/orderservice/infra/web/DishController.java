package com.tablease.orderservice.infra.web;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.usecases.DishBoundaries;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishBoundaries dishBoundaries;

    public DishController(DishBoundaries dishBoundaries) {
        this.dishBoundaries = dishBoundaries;
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody DishRequest request) {
        DishResponse response = dishBoundaries.createDish(request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> deleteDish(@PathVariable("dishUuid") UUID dishUuid) {
        DishResponse response = dishBoundaries.deleteDish(dishUuid);
        return ResponseEntity.ok(response);
    }
}