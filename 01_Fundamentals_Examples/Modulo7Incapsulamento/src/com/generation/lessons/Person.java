package com.generation.lessons;

public class Person
{
	private String	name;
	private String	surname;
	private String	dateOfBirth;
	private String	gender;
	
	
	public String getName()
	{
		return name == null ? "UNKNOWN" : name;
	}
	
	public void setName(String name) {
		this.name = name == null ? "UNKNOWN" : name;
	}

	public String getSurname() {
		return surname == null ? "UNKNOWN" : surname;
	}

	public void setSurname(String surname) {
		this.surname = surname == null ? "UNKNOWN" : surname;
	}

	public String getDateOfBirth() {
		return dateOfBirth == null ? "UNKNOWN" : dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth == null ? "UNKNOWN" : dateOfBirth;
	}

	public String getGender() {
		return gender == null ? "UNKNOWN" : gender;
	}

	public void setGender(String gender) {
		this.gender = gender == null ? "UNKNOWN" : gender;
	}

	public	Person() 
	{
		this.name = "";
		this.surname = "";
		this.dateOfBirth = "";
		this.gender = "";
	}
	
	public Person (String name, String surname, String dateOfBirth, String gender) {
		this.name = name;
		this.surname = surname;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender; 
	}
	
	public String toString()
	{
		return getName() + " " + getSurname() + " " + getDateOfBirth() + " " + getGender(); 
	}
}