package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class AvailableTracksByUserIdSpecification implements Specification {
    private static final String AUDIOTRACK_PREPARED_QUERY = "WHERE audiotrack_id " +
            "NOT IN (SELECT ordered_track.audiotrack_id " +
            "FROM ordered_track " +
            "WHERE audio_order_id " +
            "IN (SELECT audio_order.audio_order_id " +
            "FROM audio_order " +
            "WHERE user_id=?)) ";
    private Long userId;

    public AvailableTracksByUserIdSpecification(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toSql() {
        return AUDIOTRACK_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Collections.singletonList(userId);
    }
}
