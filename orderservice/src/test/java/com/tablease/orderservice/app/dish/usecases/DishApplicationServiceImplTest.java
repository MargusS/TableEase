package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.presenter.DishPresenter;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.factory.DishFactory;
import com.tablease.orderservice.domain.dish.repository.AllergenRepository;
import com.tablease.orderservice.domain.dish.repository.DishRepository;
import com.tablease.orderservice.domain.dish.repository.DishTypeRepository;
import com.tablease.orderservice.domain.dish.valueobjects.CostPrice;
import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import com.tablease.orderservice.domain.dish.valueobjects.SellingPrice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishApplicationServiceImplTest {

    @Mock
    DishPresenter presenter;
    @Mock
    DishRepository dishRepository;
    @Mock
    AllergenRepository allergenRepository;
    @Mock
    DishTypeRepository dishTypeRepository;

    DishApplicationServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new DishApplicationServiceImpl(presenter, dishRepository, allergenRepository, dishTypeRepository, new HashMap<>());
    }

    @Test
    void createDishSuccess() {
        UUID dishTypeId = UUID.randomUUID();
        DishRequest request = new DishRequest("name", "desc", List.of(), true, BigDecimal.ONE, BigDecimal.ONE, dishTypeId, "thumb");

        DishType dishType = new DishType(dishTypeId, "type", Destination.KITCHEN, Instant.now(), Instant.now());
        Dish createdDish = sampleDish(UUID.randomUUID());
        DishResponse expected = mock(DishResponse.class);
        DishFactory factory = mock(DishFactory.class);

        when(dishTypeRepository.findByUuid(dishTypeId)).thenReturn(Optional.of(dishType));
        when(allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs())).thenReturn(List.of());
        when(factory.create(any(), any(), any(), anyBoolean(), any(), any(), any(), any())).thenReturn(createdDish);
        when(dishRepository.save(createdDish)).thenReturn(createdDish);
        when(presenter.success(createdDish)).thenReturn(expected);

        service = new DishApplicationServiceImpl(presenter, dishRepository, allergenRepository, dishTypeRepository, Map.of("type", factory));
        DishResponse result = service.createDish(request);

        verify(presenter).success(createdDish);
        assertSame(expected, result);
    }

    @Test
    void createDishThrowsWhenDishTypeNotFound() {
        DishRequest request = new DishRequest("name", "desc", List.of(), true, BigDecimal.ONE, BigDecimal.ONE, UUID.randomUUID(), "thumb");

        when(dishTypeRepository.findByUuid(request.dishTypeId())).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.createDish(request);
        });

        assertEquals("404 NOT_FOUND \"Invalid dish type\"", ex.getMessage());
    }

    @Test
    void createDishThrowsWhenSaveFails() {
        UUID dishTypeId = UUID.randomUUID();
        DishRequest request = new DishRequest("name", "desc", List.of(), true, BigDecimal.ONE, BigDecimal.ONE, dishTypeId, "thumb");
        DishType dishType = new DishType(dishTypeId, "type", Destination.KITCHEN, Instant.now(), Instant.now());
        Dish dish = sampleDish(UUID.randomUUID());
        DishFactory factory = mock(DishFactory.class);

        when(dishTypeRepository.findByUuid(dishTypeId)).thenReturn(Optional.of(dishType));
        when(allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs())).thenReturn(List.of());
        when(factory.create(any(), any(), any(), anyBoolean(), any(), any(), any(), any())).thenReturn(dish);
        when(dishRepository.save(dish)).thenReturn(null);

        service = new DishApplicationServiceImpl(presenter, dishRepository, allergenRepository, dishTypeRepository, Map.of("type", factory));

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.createDish(request);
        });

        assertEquals("500 INTERNAL_SERVER_ERROR \"Failed to create dish\"", ex.getMessage());
    }

    @Test
    void getDishSuccess() {
        UUID id = UUID.randomUUID();
        Dish dish = sampleDish(id);
        DishResponse expected = mock(DishResponse.class);

        when(dishRepository.findByUuid(id)).thenReturn(Optional.of(dish));
        when(presenter.success(dish)).thenReturn(expected);

        DishResponse result = service.getDish(id);

        verify(presenter).success(dish);
        assertSame(expected, result);
    }

    @Test
    void getDishThrowsWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(dishRepository.findByUuid(id)).thenReturn(Optional.empty());

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.getDish(id);
        });

        assertEquals("404 NOT_FOUND \"Dish not found\"", ex.getMessage());
    }

    @Test
    void updateDishThrowsWhenDishNotFound() {
        UUID id = UUID.randomUUID();
        DishRequest request = new DishRequest("n", "d", List.of(), true, BigDecimal.ONE, BigDecimal.ONE, UUID.randomUUID(), "t");

        when(dishRepository.findByUuid(id)).thenReturn(Optional.empty());

        ResponseStatusException thrown = assertThrows(ResponseStatusException.class, () -> {
            service.updateDish(id, request);
        });

        assertEquals("404 NOT_FOUND \"Dish not found\"", thrown.getMessage());
    }

    @Test
    void updateDishReturnsErrorWhenDishTypeInvalid() {
        UUID id = UUID.randomUUID();
        DishRequest request = new DishRequest("n","d",List.of(),true,BigDecimal.ONE,BigDecimal.ONE,UUID.randomUUID(),"t");
        Dish existing = sampleDish(id);

        when(dishRepository.findByUuid(id)).thenReturn(Optional.of(existing));
        when(dishTypeRepository.findByUuid(request.dishTypeId())).thenReturn(Optional.empty());
        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.updateDish(id, request);
        });
        assertEquals("400 BAD_REQUEST \"Invalid dish type\"", ex.getMessage());
    }

    @Test
    void updateDishWithSamePriceAndCostUpdatesDirectly() {
        UUID id = UUID.randomUUID();
        UUID typeId = UUID.randomUUID();
        DishType dishType = new DishType(typeId, "type", Destination.KITCHEN, Instant.now(), Instant.now());

        DishRequest request = new DishRequest(
                "Updated Name",
                "Updated Description",
                List.of(),
                false,
                BigDecimal.ONE,
                BigDecimal.ONE,
                typeId,
                "updated-thumbnail"
        );

        Dish existing = new Dish(
                id,
                Instant.now().minusSeconds(3600),
                null,
                "Original Name",
                "Original Description",
                List.of(),
                true,
                new SellingPrice(BigDecimal.ONE),
                new CostPrice(BigDecimal.ONE),
                "original-thumbnail",
                dishType
        );

        Dish updated = new Dish(
                id,
                existing.createdAt(),
                Instant.now(),
                request.name(),
                request.description(),
                List.of(),
                request.isActive(),
                existing.price(),
                existing.cost(),
                request.thumbnailUrl(),
                dishType
        );

        DishResponse expected = mock(DishResponse.class);

        when(dishRepository.findByUuid(id)).thenReturn(Optional.of(existing));
        when(dishTypeRepository.findByUuid(typeId)).thenReturn(Optional.of(dishType));
        when(allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs())).thenReturn(List.of());
        when(dishRepository.save(any(Dish.class))).thenReturn(updated);
        when(presenter.success(updated)).thenReturn(expected);

        DishResponse result = service.updateDish(id, request);

        verify(presenter).success(updated);
        assertSame(expected, result);
    }

    @Test
    void updateDishWithChangedPriceCreatesNewDish() {
        UUID id = UUID.randomUUID();
        UUID typeId = UUID.randomUUID();
        DishType dishType = new DishType(typeId, "type", Destination.KITCHEN, Instant.now(), Instant.now());

        DishRequest request = new DishRequest(
                "Updated Name",
                "Updated Description",
                List.of(),
                true,
                BigDecimal.TEN, // <- precio diferente
                BigDecimal.ONE,
                typeId,
                "new-thumbnail"
        );

        Dish existing = new Dish(
                id,
                Instant.now().minusSeconds(3600),
                null,
                "Original Name",
                "Original Description",
                List.of(),
                true,
                new SellingPrice(BigDecimal.ONE),
                new CostPrice(BigDecimal.ONE),
                "original-thumbnail",
                dishType
        );

        Dish deactivated = new Dish(
                id,
                existing.createdAt(),
                Instant.now(),
                existing.name(),
                existing.description(),
                List.of(),
                false,
                existing.price(),
                existing.cost(),
                existing.thumbnailUrl(),
                dishType
        );

        Dish newDish = new Dish(
                UUID.randomUUID(),
                Instant.now(),
                null,
                request.name(),
                request.description(),
                List.of(),
                true,
                new SellingPrice(request.price()),
                new CostPrice(request.cost()),
                request.thumbnailUrl(),
                dishType
        );

        DishResponse expected = mock(DishResponse.class);

        when(dishRepository.findByUuid(id)).thenReturn(Optional.of(existing));
        when(dishTypeRepository.findByUuid(typeId)).thenReturn(Optional.of(dishType));
        when(allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs())).thenReturn(List.of());
        when(dishRepository.save(any(Dish.class))).thenReturn(newDish); // para ambos saves
        when(presenter.success(newDish)).thenReturn(expected);

        DishResponse result = service.updateDish(id, request);

        verify(presenter).success(newDish);
        assertSame(expected, result);
    }

    @Test
    void listDishesMapsResults() {
        Dish dish = sampleDish(UUID.randomUUID());
        DishResponse mapped = mock(DishResponse.class);
        when(dishRepository.findAll()).thenReturn(List.of(dish));
        when(presenter.success(dish)).thenReturn(mapped);

        List<DishResponse> result = service.getAllDishes();

        assertEquals(1, result.size());
        assertSame(mapped, result.getFirst());
    }

    @Test
    void deleteDishSuccess() {
        UUID id = UUID.randomUUID();
        Dish dish = sampleDish(id);
        DishResponse response = mock(DishResponse.class);

        when(dishRepository.deleteByUuid(id)).thenReturn(dish);
        when(presenter.success(dish)).thenReturn(response);

        DishResponse result = service.deleteDish(id);

        verify(presenter).success(dish);
        assertSame(response, result);
    }

    @Test
    void deleteDishThrowsWhenNotFound() {
        UUID id = UUID.randomUUID();
        when(dishRepository.deleteByUuid(id)).thenReturn(null);

        ResponseStatusException ex = assertThrows(ResponseStatusException.class, () -> {
            service.deleteDish(id);
        });

        assertEquals("404 NOT_FOUND \"Dish not found\"", ex.getMessage());
    }


    private Dish sampleDish(UUID id) {
        DishType type = new DishType(UUID.randomUUID(), "type", Destination.KITCHEN, Instant.now(), Instant.now());
        return new Dish(id, "name", "desc", List.of(), true, new SellingPrice(BigDecimal.ONE), new CostPrice(BigDecimal.ONE), type, "thumb");
    }
}
