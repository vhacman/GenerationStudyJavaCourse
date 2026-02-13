package com.generation.checker;

import com.generation.library.Console;

public class AgeAndRALChecker {

	/*
	 * Scrivere un programma che richieda all’utente l’età e il reddito familiare annuo. Nel caso in cui
	l’utente avesse meno di 13 anni o un reddito familiare inferiore ai 20000 euro stampare
	“Abbonamento richiesto 15 euro al mese”, in caso contrario “Abbonamento richiesto 50 euro al
	mese”.
	 */
	public static void AgeAndRal() {
		int	RAL;
		int	age;
		
		int	RALMAX = 20000;
		int	MINAGE = 13;
		
		Console.print("Inserire età; ");
		age = Console.readInt();
		Console.print("Inserire RAL");
		RAL = Console.readInt();
		
		if (age < 13 || RAL < RALMAX) {
			Console.print("Abbonamento richiesto 15 € al mese" );
		}
			
	}
}
