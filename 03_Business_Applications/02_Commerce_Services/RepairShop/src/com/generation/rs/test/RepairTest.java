package com.generation.rs.test;

import org.junit.jupiter.api.Test;

import com.generation.rs.model.entities.Repair;

public class RepairTest
{
	@Test	
	void getExtimatedPrice()
	{
		Repair r = new Repair();
		r.client = "Ferdinando";
		r.fix = "Iphone con schermo rotto. Sostituito";
		r.materialPartsCost = 300;
		r.hour = 2;
		r.price = 350;
		
		double extimatedPrice = r.getExtimatedPrice();
		assert(extimatedPrice == 400);
	}
	
	@Test 
	void isValid()
	{
		Repair r = new Repair();
		r.client = "Ferdinando";
		r.fix = "Iphone con schermo rotto. Sostituito";
		r.materialPartsCost = 300;
		r.hour = 2;
		r.price = 350;
		
		boolean isValid = r.isValid();
		assert (isValid == false);
		
		r.phone = "3278149801";
		boolean isValid2 = r.isValid();
		assert(isValid2 == true);
	}
}
