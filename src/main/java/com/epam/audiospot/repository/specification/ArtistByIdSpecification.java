package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class ArtistByIdSpecification implements Specification {
    private static final String ARTIST_PREPARED_QUERY = "WHERE artist_id = ?";
    private Long id;

    public ArtistByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return ARTIST_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(id);
    }
}
