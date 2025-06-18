package com.tablease.orderservice.app.dish.usecases;

import com.tablease.orderservice.app.dish.dto.DishRequest;
import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.app.dish.presenter.DishPresenter;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.repository.AllergenRepository;
import com.tablease.orderservice.domain.dish.repository.DishRepository;
import com.tablease.orderservice.domain.dish.repository.DishTypeRepository;
import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DishBoundariesImplTest {

    @Mock
    DishPresenter presenter;
    @Mock
    DishRepository dishRepository;
    @Mock
    AllergenRepository allergenRepository;
    @Mock
    DishTypeRepository dishTypeRepository;

    DishBoundariesImpl service;

    @BeforeEach
    void setUp() {
        service = new DishBoundariesImpl(presenter, dishRepository, allergenRepository, dishTypeRepository, new HashMap<>());
    }

    @Test
    void updateDishReturnsErrorWhenDishNotFound() {
        UUID id = UUID.randomUUID();
        DishRequest request = new DishRequest("n","d",List.of(),true,BigDecimal.ONE,BigDecimal.ONE,UUID.randomUUID(),"t");

        when(dishRepository.findById(id)).thenReturn(Optional.empty());
        DishResponse expected = mock(DishResponse.class);
        when(presenter.error("Dish not found")).thenReturn(expected);

        DishResponse result = service.updateDish(id, request);

        verify(presenter).error("Dish not found");
        assertSame(expected, result);
    }

    @Test
    void updateDishReturnsErrorWhenDishTypeInvalid() {
        UUID id = UUID.randomUUID();
        DishRequest request = new DishRequest("n","d",List.of(),true,BigDecimal.ONE,BigDecimal.ONE,UUID.randomUUID(),"t");
        Dish existing = sampleDish(id);

        when(dishRepository.findById(id)).thenReturn(Optional.of(existing));
        when(dishTypeRepository.findByUuid(request.dishTypeId())).thenReturn(Optional.empty());
        DishResponse expected = mock(DishResponse.class);
        when(presenter.error("Invalid dish type")).thenReturn(expected);

        DishResponse result = service.updateDish(id, request);

        verify(presenter).error("Invalid dish type");
        assertSame(expected, result);
    }

    @Test
    void updateDishSuccess() {
        UUID id = UUID.randomUUID();
        UUID typeId = UUID.randomUUID();
        DishRequest request = new DishRequest("new","desc",List.of(),true,BigDecimal.TEN,BigDecimal.ONE,typeId,"thumb2");
        Dish existing = sampleDish(id);
        DishType type = new DishType(typeId, "type", Destination.KITCHEN, Instant.now(), Instant.now());
        Dish updated = sampleDish(id);

        when(dishRepository.findById(id)).thenReturn(Optional.of(existing));
        when(dishTypeRepository.findByUuid(typeId)).thenReturn(Optional.of(type));
        when(allergenRepository.findAllByAllergenByUuidIn(request.allergenUUIDs())).thenReturn(List.of());
        when(dishRepository.update(any(Dish.class))).thenReturn(updated);
        DishResponse success = mock(DishResponse.class);
        when(presenter.success(updated)).thenReturn(success);

        DishResponse result = service.updateDish(id, request);

        verify(presenter).success(updated);
        assertSame(success, result);
    }

    @Test
    void listDishesMapsResults() {
        Dish dish = sampleDish(UUID.randomUUID());
        DishResponse mapped = mock(DishResponse.class);
        when(dishRepository.findAll()).thenReturn(List.of(dish));
        when(presenter.success(dish)).thenReturn(mapped);

        List<DishResponse> result = service.listDishes();

        assertEquals(1, result.size());
        assertSame(mapped, result.getFirst());
    }

    private Dish sampleDish(UUID id) {
        DishType type = new DishType(UUID.randomUUID(), "type", Destination.KITCHEN, Instant.now(), Instant.now());
        return new Dish(id, "name", "desc", List.of(), true, new Price(BigDecimal.ONE), new Price(BigDecimal.ONE), type, "thumb");
    }
}
