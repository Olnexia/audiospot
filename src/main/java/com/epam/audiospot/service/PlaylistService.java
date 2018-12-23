package com.epam.audiospot.service;

import com.epam.audiospot.entity.Playlist;
import com.epam.audiospot.entity.TrackAtPlaylist;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.OrderRepository;
import com.epam.audiospot.repository.PlaylistRepository;
import com.epam.audiospot.repository.RepositoryCreator;
import com.epam.audiospot.repository.TrackAtPlaylistRepository;
import com.epam.audiospot.repository.specification.PlaylistByIdSpecification;
import com.epam.audiospot.repository.specification.PublicPlaylistsSpecification;

import java.util.List;
import java.util.Optional;

public class PlaylistService implements Service {

    public Optional<Playlist> findPlaylist(Long playlistId) throws ServiceException {
        PlaylistByIdSpecification specification = new PlaylistByIdSpecification(playlistId);
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            PlaylistRepository repository = repositoryCreator.getPlaylistRepository();
            return repository.queryForSingleResult(specification);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void savePlaylist(Playlist playlist) throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            PlaylistRepository repository = repositoryCreator.getPlaylistRepository();
            repository.save(playlist);
        }catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public List<Playlist> findPyblicPlaylists() throws ServiceException {
        PublicPlaylistsSpecification specification = new PublicPlaylistsSpecification();
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()) {
            PlaylistRepository repository = repositoryCreator.getPlaylistRepository();
            return repository.query(specification);
        } catch (RepositoryException e) {
            throw new ServiceException(e.getMessage(),e);
        }
    }

    public void addTrack(Long playlistId, Long trackId) throws ServiceException{
        try(RepositoryCreator repositoryCreator = new RepositoryCreator()){
            TrackAtPlaylistRepository repository = repositoryCreator.getTrackAtPlaylistRepository();
            TrackAtPlaylist trackAtPlaylist = new TrackAtPlaylist(null,playlistId,trackId);
            repository.save(trackAtPlaylist);
        } catch (RepositoryException e){
            throw new ServiceException(e.getMessage(),e);
        }
    }
}
