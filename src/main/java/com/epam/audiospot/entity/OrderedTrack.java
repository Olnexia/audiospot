package com.epam.audiospot.entity;

public class OrderedTrack implements Entity {

    public static final String ID_LABEL = "ordered_track_id";
    public static final String ORDER_ID_LABEL = "order_id";
    public static final String AUDIOTRACK_LABEL = "audiotrack_id";

    private Long id;
    private Long orderId;
    private Long trackId;

    public OrderedTrack(Long id, Long orderId, Long trackId) {
        this.id = id;
        this.orderId = orderId;
        this.trackId = trackId;
    }

    public Long getId() {
        return id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getTrackId() {
        return trackId;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
