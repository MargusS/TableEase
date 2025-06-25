package com.tablease.orderservice.domain.order;

import com.tablease.orderservice.domain.order.valueobjects.OrderChannel;
import com.tablease.orderservice.domain.order.valueobjects.OrderStatus;
import com.tablease.orderservice.domain.order.valueobjects.OrderType;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Order {

    private final UUID uuid;
    private final OrderChannel channel;
    private final OrderType type;
    private final Integer tableNumber;
    private final List<OrderEntry> entries = new ArrayList<>();
    private final Instant createdAt;
    private OrderStatus status;
    private Instant updatedAt;

    public Order(UUID uuid, OrderChannel channel, OrderType type, List<OrderEntry> entries, Integer tableNumber, Instant createdAt) {
        if (channel == null) throw new IllegalArgumentException("Order channel cannot be null");
        if (type == null) throw new IllegalArgumentException("Order type cannot be null");
        if (entries == null || entries.isEmpty())
            throw new IllegalArgumentException("Order entries cannot be null or empty");
        validateOrderType(channel, type, tableNumber);

        this.uuid = uuid;
        this.channel = channel;
        this.type = type;
        this.tableNumber = tableNumber;
        this.status = OrderStatus.CREATED;
        this.createdAt = createdAt;
        this.updatedAt = createdAt;
    }

    private void validateOrderType(OrderChannel channel, OrderType type, Integer tableNumber) {
        if (type == OrderType.DINE_IN && tableNumber == null) {
            throw new IllegalArgumentException("Table number is required for dine-in orders.");
        }
    }

    public void receive() {
        ensureStatus(OrderStatus.CREATED);
        this.status = OrderStatus.RECEIVED;
        this.updatedAt = Instant.now();
    }

    public void markInProgress() {
        ensureStatus(OrderStatus.RECEIVED);
        this.status = OrderStatus.IN_PROGRESS;
        this.updatedAt = Instant.now();
    }

    public void markReady() {
        ensureStatus(OrderStatus.IN_PROGRESS);
        this.status = OrderStatus.READY;
        this.updatedAt = Instant.now();
    }

    public void markDelivered() {
        ensureStatus(OrderStatus.READY);
        this.status = OrderStatus.DELIVERED;
        this.updatedAt = Instant.now();
    }

    public void cancel() {
        if (this.status == OrderStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel a delivered order.");
        }
        this.status = OrderStatus.CANCELLED;
        this.updatedAt = Instant.now();
    }

    private void ensureStatus(OrderStatus expected) {
        if (this.status != expected) {
            throw new IllegalStateException("Expected order to be in state " + expected + " but was " + this.status);
        }
    }

}
