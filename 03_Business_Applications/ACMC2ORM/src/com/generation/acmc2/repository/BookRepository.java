package com.generation.acmc2.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc2.model.entities.Book;

public interface BookRepository
{
	Book findById(int id) throws SQLException;
	
	// se complete = true, caricherò anche le sue donazioni
	Book findById(int id, boolean complete) throws SQLException;
	
	Book update(Book m) throws SQLException;
	
	Book insert(Book m) throws SQLException;

	boolean delete(int id) throws SQLException;
	
	List<Book> findAll() throws SQLException;
	
	List<Book> findWhere(String cond) throws SQLException;
}
