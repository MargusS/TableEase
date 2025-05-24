package com.tablease.orderservice.infra.db.jpa.repositories.dish;

import com.tablease.orderservice.infra.db.jpa.entities.dish.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface DishJpaRepository extends JpaRepository<DishEntity, Long> {
    Optional<DishEntity> findByUuid(UUID uuid);
}