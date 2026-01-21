package com.generation.lessons;

import com.generation.library.Console;

public class Lesson002MetodoStampaPersonalizzata 
{

	public static void main(String[] args) 
	{
		printPerson("Hendrik", "Macca", 25);
		printPerson("Ferdinando", "Primerano", 2400);
	}

	// private => visibilit. Questo metodo è usabile solo dentro questa stessa classe
	// static => questo metodo appartiene alla classe e non ai suoi oggetti (??)
	// void   => questo metodo NON PRODUCE (RITORNA) valori
	// printPerson => nome del metodo
	// (String name, String surname, int age) => parametri
	private static void printPerson(String name, String surname, int age)
	{
		Console.print(name+" "+surname+" "+age+" "+(age>=18 ? "Maggiorenne" : "Minorenne"));
	}
	
}
