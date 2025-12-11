package com.generation.xmas;

import com.generation.library.Console;

public class PresentListV5 
{

	public static void main(String[] args) 
	{
	
		// D
		int n;	// numero di regali da fare
		String presentSummary = "Lista regali";
		String present;
		int total = 0, cost;
		
		Console.print("Inserire numero regali");
		n = Console.readInt();
		
		
		
		//	imposta i a 0, ripeti il corpo del ciclo finchè i è INFERIORE a n, dopo ogni giro fai i = i+1 (i++)
		//  init					cond ripetizione								avanzamento
		for(int i=0;i<n;i++)
		{
			Console.print("Inserire regalo");
			present = Console.readString();
			Console.print("Inserire costo");
			cost = Console.readInt();
			presentSummary+=present+" "+cost+" euro \n";
			total+=cost;
			// e come se ci fosse scritto qui come ultima istruzione l'avanzamento: i++
			// rivaluto la condizione: i<n. Se è vera torno alla riga 24, se è falsa esco (riga 31)
		}
		
		presentSummary+="Total "+total+" euro\n";
		
		Console.print(presentSummary);
		
	}

}
