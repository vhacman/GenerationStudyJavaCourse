package com.generation.nsmi.model.entities;

import java.time.LocalDate;

/*
 * EREDITARIETÀ (Inheritance)
 *
 * La classe Paziente ESTENDE (extends) la classe Person.
 * Questo significa che Paziente:
 * - EREDITA tutti gli attributi di Person (id, firstName, lastName, SSN, dob)
 * - EREDITA tutti i metodi di Person (getter, setter, toString)
 * - PUÒ AGGIUNGERE nuovi attributi e metodi specifici per un paziente
 * - PUÒ SOVRASCRIVERE (override) metodi ereditati per personalizzarli
 *
 * TERMINOLOGIA:
 * - Person = superclasse (classe padre/base)
 * - Paziente = sottoclasse (classe figlia/derivata)
 *
 * RELAZIONE: "IS-A" (è un)
 * - Un Paziente È UNA Person
 * - Un Paziente ha tutte le caratteristiche di una Person + caratteristiche aggiuntive
 */
public class Patient extends Person
{
	// Attributi specifici di Patient (oltre a quelli ereditati da Person)
	private String			history;				// Storia clinica del paziente
	private InsuranceType 	insurance;	// Tipo di assicurazione (FULL, PARTIAL, NONE)

	public Patient() {super();}

	// Costruttore completo
	public Patient(int id, String firstName, String lastName, String SSN, LocalDate dob,
	                String history, InsuranceType insurance) {
		/*
		 * super() chiama il costruttore della superclasse (Person)
		 * Passiamo i parametri al costruttore di Person per inizializzare
		 * gli attributi ereditati (id, firstName, lastName, SSN, dob)
		 *
		 * IMPORTANTE: super() deve essere la PRIMA istruzione nel costruttore
		 */
		super(id, firstName, lastName, SSN, dob);

		// Inizializzazione attributi specifici di Patient
		this.history = history;
		this.insurance = insurance;
	}

	// Getters e Setters per gli attributi specifici di Patient
	public String 				getHistory() { return history; }
	public void 				setHistory(String history) { this.history = history; }

	public InsuranceType 		getInsurance() { return insurance; }
	public void 				setInsurance(InsuranceType insurance) { this.insurance = insurance; }

	@Override
	public String toString() {
		return "Patient [" +
		       "history=" + history +
		       ", insurance=" + insurance +
		       ", " + super.toString() + // Chiama toString() di Person
		       "]";
	}
}
