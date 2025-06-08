package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
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

    @ManyToMany
    @JoinTable(
            name = "dish_allergens",
            schema = "dish_management",
            joinColumns = @JoinColumn(name = "dish_uuid"),
            inverseJoinColumns = @JoinColumn(name = "allergen_uuid")
    )
    private List<AllergenEntity> allergens;

    private Instant createdAt;
    private Instant updatedAt;
}
