package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.CommandExecutionException;
import com.epam.audiospot.exception.ServiceException;

import java.sql.ResultSet;
import java.sql.SQLException;


public interface Builder<T extends Entity>  {
    T build(ResultSet resultSet) throws SQLException, ServiceException;
}
