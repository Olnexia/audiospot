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
    public void save(T object) throws RepositoryException{
        if(object.getId()==null){
            add(object);
        }else{
            update(object);
        }
    }

    @Override
    public void add(T object) throws RepositoryException {
        Map<String,Object> fields = getFields(object);
        String insertQuery = buildInsertQuery(object,fields);
        try{
            PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(insertQuery);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getLong(1));
                }
                else {
                    throw new RepositoryException("Creating failed, no ID obtained.");
                }
            }
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void update(T object) throws RepositoryException {
        Map<String,Object> fields = getFields(object);
        String updateQuery = buildUpdateQuery(object,fields);
        try{
            PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(updateQuery);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Updating failed, no rows affected.");
            }
        }catch (SQLException e){
            throw new RepositoryException(e.getMessage(),e);
        }
    }

    @Override
    public void remove(T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<T> query(Specification specification) throws RepositoryException{
        String preparedQuery= specification.toSql();
        List<Object> parameters = specification.getParameters();
        return executeQuery(preparedQuery,parameters);
    }

    public String buildInsertQuery(T object, Map<String,Object> fields){
        String fieldNames = "(" + String.join(",", fields.keySet()) + ")";

        String fieldValues="VALUES("+String.join(",", fields.values()
                .stream()
                .map(name -> ("'" + name + "'"))
                .collect(Collectors.toList())).replaceAll("'null'","NULL")
                .replaceAll("'true'","true")
                .replaceAll("'false'","false")+ ");";

        return String.join(" ", INSERT, getTableName(),fieldNames,fieldValues);
    }

    //NOT TESTED
    public String buildUpdateQuery(T object, Map<String,Object> fields){
        String setValues = "SET "+String.join(",", fields.entrySet()
                .stream()
                .map(entry -> entry.getKey()+"= '"+entry.getValue()+"'")
                .collect(Collectors.toList())).replaceAll("'null'","NULL")
                .replaceAll("'true'","true")
                .replaceAll("'false'","false");
        return String.join(" ","update",getTableName(),setValues,"WHERE id='",object.getId().toString()+"';");
    }

    public abstract Builder<T> getBuilder();
    public abstract String getTableName();
    public abstract Map<String,Object> getFields(T object);
}