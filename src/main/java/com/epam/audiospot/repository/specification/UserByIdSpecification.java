package com.epam.audiospot.repository.specification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserByIdSpecification implements Specification {
    private static final String USERS_PREPARED_QUERY = "WHERE user_id=?";
    private Long userId;

    public UserByIdSpecification(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toSql() {
        return USERS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return new ArrayList<Object>(Arrays.asList(userId));
    }
}
