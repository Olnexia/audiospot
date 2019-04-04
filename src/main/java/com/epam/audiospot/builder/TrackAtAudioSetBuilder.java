package com.epam.audiospot.builder;

import by.belstu.losik.audiospot.entity.TrackAtAudioSet;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrackAtAudioSetBuilder implements Builder <TrackAtAudioSet> {

    @Override
    public TrackAtAudioSet build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(TrackAtAudioSet.ID_LABEL);
        Long playlistId = resultSet.getLong(TrackAtAudioSet.AUDIOSET_ID_LABEL);
        Long trackId = resultSet.getLong(TrackAtAudioSet.AUDIOTRACK_LABEL);
        return new TrackAtAudioSet(id, playlistId, trackId);
    }
}
