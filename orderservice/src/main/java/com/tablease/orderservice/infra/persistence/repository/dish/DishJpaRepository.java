package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.infra.persistence.entity.dish.DishEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DishJpaRepository extends JpaRepository<DishEntity, UUID> {
    List<DishEntity> findAllByDishTypeIn(List<UUID> uuids);
}