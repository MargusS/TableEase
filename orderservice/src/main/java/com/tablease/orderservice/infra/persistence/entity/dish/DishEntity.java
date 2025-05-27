package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dishes", schema = "dish_management")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "uuid")
    private UUID uuid;

    private String name;
    private String description;
    private BigDecimal price;
    private BigDecimal cost;
    private boolean isActive;
    private String thumbnail;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_type_id")
    private DishTypeEntity dishType;

    @ManyToMany
    @JoinTable(
            name = "dish_allergens",
            schema = "dish_management",
            joinColumns = @JoinColumn(name = "dish_id"),
            inverseJoinColumns = @JoinColumn(name = "allergen_id")
    )
    private List<AllergenEntity> allergens;

    private Instant createdAt;
    private Instant updatedAt;
}
