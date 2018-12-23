package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class PublicPlaylistsSpecification implements Specification {
    private static final String PLAYLISTS_PREPARED_QUERY = "WHERE name <> NULL";

    @Override
    public String toSql() {
        return PLAYLISTS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Collections.emptyList();
    }
}
