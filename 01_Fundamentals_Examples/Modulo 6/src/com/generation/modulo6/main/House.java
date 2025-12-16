package com.generation.modulo6.main;

public class House 
{
	String	address;
	int		area;
	int		spm;
	
	public	House()
	{
		this.address = "";
		this.area = 0;
		this.spm = 0;
	}
	
	public int	getPrice() {
		return spm * area;
	}
	
	public String toString()
	{
		return "Indirizzo: " + address + ", Area: " + area + " MQ" + ", Prezzo: " + getPrice() + " EUR";
	}	
    
}
