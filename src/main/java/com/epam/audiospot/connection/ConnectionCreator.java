package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionCreator {
    private static final String DB_PROPERTIES = "/database.properties";
    private static final String URL_PROPERTY = "db.url";
    private static final String USER_PROPERTY = "db.user";
    private static final String PASSWORD_PROPERTY = "db.password";
    private static final Logger logger = LogManager.getLogger(ConnectionCreator.class);
    private String url;
    private String user;
    private String password;

    public ConnectionCreator(){
        Properties properties = new Properties();
        try(InputStream input = ConnectionCreator.class.getResourceAsStream(DB_PROPERTIES)) {
            properties.load(input);
            url = properties.getProperty(URL_PROPERTY);
            user = properties.getProperty(USER_PROPERTY);
            password = properties.getProperty(PASSWORD_PROPERTY);
        }catch ( IOException e){
            logger.error(e.getMessage(),e);
            throw new ConnectionException(e.getMessage(),e);
        }
    }

    public  ConnectionWrapper createConnection(){
        return new ConnectionWrapper(url,user,password);
    }
}
