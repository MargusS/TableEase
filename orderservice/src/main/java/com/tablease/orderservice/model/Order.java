package com.tablease.orderservice.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.print.attribute.standard.Destination;

import com.tablease.orderservice.model.valueObject.OrderOrigin;
import com.tablease.orderservice.model.valueObject.OrderStatus;
import com.tablease.orderservice.model.valueObject.RoutingDestination;

import lombok.Getter;

@Getter
public class Order {
	private final UUID id;
	private OrderStatus status;
	private final OrderOrigin origin; // TABLE, ONLINE, QR, etc.
	private final RoutingDestination destination; // BAR, KITCHEN, BOTH
	private final String tableNumber;
	private final LocalDateTime createdAt;
	private final List<OrderItem> orderItems;

	public Order(UUID id, OrderOrigin origin, String tableNumber, List<OrderItem> items) {
		if (id == null || origin == null || items == null || items.isEmpty()) {
			throw new IllegalArgumentException("Invalid order");
		}

		if ((origin == OrderOrigin.QR) && (tableNumber == null || tableNumber.isBlank())) {
			throw new IllegalArgumentException("Table number is required for origin: " + origin);
		}

		this.id = id;
		this.origin = origin;
		this.tableNumber = tableNumber;
		this.orderItems = new ArrayList<>(items);
		this.destination = calculateDestination(items);
		this.status = OrderStatus.CREATED;
		this.createdAt = LocalDateTime.now();
	}

	private RoutingDestination calculateDestination(List<OrderItem> items) {
		boolean hasKitchen = false, hasBar = false;

		for (OrderItem item : orderItems) {
			switch (item.getDishType()) {
				case KITCHEN -> hasKitchen = true;
				case BAR -> hasBar = true;
			}
		}

		if (hasKitchen && hasBar)
			return Destination.BOTH;
		if (hasKitchen)
			return Destination.KITCHEN;
		return Destination.BAR;
	}

	public void markInProgress() {
		if (status != OrderStatus.CREATED) {
			throw new IllegalStateException("Cannot mark as in progress from " + status);
		}
		this.status = OrderStatus.IN_PROGRESS;
	}

	public void markServed() {
		if (status != OrderStatus.IN_PROGRESS) {
			throw new IllegalStateException("Cannot serve order not in progress");
		}
		this.status = OrderStatus.COMPLETED;
	}
}
