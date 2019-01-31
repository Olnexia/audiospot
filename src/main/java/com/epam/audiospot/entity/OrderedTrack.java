package com.epam.audiospot.entity;

public class OrderedTrack implements Entity {
    private static final long serialVersionUID = 4943852198988713799L;

    public static final String ID_LABEL = "ordered_track_id";
    public static final String ORDER_ID_LABEL = "audio_order_id";
    public static final String AUDIOTRACK_LABEL = "audiotrack_id";

    private Long id;
    private Long orderId;
    private Long trackId;

    public OrderedTrack(Long id, Long orderId, Long trackId) {
        this.id = id;
        this.orderId = orderId;
        this.trackId = trackId;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        OrderedTrack track = (OrderedTrack) object;
        return id.equals(track.getId())
                && orderId.equals(track.getOrderId())
                && trackId.equals(track.getTrackId());
    }

    @Override
    public int hashCode() {
        int result = 17;
        final int prime = 31;
        result = prime * result + id.hashCode();
        result = prime * result + orderId.hashCode();
        return result;
    }

    @Override
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
