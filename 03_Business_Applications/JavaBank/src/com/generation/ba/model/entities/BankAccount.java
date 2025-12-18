package com.generation.ba.model.entities;

import java.math.BigDecimal;

//PER I DATI ECONOMICI VIETATO DOUBLE
//BigDecimal
public class BankAccount
{
	private	long		id;
	private BigDecimal	balance;
	private Client		client;
	

	public BankAccount() {}
	
	public BankAccount(long id, BigDecimal balance, Client client) {
		this.id = id;
		this.balance = balance;
		this.client = client;
	}
	
	public Client getClient() {
		return client;
	}
	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(String balanceValue) {
		this.balance = new BigDecimal(balanceValue);
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void deposit(int euro, int cents) {
		String formatter = euro + "." + cents;
		BigDecimal toAdd = new BigDecimal(formatter);
		this.balance = this.balance.add(toAdd);
	}

	public void withdrawl(int euro, int cents) {
		String formatter = euro + "." + cents;
		BigDecimal toSubtract = new BigDecimal(formatter);
		this.balance = this.balance.subtract(toSubtract);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
