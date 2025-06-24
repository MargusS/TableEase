package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Name cannot be blank")
    @Size(max = 150, message = "Name must be at most 150 characters")
    private String name;

    @Size(max = 1000, message = "Description is too long")
    private String description;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Cost is required")
    @DecimalMin(value = "0.0", inclusive = true, message = "Cost must be zero or positive")
    private BigDecimal cost;

    @NotNull(message = "Active status is required")
    private boolean active;
    private String thumbnail;

    @NotNull(message = "Dish type is required")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dish_type_uuid")
    private DishTypeEntity dishType;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
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
