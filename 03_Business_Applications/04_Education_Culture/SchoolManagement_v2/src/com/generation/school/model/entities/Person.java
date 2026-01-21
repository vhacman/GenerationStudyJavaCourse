package com.generation.school.model.entities;

import java.time.LocalDate;
import java.time.Period;

/**
 * Classe base che rappresenta una persona generica nel sistema scolastico.
 * Serve come classe padre per entità più specifiche come Student e Teacher.
 * Contiene le informazioni anagrafiche fondamentali.
 *
 */
public class Person
{
	protected int 			id;
	protected LocalDate 	dateOfBirth;
	protected String		name;
	protected String 		surname;

	// ==================== COSTRUTTORI ====================	
	public Person() {}
	
	/**
	 * Costruttore con parametri base.
	 *
	 * @param id Identificativo univoco
	 * @param name Nome della persona
	 * @param surname Cognome della persona
	 * @param dateOfBirth Data di nascita
	 */
	public Person(int id, String name, String surname, LocalDate dateOfBirth) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	// ==================== GETTER E SETTER ====================

	/** @return l'identificativo della persona */
	public int 			getId() { return id; }

	/** @return la data di nascita */
	public LocalDate 	getDateOfBirth() { return dateOfBirth; }
	
	/** @return il nome della persona */
	public String 		getName() { return name; }
	
	/** @return il cognome della persona */
	public String 		getSurname() { return surname; }
	
	/** @param id l'identificativo da impostare */
	public void 		setId(int id) { this.id = id; }

	/** @param dateOfBirth la data di nascita da impostare */
	public void 		setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

	/** @param name il nome da impostare */
	public void 		setName(String name) { this.name = name; }

	/** @param surname il cognome da impostare */
	public void 		setSurname(String surname) { this.surname = surname; }

	/**
	 * Calcola l'età della persona in base alla data di nascita
	 * Utilizza Period per calcolare la differenza tra la data corrente e quella di nascita
	 *
	 * @return l'età in anni, o 0 se la data di nascita non è impostata
	 */
	public int getAge()
	{
		if (dateOfBirth == null) return 0;
		return Period.between(dateOfBirth, LocalDate.now()).getYears();
	}

	/**
	 * Verifica se l'oggetto Person è valido.
	 * Una persona è valida se:
	 * - L'ID è maggiore di 0
	 * - Il nome non è null e non è vuoto
	 * - Il cognome non è null e non è vuoto
	 * - La data di nascita non è null e non è futura
	 *
	 * @return true se la persona è valida, false altrimenti
	 */
	public boolean isValid()
	{
		if (id < 0) return false;
		if (name == null || name.isEmpty()) return false;
		if (surname == null || surname.isEmpty()) return false;
		if (dateOfBirth == null || dateOfBirth.isAfter(LocalDate.now())) return false;
		return true;
	}

	// ==================== OVERRIDE METODI ============================
	// =========== SOVRASCRIVERE UN METODO DI UN SUPERTIPO =============
	// Override: riscrivere un metodo ereditato da una superclasse mantenendo identica la firma
	// (stesso nome, stessi parametri, stesso tipo di ritorno)
	// Permette di specializzare e personalizzare il comportamento del metodo per la classe corrente

	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Person.
	 * Include tutti gli attributi principali e l'età calcolata.
	 *
	 * @return stringa formattata con le informazioni della persona
	 */
	@Override
	public String toString() {
		return 	"id=" + id +
				", name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", dateOfBirth=" + dateOfBirth;
	}
}
