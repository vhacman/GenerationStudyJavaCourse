package com.generation.school.model.entities;

import java.time.LocalDate;

public class Student extends Person
{

	// Properties (private fields)
	private int year;
	private String section;

	// Default constructor
	public Student()
	{
		super();
		this.year = 0;
		this.section = "";
	}

	// Full constructor with all parameters
	public Student(String name, String surname, LocalDate dateOfBirth, int year, String section)
	{
		super(name, surname, dateOfBirth);
		this.year = year;
		this.section = section;
	}

	// Getter methods
	public int			getYear()					{return year;}
	public String 		getSection()				{return section != null ? section : "UNKNOWN";}
	// Setter methods
	public void 		setYear(int year) 			{this.year = year;}
	public void 		setSection(String section) 	{this.section = section != null ? section.trim() : "UNKNOWN";}
	@Override
	public String toString(){
		return super.toString() + " " +
						getYear() + " " +
						getSection();
	}
}
