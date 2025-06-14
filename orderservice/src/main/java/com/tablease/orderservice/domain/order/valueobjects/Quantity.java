package com.tablease.orderservice.domain.order.valueobjects;

public record Quantity(int value) {
    public Quantity {
        if (value <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
    }

}
