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
import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class DishApplicationServiceImpl implements DishApplicationService {

    private final DishPresenter dishPresenter;
    private final DishRepository dishRepository;
    private final AllergenRepository allergenRepository;
    private final DishTypeRepository dishTypeRepository;
    private final Map<String, DishFactory> factories;

    public DishApplicationServiceImpl(DishPresenter dishPresenter, DishRepository dishRepository, AllergenRepository allergenRepository, DishTypeRepository dishTypeRepository, Map<String, DishFactory> factories) {
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
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Invalid dish type");
        }

        List<Allergen> allergens = allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs());

        DishFactory factory = factories.get(dishType.name());
        Dish dish = factory.create(request.name(),
                request.description(),
                allergens,
                request.isActive(),
                new SellingPrice(request.price()),
                new CostPrice(request.cost()),
                dishType,
                request.thumbnailUrl());

        Dish savedDish = dishRepository.save(dish);

        if (savedDish == null) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create dish");
        }
        return dishPresenter.success(savedDish);
    }

    @Override
    public DishResponse getDish(UUID dishId) {
        Dish dish = dishRepository.findByUuid(dishId).orElse(null);
        if (dish == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
        }
        return dishPresenter.success(dish);
    }

    @Override
    public List<DishResponse> getAllDishes() {
        List<Dish> dishes = dishRepository.findAll();
        if (dishes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No dishes found");
        }
        return dishes.stream().map(dishPresenter::success).toList();
    }

    @Override
    public DishResponse updateDish(UUID dishId, DishRequest request) {
        Dish existing = dishRepository.findByUuid(dishId).orElse(null);
        if (existing == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
        }

        DishType dishType = dishTypeRepository.findByUuid(request.dishTypeId()).orElse(null);
        if (dishType == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid dish type");
        }

        List<Allergen> allergens = allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs());

        boolean priceChanged = !existing.price().getAmount().equals(request.price());
        boolean costChanged = !existing.cost().getAmount().equals(request.cost());

        if (priceChanged || costChanged) {
            Dish deactivated = new Dish(
                    existing.uuid(),
                    existing.createdAt(),
                    Instant.now(),
                    existing.name(),
                    existing.description(),
                    allergens,
                    false,
                    existing.price(),
                    existing.cost(),
                    existing.thumbnailUrl(),
                    dishType
            );
            dishRepository.save(deactivated);

            Dish newDish = new Dish(
                    UUID.randomUUID(),
                    Instant.now(),
                    null,
                    request.name(),
                    request.description(),
                    allergens,
                    true,
                    new SellingPrice(request.price()),
                    new CostPrice(request.cost()),
                    request.thumbnailUrl(),
                    dishType
            );

            Dish saved = dishRepository.save(newDish);
            if (saved == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to create new dish");
            }
            return dishPresenter.success(saved);
        } else {
            Dish updated = new Dish(
                    existing.uuid(),
                    existing.createdAt(),
                    Instant.now(),
                    request.name(),
                    request.description(),
                    allergens,
                    request.isActive(),
                    existing.price(),
                    existing.cost(),
                    request.thumbnailUrl(),
                    dishType
            );

            Dish saved = dishRepository.save(updated);
            if (saved == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to update dish");
            }
            return dishPresenter.success(saved);
        }
    }

    @Override
    public DishResponse deleteDish(UUID dishId) {
        Dish deletedDish = this.dishRepository.deleteByUuid(dishId);
        if (deletedDish == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Dish not found");
        }
        return dishPresenter.success(deletedDish);
    }

}
