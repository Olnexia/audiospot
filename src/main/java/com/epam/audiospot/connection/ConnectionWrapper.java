package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class ConnectionWrapper{
    private static final Logger logger = LogManager.getLogger(ConnectionWrapper.class);
    private Connection connection;

    public ConnectionWrapper(String url, String user, String password){
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
            logger.error(e.getMessage(),e);
            throw new ConnectionException(e.getMessage(),e);
        }
    }

    public PreparedStatement getPreparedStatement(String query) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(query);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    public PreparedStatement getPreparedStatementGeneratedKeys(String query) throws SQLException {
        if (connection != null) {
            PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            if (statement != null) {
                return statement;
            }
        }
        throw new SQLException("connection or statement is null");
    }

    public void close(){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error(e.getMessage(),e);
                throw new ConnectionException(e.getMessage(),e);
            }
        }
    }
}
