package com.tablease.orderservice.model.order;

import com.tablease.orderservice.model.order.valueobjects.OrderId;
import com.tablease.orderservice.model.order.valueobjects.Origin;
import com.tablease.orderservice.model.order.valueobjects.Status;
import lombok.Getter;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@Getter
public class Order {
	private final OrderId id;
	private final Instant createdAt;
	private Instant updatedAt;
	private final Origin origin; // TABLE, ONLINE, QR, etc.
	private final String tableNumber;
	private Status status;
	private final List<OrderEntry> orderEntries;

	public Order(OrderId id, Instant createdAt, Origin origin, String tableNumber, List<OrderEntry> entries) {
		this.id = id;
		this.createdAt = createdAt != null ? createdAt : Instant.now();
		this.updatedAt = this.createdAt;
		this.origin = origin;
		this.tableNumber = origin == Origin.QR ? Objects.requireNonNull(tableNumber) : null;
		this.orderEntries = List.copyOf(entries);
	}

	public void updateStatus(Status newStatus) {
		this.status = newStatus;
		this.updatedAt = Instant.now();
	}
}
