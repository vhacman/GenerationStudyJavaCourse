package com.generation.pl.model.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * per le tredici
 * Student e StudentRepository pronti
 * ma non chiedo update... ok
 * solo insert e login
 */
public class Student extends User
{
	private static final int 				MIN_AGE = 14;
	protected LocalDate 					dob;
	protected List<Lesson>					lessons = new ArrayList<Lesson>();

	public int getAge()
	{
		if(dob == null)
			return 0;
		return Period.between(dob, LocalDate.now()).getYears();
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = super.getErrors();

		if(dob == null)
			res.add("Date of birth is required");
		else if(getAge() < MIN_AGE)
			res.add("Student must be at least " + MIN_AGE + " years old");
		return res;
	}

	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(dob, lessons);
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		return Objects.equals(dob, other.dob) && Objects.equals(lessons, other.lessons);
	}
	

	@Override
	public String toString()
	{
		return "Student [dob=" + dob + ", lessons=" + lessons + "]";
	}

	public LocalDate		getDob()							{ return dob; 								}
	public void				setDob(LocalDate dob)				{ this.dob = dob; 							}
	public void				setDob(String dobString)			{ this.dob = LocalDate.parse(dobString); 	}

	public List<Lesson>		getLessons()						{ return lessons; 							}
	public void				setLessons(List<Lesson> lessons)	{ this.lessons = lessons; 					}

}
