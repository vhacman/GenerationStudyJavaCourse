package com.generation.lessons;

public class Student extends Person
{
	protected int			year;
	protected String		section;

	public Student() {
		this.year = 0;
		this.section = "";
	}

	public Student(String name, String surname, String dateOfBirth, String gender, int year, String section)
	{
	    super(name, surname, dateOfBirth, gender);
	    this.year = year;
	    this.section = section;
	}

	public int 		getYear() {return year;}
	public String 	getSection() {return section == null ? "UNKNOWN" : section;}
	public void 	setYear(int year) {this.year = year;}
	public void 	setSection(String section) { this.section = section == null ? "UNKNOWN" : section;}
	
	@Override
	public String toString()
	{
		return getName() + " " + 
				getSurname() + " " + 
				getDateOfBirth() + " " + 
				getGender() + " Year: " + year + " Section: " + 
				getSection();
	}
}
