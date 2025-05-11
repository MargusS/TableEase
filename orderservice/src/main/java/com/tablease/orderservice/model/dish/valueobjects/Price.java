package com.tablease.orderservice.model.dish.valueobjects;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public class Price {
    private final BigDecimal amount;

    public Price(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price must be non-negative.");
        }
        this.amount = amount.setScale(2, RoundingMode.HALF_UP);
    }
}
