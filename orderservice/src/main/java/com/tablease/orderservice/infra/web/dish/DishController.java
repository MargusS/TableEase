package com.tablease.orderservice.infra.web.dish;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.usecases.DishApplicationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/dishes")
public class DishController {

    private final DishApplicationService dishApplicationService;

    public DishController(DishApplicationService dishApplicationService) {
        this.dishApplicationService = dishApplicationService;
    }

    @PostMapping
    public ResponseEntity<DishResponse> createDish(@RequestBody DishRequest request) {
        DishResponse response = dishApplicationService.createDish(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> getDish(@PathVariable("dishUuid") UUID dishUuid) {
        DishResponse response = dishApplicationService.getDish(dishUuid);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<DishResponse>> getAllDishes() {
        List<DishResponse> response = dishApplicationService.getAllDishes();
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> updateDish(@PathVariable("dishUuid") UUID dishId, @RequestBody DishRequest request) {
        DishResponse response = dishApplicationService.updateDish(dishId, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{dishUuid}")
    public ResponseEntity<DishResponse> deleteDish(@PathVariable("dishUuid") UUID dishUuid) {
        DishResponse response = dishApplicationService.deleteDish(dishUuid);
        return ResponseEntity.ok(response);
    }

}
