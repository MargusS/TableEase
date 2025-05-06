package com.tablease.orderservice.model;

import java.time.Instant;
import java.time.LocalDateTime;

import com.tablease.orderservice.model.valueObject.Quantity;

import lombok.Getter;

@Getter
public class OrderItem {

	private final DishSnapshot dishSnapshot;
	private final Quantity quantity;
	private final LocalDateTime createdAt;

	public OrderItem(DishSnapshot dishSnapshot, Quantity quantity) {
		if (dishSnapshot == null || quantity == null) {
			throw new IllegalArgumentException("Dish and quantity must not be null");
		}

		if (quantity.getValue() <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}

		this.dishSnapshot = dishSnapshot;
		this.quantity = quantity;
		this.createdAt = Instant.now();
	}

	public double totalPrice() {
		return quantity.getValue() * dishSnapshot.getPrice();
	}
}
