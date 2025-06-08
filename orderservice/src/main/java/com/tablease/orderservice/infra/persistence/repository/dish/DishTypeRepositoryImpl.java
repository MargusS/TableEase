package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.repository.DishTypeRepository;
import com.tablease.orderservice.infra.mapper.DishTypeEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DishTypeRepositoryImpl implements DishTypeRepository {

    private final DishTypeJpaRepository dishTypeJpaRepository;
    private final DishTypeEntityMapper dishTypeEntityMapper;

    public DishTypeRepositoryImpl(DishTypeJpaRepository dishTypeJpaRepository, DishTypeEntityMapper dishTypeEntityMapper) {
        this.dishTypeJpaRepository = dishTypeJpaRepository;
        this.dishTypeEntityMapper = dishTypeEntityMapper;
    }

    @Override
    public DishType save(DishType dishType) {
        return null;
    }

    @Override
    public Optional<DishType> findByUuid(UUID dishTypeId) {
        return this.dishTypeJpaRepository.findByUuid(dishTypeId).map(this.dishTypeEntityMapper::toDomain);
    }

    @Override
    public List<DishType> findAll() {
        return new ArrayList<>();
    }

    @Override
    public DishType update(DishType dishType) {
        return null;
    }

    @Override
    public void delete(DishType dishType) {

    }
}
