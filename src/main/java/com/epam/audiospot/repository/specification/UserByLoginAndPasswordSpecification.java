package com.epam.audiospot.repository.specification;

import com.epam.audiospot.repository.specification.Specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserByLoginAndPasswordSpecification implements Specification {
    private static final String USERS_PREPARED_QUERY = "WHERE login = ? AND password = ?";
    private String login;
    private String password;

    public UserByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toSql() {
        return USERS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return new ArrayList <Object>(Arrays.asList(login,password));
    }
}
