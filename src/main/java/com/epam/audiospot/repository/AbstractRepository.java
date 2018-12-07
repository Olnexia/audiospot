package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionPool;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.ConnectionPoolException;
import com.epam.audiospot.exception.RepositoryException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T>{

     protected List<T> executeQuery(String query, Object...params)throws RepositoryException {
         try {
             ConnectionPool connectionPool = ConnectionPool.getInstance();
             ConnectionWrapper connectionWrapper = connectionPool.getConnection();
             PreparedStatement preparedStatement = connectionWrapper.getPreparedStatement(query);
             for(int i =0;i<params.length;i++){
                 preparedStatement.setObject(i,params[i]);
             }
             ResultSet resultSet = preparedStatement.executeQuery(query);
             List <T> entities = new ArrayList <>();
             Builder <T> builder = getBuilder();
             while (resultSet.next()) {
                 T entity = builder.build(resultSet);
                 entities.add(entity);
             }
             return entities;
         } catch (SQLException | ConnectionPoolException e) {
             throw new RepositoryException(e.getMessage(), e);
         }
     }

    @Override
    public void add(T object) {
         throw new UnsupportedOperationException();
    }

    @Override
    public void remove(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List <T> query(Specification specification) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<T> queryForSingleResult(Specification specification) {
        return Optional.empty();
    }

    public abstract Builder<T> getBuilder();
}
