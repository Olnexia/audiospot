package com.epam.audiospot.repository;

import com.epam.audiospot.builder.AudioBuilder;
import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.exception.RepositoryException;
import java.util.List;

public class AudioRepository extends AbstractRepository<AudioTrack> {

    @Override
    public void add(AudioTrack object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void remove(AudioTrack object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(AudioTrack object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<AudioTrack> query(Specification specification) throws RepositoryException {
        String preparedQuery = specification.toSql();
        List<Object> parameters = specification.getParameters();
        try {
            return executeQuery(preparedQuery,parameters);
        } catch (RepositoryException e) {
            throw new RepositoryException(e.getMessage(),e);
        }
    }

    @Override
    public Builder <AudioTrack> getBuilder() {
        return new AudioBuilder();
    }
}
