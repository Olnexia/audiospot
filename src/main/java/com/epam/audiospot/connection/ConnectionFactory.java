package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_PROPERTIES = "/database.properties";
    private static final String URL_PROPERTY = "db.url";
    private static final String USER_PROPERTY = "db.user";
    private static final String PASSWORD_PROPERTY = "db.password";
    private static final Logger logger = LogManager.getLogger(ConnectionFactory.class);

    public static ConnectionWrapper getInstance(){
        Properties properties = new Properties();
        try(InputStream input = ConnectionFactory.class.getResourceAsStream(DB_PROPERTIES)) {
            properties.load(input);
            String url = properties.getProperty(URL_PROPERTY);
            String user = properties.getProperty(USER_PROPERTY);
            String password = properties.getProperty(PASSWORD_PROPERTY);
            return new ConnectionWrapper(url,user,password);
        }catch ( IOException e){
            logger.error("Connection creation is failed");
            throw new ConnectionException(e.getMessage(),e);
        }
    }
}
