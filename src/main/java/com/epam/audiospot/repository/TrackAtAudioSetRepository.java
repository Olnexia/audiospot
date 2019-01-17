package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.TrackAtAudioSetBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.TrackAtAudioSet;
import java.util.HashMap;
import java.util.Map;

public class TrackAtAudioSetRepository extends AbstractRepository<TrackAtAudioSet> {
    private static final String TABLE_NAME = "track_at_audioset";

    public TrackAtAudioSetRepository(ConnectionWrapper connection){
        super(connection);
    }

    @Override
    public Builder<TrackAtAudioSet> getBuilder() {
        return new TrackAtAudioSetBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(TrackAtAudioSet trackAtAudioSet) {
        Map<String,Object> fields = new HashMap<>();
        fields.put(TrackAtAudioSet.ID_LABEL,trackAtAudioSet.getId());
        fields.put(TrackAtAudioSet.AUDIOSET_ID_LABEL,trackAtAudioSet.getAudioSetId());
        fields.put(TrackAtAudioSet.AUDIOTRACK_LABEL,trackAtAudioSet.getTrackId());
        return fields;
    }
}
