package com.generation.acmc2.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc2.model.entities.Donation;

public interface DonationRepository 
{

	Donation findById(int id) throws SQLException;
	
	List<Donation> findAll() throws SQLException;
	
	List<Donation> findWhere(String condition) throws SQLException;
		
	Donation insert(Donation donation) throws SQLException;
	
	Donation update(Donation donation) throws SQLException;
	
	boolean delete(int id) throws SQLException;
	
}
