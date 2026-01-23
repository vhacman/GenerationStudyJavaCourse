package com.generation.acmc.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Donation extends Entity
{
	private Member		member;
	private BigDecimal	amount;
	private LocalDate	date;
	private String		notes;

	public Donation()
	{
	}

	public Donation(int id, Member member, BigDecimal amount, LocalDate date, String notes)
	{
		this.id = id;
		this.member	= member;
		this.amount	= amount;
		this.date	= date;
		this.notes	= notes;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<String>();

		if(member == null)
			res.add("Missing donor");
		if(amount == null)
			res.add("Missing amount");
		if(amount != null && amount.compareTo(BigDecimal.ZERO) <= 0)
			res.add("Invalid amount (must be positive)");
		if(date == null)
			res.add("Missing date");
		if(date != null && date.isAfter(LocalDate.now()))
			res.add("Invalid date (future date)");
		return res;
	}

	@Override
	public String toString()
	{
		return "Donation [donor=" + member + ", amount=" + amount + ", date=" + date
									+ ", notes=" + notes + "]";
	}

	public Member		getMember()						{ return member; }
	public void			setMember(Member donor)			{ this.member = donor; }
	
	public BigDecimal	getAmount()						{ return amount; }
	public void			setAmount(BigDecimal amount)	{ this.amount = amount; }
	public void			setAmount(String amount)		{ this.amount = new BigDecimal(amount); }

	public LocalDate	getDate()						{ return date; }
	public void			setDate(LocalDate date)			{ this.date = date; }
	public void			setDate(String date)			{ this.date = LocalDate.parse(date); }

	public String		getNotes()						{ return notes; }
	public void			setNotes(String notes)			{ this.notes = notes; }
}
