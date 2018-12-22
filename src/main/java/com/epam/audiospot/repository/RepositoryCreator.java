package com.epam.audiospot.repository;

import com.epam.audiospot.connection.ConnectionPool;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Artist;
import com.epam.audiospot.entity.Order;
import com.epam.audiospot.exception.ConnectionPoolException;
import com.epam.audiospot.exception.RepositoryException;

//Maybe change to classic factory method?

public class RepositoryCreator implements AutoCloseable {
    private ConnectionWrapper connection ;

    public RepositoryCreator() throws RepositoryException {
        try {
            ConnectionPool connectionPool = ConnectionPool.getInstance();
            this.connection = connectionPool.getConnection();
        }catch(ConnectionPoolException e){
            throw new RepositoryException(e.getMessage(),e);
        }
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

    @Override
    public void close(){
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
    }
}
