package com.generation.acmc2.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Donation è sottoclasse di Entità
 * Donation è entità figlia di Member (NON è ereditarietà, è POSSESSO)
 * Donation è collegata a UN SOLO MEMBRO
 */
public class Donation extends Entity
{
	protected LocalDate date;
	protected int amount;
	protected Member member; // COLLEGAMENTO A OGGETTO PADRE
	
	@Override
	public List<String> getErrors() 
	{
	    List<String> res = new ArrayList<String>();
	    
	    if(date == null)
	        res.add("Date is required");
	    
	    if(amount <= 0)
	        res.add("Amount must be greater than zero");
	    
	    if(member == null)
	        res.add("Member reference is missing");
	        
	    return res;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "Donation [date=" + date + ", amount=" + amount + ", member=" + member + "]";
	}
	
	

}
