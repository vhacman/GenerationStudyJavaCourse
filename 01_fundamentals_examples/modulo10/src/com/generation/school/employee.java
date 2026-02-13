package com.generation.school;

public abstract class Employee extends Person
{
	protected double	salary;

	public Employee()
	{
		this.salary = 0.0;
	}

	public Employee(String name, String surname, String dateOfBirth, String gender, double salary)
	{
		//super(name, surname, dateOfBirth, gender);
		setName(name);
		setSurname(surname);
		setDateOfBirth(dateOfBirth);
		setGender(gender);
		this.salary = salary;
	}

	public double 	getSalary() 				{return salary;}
	public void 	setSalary(double salary) 	{this.salary = salary;}

	@Override
	public String toString()
	{
		return getName() + " " +
				getSurname() + " " +
				getDateOfBirth() + " " +
				getGender() + " Salary: " + salary;
	}

	public abstract double getYearlyRetribution();

	@Override
	public int getCost()
	{
		return (int)(getYearlyRetribution() * 2);
	}

	@Override
	public boolean equals(Object obj)
	{
		// Verifica se è lo stesso oggetto (stesso riferimento in memoria)
		if (this == obj)
			return true;

		// Verifica se l'oggetto è null
		if (obj == null)
			return false;

		// Verifica se l'oggetto è della stessa classe
		if (getClass() != obj.getClass())
			return false;

		// Cast dell'oggetto a Employee
		Employee other = (Employee) obj;

		// Confronta tutte le proprietà di Employee
		// (incluse quelle ereditate da Person)
		return this.getName().equals(other.getName()) &&
				this.getSurname().equals(other.getSurname()) &&
				this.getDateOfBirth().equals(other.getDateOfBirth()) &&
				this.getGender().equals(other.getGender()) &&
				this.salary == other.salary;
	}
}
