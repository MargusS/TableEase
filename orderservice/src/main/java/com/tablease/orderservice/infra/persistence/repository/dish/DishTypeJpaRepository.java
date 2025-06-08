package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.infra.persistence.entity.dish.DishTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DishTypeJpaRepository extends JpaRepository<DishTypeEntity, Long> {
    Optional<DishTypeEntity> findByUuid(UUID uuid);
}
