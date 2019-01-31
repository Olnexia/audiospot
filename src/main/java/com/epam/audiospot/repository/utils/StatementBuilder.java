package com.epam.audiospot.repository.utils;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class StatementBuilder {
    private String tableName;

    public StatementBuilder(String tableName) {
        this.tableName = tableName;
    }

    public String buildDeleteQuery(Long id) {
        return String.join(" ", "DELETE FROM", tableName, "WHERE",
                tableName + "_id=" + id + ";");
    }

    public String buildInsertQuery(Set <Map.Entry <String, Object>> fields) {
        String fieldNames = "(" + String.join(",", fields.
                stream()
                .map(field -> (field.getKey()))
                .collect(Collectors.toList())) + ")";

        String fieldValues = "VALUES(" + String.join(",", fields
                .stream()
                .map(field -> ("?"))
                .collect(Collectors.toList())) + ");";
        return String.join(" ", "INSERT INTO", tableName, fieldNames, fieldValues);
    }

    public String buildUpdateQuery(Long id, Map <String, Object> fields) {
        String setValues = "SET " + String.join(",", fields.entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=?")
                .collect(Collectors.toList()));

        return String.join(" ", "UPDATE", tableName, setValues, "WHERE",
                tableName + "_id=" + id + ";");
    }
}
