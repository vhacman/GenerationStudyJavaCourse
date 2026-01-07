package com.generation.school.model.entities;

import java.time.LocalDate;

/**
 * Classe che rappresenta un impiegato generico del sistema scolastico.
 * Estende Person aggiungendo attributi specifici relativi all'impiego.
 *
 */
public class Employee extends Person
{
	// ==================== ATTRIBUTI ====================
	/** Stipendio mensile dell'impiegato */
	protected double 		salary;
	/** Ruolo o mansione dell'impiegato */
	protected String 		role;

	// ==================== COSTRUTTORI ====================
	/**
	 * Costruttore vuoto.
	 * Utile per la creazione di oggetti tramite framework o per inizializzazione graduale.
	 */
	public Employee() {	super();}

	/**
	 * Costruttore completo.
	 *
	 * @param id Identificativo univoco
	 * @param name Nome dell'impiegato
	 * @param surname Cognome dell'impiegato
	 * @param dateOfBirth Data di nascita
	 * @param salary Stipendio mensile
	 * @param role Ruolo o mansione
	 */
	public Employee(int id, String name, String surname, LocalDate dateOfBirth,
					double salary, String role) {
		super(id, name, surname, dateOfBirth);
		this.salary = salary;
		this.role = role;
	}

	// ==================== GETTER E SETTER ====================

	/** @return lo stipendio mensile */
	public double 	getSalary() { return salary; }

	/** @return il ruolo o mansione */
	public String 	getRole() { return role; }

	/** @param salary lo stipendio mensile da impostare */
	public void 	setSalary(double salary) { this.salary = salary; }

	/** @param role il ruolo o mansione da impostare */
	public void 	setRole(String role) { this.role = role; }

	/**
	 * Verifica se l'oggetto Employee è valido.
	 * Un impiegato è valido se:
	 * - I dati base della persona sono validi (super.isValid())
	 * - Lo stipendio è maggiore o uguale a 0
	 * - Il ruolo non è null e non è vuoto
	 *
	 * @return true se l'impiegato è valido, false altrimenti
	 */
	@Override
	public boolean isValid()
	{
		if (!super.isValid()) return false;
		if (salary < 0) return false;
		if (role == null || role.trim().isEmpty()) return false;
		return true;
	}


	// ==================== OVERRIDE METODI ============================
	// =========== SOVRASCRIVERE UN METODO DI UN SUPERTIPO =============

	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Employee.
	 * Utilizza super.toString() per includere gli attributi della superclasse Person
	 * e aggiunge gli attributi specifici di Employee.
	 *
	 * @return stringa formattata con le informazioni dell'impiegato
	 */
	@Override
	public String toString() {
		return  super.toString() +
				", salary=" + salary +
				", role='" + role + '\'';
	}
}
