package com.generation.mg.utility;

import com.generation.library.Console;

public class	MGUtility
{
	
	//METODO PER CHIEDERE NOME COMPLETO
	public static String	getName() {
		String	name;
		
		Console.print("What is your full name? ");
		name = Console.readString();
		return (name);
	}
	
	//METODO PER CHIEDERE ETA
	
	public static int	getAge() {
		int	age;
		
		Console.print("Your Age is: ");
		age = Console.readInt();
		while (age < 0) {
			Console.print("Insert an age between 0 and 100");
			age = Console.readInt();
		}
		return (age);
	}
	
	
	//METODO PER INCOME PER YEAR
	
	public static double	getRAL() {
		double	RAL;
		
		Console.print("What is you Income per Year? ");
		RAL = Console.readDouble();
		
		while (RAL < 0) {
			Console.print("Insert a valid RAL");
			RAL = Console.readInt();
		}
		return (RAL);
	}
	
	// METODO PER PRENDERE CLASSCHOICE
	
	public static int	getClassChoice() {
		
		int	classChoice;
		
		Console.print("Which class do you chose? ");
		classChoice = Console.readInt();
		
		while (classChoice != 1 && classChoice != 2 ) {
			Console.print("Choose a class between 1 and 2");
			classChoice = Console.readInt();
		}
		return (classChoice);
	}
	
	//METODO PER CALCOLARE COSTO DEL BIGLIETTO
	
	public static double	calculateCost(String fullName, int age, double RAL, int classChoice)
	{
		
		double		price = 0;
		int			firstClass = 1;
		int			secondClass = 2;
		int			costFirstClass = 5;
		int			costFirstClassReduced = 3;
		
		
		if (classChoice == firstClass) {
			if(age >= 70)
			{
				price = costFirstClassReduced;
			}
			else if (age < 70)
			{
				if (RAL < 50000) 
				{
					price = costFirstClass + 5;
				}
				else 
				{
					price += costFirstClass + 10;
				}
			}
			return price;
		}
		else if (classChoice == secondClass)
		{
			if(age >= 70)
			{
				price = 0;
			}
			else if (age < 70)
			{
				if (RAL < 50000) 
				{
					price = 5;
				}
				else 
				{
					price = 10;
				}
			}
			return price;
		}
		else
		{
			Console.print("Choose a class between 1 and 2");
			return 0;
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
