package com.epam.audiospot.builder;

import com.epam.audiospot.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBuilder {

    public User build(ResultSet resultSet){

        try {
            resultSet.getLong("id");
            resultSet.
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
