package com.generation.oc.model.repository;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.oc.model.entities.Prestation;

public interface PrestationRepository
{
	List<Prestation> findAll() 											throws SQLException;
	List<Prestation> findWhere(String cond) 							throws SQLException;
	Prestation findById(int id) 										throws SQLException;
	// se complete = true, caricherò anche patient e healthService
	Prestation findById(int id, boolean complete) 						throws SQLException;

	Prestation update(Prestation p) 									throws SQLException;
	Prestation insert(Prestation p) 									throws SQLException;
	boolean delete(int id) 												throws SQLException;
	
	// metodo di default usato per il comando 1
	default List<Prestation> findByDate(LocalDate date) 				throws SQLException
	{
		return findWhere("date='"+date+"'");
	}
	
	default List<Prestation> findByPeriod(LocalDate from, LocalDate to) throws SQLException
	{
		return findWhere("date between '"+from+"' and '"+to+"'");
	}
}
