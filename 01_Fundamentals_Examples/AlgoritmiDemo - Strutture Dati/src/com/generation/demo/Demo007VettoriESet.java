package com.generation.demo;

import java.util.HashSet;
import java.util.Set;

/**
 * Demo che illustra l'uso di array (vettori) e Set per operazioni di riduzione.
 * Dimostra come estrarre valori univoci da una collezione di oggetti Person.
 *
 */
public class Demo007VettoriESet
{
	/**
	 * Metodo principale che esegue le operazioni di riduzione.
	 *
	 * @param args argomenti della linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		// Voglio sapere quali città serviamo coi nostri servizi
		Person[] students =
		{
			new Person("Antonio", "Agrigento"),
			new Person("Alessio", "Torino"),
			new Person("Piero", "Roma"),
			new Person("Gabriela", "Ladispoli"),
			new Person("Giovanni", "Nettuno"),
			new Person("Simone", "Roma"),
			new Person("Adriano", "Roma"),
			new Person("Jojo", "Bologna")
		};
		estraiEStampaCittaServite(students);
		// Array di studenti con titoli di studio
		Person[] studentsWithDegree =
		{
			new Person("Antonio", "Agrigento", "Laurea Magistrale", true),
			new Person("Alessio", "Torino", "Laurea Breve", true),
			new Person("Piero", "Roma", "Dottorato", true),
			new Person("Gabriela", "Ladispoli", "Laurea Magistrale", true),
			new Person("Giovanni", "Nettuno", "Diploma", true),
			new Person("Simone", "Roma", "Laurea Breve", true),
			new Person("Adriano", "Roma", "Laurea Magistrale", true),
			new Person("Jojo", "Bologna", "Medie Inferiori", true),
			new Person("Marco", "Milano", "Laurea Breve", true)
		};
		estraiEStampaTitoliDiStudio(studentsWithDegree);
	}

	/**
	 * Estrae le città uniche dall'array di studenti e le stampa.
	 * RIDUZIONE: da Person[] a Set&lt;String&gt; (città).
	 *
	 * @param students array di studenti
	 */
	private static void estraiEStampaCittaServite(Person[] students)
	{
		// RIDUZIONE:
		// Da un vettore di Person (Person[]) a un Set<String> dove la stringa è la città
		// Il Set elimina automaticamente i duplicati
		Set<String> cities = new HashSet<String>();
		for(Person student : students)
			cities.add(student.city); // Roma viene aggiunto 3 volte ma appare solo 1 volta
		System.out.println("Città servite:");
		System.out.println(cities);
	}

	/**
	 * Estrae i titoli di studio unici dall'array di studenti e li stampa.
	 * RIDUZIONE: da Person[] a Set&lt;String&gt; (titoli di studio).
	 *
	 * @param studentsWithDegree array di studenti con titolo di studio
	 */
	private static void estraiEStampaTitoliDiStudio(Person[] studentsWithDegree)
	{
		// RIDUZIONE: da Person[] a Set<String> (titoli di studio)
		Set<String> degrees = new HashSet<String>();
		for(Person student : studentsWithDegree)
			degrees.add(student.qualification); // Il Set elimina automaticamente i duplicati
		System.out.println("\nTitoli di studio (senza ripetizioni):");
		System.out.println(degrees);
	}
}
