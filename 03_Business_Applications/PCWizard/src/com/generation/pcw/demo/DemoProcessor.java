package com.generation.pcw.demo;

import com.generation.pcw.util.Console;
import com.generation.pcw.model.entities.Processor;

// Classe demo per testare la gestione dei processori
// Richiede all'utente di inserire il nome di un processore e verifica che sia valido
public class DemoProcessor
{

	public static void main(String[] args)
	{
		// Blocco try-catch per gestire eventuali errori di input
		// Se l'utente inserisce un nome non valido, viene sollevata un'eccezione
		try
		{
			Console.print("Inserire processore: ");
			Processor p = Processor.valueOf(Console.readString());


		}
		catch (Exception e)
		{
			Console.print("Processore non valido");
		}
		Console.print("Programma FINITO");
	}
	
	

}