package com.tablease.orderservice.domain.dish.valueobjects;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SellingPrice extends MonetaryValue {

    public SellingPrice(BigDecimal amount) {
        super(amount);
    }

    @Override
    protected void validate(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Selling price cannot be negative");
        }
    }
}
