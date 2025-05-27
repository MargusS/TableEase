package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.infra.persistence.entity.dish.DishTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishTypeJpaRepository extends JpaRepository<DishTypeEntity, Long> {
}
