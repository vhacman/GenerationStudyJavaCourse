package com.generation.school;

public class Student extends Person
{
	protected int			year;
	protected String		section;
	protected double		average;
	protected boolean		hasFailingGrades;

	public Student() {
		this.year = 0;
		this.section = "";
		this.average = 0.0;
		this.hasFailingGrades = false;
	}

	public Student(String name, String surname, String dateOfBirth, String gender, int year, String section)
	{
	    super(name, surname, dateOfBirth, gender);
	    this.year = year;
	    this.section = section;
	    this.average = 0.0;
	    this.hasFailingGrades = false;
	}

	public Student(String name, String surname, String dateOfBirth, String gender, int year, String section, double average, boolean hasFailingGrades)
	{
	    super(name, surname, dateOfBirth, gender);
	    this.year = year;
	    this.section = section;
	    this.average = average;
	    this.hasFailingGrades = hasFailingGrades;
	}

	public int 		getYear() {return year;}
	public String 	getSection() {return section == null ? "UNKNOWN" : section;}
	public double 	getAverage() {return average;}
	public boolean 	hasFailingGrades() {return hasFailingGrades;}
	public void 	setYear(int year) {this.year = year;}
	public void 	setSection(String section) { this.section = section == null ? "UNKNOWN" : section;}
	public void 	setAverage(double average) {this.average = average;}
	public void 	setHasFailingGrades(boolean hasFailingGrades) {this.hasFailingGrades = hasFailingGrades;}
	
	@Override
	public int getCost()
	{
		int scholarship = 0;
		int canteenCost = 2000;

		// Borsa di studio: 1000 euro se media > 8 e senza insufficienze
		if (average > 8 && !hasFailingGrades) {
			scholarship = 1000;
		}

		// Il costo è la mensa meno la borsa di studio (se ne ha diritto)
		return canteenCost - scholarship;
	}

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
