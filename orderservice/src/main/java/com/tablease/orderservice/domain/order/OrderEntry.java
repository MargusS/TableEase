package com.tablease.orderservice.domain.order;

import com.tablease.orderservice.domain.dish.valueobjects.Destination;
import com.tablease.orderservice.domain.dish.valueobjects.Price;
import com.tablease.orderservice.domain.order.valueobjects.OrderEntryId;
import com.tablease.orderservice.domain.order.valueobjects.Quantity;
import lombok.Getter;

import java.time.Instant;
import java.util.UUID;

@Getter
public class OrderEntry {

	private final OrderEntryId id;
	private final UUID dishId;
	private final String dishName;
	private final Price dishPriceAtOrder;
	private final Quantity quantity;
	private final Destination dishDestination;
	private final Instant createdAt;
	private Instant updatedAt;

	public OrderEntry(OrderEntryId id, UUID dishId, String dishName, Price price, Quantity quantity, Destination dishDestination,Instant createdAt) {
		this.id = id;
		this.dishId = dishId;
		this.dishName = dishName;
		this.dishPriceAtOrder = price;
		this.quantity = quantity;
		this.dishDestination = dishDestination;
		this.createdAt = createdAt != null ? createdAt : Instant.now();
		this.updatedAt = this.createdAt;
	}
}
