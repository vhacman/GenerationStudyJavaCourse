package com.generation.oc.model.entities;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Patient extends Entity
{
    protected String              			firstName, lastName, ssn;
    protected LocalDate           		dob;
    protected List<Prestation>    	prestations = new ArrayList<>();

    public Patient()  { }

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
        if(isMissing(firstName))
			res.add("Missing first name");
        if(isMissing(lastName))
			res.add("Missing last name");
        if(isMissing(ssn))
			res.add("Missing SSN");
        if(dob == null)
			res.add("Missing date of birth");
        return res;
    }

    public String               				getFirstName()                              								{ return firstName; }
    public void                 				setFirstName(String firstName)              				{ this.firstName = firstName; }
    public String               				getLastName()                               								{ return lastName; }
    public void                 				setLastName(String lastName)        				        { this.lastName = lastName; }
    public String               				getSsn()                                               	 				        { return ssn; }
    public void                 				setSsn(String ssn)                         	 							{ this.ssn = ssn; }
    public LocalDate            			getDob()                                    									{ return dob; }
    public void                 				setDob(LocalDate dob)                       						{ this.dob = dob; }
    public void                 				setDob(String dob)                          							{ this.dob = LocalDate.parse(dob); }
    public List<Prestation>     	getPrestations()                            								{ return prestations; }
    public void                 				setPrestations(List<Prestation> prestations)		{ this.prestations = prestations; }

    @Override
	public String toString()
	{
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", ssn=" + ssn + ", dob=" + dob + "]";
	}
}
