package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionPoolException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool{
    private static final int INITIAL_POOL_SIZE = 10;
    private static final AtomicBoolean initialized = new AtomicBoolean(false);
    private static final Lock lock = new ReentrantLock();
    private static ConnectionPool instance = null;

    private final Semaphore semaphore = new Semaphore(INITIAL_POOL_SIZE);
    private Queue<ConnectionWrapper> pool;

    public static ConnectionPool getInstance(){
        if(!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    instance = new ConnectionPool();
                    initConnectionPool(instance);
                    initialized.set(true);
                }
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private static void initConnectionPool(ConnectionPool connectionPool){
        Queue<ConnectionWrapper> connections = new LinkedList <>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            ConnectionWrapper connection = ConnectionFactory.getInstance();
            connections.add(connection);
        }
        connectionPool.setPool(connections);
    }

    public ConnectionWrapper getConnection() {
        try {
            lock.lock();
            semaphore.acquire();
            return pool.poll();
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e.getMessage(), e);
        }finally {
            lock.unlock();
        }
    }

    public void releaseConnection(ConnectionWrapper connection){
        pool.add(connection);
        semaphore.release();
    }

    private void setPool(Queue <ConnectionWrapper> pool) {
        this.pool = pool;
    }
}
