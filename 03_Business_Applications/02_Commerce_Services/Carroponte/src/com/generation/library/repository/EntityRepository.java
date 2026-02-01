package com.generation.library.repository;

import com.generation.library.Entity;
import java.sql.SQLException;
import java.util.List;

public interface EntityRepository<X extends Entity> {
   List<X> findAll() ;

   List<X> findWhere(String id) throws SQLException;

   X insert(X id) throws SQLException;

   X update(X id) throws SQLException;

   boolean delete(int id) throws SQLException;
}
