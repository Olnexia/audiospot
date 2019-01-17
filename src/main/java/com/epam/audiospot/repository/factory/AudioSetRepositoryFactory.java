package com.epam.audiospot.repository.factory;

import com.epam.audiospot.entity.AudioSet;
import com.epam.audiospot.repository.AudioSetRepository;
import com.epam.audiospot.repository.Repository;

public class AudioSetRepositoryFactory extends RepositoryFactory<AudioSet> {
    @Override
    public Repository<AudioSet> createRepository() {
        return new AudioSetRepository(getConnection());
    }
}
