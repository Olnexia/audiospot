package com.epam.audiospot.repository;

public class UserByLoginAndPasswordSpecification implements Specification {
    private String login;
    private String password;

    public UserByLoginAndPasswordSpecification(String login, String password) {
        this.login = login;
        this.password = password;
    }

    @Override
    public String toSql() {
        throw new UnsupportedOperationException();
    }
}
