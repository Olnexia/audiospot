package com.epam.audiospot.repository.specification;

import java.util.Arrays;
import java.util.List;

public class OrderByUserIdAndStatusSpecification implements Specification {
    private static final String ORDER_PREPARED_QUERY = "WHERE user_id = ? AND paid = ?";
    private boolean paid;
    private Long userId;

    public OrderByUserIdAndStatusSpecification(Long userId, boolean paid) {
        this.paid = paid;
        this.userId = userId;
    }

    @Override
    public String toSql() {
        return ORDER_PREPARED_QUERY;
    }

    @Override
    public List <Object> getParameters() {
        return Arrays.asList(userId, paid);
    }
}
