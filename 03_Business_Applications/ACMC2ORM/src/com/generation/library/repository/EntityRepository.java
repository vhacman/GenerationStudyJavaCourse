package com.generation.library.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.library.Entity;

public interface EntityRepository<X extends Entity> 
{
	List<X> findAll() throws SQLException;
	
	List<X> findWhere(String cond) throws SQLException;
	
	X insert(X x) throws SQLException;
	
	X update(X x) throws SQLException;
	
	boolean delete(int id) throws SQLException;
	
}
