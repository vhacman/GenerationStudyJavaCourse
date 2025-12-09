package com.generation.checker;
import com.generation.library.*;
import com.generation.ex.*;


public class AgeAndHeightChecker {

	/**
	 * Scrivere un programma che richieda all’utente l’età. Se l’utente è maggiorenne il programma
		stampa “ingresso consentito”, altrimenti stampa “ingresso vietato”.*/
	public static void heightAndAge ()
	{
		int height;
		int age;
		int	MAXHEIGHT = 120;
		int	MAXAGE = 13;
	
		Console.print("Quanti anni hai? ");
		age = Console.readInt();
		
		Console.print("Quanto sei alto? ");
		height = Console.readInt();
		
		if (age > MAXAGE) {
			if(height >= MAXHEIGHT) {
				Console.print("Ingresso consentito" );
			}
		}
		else {
			Console.print("Ingresso negato ");
		}
	
	}
}
