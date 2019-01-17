package com.epam.audiospot.service;

import com.epam.audiospot.entity.*;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.Repository;
import com.epam.audiospot.repository.factory.AudioRepositoryFactory;
import com.epam.audiospot.repository.factory.RepositoryFactory;
import com.epam.audiospot.repository.specification.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AudioTrackService {

    public void submitTrack(AudioTrack track) throws ServiceException{
        try (RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            repository.add(track);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public AudioTrack buildTrack(String title, BigDecimal price,
                                 Genre genre,String artistName,String albumId) throws  ServiceException{
        ArtistService artistService = new ArtistService();
        Optional<Artist> artistOptional = artistService.getArtist(artistName,true);
        Artist artist;
        if(artistOptional.isPresent()) {
            artist = artistOptional.get();
        }else{
            throw new ServiceException("Artist is missing");
        }
        return (albumId==null) ?AudioTrack.single(artist,title,price,genre)
                :AudioTrack.albumTrack(artist,title,price,genre,Long.parseLong(albumId));
    }

    public List<AudioTrack> findOrderedTracks(Long orderId) throws ServiceException{
        AudioTrackByOrderIdSpecification specification = new AudioTrackByOrderIdSpecification(orderId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findTracksAtPlaylist(Long userId) throws ServiceException{
        AudioTracksByUserIdSpecification specification = new AudioTracksByUserIdSpecification(userId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findAvailableTracks(Long userId) throws ServiceException{
        AvailableTracksByUserIdSpecification specification = new AvailableTracksByUserIdSpecification(userId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findTracksAtAlbum(Long albumId) throws ServiceException{
        AudioTracksByAlbumIdSpecification specification = new AudioTracksByAlbumIdSpecification(albumId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findTracksAtAudioSet(Long audioSetId) throws ServiceException{
        AudioTracksByAudioSetIdSpecification specification = new AudioTracksByAudioSetIdSpecification(audioSetId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findNotSettedTracks(Long audioSetId) throws ServiceException{
        AvailableTracksByAudioSetIdSpecification specification = new AvailableTracksByAudioSetIdSpecification(audioSetId);
        try(RepositoryFactory<AudioTrack> factory = new AudioRepositoryFactory()) {
            Repository<AudioTrack> repository = factory.createRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
