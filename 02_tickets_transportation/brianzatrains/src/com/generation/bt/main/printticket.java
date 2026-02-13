package com.generation.bt.main;

import com.generation.library.Console;

public class PrintTicket 
{

	public static void main(String[] args) 
	{
		int 		km, level;												
		String		name, surname;
		int			age;
		int			hourStart, minuteStart; 
		int			hourArrival, minuteArrival;
		
		// output
		boolean 	valid;												
		double 		price, pricePerKm;											
		String		label;
		
		Console.print("Inserire km");
		km = Console.readInt();										
		Console.print("Inserire classe (1 o 2)");
		level = Console.readInt();									
		
		Console.print("Inserire il nome del cliente ");
		name = Console.readString(); // prendere input da console.
		Console.print("Inserire il cognome del cliente ");
		surname = Console.readString(); // prendere input da console.
		
		Console.print("Inserire età del cliente ");
		age = Console.readInt(); // prendere input da console.
		
		Console.print("Ora di partenza: ");
		hourStart = Console.readInt();
		minuteStart = Console.readInt();
		
		Console.print("Ora di arrivo: ");
		hourArrival = 	Console.readInt();
		minuteArrival = Console.readInt();
		
		valid = 	km > 0 		&& 		level>=1 		&& 		level<=2;	
		if(valid) {
			if(level==1) {
				pricePerKm = 0.2;	 
			}
			else {
				pricePerKm = 0.15;
			}
			price = pricePerKm * km;
			if(price<1.7) {
				price = 1.7;
			}
			
			label = "═══════════════════════════════════\n" +
			        "  🚂 BIGLIETTO FERROVIARIO 🚂\n" +
			        "═══════════════════════════════════\n" +
			        "\n" +
			        "👤 Passeggero: " + name + " " + surname + "\n" +
			        "   Età: " + age + " anni\n" +
			        "\n" +
			        "🕐 Partenza: " + hourStart + ":" + minuteStart + "\n" +
			        "   Arrivo:   " + hourArrival + ":" + minuteArrival + "\n" +
			        "\n" +
			        "💵 Totale: €" + price + "\n" +
			        "\n" +
			        "═══════════════════════════════════\n";
			Console.print(label);
		}
		else {
			Console.print("Errore: dati non validi");		
		}
	}
}
