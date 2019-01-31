package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class AudioTracksByUserIdSpecification implements Specification {
    private static final String AUDIOTRACK_PREPARED_QUERY = "INNER JOIN ordered_track " +
            "ON audiotrack.audiotrack_id=ordered_track.audiotrack_id " +
            "WHERE ordered_track.audio_order_id " +
            "IN(SELECT audio_order.audio_order_id " +
            "FROM audio_order " +
            "WHERE paid = TRUE AND user_id = ?);";
    private Long userId;

    public AudioTracksByUserIdSpecification(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toSql() {
        return AUDIOTRACK_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Arrays.asList(userId);
    }
}
