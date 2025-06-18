package com.tablease.orderservice.infra.mapper;

import com.tablease.orderservice.app.dish.dto.DishResponse;
import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DishDtoMapperImplTest {

    DishDtoMapperImpl mapper = Mappers.getMapper(DishDtoMapperImpl.class);

    @Test
    void mapsDomainToDto() {
        Dish domain = sampleDish();
        DishResponse dto = mapper.toResponse(domain);
        assertEquals(domain.uuid(), dto.uuid());
        assertEquals(domain.name(), dto.name());
        assertEquals(domain.description(), dto.description());
        assertEquals(domain.price().amount(), dto.price());
        assertEquals(domain.cost().amount(), dto.cost());
        assertEquals(domain.thumbnailUrl(), dto.thumbnail());
        assertEquals(domain.type().uuid(), dto.dishType().uuid());
        assertEquals(domain.type().name(), dto.dishType().name());
    }

    @Test
    void mapsList() {
        Dish domain = sampleDish();
        List<DishResponse> list = mapper.toResponseList(List.of(domain));
        assertEquals(1, list.size());
        assertEquals(domain.uuid(), list.getFirst().uuid());
    }

    private Dish sampleDish() {
        DishType type = new DishType(UUID.randomUUID(), "type", Destination.KITCHEN, Instant.now(), Instant.now());
        Allergen allergen = new Allergen(UUID.randomUUID(), "a", "u", "d");
        return new Dish(UUID.randomUUID(), "name", "desc", List.of(allergen), true,
                new Price(BigDecimal.ONE), new Price(BigDecimal.ONE), type, "thumb");
    }
}
