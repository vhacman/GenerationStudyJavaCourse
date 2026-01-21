package com.generation.school.model.entities;

import java.time.LocalDate;

/**
 * Classe che rappresenta un insegnante.
 * Estende la classe Person aggiungendo proprietà specifiche per gli insegnanti
 * come la materia insegnata e lo stipendio.
 *
 */
public class Teacher extends Person
{

	// Properties (private fields)
	private String subject;
	private double salary;

	/**
	 * Costruttore di default.
	 * Invoca super() per inizializzare la parte Person,
	 * poi inizializza la parte Teacher.
	 */
	public Teacher()
	{
		super();
		this.subject = "";
		this.salary = 0.0;
	}

	/**
	 * Costruttore completo con tutti i parametri.
	 *
	 * @param name Nome dell'insegnante
	 * @param surname Cognome dell'insegnante
	 * @param dateOfBirth Data di nascita dell'insegnante
	 * @param subject Materia insegnata
	 * @param salary Stipendio dell'insegnante
	 */
	public Teacher(String name, String surname, LocalDate dateOfBirth, String subject, double salary)
	{
		super(name, surname, dateOfBirth);
		this.subject = subject;
		this.salary = salary;
	}

	// Getter methods
	public String 	getSubject() 	{return subject != null ? subject : "UNKNOWN";}
	public double 	getSalary() 	{return salary;}

	// Setter methods
	public void 	setSubject(String subject) 	{this.subject = subject != null ? subject.trim() : "UNKNOWN";}
	public void 	setSalary(double salary) 	{this.salary = salary;}

	/**
	 * Override del metodo toString() della classe Person.
	 * Combina le informazioni di Person con quelle specifiche di Teacher.
	 *
	 * @return Stringa formattata con tutte le informazioni dell'insegnante
	 */
	@Override
	public String toString()
	{
		return super.toString() + " " +
						getSubject() + " " +
						getSalary();
	}

}
