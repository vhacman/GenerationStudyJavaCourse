package com.generation.ternary;

import com.generation.library.Console;

public class TernaryChoice {

	public static void main(String[] args) {
		int choice;
		
		Console.print("=== MENU PRINCIPALE ===");
		Console.print("1. Controllo Età (Maggiorenne)");
		Console.print("2. Controllo Età e Statura");
		Console.print("3. Controllo Età e Reddito Familiare");
		Console.print("Scegli un'opzione (1-3): ");
		choice = Console.readInt();
		
		switch(choice) {
			case 1:
				esercizio7();
				break;
			case 2:
				esercizio8();
				break;
			case 3:
				esercizio9();
				break;
			default:
				Console.print("Opzione non valida!");
		}
	}
	
	// Esercizio #7 - Controllo maggioranza d'età
	
	//final --> serve per rendere una variabile immutabile
	public static void esercizio7()
	{
		int age;
		final int ADULT_AGE = 18;
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		// Uso dell'operatore ternario per stampare il messaggio appropriato
		String message = (age >= ADULT_AGE) ? "Ingresso consentito" : "Ingresso vietato";
		Console.print(message);
	}
	
	// Esercizio #8 - Controllo età e statura
	public static void esercizio8()
	{
		int age;
		int height;
		final int MIN_AGE = 13;
		final int MIN_HEIGHT = 120;
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Inserisci la tua statura (cm): ");
		height = Console.readInt();
		
		// Uso dell'operatore ternario con condizione composta (AND)
		String message = (age > MIN_AGE && height >= MIN_HEIGHT) 
			? "Ingresso consentito" 
			: "Ingresso vietato";
		Console.print(message);
	}
	
	// Esercizio #9 - Controllo età e reddito familiare
	public static void esercizio9()
	{
		int			age;
		int 		familyIncome;
		final int 	MIN_AGE = 13;
		final int 	MIN_INCOME = 20000;
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Inserisci il reddito familiare annuo (€): ");
		familyIncome = Console.readInt();
		
		// Uso dell'operatore ternario con condizione composta (OR)
		String message = (age < MIN_AGE || familyIncome < MIN_INCOME) 
			? "Abbonamento richiesto 15 euro al mese" 
			: "Abbonamento richiesto 50 euro al mese";
		Console.print(message);
	}
}