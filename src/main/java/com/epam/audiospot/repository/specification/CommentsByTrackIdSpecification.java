package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class CommentsByTrackIdSpecification implements Specification {
    private static final String COMMENT_PREPARED_QUERY = "WHERE audiotrack_id = ?";
    private Long trackId;

    public CommentsByTrackIdSpecification(Long trackId) {
        this.trackId = trackId;
    }

    @Override
    public String toSql() {
        return COMMENT_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Collections.singletonList(trackId);
    }
}
