package com.generation.nsmpi.etl;

import java.util.List;

import com.generation.nsmpi.model.entities.Doctor;


/**
 * Questa è in assoluto la forma più generica
 * un componente che implementi PatientExtractor
 * deve produrre una lista di pazienti. 
 */
public interface DoctorExtractor
{
	List<Doctor> getDoctorsFromFile(String filename);
}
