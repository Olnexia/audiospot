package com.epam.audiospot.entity;

import java.time.LocalDate;

public class Order implements Entity {
    private static final long serialVersionUID = -2575420337998768374L;

    public static final String ID_LABEL = "audio_order_id";
    public static final String USER_ID_LABEL = "user_id";
    public static final String ORDER_DATE_LABEL = "date";
    public static final String PAID_LABEL = "paid";

    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private boolean paid;

    public Order(Long orderId, Long userId, LocalDate orderDate, boolean paid) {
        this.id = orderId;
        this.orderDate = orderDate;
        this.userId = userId;
        this.paid = paid;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Order order = (Order) object;
        return id.equals(order.getId())
                && orderDate.equals(order.getOrderDate())
                && userId.equals(order.getUserId())
                && paid == order.isPaid();
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + orderDate.hashCode();
        result = prime * result + userId.hashCode();
        result = prime * result + Boolean.hashCode(paid);
        return result;
    }

    @Override
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
