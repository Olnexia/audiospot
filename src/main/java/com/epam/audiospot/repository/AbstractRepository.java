package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.connection.ConnectionWrapper;
import by.belstu.losik.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;
import com.epam.audiospot.exception.ServiceException;
import com.epam.audiospot.repository.specification.Specification;
import com.epam.audiospot.repository.utils.StatementBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.*;

public abstract class AbstractRepository<T extends Entity> implements Repository <T> {
    private static final Logger logger = LogManager.getLogger(AbstractRepository.class);
    private ConnectionWrapper connection;
    private StatementBuilder statementBuilder = new StatementBuilder(getTableName());

    public AbstractRepository(ConnectionWrapper connection) {
        this.connection = connection;
    }

    private List <T> executeQuery(String query, List <Object> params) throws RepositoryException {
        try (PreparedStatement preparedStatement = connection.getPreparedStatement(query)) {
            setPreparedStatementParams(preparedStatement, params);
            logger.trace(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            List <T> entities = new ArrayList <>();
            Builder <T> builder = getBuilder();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
            return entities;
        } catch (SQLException | ServiceException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public List <T> queryForAll() throws RepositoryException {
        String preparedQuery = String.join(" ", "select", getTableName() + ".*", "from", getTableName());
        return executeQuery(preparedQuery, Collections.emptyList());
    }

    @Override
    public Optional <T> queryForSingleResult(Specification specification) throws RepositoryException {
        List <T> entities = query(specification);
        return (!entities.isEmpty()) ? Optional.of(entities.get(0)) : Optional.empty();
    }

    @Override
    public List <T> query(Specification specification) throws RepositoryException {
        String specificationQuery = specification.toSql();
        List <Object> parameters = specification.getParameters();
        String preparedQuery =
                String.join(" ", "SELECT", getTableName() + ".*", "FROM", getTableName(), specificationQuery);
        return executeQuery(preparedQuery, parameters);
    }

    @Override
    public void save(T object) throws RepositoryException {
        if (object.getId() == null) {
            add(object);
        } else {
            update(object);
        }
    }

    @Override
    public void add(T object) throws RepositoryException {
        Map <String, Object> fields = getFields(object);
        String insertQuery = statementBuilder.buildInsertQuery(fields.entrySet());

        try (PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(insertQuery)) {
            setPreparedStatementParams(preparedStatement, new ArrayList <>(fields.values()));
            logger.trace(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Creating failed, no rows affected.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getLong(1));
                } else {
                    throw new RepositoryException("Creating failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void update(T object) throws RepositoryException {
        Map <String, Object> fields = getFields(object);
        String updateQuery = statementBuilder.buildUpdateQuery(object.getId(), fields);
        try (PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(updateQuery)) {
            setPreparedStatementParams(preparedStatement, new ArrayList <>(fields.values()));
            logger.trace(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Updating failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    @Override
    public void remove(T object) throws RepositoryException {
        String deleteQuery = statementBuilder.buildDeleteQuery(object.getId());
        try (PreparedStatement preparedStatement = connection.getPreparedStatementGeneratedKeys(deleteQuery)) {
            logger.trace(preparedStatement);
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new RepositoryException("Deleting failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage(), e);
        }
    }

    private void setPreparedStatementParams(PreparedStatement statement, List <Object> params) throws SQLException {
        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }
    }

    public abstract Builder <T> getBuilder();

    public abstract String getTableName();

    public abstract Map <String, Object> getFields(T object);


}