package com.generation.xmas;

import com.generation.library.Console;

public class PresentListV4 
{
	/*
	 * da fare esecuzione simulata con la tabellina e le variabili
	 * budget = 500
	 * 
	 * regali:
	 * smartwatch 30 euro (accettato)
	 * motocicletta 5000 (rifiutato)
	 * bici elettrica 400 (accettata)
	 * mass effect legenday edizioni scamosciata firmata dalla bioware con benedizione papale (70 euro)
	 * (fine ho esaurito il budget)
	 * 
	 * 
	 * riga			present			cost		total		budget		residualBudget		goOn	
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	public static void main(String[] args) 
	{
		// D
		// budget è stato dichiarto fra le graffe del main
		// budget esiste in tutto il main
		// ma le variabili che dichiaro dentro l'else
		// esisteranno solo dentro l'else
		// che budget ha SCOPE (AMBITO, CAMPO DI ESISTENZA)
		// budget ha SCOPE main, è definito in main
		int budget; // quanto posso e voglio spendere
		
		Console.print("Inserire budget lista");
		budget = Console.readInt();
		
		if(budget<=0)
			Console.print("Il tuo amore è un regalo sufficiente");
		else
		{
			String present; // nome del regalo
			int cost;		// costo del regalo
			String presentSummary="Lista regali \n"; // riepilogo dei regali
			int residualBudget = budget;
			String goOn="s";
			do
			{
				Console.print("Inserire nome regalo");
				present = Console.readString();
				Console.print("Inserire costo regalo");
				cost = Console.readInt();
				if(cost<=residualBudget)
				{
					// lo posso comprare
					presentSummary+=present+" "+cost+" euro\n";
					residualBudget-=cost;	// residualBudget = residualBudget - cost, scalo il costo
					Console.print("Acquisto effettuato. Budget residuo "+residualBudget+" euro");
				}
				else
					Console.print("Non puoi permetterti questa spesa");
				
				if(residualBudget>0)
				{
					Console.print("Chiedo se vuole andare avanti");
					goOn = Console.readString();
				}
				else
					Console.print("Hai finito il tuo budget");
				
				
			}while(goOn.equalsIgnoreCase("s") && residualBudget>0);		
			
			int total = budget - residualBudget; // budget - quello che mi resta = soldi spesi
			presentSummary+="Totale spesa " + total + " euro \n";
			presentSummary+="Budget residuo "+ residualBudget + " euro \n";
			Console.print(presentSummary);
		}
		
	
	}

}
