package com.generation.modulo6.main;

public class Person
{
	String	name;
	String	surname;
	String	dateOfBirth;
	String	gender;
	
	
	public	Person() 
	{
		this.name = "";
		this.surname = "";
		this.dateOfBirth = "";
		this.gender = "";
	}
	
	public	String toString()
	{
		return name + " " + surname + " " + dateOfBirth + gender; 
	}
}
