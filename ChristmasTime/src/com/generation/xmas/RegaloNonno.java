package com.generation.xmas;

import com.generation.library.Console;

public class RegaloNonno 
{

	public static void main(String[] args) 
	{
		Console.print("Gianumbertino, quanti voti hai preso in Java?");
		// funziona... perchè gli int ci stanno nei double..
		double n = Console.readInt();
		
		double sum = 0;
		
		// questo ciclo verrà ripetuto n volte
		for(int i=0;i<n;i++)
		{
			Console.print("Dimmi il voto del tuo compito "+(i+1));
			sum+=Console.readDouble(); // prendo il valore di readDouble e lo sommo a sum
			// non passo neanche per una variabile
		}
		
		double avg = sum / n; // calcolo della media dei voti di Java
		
		String msg;
		
		if(avg<6)
			msg = "Ti ho comprato la raccolta completa di Al Bano";
		else
			if(avg>=6 && avg<7)
				msg = "Ti ho preso un panettone con l'uvetta";
			else
				if(avg>=7 && avg<9)
					msg ="Ti ho preso la PS4 usata dal rigattiere";
				else
					msg = "C'è la steam machine sotto l'albero, ma ci giocherò io";
		
		
		Console.print(msg);
		
		
		
		
	}

}
