package com.tablease.orderservice.domain.order.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record OrderId(UUID value) {
    public OrderId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }
}
