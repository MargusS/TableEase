package com.tablease.orderservice.infra.web.dish;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.usecases.DishBoundaries;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
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

    @GetMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> getDish(@PathVariable("dishUuid") UUID dishUuid) {
        DishResponse response = dishBoundaries.getDish(dishUuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        List<DishResponse> response = dishBoundaries.getAllDishes();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable("dishUuid") UUID dishId, @RequestBody DishRequest request) {
        DishResponse response = dishBoundaries.updateDish(dishId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> deleteDish(@PathVariable("dishUuid") UUID dishUuid) {
        DishResponse response = dishBoundaries.deleteDish(dishUuid);
        return ResponseEntity.ok(response);
    }

}
