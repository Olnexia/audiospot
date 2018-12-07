package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import com.epam.audiospot.exception.ConnectionPoolException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private static final int INITIAL_POOL_SIZE = 10;
    private static AtomicBoolean initialized = new AtomicBoolean(false);
    private static Lock lock = new ReentrantLock();
    private static ConnectionPool instance = null;
    private Semaphore semaphore;
    private Queue<ConnectionWrapper> pool;

    public static ConnectionPool getInstance() throws ConnectionPoolException{
        if(!initialized.get()) {
            try {
                lock.lock();
                if (!initialized.get()) {
                    instance = new ConnectionPool();
                    initConnectionPool(instance);
                    initialized.set(true);
                }
            }catch (ConnectionException e){
                throw new ConnectionPoolException(e.getMessage(),e);
            }finally {
                lock.unlock();
            }
        }
        return instance;
    }

    private static void initConnectionPool(ConnectionPool connectionPool) throws ConnectionException {
        Queue<ConnectionWrapper> connections = new LinkedList <>();
        for (int i = 0; i < INITIAL_POOL_SIZE; i++) {
            ConnectionWrapper connection = ConnectionFactory.getInstance();
            connections.add(connection);
        }
        connectionPool.setPool(connections);
    }

    public ConnectionWrapper getConnection() throws ConnectionPoolException{
        lock.lock();
        try {
            semaphore.acquire();
            ConnectionWrapper connection = pool.poll();
            return connection;
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
