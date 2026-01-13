package com.generation.nsmpi.test;

import org.junit.jupiter.api.Test;

import com.generation.library.Console;
import com.generation.nsmpi.model.entities.Doctor;
import com.generation.nsmpi.model.entities.Gender;
import com.generation.nsmpi.model.entities.Specialty;

import java.time.LocalDate;

/**
 * Test suite per la classe Doctor.
 * Verifica i vincoli di integrità e la logica di validazione dell'entità medico.
 * Applica metodologia TDD (Test-Driven Development) per garantire correttezza funzionale.
 */
class DoctorTest
{

	@Test
	void testGetErrors()
	{
	    Doctor d = new Doctor();

	    d.setSalary(-5000);
	    assert(d.getErrors().size() == 6); // 4 (Person) + 1 (specialties empty) + 1 (salary < 0)
	    assert(!d.isValid());

	    d.setFirstName("Giovanni");
	    assert(d.getErrors().size() == 5);
	    
	    d.setLastName("Bianchi");
	    assert(d.getErrors().size() == 4);

	    d.setDob(LocalDate.of(1970, 8, 25));
	    assert(d.getErrors().size() == 3);

	    d.setGender(Gender.M);
	    assert(d.getErrors().size() == 2);

	    // Corretto: aggiungiamo una Specialty (Enum) invece di una String
	    d.addSpecialty(Specialty.CARDIOLOGY); 
	    assert(d.getErrors().size() == 1);

	    d.setSalary(50000);
	    assert(d.getErrors().size() == 0);
	    assert(d.isValid());
	}
}
