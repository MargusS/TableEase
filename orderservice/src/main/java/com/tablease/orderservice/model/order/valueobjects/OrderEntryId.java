package com.tablease.orderservice.model.order.valueobjects;

import java.util.Objects;
import java.util.UUID;

public class OrderEntryId {
    private final UUID value;

    public OrderEntryId(UUID value) {
        this.value = Objects.requireNonNull(value);
    }

    public UUID getValue() {
        return value;
    }
}
