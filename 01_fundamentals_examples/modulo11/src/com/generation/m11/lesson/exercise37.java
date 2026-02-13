package com.generation.m11.lesson;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Console;

/**
 * ESERCIZIO #37
 *
 * Dimostra l'uso di lambda expressions per filtrare una lista di oggetti Person.
 *
 * Operazioni:
 * 1. Crea una List di Person
 * 2. Inserisce dieci Person con dati diversi
 * 3. Filtra le persone eliminando quelle di genere maschile usando lambda
 * 4. Stampa il risultato
 */
public class Exercise37
{
	public static void main(String[] args)
	{
		System.out.println("=== ESERCIZIO #37 - FILTRAGGIO CON LAMBDA ===\n");

		// 1. Creare una List di Person
		List<Person> people = new ArrayList<>();

		// 2. Inserire dieci Person con dati diversi
		people.add(new Person("Mario", "Rossi", "1990-05-15", "M"));
		people.add(new Person("Laura", "Bianchi", "1985-08-20", "F"));
		people.add(new Person("Giuseppe", "Verdi", "1992-03-10", "M"));
		people.add(new Person("Anna", "Neri", "1988-11-25", "F"));
		people.add(new Person("Luca", "Ferrari", "1995-07-30", "M"));
		people.add(new Person("Sofia", "Romano", "1991-12-05", "F"));
		people.add(new Person("Marco", "Marino", "1987-02-18", "M"));
		people.add(new Person("Giulia", "Greco", "1993-09-22", "F"));
		people.add(new Person("Alessandro", "Bruno", "1989-04-14", "M"));
		people.add(new Person("Chiara", "Colombo", "1994-06-08", "F"));

		Console.print("Lista originale (" + people.size() + " persone):");
		Console.print("----------------------------------------");
		printPeople(people);

		// 3. Filtrare le persone eliminando quelle di genere maschile usando lambda
		// removeIf() rimuove gli elementi che soddisfano il predicato
		people.removeIf(person -> person.getGender().equals("M"));

		// 4. Stampare il risultato
		Console.print("\n\nLista filtrata (solo genere femminile - " + people.size() + " persone):");
		Console.print("----------------------------------------");
		printPeople(people);

		Console.print("\n=== FINE ESERCIZIO ===");
	}

	/**
	 * Metodo helper per stampare una lista di persone in modo formattato
	 *
	 * @param people La lista di persone da stampare
	 */
	private static void printPeople(List<Person> people)
	{
		int counter = 1;
		for (Person person : people)
		{
			Console.print(String.format("%2d. %-15s %-15s | Genere: %s | Età: %d anni",
					counter++,
					person.getName(),
					person.getSurname(),
					person.getGender(),
					person.getAge()));
		}
	}
}
