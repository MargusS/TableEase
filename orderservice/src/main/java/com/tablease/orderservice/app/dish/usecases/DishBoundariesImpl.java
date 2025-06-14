package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.presenter.DishPresenter;
import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.factory.DishFactory;
import com.tablease.orderservice.domain.dish.repository.AllergenRepository;
import com.tablease.orderservice.domain.dish.repository.DishRepository;
import com.tablease.orderservice.domain.dish.repository.DishTypeRepository;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DishBoundariesImpl implements DishBoundaries {

    private final DishPresenter dishPresenter;
    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;
    private final DishTypeRepository dishTypeRepository;
    private final Map<String, DishFactory> factories;

    public DishBoundariesImpl(DishPresenter dishPresenter, DishRepository dishRepository, AllergenRepository allergenRepository, DishTypeRepository dishTypeRepository, Map<String, DishFactory> factories) {
        this.dishPresenter = dishPresenter;
        this.dishRepository = dishRepository;
        this.allergenRepository = allergenRepository;
        this.dishTypeRepository = dishTypeRepository;
        this.factories = factories;
    }

    @Override
    public DishResponse createDish(DishRequest request) {

        DishType dishType = dishTypeRepository.findByUuid(request.dishTypeId()).orElse(null);
        if (dishType == null) {
            return dishPresenter.error("Invalid dish type");
        }

        List<Allergen> allergens = allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs());

        DishFactory factory = factories.get(dishType.name());
        Dish dish = factory.create(request.name(),
                request.description(),
                allergens,
                request.isActive(),
                new Price(request.price()),
                new Price(request.cost()),
                dishType,
                request.thumbnailUrl());

        Dish savedDish = dishRepository.save(dish);

        if (savedDish == null) {
            return dishPresenter.error("Failed to create dish");
        }
        return dishPresenter.success(savedDish);
    }

    @Override
    public DishResponse getDish(UUID dishId) {
        Dish dish = dishRepository.findByUuid(dishId).orElse(null);
        if (dish == null) {
            return dishPresenter.error("Dish not found");
        }
        return dishPresenter.success(dish);
    }

    @Override
    public DishResponse deleteDish(UUID dishId) {
        Dish deletedDish = this.dishRepository.deleteByUuid(dishId);
        if (deletedDish == null) {
            return dishPresenter.error("Dish not found");
        }
        return dishPresenter.success(deletedDish);
    }

    @Override
    public DishResponse updateDish(UUID dishId, DishRequest request) {
        Dish existing = dishRepository.findById(dishId).orElse(null);
        if (existing == null) {
            return dishPresenter.error("Dish not found");
        }

        DishType dishType = dishTypeRepository.findByUuid(request.dishTypeId()).orElse(null);
        if (dishType == null) {
            return dishPresenter.error("Invalid dish type");
        }

        List<Allergen> allergens = allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs());

        Dish updated = new Dish(
                dishId,
                existing.createdAt(),
                Instant.now(),
                request.name(),
                request.description(),
                allergens,
                request.isActive(),
                new Price(request.price()),
                new Price(request.cost()),
                request.thumbnailUrl(),
                dishType
        );

        Dish saved = dishRepository.update(updated);
        if (saved == null) {
            return dishPresenter.error("Failed to update dish");
        }
        return dishPresenter.success(saved);
    }

    @Override
    public List<DishResponse> listDishes() {
        List<Dish> dishes = dishRepository.findAll();
        return dishes.stream().map(dishPresenter::success).toList();
    }
}
