package com.generation.oc.model.repository;

import org.junit.jupiter.api.Test;

import com.generation.oc.context.Context;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;

class TestGlobale
{

	@Test
	void testLoadPatient() throws Exception
	{
		PatientRepository repo =
				Context.getDependency(PatientRepository.class);

		Patient p = repo.findById(1);

		assert(p.getFirstName().equals("Giovanni"));
		assert(p.getPrestations().size()==3);
		assert(p.getPrestations().get(0).getHealthService().getName().equals("Diet"));

		// giovanni ha speso per noi 2000 euro nel 2025
		assert(p.getExpenses(2025)==2000);
	}

	@Test
	void testLoadPrestation() throws Exception
	{
		PrestationRepository repo =
				Context.getDependency(PrestationRepository.class);

		Prestation p = repo.findById(1);

		assert(p.getPatient().getFirstName().equals("Giovanni"));
		assert(p.getHealthService().getName().equals("Diet"));

	}


}
