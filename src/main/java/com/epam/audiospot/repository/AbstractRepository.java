package com.epam.audiospot.repository;


import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.entity.Entity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T > implements Repository<T>{

     public List<T> executeQuery(String query, String...params){

         try {
             Connection connection = getConnetion();
             PreparedStatement preparedStatement = connection.prepareStatement(query,params);
             ResultSet resultSet = preparedStatement.executeQuery(query);

             List<T> entities = new ArrayList <>();
             Builder builder = getBuilder();
             while(resultSet.next()){
                 T entity = builder.build(resultSet);
                 entities.add(entity);
             }
             return entities;
         }catch (SQLException e) {
             //Some ex
         }
     }

    @Override
    public void add(T object) {

    }

    @Override
    public void remove(T object) {

    }

    @Override
    public void update(T object) {

    }

    @Override
    public List <T> query(Specification specification) {
        return null;
    }

    @Override
    public Optional<T> queryForSingleResult(Specification specification) {
        return Optional.empty();
    }

    public abstract Builder<T> getBuilder();
}
