package com.generation.acmc.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Member extends Entity
{
	private String			firstName, lastName, gender;
	private LocalDate		dob;
	private BigDecimal		incomeEst;
	private MembershipLevel	level;

	public Member() {}

	public Member(int id, String firstName, String lastName, String gender, LocalDate dob, BigDecimal incomeEst, MembershipLevel level)
	{
		this.id = id;
		this.firstName	= firstName;
		this.lastName	= lastName;
		this.gender		= gender;
		this.dob		= dob;
		this.incomeEst	= incomeEst;
		this.level		= level;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<>();

		if(isMissing(firstName))
		{
			res.add("Missing first name");
		}
		if(isMissing(lastName))
		{
			res.add("Missing last name");
		}
		if(isMissing(gender))
		{
			res.add("Missing gender");
		}
		if(dob == null)
		{
			res.add("Missing date of birth");
		}
		if(dob != null && dob.isAfter(LocalDate.now()))
		{
			res.add("Invalid date of birth (future date)");
		}
		if(incomeEst == null)
		{
			res.add("Missing income estimate");
		}
		if(incomeEst != null && incomeEst.compareTo(BigDecimal.ZERO) < 0)
		{
			res.add("Invalid income estimate (negative)");
		}
		if(level == null)
		{
			res.add("Missing membership level");
		}
		return res;
	}

	@Override
	public String toString()
	{
		return "Member [firstName=" + firstName + ", lastname=" + lastName + ", gender="
									+ gender + ", dob=" + dob + ", incomeEst=" + incomeEst
									+ ", level=" + level + "]";
	}

	// getId() ereditato da Entity
	public String			getFirstName()							{ return firstName; }
	public String			getLastName()							{ return lastName; }
	public String			getGender()								{ return gender; }
	public LocalDate		getDob()								{ return dob; }
	public BigDecimal		getIncomeEst()							{ return incomeEst; }
	public MembershipLevel	getLevel()								{ return level; }

	public void				setLastName(String lastname)			{ this.lastName = lastname; }
	public void				setFirstName(String firstName)			{ this.firstName = firstName; }
	public void				setGender(String gender)				{ this.gender = gender; }
	public void				setDob(LocalDate dob)					{ this.dob = dob; }
	public void				setDob(String dob)						{ this.dob = LocalDate.parse(dob); }
	public void				setIncomeEst(BigDecimal incomeEst)		{ this.incomeEst = incomeEst; }
	public void				setIncomeEst(String incomeEst)			{ this.incomeEst = new BigDecimal(incomeEst); }
	public void				setLevel(MembershipLevel level)			{ this.level = level; }
	public void				setLevel(String level)					{ this.level = MembershipLevel.valueOf(level); }


}
