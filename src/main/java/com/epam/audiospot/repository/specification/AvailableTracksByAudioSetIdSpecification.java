package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class AvailableTracksByAudioSetIdSpecification implements Specification {
    private static final String AUDIOTRACK_PREPARED_QUERY = "WHERE audiotrack_id "+
                                                            "NOT IN (SELECT track_at_audioset.audiotrack_id "+
                                                            "FROM track_at_audioset "+
                                                            "WHERE audioset_id =?);";
    private Long audioSetId;

    public AvailableTracksByAudioSetIdSpecification(Long audioSetId) {
        this.audioSetId = audioSetId;
    }

    @Override
    public String toSql() {
        return AUDIOTRACK_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(audioSetId);
    }
}
