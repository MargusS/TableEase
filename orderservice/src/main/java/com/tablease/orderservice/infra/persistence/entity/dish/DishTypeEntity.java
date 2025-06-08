package com.tablease.orderservice.infra.persistence.entity.dish;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "dish_type", schema = "dish_management")
public class DishTypeEntity {

    @Id
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
