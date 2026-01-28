package com.generation.oc.model.repository;
import java.sql.SQLException;
import java.util.List;

import com.generation.oc.model.entities.HealthService;

public interface HealthServiceRepository
{
	List<HealthService> 	findAll() 								throws SQLException;
	List<HealthService> 	findWhere(String cond) 					throws SQLException;
	HealthService 			findById(int id) 						throws SQLException;

	HealthService 			update(HealthService h) 				throws SQLException;
	HealthService 			insert(HealthService h) 				throws SQLException;
	boolean					delete(int id) 							throws SQLException;

	default List<HealthService> findByNameContaining(String part) throws SQLException
	{
		return findWhere("name like '%"+part+"%'");
	}
}
