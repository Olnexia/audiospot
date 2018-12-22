package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID_LABEL);
        Long playlistId = resultSet.getLong(User.PLAYLIST_ID_LABEL);
        String name = resultSet.getString(User.NAME_LABEL);
        String surname = resultSet.getString(User.SURNAME_LABEL);
        String login = resultSet.getString(User.LOGIN_LABEL);
        String password = resultSet.getString(User.PASSWORD_LABEL);
        BigDecimal account = resultSet.getBigDecimal(User.ACCOUNT_LABEL);
        String roleContent = resultSet.getString(User.ROLE_LABEL);
        Role role = Role.valueOf(roleContent.toUpperCase());
        return new User(id,name,surname,login,password,account,role,playlistId);
    }
}
