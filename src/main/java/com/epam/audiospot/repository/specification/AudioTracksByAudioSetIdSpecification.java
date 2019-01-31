package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class AudioTracksByAudioSetIdSpecification implements Specification {
    private static final String TRACK_PREPARED_QUERY = "INNER JOIN track_at_audioset " +
            "ON audiotrack.audiotrack_id=track_at_audioset.audiotrack_id " +
            "WHERE track_at_audioset.audioset_id=?;";
    private Long audioSetId;

    public AudioTracksByAudioSetIdSpecification(Long audioSetId) {
        this.audioSetId = audioSetId;
    }

    @Override
    public String toSql() {
        return TRACK_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Arrays.asList(audioSetId);
    }
}
