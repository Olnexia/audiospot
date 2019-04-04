package com.epam.audiospot.service;

import by.belstu.losik.audiospot.entity.AudioSet;
import by.belstu.losik.audiospot.entity.TrackAtAudioSet;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.AudioSetRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.factory.TrackAtAudioSetRepositoryFactory;
import com.epam.audiospot.repository.specification.AudioSetByIdSpecification;

import java.util.List;
import java.util.Optional;

public class AudioSetService {

    public Optional <AudioSet> findAudioSet(Long audioSetId) throws ServiceException {
        AudioSetByIdSpecification specification = new AudioSetByIdSpecification(audioSetId);
        try (RepositoryFactory <AudioSet> factory = new AudioSetRepositoryFactory()) {
            Repository <AudioSet> repository = factory.createRepository();
            return repository.queryForSingleResult(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void saveAudioSet(AudioSet audioSet) throws ServiceException {
        try (RepositoryFactory <AudioSet> factory = new AudioSetRepositoryFactory()) {
            Repository <AudioSet> repository = factory.createRepository();
            repository.save(audioSet);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List <AudioSet> findAllAudioSets() throws ServiceException {
        try (RepositoryFactory <AudioSet> factory = new AudioSetRepositoryFactory()) {
            Repository <AudioSet> repository = factory.createRepository();
            return repository.queryForAll();
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void addToSet(Long audioSetId, Long trackId) throws ServiceException {
        try (RepositoryFactory <TrackAtAudioSet> factory = new TrackAtAudioSetRepositoryFactory()) {
            Repository <TrackAtAudioSet> repository = factory.createRepository();
            TrackAtAudioSet orderedTrack = new TrackAtAudioSet(null, audioSetId, trackId);
            repository.add(orderedTrack);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
