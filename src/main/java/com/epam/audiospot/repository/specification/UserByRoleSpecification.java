package com.epam.audiospot.repository.specification;

import by.belstu.losik.audiospot.entity.Role;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UserByRoleSpecification implements Specification {
    private static final String USERS_PREPARED_QUERY = "WHERE role=?";
    private Role role;

    public UserByRoleSpecification(Role role) {
        this.role = role;
    }

    @Override
    public String toSql() {
        return USERS_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return new ArrayList <Object>(Collections.singletonList(role.getValue()));
    }
}
