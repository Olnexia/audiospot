package com.epam.audiospot.repository.factory;

import com.epam.audiospot.connection.ConnectionPool;
import com.epam.audiospot.connection.ConnectionWrapper;
import by.belstu.losik.audiospot.entity.Entity;
import com.epam.audiospot.repository.*;

public abstract class RepositoryFactory<T extends Entity> implements AutoCloseable {
    private ConnectionWrapper connection;

    public RepositoryFactory() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        this.connection = connectionPool.getConnection();
    }

    public abstract Repository <T> createRepository();

    public ConnectionWrapper getConnection() {
        return connection;
    }

    @Override
    public void close() {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        connectionPool.releaseConnection(connection);
    }
}
