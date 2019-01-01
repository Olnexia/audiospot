package com.epam.audiospot.repository;

import com.epam.audiospot.connection.ConnectionPool;
import com.epam.audiospot.connection.ConnectionWrapper;

public class RepositoryCreator implements AutoCloseable {
    private ConnectionWrapper connection ;

    public RepositoryCreator() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.getConnection();
    }

    public UserRepository getUserRepository(){
        return new UserRepository(connection);
    }

    public AudioRepository getAudioRepository(){
        return new AudioRepository(connection);
    }

    public ArtistRepository getArtistRepository(){
        return new ArtistRepository(connection);
    }

    public OrderRepository getOrderRepository(){
        return new OrderRepository(connection);
    }

    public OrderedTrackRepository getOrderedTrackRepository(){
        return new OrderedTrackRepository(connection);
    }

    public PlaylistRepository getPlaylistRepository(){
        return new PlaylistRepository(connection);
    }

    public TrackAtPlaylistRepository getTrackAtPlaylistRepository(){
        return new TrackAtPlaylistRepository(connection);
    }

    @Override
    public void close(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
    }
}
