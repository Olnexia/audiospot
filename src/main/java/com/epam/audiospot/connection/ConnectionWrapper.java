package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionWrapper {
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

    public void closePreparedStatement(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                //log or exc
            }
        }
    }

    public void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                //log or exc
            }
        }
    }

}
