package com.generation.lessons;

public class TestEquals
{
	public static void main(String[] args)
	{
		System.out.println("=== ESERCIZIO #33: Test del metodo equals() ===\n");

		// Creare due oggetti Employee con lo stesso contenuto
		Employee a = new Employee("Mario", "Rossi", "01-01-1980", "M", 30000.0, "IT");
		Employee b = new Employee("Mario", "Rossi", "01-01-1980", "M", 30000.0, "IT");

		System.out.println("--- Oggetti creati ---");
		System.out.println("a: " + a);
		System.out.println("b: " + b);

		// Stampare il risultato di equals() - DOPO l'override
		System.out.println("\n--- Test equals() dopo l'override ---");
		System.out.println("a.equals(b): " + a.equals(b));
		System.out.println("Risultato: true (confronta il CONTENUTO)");

		// Confronto riferimenti
		System.out.println("\n--- Confronto riferimenti ---");
		System.out.println("a == b: " + (a == b));
		System.out.println("Risultato: false (sono due oggetti DIVERSI in memoria)");

		// Test con oggetti con contenuto diverso
		System.out.println("\n--- Test con contenuto diverso ---");
		Employee c = new Employee("Luigi", "Verdi", "15-05-1985", "M", 35000.0, "HR");
		System.out.println("c: " + c);
		System.out.println("a.equals(c): " + a.equals(c));

		// Test con stesso riferimento
		System.out.println("\n--- Test con stesso riferimento ---");
		Employee d = a;
		System.out.println("d = a (stesso riferimento)");
		System.out.println("a.equals(d): " + a.equals(d));
		System.out.println("a == d: " + (a == d));

		// Test con null
		System.out.println("\n--- Test con null ---");
		System.out.println("a.equals(null): " + a.equals(null));

		// Test con oggetto di tipo diverso
		System.out.println("\n--- Test con tipo diverso ---");
		Person p = new Person("Mario", "Rossi", "01-01-1980", "M");
		System.out.println("p: " + p);
		System.out.println("a.equals(p): " + a.equals(p));
		System.out.println("(Person non ha salary e department, quindi è un tipo diverso)");
	}
}
