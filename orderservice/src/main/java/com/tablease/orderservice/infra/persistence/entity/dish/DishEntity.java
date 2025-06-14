package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "dish", schema = "dish_management")
public class DishEntity {
    @Id
    private UUID uuid;
    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal cost;
    private boolean isActive;
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_type_uuid")
    private DishTypeEntity dishType;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "dish_allergen",
            schema = "dish_management",
            joinColumns = @JoinColumn(name = "dish_uuid"),
            inverseJoinColumns = @JoinColumn(name = "allergen_uuid")
    )
    private Set<AllergenEntity> allergens = new HashSet<>();

    private Instant createdAt;
    private Instant updatedAt;
}
