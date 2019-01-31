package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class AudioTracksByAlbumIdSpecification implements Specification {
    private static final String TRACK_PREPARED_QUERY = "WHERE album_id = ?";
    private Long albumId;

    public AudioTracksByAlbumIdSpecification(Long albumId) {
        this.albumId = albumId;
    }

    @Override
    public String toSql() {
        return TRACK_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Collections.singletonList(albumId);
    }
}
