package com.generation.lessons;

import com.generation.library.Console;

public class Lesson004EsempioCiclo 
{

	public static void main(String[] args) 
	{
	
		int side;
		
		do		// apertura di un ciclo
		{
			Console.print("Inserire lato");			// corpo del ciclo 
			side = Console.readInt();				// ed è la parte ripetuta
			if(side<=0)								//
				Console.print("Dato non valido");	//
		}while(side<=0); // se side<=0 torno alla quindici! ripeto 15,16,17,forse 18
		//     condizione di ripetizione
		// 	   se la condizione è VERA io SALTO indietro a inizio ciclo
		// in questo caso, riga 15
		// in caso contrario esco
		// riga 26:
		
		int area = side * side;
		Console.print("L'area è "+area);
		
	}

}
