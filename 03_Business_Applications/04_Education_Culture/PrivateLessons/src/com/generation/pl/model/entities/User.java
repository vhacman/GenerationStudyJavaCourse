package com.generation.pl.model.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.generation.library.Entity;

/**
 * Cosa è uno User?
 * Chiunque si logghi nel sistema.
 * Ogni utente avrà un ruolo diverso e privilegi diversi.
 */
public class User extends Entity
{
	protected String		firstName;
	protected String		lastName;
	protected String		ssn;
	protected String		email;
	protected String		password;
	protected UserStatus	status;

	@Override
	public int hashCode()
	{
		return Objects.hash(ssn);
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return this.ssn.equals(other.ssn) || this.id == other.id;
	}

	@Override
	public List<String> 		getErrors()
	{
	    List<String> res = new ArrayList<String>();

	    if(isMissing(firstName))	res.add("First name is required");
	    if(isMissing(lastName))		res.add("Last name is required");
	    if(isMissing(ssn))			res.add("SSN is required");
	    if(isMissing(email))		res.add("Email address is required");
	    if(isMissing(password))		res.add("Password is required");
	    if(status == null)			res.add("Status is required");

	    return res;
	}

	public UserStatus	getStatus()							{ return status; }
	public String		getFirstName()						{ return firstName; }
	public String		getLastName()						{ return lastName; }
	public String		getSsn()							{ return ssn; }
	public String		getEmail()							{ return email; }
	public String		getPassword()						{ return password; }

	public void			setStatus(String status)			{ this.status = UserStatus.valueOf(status); }
	public void			setStatus(UserStatus status)		{ this.status = status; }
	public void			setFirstName(String firstName)		{ this.firstName = firstName; }
	public void			setLastName(String lastName)		{ this.lastName = lastName; }
	public void			setSsn(String ssn)					{ this.ssn = ssn; }
	public void			setEmail(String email)				{ this.email = email; }
	public void			setPassword(String password)		{ this.password = password; }
}