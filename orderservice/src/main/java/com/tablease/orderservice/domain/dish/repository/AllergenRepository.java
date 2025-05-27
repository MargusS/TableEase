package com.tablease.orderservice.domain.dish.repository;

import com.tablease.orderservice.domain.dish.Allergen;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AllergenRepository {
    Allergen save(Allergen allergen);

    Optional<Allergen> findById(UUID allergenId);

    List<Allergen> findAll();

    List<Allergen> findAllByAllergenByUuidIn(List<UUID> allergenIds);

    Allergen update(Allergen allergen);

    void delete(Allergen allergen);

}
