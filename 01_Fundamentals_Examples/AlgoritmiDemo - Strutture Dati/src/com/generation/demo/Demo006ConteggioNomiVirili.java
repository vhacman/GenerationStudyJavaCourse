package com.generation.demo;

import java.util.HashSet;
import java.util.Set;

public class Demo006ConteggioNomiVirili 
{
	public static void main(String[] args) 
	{
		/*
		 * Ho deciso che da ora in avanti mi farò vanto
		 * della mia mascolinità tossica
		 * Esaminerò un vettore di nomi
		 * e conterò quanti di questi sono veramente virili
		 * da uomo vero, da macho
		 * birra, sigaro, partita di calcio in tv
		 */		
		String[] people = 
		{
			"Chuck",      // appare 3 volte
			"Leonida",
			"Bettino",
			"Chuck",
			"Chuck",
			"Ash",
			"Domenico",   // appare 2 volte
			"Adriano",
			"Domenico",
			"Matteo",
			"Bryton",
			"Moussom",
			"Giovanni",   // appare 2 volte
			"Giovanni",
			"Mirtilla",
			"Chiara",
			"Ferdinando",
			"Bettina",
			"Valentina"
		};		
		// Definisco quali sono i "nomi virili" (ironico)
		Set<String> nomiVirili = new HashSet<String>();
		nomiVirili.add("Bettina");   
		nomiVirili.add("Chuck");
		nomiVirili.add("Mirtilla"); 
		// Tutti gli altri non sono veri uomini		
		// Conteggio: ciclo attraverso l'array e verifico se ogni nome
		// è presente nel Set di nomi virili usando contains()
		int conteggio = 0;
		for(String personName : people)
			// contains() è O(1) per HashSet: molto efficiente!
			if(nomiVirili.contains(personName))
				conteggio++;		
		System.out.println("Conteggio nomi virili: " + conteggio);
		// Output: 5 (Chuck appare 3 volte + Mirtilla 1 volta + Bettina 1 volta)
	}
}
