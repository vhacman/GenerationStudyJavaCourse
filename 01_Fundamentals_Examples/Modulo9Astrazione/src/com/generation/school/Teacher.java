package com.generation.school;

public class Teacher extends Employee
{
	protected String	subject;
	protected int		yearsOfExperience;

	public Teacher()
	{
		this.subject = "";
		this.yearsOfExperience = 0;
	}

	public Teacher(String name, String surname, String dateOfBirth, String gender,
			   double salary, String subject, int yearsOfExperience)
	{
		super(name, surname, dateOfBirth, gender, salary);
		this.subject = subject;
		this.yearsOfExperience = yearsOfExperience;
	}

	public String 	getSubject() {return subject == null ? "UNKNOWN" : subject;}
	public int 		getYearsOfExperience() {return yearsOfExperience;}
	public void 	setSubject(String subject) {this.subject = subject == null ? "UNKNOWN" : subject;}
	public void 	setYearsOfExperience(int yearsOfExperience) {this.yearsOfExperience = yearsOfExperience;}

	@Override
	public double getYearlyRetribution()
	{
		return salary * 12;
	}

	@Override
	public String toString()
	{
		return super.toString() + " Subject: " +
				getSubject() + " Experience: " +
				yearsOfExperience + " years";
	}
}
