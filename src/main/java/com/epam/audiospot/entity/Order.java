package com.epam.audiospot.entity;

import java.time.LocalDate;

public class Order implements Entity {
    public static final String ID_LABEL = "order_id";
    public static final String USER_ID_LABEL = "user_id";
    public static final String ORDER_DATE_LABEL = "date";
    public static final String PAID_LABEL = "paid";

    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private boolean paid;

    public Order(Long orderId, long userId, LocalDate orderDate,boolean paid) {
        this.id = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.paid = paid;
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

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
