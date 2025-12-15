package com.generation.xmas;

import com.generation.library.Console;

public class PresentListV3 
{

	public static void main(String[] args) 
	{
		// D
		String present;			// nome singolo regalo
		int cost;				// costo singolo regalo
		int total = 0;			// voglio una variabile che mantenga la somma dei costi
		String presentSummary="Lista regali\n";	// riepilogo regali CON COSTI
		String goOn;
		
		do
		{
			Console.print("Inserire un regalo per un* brav* bambin*");
			present = Console.readString();
			Console.print("Non vorrei dare un valore ai sentimenti, ma quanto costa?");
			cost = Console.readInt();
			// presentSummary = presentSummary + present + "\n";
			presentSummary+=present+" "+cost+" euro \n";
			total+=cost;
			Console.print("Vuoi andare avanti");
			goOn = Console.readString();
			
		}while(goOn.equalsIgnoreCase("s"));
		
		presentSummary+="\nTotale "+total+ " euro";
		
		Console.print(presentSummary);
	}

}
