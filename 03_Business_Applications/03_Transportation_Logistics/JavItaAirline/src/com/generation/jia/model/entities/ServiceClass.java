package com.generation.jia.model.entities;

public enum ServiceClass
{
	BASIC(0.1),
	SILVER(0.2),
	GOLD(0.5);
	
	public double pricePerKm;
	
	private ServiceClass(double pricePerKm)
	{
		this.pricePerKm = pricePerKm;
	}

}
