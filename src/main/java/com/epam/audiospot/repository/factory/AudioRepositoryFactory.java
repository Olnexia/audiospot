package com.epam.audiospot.repository.factory;

import com.epam.audiospot.entity.AudioTrack;
import com.epam.audiospot.repository.AudioRepository;
import com.epam.audiospot.repository.Repository;

public class AudioRepositoryFactory extends RepositoryFactory <AudioTrack> {
    @Override
    public Repository <AudioTrack> createRepository() {
        return new AudioRepository(getConnection());
    }
}
