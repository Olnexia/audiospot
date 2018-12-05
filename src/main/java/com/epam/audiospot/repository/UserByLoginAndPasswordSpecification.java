package com.epam.audiospot.repository;

import java.util.Arrays;
import java.util.List;

public class UserByLoginAndPasswordSpecification implements Specification {
    private String login;
    private String password;

    public UserByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toSql() {
        throw new UnsupportedOperationException(); //Query with ? instead of params
    }

    @Override
    public List<String> getParameters() {
        return Arrays.asList(login,password);
    }
}
