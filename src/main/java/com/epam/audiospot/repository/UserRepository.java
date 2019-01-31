package com.epam.audiospot.repository;

import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.builder.UserBuilder;
import com.epam.audiospot.connection.ConnectionWrapper;
import com.epam.audiospot.entity.User;

import java.util.LinkedHashMap;
import java.util.Map;

public class UserRepository extends AbstractRepository <User> {
    private static final String TABLE_NAME = "user";

    public UserRepository(ConnectionWrapper connection) {
        super(connection);
    }

    @Override
    public Builder <User> getBuilder() {
        return new UserBuilder();
    }

    @Override
    public String getTableName() {
        return TABLE_NAME;
    }

    @Override
    public Map <String, Object> getFields(User user) {
        Map <String, Object> fields = new LinkedHashMap <>();
        fields.put(User.ID_LABEL, user.getId());
        fields.put(User.LOGIN_LABEL, user.getLogin());
        fields.put(User.PASSWORD_LABEL, user.getPassword());
        fields.put(User.NAME_LABEL, user.getName());
        fields.put(User.SURNAME_LABEL, user.getSurname());
        fields.put(User.ACTIVE_LABEL, user.isActive());
        fields.put(User.DISCOUNT_LABEL, user.getDiscount());
        fields.put(User.ROLE_LABEL, user.getRole().getValue());
        return fields;
    }
}
