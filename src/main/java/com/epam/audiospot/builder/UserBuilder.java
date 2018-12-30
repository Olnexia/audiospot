package com.epam.audiospot.builder;

import com.epam.audiospot.entity.Role;
import com.epam.audiospot.entity.User;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder implements Builder<User> {

    @Override
    public User build(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong(User.ID_LABEL);
        String name = resultSet.getString(User.NAME_LABEL);
        String surname = resultSet.getString(User.SURNAME_LABEL);
        String login = resultSet.getString(User.LOGIN_LABEL);
        String password = resultSet.getString(User.PASSWORD_LABEL);
        boolean active = resultSet.getBoolean(User.ACTIVE_LABEL);
        String roleContent = resultSet.getString(User.ROLE_LABEL);
        int discount = resultSet.getInt(User.DISCOUNT_LABEL);
        Role role = Role.valueOf(roleContent.toUpperCase());
        return new User(id,name,surname,login,password,active,discount,role);
    }
}
