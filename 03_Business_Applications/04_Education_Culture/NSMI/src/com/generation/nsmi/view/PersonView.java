package com.generation.nsmi.view;

import com.generation.nsmi.model.entities.Patient;
import com.generation.nsmi.model.entities.Person;
import com.generation.nsmi.model.entities.Doctor;
import com.generation.library.Console;
import com.generation.library.Template;

public class PersonView
{
    
    String 			templateFolder;
    
    public	PersonView(String template)			{this.templateFolder = template;}
    
    /**
     * POLIMORFISMO DI OGGETTO 
     *
     * Il parametro p è POLIMORFICO - può assumere forme diverse a runtime.
     *
     * Entra una Person, ma potrebbe essere:
     * - una Person "liscia"
     * - un Patient
     * - un Doctor (futuro)
     * - un Employee (futuro)
     * - qualsiasi sottoclasse di Person!
     *
     * In base al tipo EFFETTIVO dell'oggetto, lo trattiamo in maniera diversa.
     *
     * IMPORTANTE:
     * - Conosciamo solo il tipo FORMALE (Person) - quello della variabile
     * - NON conosciamo il tipo CONCRETO/EFFETTIVO - quello dell'oggetto
     * - Usiamo instanceof per scoprire il tipo effettivo a runtime
     *
     * Questo è POLIMORFISMO: una variabile, molte forme possibili!
     *
     * @param p variabile polimorfica di tipo Person
     * @return rappresentazione in base al tipo effettivo
     */
    public String		render(Person p) // p è polimorfica!
    {
        // Controllo il tipo EFFETTIVO di p a runtime
        if(p instanceof Patient)      // p è davvero un Patient?
        	return renderPatient(p);
        if (p instanceof Doctor)      // p è davvero un Doctor?
        	return renderDoctor(p);
        return renderPerson(p);       // altrimenti è una Person generica
    }

	/**
	 * Renderizza una Person usando il template person.txt
	 * Legge il template e sostituisce i placeholder con i dati effettivi
	 */
	private String		renderPerson(Person p)
	{
		return Template
				.load(templateFolder + "/person.txt")
			.replace("[id]", String.valueOf(p.getId()))
			.replace("[firstName]", p.getFirstName())
			.replace("[lastName]", p.getLastName())
			.replace("[SSN]", p.getSSN())
			.replace("[dob]", String.valueOf(p.getDob()));
	}

	/**
	 * Renderizza un Patient usando il template patient.txt
	 * POLIMORFISMO: riceve Person ma fa downcasting a Patient
	 */
	private String renderPatient(Person p)
	{
		/*
		 * DOWNCASTING: "Cambio pigiamino" all'oggetto puntato da p
		 *
		 * p è di tipo Person (tipo formale), ma punta a un oggetto Patient (tipo effettivo).
		 * Con il cast (Patient) diciamo al compilatore: "Fidati, so che dentro c'è un Patient!"
		 *
		 * Possiamo fare downcasting SOLO perché Patient è sottoclasse di Person.
		 * Questo ci permette di accedere ai metodi specifici di Patient (getHistory, getInsurance).
		 *
		 * ATTENZIONE: Se p non fosse davvero un Patient, otterremmo ClassCastException a runtime!
		 * Per questo prima usiamo instanceof nel metodo render().
		 */
		Patient 			patient = (Patient) p;
		return Template
				.load(templateFolder + "/patient.txt")
			.replace("[id]", String.valueOf(patient.getId()))
			.replace("[firstName]", patient.getFirstName())
			.replace("[lastName]", patient.getLastName())
			.replace("[SSN]", patient.getSSN())
			.replace("[dob]", String.valueOf(patient.getDob()))
			.replace("[history]", patient.getHistory() != null ? patient.getHistory() : "N/A")
			.replace("[insurance]", patient.getInsurance() != null ? patient.getInsurance().toString() : "N/A");
	}

	/**
	 * Renderizza un Doctor usando il template doctor.txt
	 * POLIMORFISMO: riceve Person ma fa downcasting a Doctor
	 */
	private String renderDoctor(Person p)
	{
		/*
		 * DOWNCASTING: "Cambio pigiamino" all'oggetto puntato da p
		 * p è di tipo Person (tipo formale), ma punta a un oggetto Doctor (tipo effettivo).
		 * Il cast ci permette di accedere ai metodi specifici di Doctor.
		 */
		Doctor 			doctor = (Doctor) p;
		return Template
				.load(templateFolder + "/doctor.txt")
			.replace("[id]", String.valueOf(doctor.getId()))
			.replace("[firstName]", doctor.getFirstName())
			.replace("[lastName]", doctor.getLastName())
			.replace("[SSN]", doctor.getSSN())
			.replace("[dob]", String.valueOf(doctor.getDob()))
			.replace("[specialization]", doctor.getSpecialization() != null ? doctor.getSpecialization().toString() : "N/A")
			.replace("[salary]", String.format("€%.2f", doctor.getSalary()))
			.replace("[salaryInfo]", doctor.getSalaryInfo())
			.replace("[salaryValid]", doctor.isValidSalaryForSpecialty() ? "Valido" : "NON VALIDO");
	}
}