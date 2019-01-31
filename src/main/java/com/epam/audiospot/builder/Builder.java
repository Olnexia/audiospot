package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @param <T> the type of object being constructed by the class implementing {@code Builder}
 */
public interface Builder<T extends Entity> {
    /**
     * Constructs an instance of {@code T} class from {@code ResultSet}
     *
     * @param resultSet the result of query to database
     * @return an instance of {@code T} class
     * @throws SQLException     from {@code ResultSet} method of data retrieving
     * @throws ServiceException from service methods
     */
    T build(ResultSet resultSet) throws SQLException, ServiceException;
}
