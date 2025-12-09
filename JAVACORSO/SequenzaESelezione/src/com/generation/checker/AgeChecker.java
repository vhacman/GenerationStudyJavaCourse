package com.generation.checker;

import com.generation.library.Console;

public class AgeChecker {

	/*
	* Scrivere un programma che richieda all’utente età e statura. Se l’utente ha più di 13 anni ed è alto
	*	almeno 120 cm il programma stampa “ingresso consentito”, altrimenti stampa “ingresso vietato
	*/
	public static void isAdult() {
		int	age;
		
		int	ADULT = 18;
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		if (age == ADULT) {
			Console.print("Ingresso consentito" );
		}
		else {
			Console.print("Ingresso negato" );
		}

	}

}
