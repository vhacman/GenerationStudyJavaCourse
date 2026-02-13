package com.generation.exercises;

import com.generation.library.Console;

public class EsercizioLezione {

	public static void main(String[] args)
	{
		// 1 - ciclo do-while
		// in generale iterazione
		// come ripetere determinate istruzioni fin tanto che una condizione è vera
		/*
		 * desidero --> voglio richiedere all'utente dei numeri fino a quando lui non mi dice basta
		 * dopo che ha inserito tutti i numeri, voglio stampare la somma
		 * ma già che ci siamo stamperemo anche i totali parziali, via via
		 * 
		 * cosa ci serve?
		 * 
		 * 2 variabili di input e output e di lavoro
		 * 
		 * */
		int n; // un singolo numero della somma
		int sum = 0; //la somma di tutti i numeri
		String anotherOne; // una stringa per permettere all'utente di dire, si o no.
		
		/*
		 * 1- Chiedere un numero
		 * 2- Sommarlo a sum, stampiamo i parziali
		 * 3- chiedere anotherOne all'utente
		 * 4- se risponde S andare al punto 1 altrimenti al punto 5
		 * 5- stampare sum
		 * 
		 */
		
		do 
		{
			//CORPO DEL CICLO -- 
				Console.print("Inserire un numero: ");
				n = Console.readInt();
				sum += n;
				Console.print("Somma parziale: " + sum);
				Console.print("Another one? ");
				anotherOne = Console.readString();
				
		}
				//CONDIZIONE DI RIPETIZIONE
		while (anotherOne.equalsIgnoreCase("S"));
		
		Console.print("Somma finale: " + sum);
	}

}
