package com.generation.school.demo;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.school.model.entities.Teacher;

public class demoTeacher
{
	public static void main(String[] args)
	{
		Console.print("=== Demo della classe Teacher ===\n");

		/**
		 * Creazione di un oggetto Teacher usando il costruttore di default.
		 *
		 * IMPORTANTE: Quando creo un oggetto Teacher, viene automaticamente invocato
		 * anche il costruttore della classe Person (classe padre).
		 *
		 * Ordine di costruzione dell'oggetto:
		 * 1. PRIMA viene creata la parte "Person" dell'oggetto (name, surname, dateOfBirth)
		 * 2. POI viene creata la parte "Teacher" dell'oggetto (subject, salary)
		 *
		 * Questo avviene perché Teacher usa super() nel suo costruttore per chiamare
		 * il costruttore di Person, garantendo che la parte "genitore" sia inizializzata
		 * prima della parte "figlio".
		 *
		 * VISUALIZZAZIONE IN MEMORIA:
		 *
		 * t            Person
		 *              name         = ""
		 *              surname      = ""
		 *              dateOfBirth  = null
		 *              Teacher
		 *              subject      = ""
		 *              salary       = 0.0
		 *
		 * L'oggetto 't' contiene TUTTE le proprietà ereditate da Person
		 * PIÙ le proprietà specifiche di Teacher.
		 */
		Teacher t = new Teacher();

		/**
		 * Concetti filosofici della programmazione orientata agli oggetti:
		 *
		 * 1. "t is_a Teacher" (Relazione di tipo)
		 *    - Questo concetto deriva da Platone: Teacher rappresenta l'Idea/Forma astratta
		 *      degli insegnanti, mentre 't' è una manifestazione concreta di quell'Idea.
		 *    - Teacher è la CLASSE (categoria astratta/universale)
		 *    - t è l'ISTANZA/OGGETTO (entità concreta/particolare)
		 *
		 * 2. "t has_a subject" (Relazione di composizione)
		 *    - Questo concetto deriva da Aristotele e il sillogismo:
		 *      "Tutti i Teacher hanno un subject, t è un Teacher, quindi t ha un subject"
		 *    - Rappresenta le proprietà/attributi che appartengono all'oggetto
		 *
		 * 3. Ereditarietà: Teacher estende Person
		 *    - Teacher eredita tutte le proprietà e metodi di Person (name, surname, dateOfBirth, getAge())
		 *    - Teacher aggiunge le proprie proprietà specifiche (subject, salary)
		 *    - L'oggetto Teacher contiene ENTRAMBE le parti: Person + Teacher
		 */

		// Test del costruttore di default
		Console.print("1. Teacher creato con costruttore di default:");
		Console.print("   " + t.toString());
		Console.print("");

		// Test dei setter (metodi modificatori)
		Console.print("2. Impostazione dei valori tramite setter:");
		t.setName("Anna");
		t.setSurname("Verdi");
		t.setDateOfBirth(LocalDate.of(1985, 5, 20));
		t.setSubject("Matematica");
		t.setSalary(2500.50);
		Console.print("   " + t.toString());
		Console.print("");

		/*
		 * Dopo i setter, l'oggetto 't' in memoria:
		 *
		 * t            Person
		 *              name         = "Anna"
		 *              surname      = "Verdi"
		 *              dateOfBirth  = 1985-05-20
		 *              Teacher
		 *              subject      = "Matematica"
		 *              salary       = 2500.50
		 */

		// Test dei getter (metodi accessori)
		Console.print("3. Accesso ai singoli valori tramite getter:");
		Console.print("   Nome: " + t.getName());
		Console.print("   Cognome: " + t.getSurname());
		Console.print("   Data di nascita: " + t.getDateOfBirth());
		Console.print("   Età: " + t.getAge() + " anni");
		Console.print("   Materia: " + t.getSubject());
		Console.print("   Stipendio: " + t.getSalary() + " €");
		Console.print("");

		// Test del costruttore completo
		Console.print("4. Teacher creato con costruttore completo:");
		Teacher t2 = new Teacher("Marco", "Bianchi", LocalDate.of(1978, 11, 10), "Fisica", 3000.00);
		Console.print("   " + t2.toString());
		Console.print("   Età di Marco: " + t2.getAge() + " anni");
		Console.print("");

		/*
		 * L'oggetto 't2' in memoria dopo il costruttore completo:
		 *
		 * t2           Person
		 *              name         = "Marco"
		 *              surname      = "Bianchi"
		 *              dateOfBirth  = 1978-11-10
		 *              Teacher
		 *              subject      = "Fisica"
		 *              salary       = 3000.00
		 *
		 * Un SINGOLO oggetto contiene ENTRAMBE le parti: Person + Teacher
		 */

		// Test della gestione dei valori null
		Console.print("5. Test gestione valori null:");
		Teacher t3 = new Teacher(null, null, null, null, 1500.00);
		Console.print("   " + t3.toString());
		Console.print("   Età (null): " + t3.getAge());
		Console.print("");

		// Dimostrazione dell'ereditarietà e vantaggi
		Console.print("6. Dimostrazione ereditarietà e gestione del cambiamento:");
		Console.print("   Teacher eredita getName(), getSurname(), getDateOfBirth() e getAge() da Person");
		Console.print("   Teacher aggiunge getSubject() e getSalary()");
		Console.print("   Teacher override del metodo toString() di Person");
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
