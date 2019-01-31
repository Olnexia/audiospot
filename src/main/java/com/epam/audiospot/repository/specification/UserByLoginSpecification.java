package com.epam.audiospot.repository.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserByLoginSpecification implements Specification {
    private static final String USER_PREPARED_QUERY = "WHERE login = ?";
    private String login;

    public UserByLoginSpecification(String login) {
        this.login = login;
    }

    @Override
    public String toSql() {
        return USER_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return new ArrayList <Object>(Arrays.asList(login));
    }
}
