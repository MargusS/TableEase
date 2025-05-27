package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.infra.persistence.entity.dish.AllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AllergenJPARepository extends JpaRepository<AllergenEntity, Long> {
}
