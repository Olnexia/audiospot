package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T>{
    private static final String SELECT_ALL = "SELECT* FROM";
    private ConnectionWrapper connection;

    public AbstractRepository(ConnectionWrapper connection){
        this.connection = connection;
    }

    public List<T> executeQuery(String query, List<Object>params)throws RepositoryException {
        try {
            String preparedQuery = SELECT_ALL + " " + getTableName() + " " + query;
            PreparedStatement preparedStatement = connection.getPreparedStatement(preparedQuery);
            for (int i = 0; i < params.size(); i++) {
                preparedStatement.setObject(i + 1, params.get(i));
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            List <T> entities = new ArrayList <>();
            Builder <T> builder = getBuilder();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public Optional<T> queryForSingleResult(Specification specification) throws RepositoryException {
        String preparedQuery = specification.toSql();
        List<Object> parameters = specification.getParameters();
        List<T> entities = executeQuery(preparedQuery,parameters);
        return (!entities.isEmpty())?Optional.of(entities.get(0)):Optional.empty();
    }

    public abstract Builder<T> getBuilder();
    public abstract String getTableName();
}
