package com.generation.school;

public class Support extends Employee
{
	protected String	occupation;

	public Support()
	{
		this.occupation = "";
	}

	public Support(String name, String surname, String dateOfBirth, String gender,
			   double salary, String occupation)
	{
		super(name, surname, dateOfBirth, gender, salary);
		this.occupation = occupation;
	}

	public String 	getOccupation() {return occupation == null ? "UNKNOWN" : occupation;}
	public void 	setOccupation(String occupation) {this.occupation = occupation == null ? "UNKNOWN" : occupation;}

	@Override
	public double getYearlyRetribution()
	{
		return salary * 14;
	}

	@Override
	public String toString()
	{
		return super.toString() + " Occupation: " +
				getOccupation();
	}
}
