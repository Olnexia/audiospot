package com.epam.audiospot.entity;


import java.time.LocalDate;

public class Order implements Entity {
    private final Long id;
    private final Long userId;
    private final LocalDate orderDate;
    private boolean paid;

    public Order(long orderId, LocalDate orderDate, long userId) {
        this.id = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
