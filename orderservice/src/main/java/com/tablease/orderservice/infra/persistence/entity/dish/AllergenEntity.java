package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "allergen", schema = "dish_management")
public class AllergenEntity {

    @Id
    private UUID uuid;

    private String name;

    @Column(name = "symbol_url")
    private String symbolUrl;

    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.Instant createdAt;

    @Column(name = "updated_at")
    private java.time.Instant updatedAt;

    @ManyToMany(cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },mappedBy = "allergens")
    private Set<DishEntity> dishes = new HashSet<>();

}
