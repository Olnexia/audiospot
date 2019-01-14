package com.epam.audiospot.repository;

import com.epam.audiospot.entity.Entity;
import java.util.Map;
import java.util.stream.Collectors;

public class QueryBuilder<T extends Entity> {
    private String tableName;

    public QueryBuilder(String tableName) {
        this.tableName = tableName;
    }

    public String buildDeleteQuery(T object){
        return String.join(" ","DELETE FROM",tableName,"WHERE",tableName+"_id='"+object.getId().toString()+"';");
    }

    public String buildInsertQuery(Map<String,Object> fields){
        String fieldNames = "(" + String.join(",", fields.keySet()) + ")";

        String fieldValues="VALUES("+String.join(",", fields.values()
                .stream()
                .map(name -> ("'" + name + "'"))
                .collect(Collectors.toList()))+ ");";
        fieldValues = modifyLiterals(fieldValues);

        return String.join(" ", "INSERT INTO", tableName,fieldNames,fieldValues);
    }

    public String buildUpdateQuery(T object, Map<String,Object> fields){
        String setValues = "SET "+String.join(",", fields.entrySet()
                .stream()
                .map(entry -> entry.getKey()+"='"+entry.getValue()+"'")
                .collect(Collectors.toList()));
        setValues = modifyLiterals(setValues);

        return String.join(" ","UPDATE",tableName,setValues,"WHERE", tableName+"_id="+object.getId().toString()+";");
    }

    private String modifyLiterals(String query){
        return query.replaceAll("'null'","NULL")
                .replaceAll("'true'","true")
                .replaceAll("'false'","false");
    }
}
