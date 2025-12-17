package com.generation.rs.demo;

import com.generation.library.Console;
import com.generation.rs.model.entities.Repair;

public class DemoRepair
{

	public static void main(String[] args)
	{
		 
		Repair r = new Repair();
		r.client = "Ferdinando";
		r.fix = "Iphone con schermo rotto. Sostituito";
		r.materialPartsCost = 300;
		r.hour = 2;
		r.price = 350;
		
		
		Console.print("Costo stimato " + r.getExtimatedPrice());
		Console.print("Costo finale " + r.price);
	}
}
