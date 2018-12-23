package com.epam.audiospot.repository.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaylistByNameSpecification implements Specification {
    private static final String PLAYLISTS_PREPARED_QUERY = "WHERE name=?";
    private String name;

    public PlaylistByNameSpecification(String name){
        this.name = name;
    }

    @Override
    public String toSql() {
        return PLAYLISTS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return new ArrayList<Object>(Arrays.asList(name));
    }
}
