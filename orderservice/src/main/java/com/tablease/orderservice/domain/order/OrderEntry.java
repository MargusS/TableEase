package com.tablease.orderservice.domain.order;

import com.tablease.orderservice.domain.order.valueobjects.OrderEntryStatus;
import lombok.Getter;

import java.util.UUID;

@Getter
public class OrderEntry {

    private final UUID uuid;
    private final UUID dishUuid;
    private final int quantity;
    private final String suggestions;
    private OrderEntryStatus status;

    public OrderEntry(UUID uuid, UUID dishUuid, int quantity, String suggestions) {
        if (dishUuid == null) throw new IllegalArgumentException("Dish UUID cannot be null.");
        if (quantity <= 0)
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        if (suggestions != null && suggestions.length() > 500)
            throw new IllegalArgumentException("Suggestions is too long.");
        this.uuid = uuid;
        this.dishUuid = dishUuid;
        this.quantity = quantity;
        this.suggestions = suggestions;
        this.status = OrderEntryStatus.PENDING;
    }

    public void accept() {
        ensureStatus(OrderEntryStatus.PENDING);
        this.status = OrderEntryStatus.ACCEPTED;
    }

    public void start() {
        ensureStatus(OrderEntryStatus.ACCEPTED);
        this.status = OrderEntryStatus.IN_PROGRESS;
    }

    public void markReady() {
        ensureStatus(OrderEntryStatus.IN_PROGRESS);
        this.status = OrderEntryStatus.READY;
    }

    public void markServed() {
        ensureStatus(OrderEntryStatus.READY);
        this.status = OrderEntryStatus.DELIVERED;
    }

    public void cancel() {
        if (this.status == OrderEntryStatus.IN_PROGRESS || this.status == OrderEntryStatus.DELIVERED) {
            throw new IllegalStateException("Cannot cancel an entry that is in progress or already served.");
        }
        this.status = OrderEntryStatus.CANCELLED;
    }

    private void ensureStatus(OrderEntryStatus expected) {
        if (this.status != expected) {
            throw new IllegalStateException("Expected entry to be in state " + expected + " but was " + this.status);
        }
    }
}
