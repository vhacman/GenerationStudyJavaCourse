package com.generation.exercises;

import com.generation.library.*;

public class Exercises21to24 
{

	public static void main(String[] args)
	{
		int		choice;
		
		Console.print("=== MENU ESERCIZI 21 a 24 ===");
		Console.print("21:  ");
		Console.print("22: ");
		Console.print("23: ");
		Console.print("023: ");
		Console.print("24: ");
		Console.print("================================");
		
		choice = Console.readInt();
		
		switch(choice)
		{
			case 21:
				Exercises21to24.Esercizio21();
				break;
			case 22:
				Exercises21to24.Esercizio22();
				break;
			case 23:
				Exercises21to24.Esercizio23();
				break;
			case 023:
				Exercises21to24.Esercizio023();
				break;
			case 24:
				Exercises21to24.Esercizio24();
				break;			
			default:
				Console.print("Opzione non valida!");
		}
		
	}
	
	/*
	 * Scrivere un programma che inserisca da uno a infiniti numeri interi.
	 * Quando l’utente ha finito di
	 *	inserire, stampare il più grande inserito e la media di detti numeri.
	 **/
	public static void	Esercizio21()
	{
		int			num;
		String		anotherOne;
		double		average;
		int			maxNum = 0;
		int			sum = 0; //somma per calcolare la media
		int			count= 0; // contatore numeri inseriti per la media
			
		do
		{
			Console.print("Inserisci un numero: ");
			num = Console.readInt();			//legge INPUT di utente
			sum  += num; //aggiorna la somma
			count++; //incremanta il contatore
			if (num > maxNum)
			{
				maxNum = num;
			}
			Console.print("Inserire un nuovo numero ?");
			anotherOne = Console.readString();
			
		}
		while (anotherOne.equalsIgnoreCase("Si"));
		
					//cast ESPLICITO
					//average = (double)(sum / count);
		average = (sum * 1.0) / count;
		
		Console.print(" ");
		Console.print(" ==== RISULTATI =====");
		Console.print("Numero più grande: " + maxNum);
		Console.print("Media dei due numeri: " + average);
		Console.print("Totale numeri inseriti: " + count);

	}

	/*
	 * Scrivere un programma che chieda all’utente quante bollette deve pagare. Proceda poi, tramite un
	 * ciclo for, a chiedere per ogni bolletta la causale e l’importo. A fine programma stampare l’elenco
	 * delle bollette con i relativi importi e la somma totale da pagare.
	 * */
	public static void Esercizio22() 
	{
		int			numBill; //numero della bolletta
		String		description; // causale
		double		amount; // importo della bolletta
		double		total = 0; // numero totale di € da pagare per tutte le bollette
		String		list = "";
		
		
		Console.print("How many bills do you need to pay? ");
		numBill = Console.readInt();
		
		//FOR LOOP TO ENTER EACH BILL
		for (int i = 1; i <= numBill; i++ )
		{
			Console.print("\n Bill " + i + " ");
			
			Console.print("Description: ");
			description = Console.readString();
			
			Console.print("Amount (€): ");
			amount = Console.readDouble();
			
			//update total
			total += amount;
			
			list += i + " " + description + " - €" + amount + "\n";
		}
		// Print results
		Console.print("\n========== BILLS SUMMARY ==========");
		Console.print(list);
		Console.print("===================================");
		Console.print("TOTAL TO PAY: €" + total);
	}
	
	public static void Esercizio23()
	{
		/*
		 * Scrivere un programma che stampi i numeri pari inferiori a 1000. 
		 * Usare un ciclo for o un while.*/
		
		Console.print("Even numbers below 1000: ");
		// i = 8
		for (int i = 0; i < 1000; i++)
	    {
			// 8 / 2 = 4 con resto di 0 --> i % 2 == 0 --> TRUE
	        if (i % 2 == 0)
	        {
	        	//STAMPO i, QUINDI STAMPO 8
	            Console.print(i);
	        }
	    }
	}
	
	public static void Esercizio023()
	{
		/*
		 * Scrivere un programma che stampi i numeri pari inferiori a 1000. 
		 * Usare un ciclo for o un while.*/
		
		Console.print("Even numbers below 1000: ");
		Console.print(" ");
		
		int	i = 0;
		
		while (i < 1000)
		{
			if (i % 2 == 0)
				Console.print(i);
			i++;
		}
		
	}
	
	public static void Esercizio24()
	{
		/*
		 * Scrivere un programma che stampi sulla stessa riga 
		 * ogni numero dispari sotto mille col numero
		 * pari che lo segue. Ad esempio, 1-2, 3-4, 5-6 ecc…
		 * */
		
		String list = "";
		Console.print("Coppie dispari - pari sotto 1000: ");
		
		for (int i = 1; i < 1000; i += 2)
		{
			list += i + "-" + (i + 1) + ", ";
		}
		Console.print(list);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
