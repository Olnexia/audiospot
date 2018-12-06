package com.epam.audiospot.repository;


import com.epam.audiospot.builder.Builder;
import com.epam.audiospot.entity.Entity;
import com.epam.audiospot.exception.RepositoryException;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T extends Entity> implements Repository<T>{

     public List<T> executeQuery(String query, String...params)throws RepositoryException{

         try {
             Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query,params);
             ResultSet resultSet = preparedStatement.executeQuery(query);

             List<T> entities = new ArrayList <>();
             Builder<T> builder = getBuilder();
             while(resultSet.next()){
                 T entity = builder.build(resultSet);
                 entities.add(entity);
             }
             return entities;
         }catch (SQLException e) {
             throw new RepositoryException(e.getMessage(),e);
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

     private Connection getConnection() {
         return null;
     }
}
