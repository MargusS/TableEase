package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.infra.persistence.entity.dish.AllergenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AllergenJPARepository extends JpaRepository<AllergenEntity, Long> {
    List<AllergenEntity> findAllByUuidIn(List<UUID> allergenIds);
}
