package com.epam.audiospot.repository.factory;

import by.belstu.losik.audiospot.entity.TrackAtAudioSet;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.TrackAtAudioSetRepository;

public class TrackAtAudioSetRepositoryFactory extends RepositoryFactory <TrackAtAudioSet> {
    @Override
    public Repository <TrackAtAudioSet> createRepository() {
        return new TrackAtAudioSetRepository(getConnection());
    }
}
