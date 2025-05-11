package com.tablease.orderservice.domain.order.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record OrderEntryId(UUID value) {
    public OrderEntryId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }
}
