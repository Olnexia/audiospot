package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;

import java.sql.*;

public class ConnectionWrapper{
    private Connection connection;

    public ConnectionWrapper(String url, String user, String password) throws ConnectionException{
        try{
            connection = DriverManager.getConnection(url,user,password);
        }catch (SQLException e){
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

    public void closePreparedStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                //log or exc
            }
        }
    }
}
