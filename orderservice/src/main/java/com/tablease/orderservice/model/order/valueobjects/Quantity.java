package com.tablease.orderservice.model.order.valueobjects;

import lombok.Getter;

@Getter
public class Quantity {
	private final int value;

    public Quantity(int value) {
        if (value <= 0)
            throw new IllegalArgumentException("Quantity must be positive");
        this.value = value;
    }

}
