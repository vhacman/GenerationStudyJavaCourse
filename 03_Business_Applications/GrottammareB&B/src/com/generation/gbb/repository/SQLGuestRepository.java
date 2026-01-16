package com.generation.gbb.repository;

import java.sql.Connection;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Guest;

public class SQLGuestRepository implements GuestRepository
{
	//il repository ha bisogno della CONNECTION
	/**
	 * SENZA CONNECTION NON LAVORA
	 * 
	 * 
	 * LA CONNECTION È UNA DIPENDENZA DEL REPOSITORY 
	 * 
	 * 
	 */
	Connection connection = ConnectionFactory.make();

	@Override
	public List<Guest> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest findBySSN(String ssn) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Guest> findBySurnameContaining(String part) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Guest> findByCity(String city) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest insert(Guest newGuest) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Guest update(Guest newVersion) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
