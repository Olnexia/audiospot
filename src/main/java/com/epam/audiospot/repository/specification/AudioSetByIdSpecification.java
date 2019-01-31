package com.epam.audiospot.repository.specification;

import java.util.Collections;
import java.util.List;

public class AudioSetByIdSpecification implements Specification {
    private static final String AUDIOSETS_PREPARED_QUERY = "WHERE audioset_id=?";
    private Long id;

    public AudioSetByIdSpecification(Long id) {
        this.id = id;
    }

    @Override
    public String toSql() {
        return AUDIOSETS_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Collections.singletonList(id);
    }
}
