package com.epam.audiospot.builder;

import com.epam.audiospot.entity.OrderedTrack;
import com.epam.audiospot.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderedTrackBuilder implements Builder<OrderedTrack> {
    @Override
    public OrderedTrack build(ResultSet resultSet) throws SQLException, ServiceException {
        Long id = resultSet.getLong(OrderedTrack.ID_LABEL);
        Long orderId = resultSet.getLong(OrderedTrack.ORDER_ID_LABEL);
        Long trackId = resultSet.getLong(OrderedTrack.AUDIOTRACK_LABEL);
       return new OrderedTrack(id,orderId,trackId);
    }
}
