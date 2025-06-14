package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.Dish;
import com.tablease.orderservice.domain.dish.repository.DishRepository;
import com.tablease.orderservice.infra.mapper.DishEntityMapper;
import com.tablease.orderservice.infra.persistence.entity.dish.DishEntity;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

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
        DishEntity savedEntity = dishJpaRepository.save(dishEntityMapper.toEntity(dish));
        return dishEntityMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Dish> findById(UUID dishId) {
        return dishJpaRepository.findByUuid(dishId)
                .map(dishEntityMapper::toDomain);
    }

    @Override
    public List<Dish> findAll() {
        List<DishEntity> entities = dishJpaRepository.findAll();
        return entities.stream().map(dishEntityMapper::toDomain).toList();
    }

    @Override
    public Dish update(Dish dish) {
        DishEntity entity = dishEntityMapper.toEntity(dish);
        DishEntity saved = dishJpaRepository.save(entity);
        return dishEntityMapper.toDomain(saved);
    }

    @Override
    @Transactional
    public Dish deleteByUuid(UUID dishUuid) {
        DishEntity dishToDelete = this.dishJpaRepository.findById(dishUuid).orElse(null);
        if (dishToDelete == null) {
            return null;
        }
        this.dishJpaRepository.delete(dishToDelete);
        return this.dishEntityMapper.toDomain(dishToDelete);
    }
}
