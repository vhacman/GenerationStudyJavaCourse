package com.generation.jia.model.entities;

public enum MembershipType
{
	NONE(0),
	SILVER(0.2),
	GOLD(0.3);
	
	public double discount;
	
	private MembershipType(double discount)
	{
		this.discount = discount;		
	}

}
