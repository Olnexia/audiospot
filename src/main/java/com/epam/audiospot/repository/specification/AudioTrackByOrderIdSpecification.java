package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class AudioTrackByOrderIdSpecification implements Specification {
    private static final String AUDIOTRACK_PREPARED_QUERY = "INNER JOIN ordered_tracks " +
                                                            "ON audiotrack.audiotrack_id=ordered_tracks.audiotrack_id " +
                                                            "WHERE ordered_tracks.audio_order_id=?";
    private Long orderId;

    public AudioTrackByOrderIdSpecification(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toSql() {
        return AUDIOTRACK_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(orderId);
    }
}
