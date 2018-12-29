package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class ConnectionFactory {
    private static final String DB_PROPERTIES = "/database.properties";
    private static final String URL_PROPERTY = "db.url";
    private static final String USER_PROPERTY = "db.user";
    private static final String PASSWORD_PROPERTY = "db.password";

    public static ConnectionWrapper getInstance() throws ConnectionException{
        Properties properties = new Properties();
        try(InputStream input = ConnectionFactory.class.getResourceAsStream(DB_PROPERTIES)) {
            properties.load(input);
            String url = properties.getProperty(URL_PROPERTY);
            String user = properties.getProperty(USER_PROPERTY);
            String password = properties.getProperty(PASSWORD_PROPERTY);
            return new ConnectionWrapper(url,user,password);
        }catch ( IOException e){
            throw new ConnectionException(e.getMessage(),e);
        }
    }
}
