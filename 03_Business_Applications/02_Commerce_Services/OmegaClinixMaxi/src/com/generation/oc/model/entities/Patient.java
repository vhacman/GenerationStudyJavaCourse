package com.generation.oc.model.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import com.generation.library.Entity;

public class Patient extends Entity
{
	protected String				firstName, lastName, ssn;
	protected LocalDate				dob;
	protected List<Prestation>		prestations = new ArrayList<>();

	public Patient() {}

	public Patient(String firstName, String lastName, String ssn, LocalDate dob)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.dob = dob;
		this.prestations = new ArrayList<>();
	}

	public int getExpenses(int year)
	{
		int total = 0;
		for (Prestation performance : prestations)
			if(performance.getDate().getYear() == year)
				total += performance.getPrice();
		return total;
	}

	public void addPrestations(Prestation p)
	{
		p.patient = this;
		prestations.add(p);
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<>();
		if(isMissing(firstName))	res.add("Missing first name");
		if(isMissing(lastName))		res.add("Missing last name");
		if(isMissing(ssn))			res.add("Missing SSN");
		if(dob == null)				res.add("Missing date of birth");
		return res;
	}

	@Override
	public String toString()
	{
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", ssn=" + ssn + ", dob=" + dob + "]";
	}

	public String getFullname()
	{
		return firstName+" "+lastName;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)					return true;
		if (obj == null)					return false;
		if (getClass() != obj.getClass())	return false;
		Patient other = (Patient) obj;
		return Objects.equals(dob, other.dob)
			&& Objects.equals(firstName, other.firstName)
			&& Objects.equals(lastName, other.lastName)
			&& Objects.equals(prestations, other.prestations)
			&& Objects.equals(ssn, other.ssn);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(dob, firstName, lastName, prestations, ssn);
	}

	public String				getFirstName()								{ return firstName; }
	public String				getLastName()								{ return lastName; }
	public String				getSsn()									{ return ssn; }
	public LocalDate			getDob()									{ return dob; }
	public List<Prestation>		getPrestations()							{ return prestations; }
	public void					setFirstName(String firstName)				{ this.firstName = firstName; }
	public void					setLastName(String lastName)				{ this.lastName = lastName; }
	public void					setSsn(String ssn)							{ this.ssn = ssn; }
	public void					setDob(LocalDate dob)						{ this.dob = dob; }
	public void					setDob(String dob)							{ this.dob = LocalDate.parse(dob); }
	public void					setPrestations(List<Prestation> prestations){ this.prestations = prestations; }
}
