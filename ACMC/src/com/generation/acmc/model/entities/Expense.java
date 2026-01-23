package com.generation.acmc.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

public class Expense extends Entity
{
	private String		reason;
	private LocalDate	date;
	private BigDecimal	cost;

	public Expense()
	{
	}

	public Expense(int id, String reason, LocalDate date, BigDecimal cost)
	{
		this.id = id;
		this.reason	= reason;
		this.date	= date;
		this.cost	= cost;
	}

	@Override
	public List<String> getErrors()
	{
		List<String> res = new ArrayList<String>();

		if(isMissing(reason))
			res.add("Missing reason");
		if(date == null)
			res.add("Missing date");
		if(date != null && date.isAfter(LocalDate.now()))
			res.add("Invalid date (future date)");
		if(cost == null)
			res.add("Missing cost");
		if(cost != null && cost.compareTo(BigDecimal.ZERO) < 0)
			res.add("Invalid cost (negative)");
		return res;
	}

	@Override
	public String toString()
	{
		return "Expense [reason=" + reason + ", date=" + date + ", cost=" + cost + "]";
	}

	public String		getReason()						{ return reason; }
	public void			setReason(String reason)		{ this.reason = reason; }
	
	public LocalDate	getDate()						{ return date; }
	public void			setDate(LocalDate date)			{ this.date = date; }
	public void			setDate(String date)			{ this.date = LocalDate.parse(date);}
								

	public BigDecimal	getCost()						{ return cost; }
	public void			setCost(BigDecimal cost)		{ this.cost = cost; }
	public void			setCost(String cost)			{ this.cost = new BigDecimal(cost); }

}
