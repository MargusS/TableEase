package com.tablease.orderservice.infra.db.jpa.repositories.dish;

import com.tablease.orderservice.infra.db.jpa.entities.dish.AllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AllergenRepository extends JpaRepository<AllergenEntity, Long> {
}
