package com.generation.nsmpi.etl;

import java.util.List;

import com.generation.nsmpi.model.entities.Patient;


/**
 * Questa è in assoluto la forma più generica
 * un componente che implementi PatientExtractor
 * deve produrre una lista di pazienti. 
 */
public interface PatientExtractor
{
	List<Patient> getPatientsFromFile(String filename);
}
