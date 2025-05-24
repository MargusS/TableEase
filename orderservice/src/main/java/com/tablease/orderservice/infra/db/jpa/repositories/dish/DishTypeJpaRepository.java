package com.tablease.orderservice.infra.db.jpa.repositories.dish;

import com.tablease.orderservice.infra.db.jpa.entities.dish.DishTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishTypeJpaRepository extends JpaRepository<DishTypeEntity, Long> {
}
