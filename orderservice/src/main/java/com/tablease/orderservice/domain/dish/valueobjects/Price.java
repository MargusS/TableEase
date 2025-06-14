package com.tablease.orderservice.domain.dish.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record Price(BigDecimal amount) {
    public Price(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price & Cost must be non-negative.");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
}
