package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Allergen name cannot be blank")
    @Size(max = 100, message = "Allergen name must be at most 100 characters")
    private String name;

    @Column(name = "symbol_url")
    private String symbolUrl;

    @Size(max = 2000, message = "Description is too long")
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
