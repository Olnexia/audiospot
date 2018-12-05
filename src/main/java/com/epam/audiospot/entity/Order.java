package com.epam.audiospot.entity;

import java.time.LocalDate;

public class Order implements Entity {
    private final long id;
    private final LocalDate orderDate;
    private final long userId;

    public Order(long orderId, LocalDate orderDate, long userId) {
        this.id = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public long getUserId() {
        return userId;
    }
}
