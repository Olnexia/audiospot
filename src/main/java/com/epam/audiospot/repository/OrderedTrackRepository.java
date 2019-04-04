package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.OrderedTrackBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import by.belstu.losik.audiospot.entity.OrderedTrack;

import java.util.LinkedHashMap;
import java.util.Map;

public class OrderedTrackRepository extends AbstractRepository <OrderedTrack> {
    private static final String TABLE_NAME = "ordered_track";

    public OrderedTrackRepository(ConnectionWrapper connection) {
        super(connection);
    }

    @Override
    public Builder <OrderedTrack> getBuilder() {
        return new OrderedTrackBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(OrderedTrack orderedTrack) {
        Map <String, Object> fields = new LinkedHashMap <>();
        fields.put(OrderedTrack.ID_LABEL, orderedTrack.getId());
        fields.put(OrderedTrack.ORDER_ID_LABEL, orderedTrack.getOrderId());
        fields.put(OrderedTrack.AUDIOTRACK_LABEL, orderedTrack.getTrackId());
        return fields;
    }
}
