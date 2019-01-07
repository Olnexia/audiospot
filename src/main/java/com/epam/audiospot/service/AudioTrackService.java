package com.epam.audiospot.service;

import com.epam.audiospot.entity.*;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.AudioRepository;
import com.epam.audiospot.repository.creator.RepositoryCreator;
import com.epam.audiospot.repository.specification.AudioTrackByOrderIdSpecification;
import com.epam.audiospot.repository.specification.AudioTracksByAlbumIdSpecification;
import com.epam.audiospot.repository.specification.AudioTracksByUserIdSpecification;
import com.epam.audiospot.repository.specification.AvailableTracksByUserIdSpecification;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class AudioTrackService {

    public void submitTrack(AudioTrack track) throws ServiceException{
        try (RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            repository.add(track);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public AudioTrack buildTrack(String title, BigDecimal price,
                                 Genre genre,String artistName) throws  ServiceException{
        ArtistService artistService = new ArtistService();
        Optional<Artist> artistOptional = artistService.getArtist(artistName,true);
        Artist artist;
        if(artistOptional.isPresent()) {
            artist = artistOptional.get();
        }else{
            throw new ServiceException("Artist is missing");
        }
        return AudioTrack.single(artist,title,price,genre);
    }

    public List<AudioTrack> findOrderedTracks(Long orderId) throws ServiceException{
        AudioTrackByOrderIdSpecification specification = new AudioTrackByOrderIdSpecification(orderId);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findTracksAtPlaylist(Long userId) throws ServiceException{
        AudioTracksByUserIdSpecification specification = new AudioTracksByUserIdSpecification(userId);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findAvailableTracks(Long userId) throws ServiceException{
        AvailableTracksByUserIdSpecification specification = new AvailableTracksByUserIdSpecification(userId);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<AudioTrack> findTracksAtAlbum(Long albumId) throws ServiceException{
        AudioTracksByAlbumIdSpecification specification = new AudioTracksByAlbumIdSpecification(albumId);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            AudioRepository repository = repositoryCreator.getAudioRepository();
            return repository.query(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
