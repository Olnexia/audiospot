package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    private static final String ID_LABEL = "user_id";
    private static final String PLAYLIST_ID_LABEL = "playlist_id";
    private static final String NAME_LABEL = "name";
    private static final String SURNAME_LABEL = "surname";
    private static final String LOGIN_LABEL = "login";
    private static final String PASSWORD_LABEL = "password";
    private static final String ACCOUNT_LABEL = "account";
    private static final String ROLE_LABEL = "role";

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(ID_LABEL);
        Long playlistId = resultSet.getLong(PLAYLIST_ID_LABEL);
        String name = resultSet.getString(NAME_LABEL);
        String surname = resultSet.getString(SURNAME_LABEL);
        String login = resultSet.getString(LOGIN_LABEL);
        String password = resultSet.getString(PASSWORD_LABEL);
        BigDecimal account = resultSet.getBigDecimal(ACCOUNT_LABEL);
        String roleContent = resultSet.getString(ROLE_LABEL);
        Role role = Role.valueOf(roleContent);
        return new User(id,name,surname,login,password,account,role,playlistId);
    }
}
