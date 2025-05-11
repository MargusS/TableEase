package com.tablease.orderservice.domain.order.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class OrderId {
    private final UUID value;

    public OrderId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }
}
