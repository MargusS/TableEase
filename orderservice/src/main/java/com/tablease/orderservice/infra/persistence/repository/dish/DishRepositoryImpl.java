package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.repository.DishRepository;
import com.tablease.orderservice.infra.mapper.DishEntityMapper;
import com.tablease.orderservice.infra.persistence.entity.dish.DishEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DishRepositoryImpl implements DishRepository {

    private final DishJpaRepository dishJpaRepository;
    private final DishEntityMapper dishEntityMapper;

    public DishRepositoryImpl(DishJpaRepository dishJpaRepository, DishEntityMapper dishEntityMapper) {
        this.dishJpaRepository = dishJpaRepository;
        this.dishEntityMapper = dishEntityMapper;
    }


    @Override
    public Dish save(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        DishEntity saved = dishJpaRepository.save(entity);
        return dishEntityMapper.toDomain(saved);
    }

    @Override
    public Optional<Dish> findById(UUID dishId) {
        return this.dishJpaRepository
                .findByUuid(dishId)
                .map(this.dishEntityMapper::toDomain);
    }

    @Override
    public List<Dish> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Dish update(Dish dish) {
        return null;
    }

    @Override
    public void delete(Dish dish) {
        this.dishJpaRepository.deleteByUuid(dish.uuid());
    }
}
