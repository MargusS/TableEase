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
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        if (allergens.size() != request.allergenUUIDs().size()) {
            throw new IllegalArgumentException("One or more allergens not found");
        }

        DishFactory factory = factories.get(dishType.getName());
        Dish dish = factory.create(request.name(),
                request.description(),
                new SellingPrice(request.price()),
                new CostPrice(request.cost()),
                dishType,
                allergens,
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
    @Transactional
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
        if (allergens.size() != request.allergenUUIDs().size()) {
            throw new IllegalArgumentException("One or more allergens not found");
        }

        if (existing.hasSamePriceAndCost(new SellingPrice(request.price()), new CostPrice(request.cost()))) {
            existing.update(request.name(), request.description(), dishType, allergens, request.thumbnailUrl(), request.isActive());
            Dish updated = dishRepository.save(existing);
            if (updated == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update dish");
            }
            return dishPresenter.success(updated);
        } else {
            existing.deactivate();
            dishRepository.save(existing);
            DishFactory factory = factories.get(dishType.getName());
            Dish updated = factory.create(request.name(),
                    request.description(),
                    new SellingPrice(request.price()),
                    new CostPrice(request.cost()),
                    dishType,
                    allergens,
                    request.thumbnailUrl());

            Dish saved = dishRepository.save(updated);
            if (saved == null) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Failed to update price and cost");
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
