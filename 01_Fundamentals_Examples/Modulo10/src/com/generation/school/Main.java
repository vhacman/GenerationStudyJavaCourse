package com.generation.school;

import com.generation.library.Console;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args)
	{
		// Creare una lista di Person con diversi oggetti (Teacher, Student, Support, ecc.)
		ArrayList<Person> people = new ArrayList<>();

		// Aggiungiamo alcuni insegnanti
		people.add(new Teacher("Mario", "Rossi", "01/01/1980", "M", 2000.0, "Matematica", 10));
		people.add(new Teacher("Laura", "Bianchi", "15/03/1985", "F", 2200.0, "Italiano", 8));
		people.add(new Teacher("Giovanni", "Verdi", "20/06/1975", "M", 2500.0, "Storia", 15));

		// Aggiungiamo altri tipi di Person (se esistono, altrimenti commentiamo)
		// people.add(new Student(...));
		// people.add(new Support(...));

		// 1. Creare un sottoinsieme di Person contenente solo gli insegnanti
		ArrayList<Teacher> teachers = new ArrayList<>();
		for (Person person : people) {
			if (person instanceof Teacher) {
				teachers.add((Teacher) person);
			}
		}

		// 2. Creare una lista di String contenenti nomi e cognomi degli insegnanti trovati
		ArrayList<String> teacherNames = new ArrayList<>();
		for (Teacher teacher : teachers) {
			teacherNames.add(teacher.getName() + " " + teacher.getSurname());
		}

		// 3. Calcolare il totale delle retribuzioni annue degli insegnanti trovati
		double totalYearlyRetribution = 0.0;
		for (Teacher teacher : teachers) {
			totalYearlyRetribution += teacher.getYearlyRetribution();
		}

		// 4. Stampare i risultati a schermo
		Console.print("\n=== ESERCIZIO #35 ===\n");
		Console.print("\nNumero totale di persone: " + people.size());
		Console.print("Numero di insegnanti trovati: " + teachers.size() + "\n");

		Console.print("\nElenco insegnanti:");
		for (String name : teacherNames) {
			Console.print("- " + name);
		}

		Console.print("\nDettagli insegnanti:");
		for (Teacher teacher : teachers) {
			Console.print(teacher.toString());
			Console.print("  Retribuzione annua: " + teacher.getYearlyRetribution() + " EUR");
		}

		Console.print("\nTotale retribuzioni annue: " + totalYearlyRetribution + " EUR");
	}

}
