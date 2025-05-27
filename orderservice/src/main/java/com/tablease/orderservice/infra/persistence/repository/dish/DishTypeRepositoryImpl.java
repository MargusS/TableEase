package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.DishType;
import com.tablease.orderservice.domain.dish.repository.DishTypeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class DishTypeRepositoryImpl implements DishTypeRepository {

    private final DishTypeJpaRepository dishTypeJpaRepository;

    public DishTypeRepositoryImpl(DishTypeJpaRepository dishTypeJpaRepository) {
        this.dishTypeJpaRepository = dishTypeJpaRepository;
    }

    @Override
    public DishType save(DishType dishType) {
        return null;
    }

    @Override
    public Optional<DishType> findById(UUID dishTypeId) {
        return Optional.empty();
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
