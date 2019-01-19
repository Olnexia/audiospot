package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.OrderBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Order;
import java.util.HashMap;
import java.util.Map;

public class OrderRepository extends AbstractRepository<Order> {
    private static final String TABLE_NAME = "audio_order";

    public OrderRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<Order> getBuilder() {
        return new OrderBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(Order order) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(Order.ID_LABEL,order.getId());
        fields.put(Order.USER_ID_LABEL,order.getUserId());
        fields.put(Order.ORDER_DATE_LABEL,order.getOrderDate());
        fields.put(Order.PAID_LABEL,order.isPaid());
        return fields;
    }
}
