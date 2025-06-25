package com.tablease.orderservice.domain.order;

import com.tablease.orderservice.domain.order.valueobjects.OrderChannel;
import com.tablease.orderservice.domain.order.valueobjects.OrderStatus;
import com.tablease.orderservice.domain.order.valueobjects.OrderType;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Getter
public class OrderSession {

    private final UUID uuid;
    private final Integer tableNumber;
    private final OrderChannel channel;
    private final OrderType type;
    private final LocalDateTime startedAt;
    private final List<Order> orders = new ArrayList<>();
    private LocalDateTime endedAt;

    public OrderSession(UUID uuid, Integer tableNumber, OrderChannel channel, OrderType type, LocalDateTime startedAt) {
        validate(type, tableNumber);
        this.uuid = uuid;
        this.tableNumber = tableNumber;
        this.channel = channel;
        this.type = type;
        this.startedAt = startedAt;
    }

    private void validate(OrderType type, Integer tableNumber) {
        if (type == OrderType.DINE_IN && tableNumber == null) {
            throw new IllegalArgumentException("Table number is required for dine-in sessions.");
        }
    }

    public void addOrder(Order order) {
        if (this.endedAt != null) {
            throw new IllegalStateException("Cannot add orders to a closed session.");
        }
        this.orders.add(order);
    }

    public void closeSession() {
        if (!areAllOrdersClosed()) {
            throw new IllegalStateException("Cannot close session while there are open orders.");
        }
        this.endedAt = LocalDateTime.now();
    }

    private boolean areAllOrdersClosed() {
        return orders.stream().allMatch(order ->
                order.getStatus() == OrderStatus.CANCELLED ||
                        order.getStatus() == OrderStatus.DELIVERED
        );
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

}
