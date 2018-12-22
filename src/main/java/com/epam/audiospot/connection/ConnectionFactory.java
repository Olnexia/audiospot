package com.epam.audiospot.connection;

import com.epam.audiospot.exception.ConnectionException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static final String DB_BUNDLE = "database";
    private static final String URL_PROPERTY = "db.url";
    private static final String USER_PROPERTY = "db.user";
    private static final String PASSWORD_PROPERTY = "db.password";

    public static ConnectionWrapper getInstance() throws ConnectionException{
        try {
            ResourceBundle resource = ResourceBundle.getBundle(DB_BUNDLE);
            String url = resource.getString(URL_PROPERTY);
            String user = resource.getString(USER_PROPERTY);
            String password = resource.getString(PASSWORD_PROPERTY);
            return new ConnectionWrapper(url,user,password);
        }catch ( ConnectionException e){ //connectionException extends RuntimeException
            throw new ConnectionException(e.getMessage(),e);
        }
    }
}
