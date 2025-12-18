package com.generation.bank;

public class BankAccount
{
	/*
	 * INCAPSULAMENTO (Encapsulation)
	 * 
	 * L'incapsulamento è uno dei principi fondamentali della programmazione orientata agli oggetti (OOP).
	 * Consiste nel nascondere i dettagli interni di una classe e proteggere i dati da accessi non controllati.
	 * 
	 * Vantaggi dell'incapsulamento:
	 * 1. PROTEZIONE DEI DATI: I campi privati non possono essere modificati direttamente dall'esterno
	 * 2. CONTROLLO: Possiamo validare i dati prima di modificarli (nei setter)
	 * 3. MANUTENIBILITÀ: Possiamo cambiare l'implementazione interna senza influenzare il codice esterno
	 * 4. FLESSIBILITÀ: Possiamo aggiungere logica ai getter/setter senza modificare l'interfaccia pubblica
	 * 
	 * Come si implementa:
	 * - Dichiarare i campi come PRIVATE
	 * - Fornire metodi pubblici (getter/setter) per accedere e modificare i dati
	 */
	private double	balance;
	private int		clientAge;

	public BankAccount() {}
	
	public BankAccount(int balance) {this.balance = balance; this.clientAge = 0;}
	
	public double getBalance() {return balance;}

	public void setBalance(double balance){this.balance = balance;}

	public int getClientAge() {return clientAge;}

	public boolean setClientAge(int clientAge)
	{
		if (clientAge >= 18)
		{
			this.clientAge = clientAge;
			return true;
		}
		return false;
	}
	
	
}
