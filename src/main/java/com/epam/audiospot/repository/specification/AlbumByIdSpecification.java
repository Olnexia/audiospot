package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class AlbumByIdSpecification implements Specification {
    private static final String ALBUM_PREPARED_QUERY = "WHERE album_id = ?";
    private Long id;

    public AlbumByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return ALBUM_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return Arrays.asList(id);
    }
}
