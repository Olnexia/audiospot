package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Order;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class OrderBuilder implements Builder <Order> {

    @Override
    public Order build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(Order.ID_LABEL);
        Long userId = resultSet.getLong(Order.USER_ID_LABEL);
        Date sqlDate = resultSet.getDate(Order.ORDER_DATE_LABEL);
        LocalDate date = sqlDate.toLocalDate();
        boolean paid = resultSet.getBoolean(Order.PAID_LABEL);
        return new Order(id, userId, date, paid);
    }
}
