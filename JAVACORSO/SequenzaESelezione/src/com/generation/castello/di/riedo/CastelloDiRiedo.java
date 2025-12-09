package com.generation.castello.di.riedo;
import com.generation.library.Console;

public class CastelloDiRiedo {

	public static void main(String[] args) {
		String 		city;
		int 		age;
		boolean		isProLocoMember;
		boolean 	isBloodDonor;
		int 		price = 10; // Prezzo pieno di partenza
		
		Console.print("=== CALCOLO PREZZO INGRESSO MUSEO ===");
		Console.print("Inserisci la tua città: ");
		city = Console.readString();
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Sei iscritto alla pro loco? (1=si, 0=no): ");
		isProLocoMember = Console.readInt() == 1;
		
		Console.print("Sei donatore di sangue? (1=si, 0=no): ");
		isBloodDonor = Console.readInt() == 1;
		
		// CONDIZIONE 1: Residenti di Milano o Monza pagano 5 euro
		// Usando lazy evaluation: se la prima è vera, la seconda non viene valutata
		if (city.equalsIgnoreCase("Milano") || city.equalsIgnoreCase("Monza"))
		{
			price = 5;
		}
		
		// CONDIZIONE 2: Junior (sotto 12) o Senior (sopra 65) pagano 6 euro
		// Usando lazy evaluation: se la prima è vera, la seconda non viene valutata
		else if (age < 12 || age > 65)
		{
			price = 6;
		}
		// Se nessuna delle precedenti: prezzo pieno 10 euro
		// Ma verranno applicati gli sconti cumulativi
		
		// SCONTI CUMULATIVI: -1 euro per pro loco E/O -1 euro per donatori
		// Usando lazy evaluation: se isProLocoMember è vero, bloodDonor non viene valutato
		// Ma in questo caso entrambi gli sconti possono applicarsi
		if (isProLocoMember)
		{
			price -= 1; // -1 euro per iscritti pro loco
		}
		
		if (isBloodDonor)
		{
			price -= 1; // -1 euro per donatori sangue
		}
		
		Console.print("Prezzo finale d'ingresso: " + price + " euro");
	}
}