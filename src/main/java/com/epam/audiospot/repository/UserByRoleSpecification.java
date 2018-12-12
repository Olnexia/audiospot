package com.epam.audiospot.repository;

import com.epam.audiospot.entity.Role;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserByRoleSpecification implements Specification {
    private static final String USERS_PREPARED_QUERY = "WHERE role=?";
    private Role role;

    public UserByRoleSpecification(Role role){
        this.role = role;
    }

    @Override
    public String toSql() {
        return USERS_PREPARED_QUERY;
    }

    @Override
    public List<Object> getParameters() {
        return new ArrayList<Object>(Arrays.asList(role.getValue()));
    }
}
