package com.generation.m11.lesson;

import java.time.LocalDate;
import java.time.Period;

public class Person
{

	// Properties (private fields)
	private String name;
	private String surname;
	private LocalDate dateOfBirth;
	private	String	gender;

	// Default constructor
	public Person()
	{
		this.name = "";
		this.surname = "";
		this.dateOfBirth = null;
	}


	// Full constructor with all parameters
	public Person(String name, String surname, String dateOfBirth, String gender)
	{
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = LocalDate.parse(dateOfBirth);
		this.gender = gender;
	}
	
	// Full constructor with all parameters
	public Person(String name, String surname, LocalDate dateOfBirth)
	{
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
	}

	// Getter methods with null-handling
	public String 		getName() 			{return name != null ? name : "UNKNOWN";}
	public String		getSurname() 		{return surname != null ? surname : "UNKNOWN";}
	public LocalDate 	getDateOfBirth() 	{return dateOfBirth;}

	// Setter methods with null-handling
	public void setName(String name) 					{this.name = name != null ? name.trim() : "UNKNOWN";}
	public void setSurname(String surname) 				{this.surname = surname != null ? surname.trim() : "UNKNOWN";}
	public void setDateOfBirth(LocalDate dateOfBirth) 	{this.dateOfBirth = dateOfBirth;}

	/**
	 * Calcola l'età della persona basandosi sulla data di nascita.
	 * Utilizza LocalDate.now() per ottenere la data corrente e Period per calcolare la differenza.
	 *
	 * @return L'età in anni, o -1 se la data di nascita non è impostata
	 */
	public int getAge()
	{
		if (dateOfBirth == null)
			return -1;

		LocalDate today = LocalDate.now();
		Period period = Period.between(dateOfBirth, today);
		return period.getYears();
	}

	@Override
	public String toString()
	{
		String date = dateOfBirth != null ? dateOfBirth.toString() : "UNKNOWN";
		return getName() + " " + getSurname() + " " + date;
	}


	public String getGender()
	{
		return gender;
	}


	public void setGender(String gender)
	{
		this.gender = gender;
	}

}
