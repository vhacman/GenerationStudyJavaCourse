package com.generation.oc.model.repository;
import java.sql.SQLException;
import java.util.List;

import com.generation.oc.model.entities.Patient;

public interface PatientRepository
{
	Patient 				findById(int id) 										throws SQLException;
	// se complete = true, caricherò anche le sue prestations
	Patient 				findById(int id, boolean complete) 	throws SQLException;
	List<Patient> 	findAll() 														throws SQLException;
	List<Patient> 	findWhere(String cond) 						throws SQLException;

	Patient 				update(Patient p) 									throws SQLException;
	Patient 				insert(Patient p) 										throws SQLException;
	boolean 			delete(int id)		 										throws SQLException;
}
