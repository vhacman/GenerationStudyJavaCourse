package com.generation.acmc2.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/**
 * Un membro è una entità
 * un Member -> n Donation
 * Un Member contiene una LISTA di Donation
 * Un Member è COMPOSTO anche da n Donation
 * Un Member è PADRE di n Donation
 * Member è detto entità padre o entità lato 1
 * Donation è detta entità figlia o entità lato n
 * SI PARLA DI HAS, NON DI IS. Non è ereditarietarietà
 */
public class Member extends Entity
{
	protected String firstName, lastName, ssn;
	protected LocalDate dob;
	protected MemberStatus status;
	protected List<Donation> donations = new ArrayList<Donation>();

	/**
	 * Arriva una nuova donation
	 * la riconosco come mia
	 * collego lei a me
	 * collego me a lei
	 * ho visto lei che bacia lui che bacia lei
	 * collegamento BIDIREZIONALE
	 * @param d
	 */
	public void addDonation(Donation d)
	{
		// collegamento fra figlio (d) e padre (this)
		// dico alla donazione "Luke sono tuo padre"
		d.member = this; // this sono io stesso, il member
		// io per lui sono l'unico padre ma lui per me è solo un figlio
		// lo aggiungo alla mia lista di figli
		donations.add(d);
	}
		
	public String getFirstName() 
	{
		return firstName;
	}

	public void setFirstName(String firstName) 
	{
		this.firstName = firstName;
	}

	public String getLastName() 
	{
		return lastName;
	}

	public void setLastName(String lastName) 
	{
		this.lastName = lastName;
	}

	public String getSsn() 
	{
		return ssn;
	}

	public void setSsn(String ssn) 
	{
		this.ssn = ssn;
	}


	public LocalDate getDob() 
	{
		return dob;
	}

	public void setDob(LocalDate dob) 
	{
		this.dob = dob;
	}

	public MemberStatus getStatus() 
	{
		return status;
	}

	public void setStatus(MemberStatus status) 
	{
		this.status = status;
	}

	public List<Donation> getDonations() 
	{
		return donations;
	}

	public void setDonations(List<Donation> donations) 
	{
		this.donations = donations;
	}
	
	/**
	 * Totale donato in tutta la storia del cliente
	 * riduzione: da List<Donation> -> int
	 * @return
	 */
	public int getTotalDonations()
	{
		int res = 0;
		// questa lista donations sono le MIE DONATIONS
		// non sono tutte le donations del DB!
		// sono solo mie
		// mieh
		// tessoorroooooh.
		for(Donation d:donations)
			res+=d.amount;
		return res;
	}

	public int getLastYearDonations()
	{
		LocalDate oneYearAgo = LocalDate.now().minusYears(1);
		int total = 0;
		for (Donation d : donations)
		if((d.date != null && d.date.isEqual(oneYearAgo) ) || d.date.isAfter(oneYearAgo))
		total += d.amount;
		return total;
	}
	
	@Override
	public List<String> getErrors() 
	{
	    List<String> res = new ArrayList<String>();
	    
	    // Controlliamo semplicemente che le stringhe non siano vuote o null
	    if(isMissing(firstName))
	        res.add("First name is required");
	    
	    if(isMissing(lastName))
	        res.add("Last name is required");
	    
	    if(isMissing(ssn))
	        res.add("SSN is required");
	    
	    // Controllo che la data sia stata inserita
	    if(dob == null)
	        res.add("Date of birth is required");
	    
	    // Controllo che lo stato sia stato selezionato
	    if(status == null)
	        res.add("Member status is required");
	    
	    return res;
	}
	
	/**
	 * Calcolo il mio status a partire dalle donazioni reali
	 * dell'ultimo anno
	 * @return
	 */
	public MemberStatus getCalculatedStatus()
	{
		return MemberStatus.getByThreshold(getLastYearDonations());
	}
	

}
