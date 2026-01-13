package com.generation.nsmpi.test;

import org.junit.jupiter.api.Test;

import com.generation.library.Console;
import com.generation.nsmpi.model.entities.MedicalService;

class MedicalServiceTest
{

	@Test
	void testGetErrors()
	{
		MedicalService m = new MedicalService();

		m.setPrice(-100);
		Console.print(m.getErrors());
		assert(m.getErrors().size() == 2);
		assert(!m.isValid());


		m.setPrice(100);
		Console.print(m.getErrors());
		assert(m.getErrors().size() == 1);
		assert(!m.isValid());

		m.setDescription("Pulizia ai denti");
		Console.print(m.getErrors());
		assert(m.getErrors().size() == 0);
		assert(m.isValid());
	}

}
