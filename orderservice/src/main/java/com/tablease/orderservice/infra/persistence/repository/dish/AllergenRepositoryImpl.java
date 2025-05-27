package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.repository.AllergenRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AllergenRepositoryImpl implements AllergenRepository {

    private final AllergenJPARepository allergenJPARepository;

    public AllergenRepositoryImpl(AllergenJPARepository allergenJPARepository) {
        this.allergenJPARepository = allergenJPARepository;
    }

    @Override
    public Allergen save(Allergen allergen) {
        return null;
    }

    @Override
    public Optional<Allergen> findById(UUID allergenId) {
        return Optional.empty();
    }

    @Override
    public List<Allergen> findAll() {
        return List.of();
    }

    @Override
    public List<Allergen> findAllByAllergenByUuidIn(List<UUID> allergenIds) {
        return List.of();
    }

    @Override
    public Allergen update(Allergen allergen) {
        return null;
    }

    @Override
    public void delete(Allergen allergen) {

    }
}
