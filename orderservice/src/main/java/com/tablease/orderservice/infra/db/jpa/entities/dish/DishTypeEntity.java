package com.tablease.orderservice.infra.db.jpa.entities.dish;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "dish_types")
public class DishTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, columnDefinition = "uuid")
    private UUID uuid;

    private String name;

    @Enumerated(EnumType.STRING)
    private Destination destination;

    @OneToMany(mappedBy = "dishType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DishEntity> dishes;

    @Column(name = "created_at", nullable = false, updatable = false)
    private java.time.Instant createdAt;

    @Column(name = "updated_at")
    private java.time.Instant updatedAt;

    public enum Destination {
        KITCHEN,
        BAR
    }

}
