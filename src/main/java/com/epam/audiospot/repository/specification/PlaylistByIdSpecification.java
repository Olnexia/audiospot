package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class PlaylistByIdSpecification implements Specification{
    private static final String PLAYLISTS_PREPARED_QUERY = "WHERE playlist_id=?";
    private Long id;

    public PlaylistByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return PLAYLISTS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(id);
    }
}
