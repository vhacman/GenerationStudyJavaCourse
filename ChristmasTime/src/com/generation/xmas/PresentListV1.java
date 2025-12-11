package com.generation.xmas;

import com.generation.library.Console;

public class PresentListV1 
{

	public static void main(String[] args) 
	{
		// versione con do-loop, niente budget, niente totale
		
		// D:
		String present;								// nome di UN singolo regalo, neanche il destinatario
		String presentSummary="Lista regali \n";	// stringa che conterrà TUTTE le stringhe dei regali
		String goOn;								// stringa per chiedere se voglio andare avanti
		
		Console.print("Ciao Babbo Natale");
		do
		{
			Console.print("Inserire un regalo per un* brav* bambin*");
			present = Console.readString();
			// presentSummary = presentSummary + present + "\n";
			presentSummary+=present+"\n";	// ACCUMULAZIONE DI STRINGHE
			Console.print("Vuoi andare avanti");
			goOn = Console.readString();
		}while(goOn.equalsIgnoreCase("s"));
		
		Console.print(presentSummary);
		
		
		
		
	}

}
