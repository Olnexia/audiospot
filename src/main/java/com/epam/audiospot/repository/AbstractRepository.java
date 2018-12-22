package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.specification.Specification;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class AbstractRepository<T extends Entity> implements Repository<T>{
    private static final String SELECT_ALL = "SELECT* FROM";
    private static final String INSERT = "INSERT INTO";
    private ConnectionWrapper connection;

    public AbstractRepository(ConnectionWrapper connection){
        this.connection = connection;
    }

    public List<T> executeQuery(String query, List<Object>params)throws RepositoryException {
        String preparedQuery = SELECT_ALL + " " + getTableName() + " " + query;
        try {
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
        } catch (SQLException| ServiceException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    public List<T> queryForAll() throws RepositoryException{
        return executeQuery("", Collections.emptyList());
    }

    public Optional<T> queryForSingleResult(Specification specification) throws RepositoryException {
        String preparedQuery = specification.toSql();
        List<Object> parameters = specification.getParameters();
        List<T> entities = executeQuery(preparedQuery,parameters);
        return (!entities.isEmpty())?Optional.of(entities.get(0)):Optional.empty();
    }

    @Override
    public void add(T object) throws RepositoryException {
        //building method + tests
        Map<String,Object> fields = getFields(object);
        String fieldNames = "(" + String.join(",", fields.keySet()) + ")";

        String fieldValues="VALUES("+String.join(",", fields.values()
                .stream()
                .map(name -> ("'" + name + "'"))
                .collect(Collectors.toList())).replaceAll("'null'","NULL")+ ");";

        String preparedInsert = String.join(" ", INSERT, getTableName(),fieldNames,fieldValues);
        try{
            PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(preparedInsert);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new RepositoryException("Creating user failed, no ID obtained.");
                }
            }
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public List<T> query(Specification specification) throws RepositoryException{
        String preparedQuery= specification.toSql();
        List<Object> parameters = specification.getParameters();
        return executeQuery(preparedQuery,parameters);
    }

    public abstract Builder<T> getBuilder();
    public abstract String getTableName();
    public abstract Map<String,Object> getFields(T object);
}
