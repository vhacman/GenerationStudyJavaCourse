package com.generatio.bt.main;

import com.generation.library.Console;

public class DiscotecaTicket {

	public static void generateTicket()
	{
		int 	age;
		char	gender;
		String	message;
		
	
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
	
		Console.print("Inserisci il tuo genere (M/F): ");
		gender = Console.readString().charAt(0);
	
		if (age > 17)
		{
			message = (gender == 'M' || gender == 'm')
				? "Costo biglietto 18 euro"
				: "Costo biglietto 10 euro";
			Console.print(message);
		}
		else
		{
			Console.print("Ingresso vietato");
		}
	}
}
