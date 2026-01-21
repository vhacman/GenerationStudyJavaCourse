package com.generation.school.model.entities;

import java.time.LocalDate;

public class Student extends Person
{
	// ==================== ATTRIBUTI ====================
	/** Anno scolastico frequentato dallo studente */
	protected int		year;
	/** Sezione della classe (es. A, B, C) */
	protected String	section;
	
	// ==================== COSTRUTTORI ====================
	/**
	 * Costruttore vuoto.
	 * Utile per la creazione di oggetti tramite framework o per inizializzazione graduale.
	 */	
	public Student() { super();}
	
	/**
	 * Costruttore completo.
	 *
	 * @param id Identificativo univoco
	 * @param name Nome dello studente
	 * @param surname Cognome dello studente
	 * @param dateOfBirth Data di nascita
	 * @param year Anno scolastico
	 * @param section Sezione della classe
	 */
	public Student(int id, String name, String surname, LocalDate dateOfBirth, int year, String section) {
		super(id, name, surname, dateOfBirth);
		this.year = year;
		this.section = section;
	}
	
	// ==================== GETTER E SETTER ====================
	/** @return l'anno scolastico */
	public int 		getYear() { return year; }

	/** @return la sezione della classe */
	public String 	getSection() { return section; }

	/** @param year l'anno scolastico da impostare */
	public void 	setYear(int year) { this.year = year; }

	/** @param section la sezione da impostare */
	public void 	setSection(String section) { this.section = section; }

	/**
	 * Verifica se l'oggetto Student è valido.
	 * Uno studente è valido se:
	 * - I dati base della persona sono validi (super.isValid())
	 * - L'anno scolastico è compreso tra 1 e 5
	 * - La sezione non è null e non è vuota
	 *
	 * @return true se lo studente è valido, false altrimenti
	 */
	@Override
	public boolean isValid()
	{
		if (!super.isValid()) return false;
		if (year < 1 || year > 5) return false;
		if (section == null || section.isEmpty()) return false;
		return true;
	}

	// ==================== OVERRIDE METODI ============================
	// =========== SOVRASCRIVERE UN METODO DI UN SUPERTIPO =============
	/**
	 * Restituisce una rappresentazione testuale dell'oggetto Student.
	 * Include gli attributi della superclasse Person e quelli specifici di Student.
	 *
	 * @return stringa formattata con le informazioni dello studente
	 */
	@Override
	public String toString()
	{
		return super.toString() +
				", year=" + year +
				", section='" + section + '\'';
	}
	
}
