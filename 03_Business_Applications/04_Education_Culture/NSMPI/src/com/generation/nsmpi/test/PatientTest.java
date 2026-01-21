package com.generation.nsmpi.test;

import org.junit.jupiter.api.Test;

import com.generation.library.Console;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Patient;

import java.time.LocalDate;

/**
 * Test suite per la classe Patient.
 * Verifica i vincoli di integrità e la logica di validazione dell'entità paziente.
 * Applica metodologia TDD (Test-Driven Development) per garantire correttezza funzionale.
 */
class PatientTest
{

	@Test
	void testGetErrors()
	{
		Patient p = new Patient();

		Console.print(p.getErrors());
		assert(p.getErrors().size() == 5);
		assert(!p.isValid());

		p.setFirstName("Mario");
		Console.print(p.getErrors());
		assert(p.getErrors().size() == 4);
		assert(!p.isValid());

		p.setLastName("Rossi");
		Console.print(p.getErrors());
		assert(p.getErrors().size() == 3);
		assert(!p.isValid());

		p.setDob(LocalDate.of(1980, 5, 15));
		Console.print(p.getErrors());
		assert(p.getErrors().size() == 2);
		assert(!p.isValid());

		p.setGender(Gender.M);
		Console.print(p.getErrors());
		assert(p.getErrors().size() == 1);
		assert(!p.isValid());

		p.setHistory("Nessuna allergia conosciuta");
		Console.print(p.getErrors());
		assert(p.getErrors().size() == 0);
		assert(p.isValid());
	}

}
