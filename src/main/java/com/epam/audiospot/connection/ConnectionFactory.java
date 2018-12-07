package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import com.epam.audiospot.exception.PropertiesReadingException;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String DB_PROPERTIES = "database.properties";
    private static final String URL_PROPERTY = "db.url";

    public static ConnectionWrapper getInstance() throws ConnectionException{
        try {
            Properties properties = getProperties();
            return new ConnectionWrapper(properties.getProperty(URL_PROPERTY),properties);
        }catch (PropertiesReadingException | ConnectionException e){
            throw new ConnectionException(e.getMessage(),e);
        }
    }

    private static Properties getProperties() throws PropertiesReadingException {
        try(BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(DB_PROPERTIES))){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        }catch (IOException e){
            throw new PropertiesReadingException(e.getMessage(),e);
        }
    }
}
