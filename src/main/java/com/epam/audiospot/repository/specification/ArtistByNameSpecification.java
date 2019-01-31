package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class ArtistByNameSpecification implements Specification {
    private static final String ARTIST_PREPARED_QUERY = "WHERE name = ?";
    private String name;

    public ArtistByNameSpecification(String name) {
        this.name = name;
    }

    @Override
    public String toSql() {
        return ARTIST_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Collections.singletonList(name);
    }
}
