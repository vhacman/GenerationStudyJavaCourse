package com.generation.acmc2.model.entities;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

class MemberTest {

	/**
	 * Voglio sapere quanto ha versato un dato membro
	 * in tutta la sua storia
	 */
	@Test
	void testCollegamento1() 
	{
		Member m = new Member();
		m.firstName = "Veronica";
		Donation d = new Donation();
		
		d.date = LocalDate.now();
		d.amount = 1000;
		
		assert(d.member==null);		
		// creo il collegamento dall'oggetto figlio d all'oggetto padre m
		d.member = m;
		System.out.println(d.member.firstName);
		assert(d.member.firstName.equals("Veronica"));

		// collegamento padre figli
		m.donations.add(d);
		
		assert(m.donations.size()==1);
	}

	@Test
	void testCollegamento2()
	{
		Member m = new Member();
		m.firstName = "Veronica";
		Donation d1 = new Donation();
		d1.amount = 1000;
		d1.date = LocalDate.now();
		
		Donation d2 = new Donation();
		d2.amount = 10000;
		d2.date = LocalDate.now();
		
		// Luke, sono tuo padre
		d1.member = m;
		d2.member = m;
		
		
		m.donations.add(d1);
		m.donations.add(d2);
		
		// dal figlio al padre
		assert(d1.member.firstName.equals("Veronica"));
		assert(m.donations.get(1).amount==10000);
		
		
	}
	
	
	@Test
	void testCollegamento3()
	{
		Member m = new Member();
		m.firstName = "Veronica";
		Donation d1 = new Donation();
		d1.amount = 1000;
		d1.date = LocalDate.now();
		Donation d2 = new Donation();
		d2.amount = 10000;
		d2.date = LocalDate.now();

		// è qualcosa di simile a un setter o a un getter
		// è scrittura intelligente
		m.addDonation(d1);
		m.addDonation(d2);
		
		assert(d1.member.firstName.equals("Veronica"));
		assert(m.donations.get(1).amount==10000);
		
	}
	
	@Test
	void testTotalDonation()
	{
		Member m = new Member();
		m.firstName = "Veronica";
		Donation d1 = new Donation();
		d1.amount = 1000;
		d1.date = LocalDate.now();
		Donation d2 = new Donation();
		d2.amount = 10000;
		d2.date = LocalDate.now();

		// è qualcosa di simile a un setter o a un getter
		// è scrittura intelligente
		m.addDonation(d1);
		m.addDonation(d2);
		
		assert(m.getTotalDonations()==11000);
		
	}
	
	@Test
	void testDonationsLastYear()
	{
		Member m = new Member();
		m.firstName = "Veronica";
		Donation d1 = new Donation();
		d1.amount = 1000;
		d1.date = LocalDate.of(1980, 1, 1);
		Donation d2 = new Donation();
		d2.amount = 10000;
		d2.date = LocalDate.now();

		// è qualcosa di simile a un setter o a un getter
		// è scrittura intelligente
		m.addDonation(d1);
		m.addDonation(d2);
		// donazioni degli ultimi 365 giorni
		assert(m.getLastYearDonations()==10000);
		
	}
	
	
	
}
