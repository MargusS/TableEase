package com.tablease.orderservice.infra.persistence.repository.dish;

import com.tablease.orderservice.domain.dish.Allergen;
import com.tablease.orderservice.domain.dish.repository.AllergenRepository;
import com.tablease.orderservice.infra.mapper.AllergenEntityMapper;
import com.tablease.orderservice.infra.persistence.entity.dish.AllergenEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class AllergenRepositoryImpl implements AllergenRepository {

    private final AllergenJPARepository allergenJPARepository;
    private final AllergenEntityMapper allergenEntityMapper;

    public AllergenRepositoryImpl(AllergenJPARepository allergenJPARepository, AllergenEntityMapper allergenEntityMapper) {
        this.allergenJPARepository = allergenJPARepository;
        this.allergenEntityMapper = allergenEntityMapper;
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
        List<AllergenEntity> allergenEntities = this.allergenJPARepository.findAllByUuidIn(allergenIds);
        return allergenEntities.stream().map(this.allergenEntityMapper::toDomain).toList();
    }

    @Override
    public Allergen update(Allergen allergen) {
        return null;
    }

    @Override
    public void delete(Allergen allergen) {

    }
}
