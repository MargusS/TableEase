package com.tablease.orderservice.infra.db.jpa.entities.dish;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "allergens", schema = "dish_management")
public class AllergenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "uuid")
    private UUID uuid;

    private String name;

    @Column(name = "symbol_url")
    private String symbolUrl;

    private String description;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.Instant createdAt;

    @Column(name = "updated_at")
    private java.time.Instant updatedAt;

    @ManyToMany(mappedBy = "allergens")
    private List<DishEntity> dishes;
    
}
