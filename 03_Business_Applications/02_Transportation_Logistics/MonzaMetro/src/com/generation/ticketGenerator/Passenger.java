package com.generation.ticketGenerator;

public class Passenger
{
	private	String	name;
	private	String	surname;
	private	int		age;

	//costruttore parametrico
	
	public	Passenger() {};
	
	public Passenger(String name, String surname, int age)
	{
		this.name = name;
		this.surname = surname;
		this.age = age;
	}
	
	// Getter per name
	public String getName() {
		return name;
	}

	// Setter per name
	public void setName(String name) {
		this.name = name;
	}

	// Getter per surname
	public String getSurname() {
		return surname;
	}

	// Setter per surname
	public void setSurname(String surname) {
		this.surname = surname;
	}

	// Getter per age
	public int getAge() {
		return age;
	}

	// Setter per age
	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString()
	{
		return "Passeggero: " + name + " " + surname + " (Età: " + age + ")";
	}
}