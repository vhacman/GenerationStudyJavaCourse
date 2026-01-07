package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Student;

public class demoStudent
{

	public static void main(String[] args)
	{
		Console.print("=== Demo della classe Student ===\n");

		/**
		 * Creazione di un oggetto Student usando il costruttore di default.
		 *
		 * IMPORTANTE: Quando creo un oggetto Student, viene automaticamente invocato
		 * anche il costruttore della classe Person (classe padre).
		 *
		 * Ordine di costruzione dell'oggetto:
		 * 1. PRIMA viene creata la parte "Person" dell'oggetto (name, surname, dateOfBirth)
		 * 2. POI viene creata la parte "Student" dell'oggetto (year, section)
		 *
		 * Questo avviene perché Student usa super() nel suo costruttore per chiamare
		 * il costruttore di Person, garantendo che la parte "genitore" sia inizializzata
		 * prima della parte "figlio".
		 *
		 * VISUALIZZAZIONE IN MEMORIA:
		 *
		 * s            Person
		 *              name         = ""
		 *              surname      = ""
		 *              dateOfBirth  = null
		 *              Student
		 *              year         = 0
		 *              section      = ""
		 *
		 * L'oggetto 's' contiene TUTTE le proprietà ereditate da Person
		 * PIÙ le proprietà specifiche di Student.
		 */
		Student s = new Student();

		/**
		 * Concetti filosofici della programmazione orientata agli oggetti:
		 *
		 * 1. "s is_a Student" (Relazione di tipo)
		 *    - Questo concetto deriva da Platone: Student rappresenta l'Idea/Forma astratta
		 *      degli studenti, mentre 's' è una manifestazione concreta di quell'Idea.
		 *    - Student è la CLASSE (categoria astratta/universale)
		 *    - s è l'ISTANZA/OGGETTO (entità concreta/particolare)
		 *
		 * 2. "s has_a year" (Relazione di composizione)
		 *    - Questo concetto deriva da Aristotele e il sillogismo:
		 *      "Tutti gli Student hanno un year, s è uno Student, quindi s ha un year"
		 *    - Rappresenta le proprietà/attributi che appartengono all'oggetto
		 *
		 * 3. Ereditarietà: Student estende Person
		 *    - Student eredita tutte le proprietà e metodi di Person (name, surname)
		 *    - Student aggiunge le proprie proprietà specifiche (year, section)
		 *    - L'oggetto Student contiene ENTRAMBE le parti: Person + Student
		 */

		// Test del costruttore di default
		Console.print("1. Student creato con costruttore di default:");
		Console.print("   " + s.toString());
		Console.print("");

		// Test dei setter (metodi modificatori)
		Console.print("2. Impostazione dei valori tramite setter:");
		s.setName("Mario");
		s.setSurname("Rossi");
		s.setDateOfBirth(LocalDate.of(2005, 3, 15));
		s.setYear(3);
		s.setSection("A");
		Console.print("   " + s.toString());
		Console.print("");

		/*
		 * Dopo i setter, l'oggetto 's' in memoria:
		 *
		 * s            Person
		 *              name         = "Mario"
		 *              surname      = "Rossi"
		 *              dateOfBirth  = 2005-03-15
		 *              Student
		 *              year         = 3
		 *              section      = "A"
		 */

		// Test dei getter (metodi accessori)
		Console.print("3. Accesso ai singoli valori tramite getter:");
		Console.print("   Nome: " + s.getName());
		Console.print("   Cognome: " + s.getSurname());
		Console.print("   Data di nascita: " + s.getDateOfBirth());
		Console.print("   Età: " + s.getAge() + " anni");
		Console.print("   Anno: " + s.getYear());
		Console.print("   Sezione: " + s.getSection());
		Console.print("");

		// Test del costruttore completo
		Console.print("4. Student creato con costruttore completo:");
		Student s2 = new Student("Lucia", "Bianchi", LocalDate.of(2006, 7, 22), 5, "B");
		Console.print("   " + s2.toString());
		Console.print("   Età di Lucia: " + s2.getAge() + " anni");
		Console.print("");

		/*
		 * L'oggetto 's2' in memoria dopo il costruttore completo:
		 *
		 * s2           Person
		 *              name         = "Lucia"
		 *              surname      = "Bianchi"
		 *              dateOfBirth  = 2006-07-22
		 *              Student
		 *              year         = 5
		 *              section      = "B"
		 *
		 * Un SINGOLO oggetto contiene ENTRAMBE le parti: Person + Student
		 */

		// Test della gestione dei valori null
		Console.print("5. Test gestione valori null:");
		Student s3 = new Student(null, null, null, 1, null);
		Console.print("   " + s3.toString());
		Console.print("   Età (null): " + s3.getAge());
		Console.print("");

		// Dimostrazione dell'ereditarietà e vantaggi
		Console.print("6. Dimostrazione ereditarietà e gestione del cambiamento:");
		Console.print("   Student eredita getName(), getSurname(), getDateOfBirth() e getAge() da Person");
		Console.print("   Student aggiunge getYear() e getSection()");
		Console.print("   Student override del metodo toString() di Person");
		Console.print("");
		Console.print("   VANTAGGI dell'ereditarietà:");
		Console.print("   1. Aggiungendo dateOfBirth a Person, automaticamente anche");
		Console.print("      Student e Teacher hanno questa proprietà senza modificarli!");
		Console.print("   2. Aggiungendo getAge() a Person, automaticamente anche");
		Console.print("      Student e Teacher possono calcolare l'età!");
		Console.print("   3. Uso di LocalDate invece di String per gestire meglio le date");
		Console.print("");
		Console.print("   Questo dimostra come l'ereditarietà facilita la gestione del cambiamento.");
		Console.print("");

		Console.print("=== Fine Demo ===");

	}

}
