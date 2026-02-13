package com.generation.lessons;

import com.generation.library.Console;

public class Main {

	public static void main(String[] args)
	{
		Person p1 = new Person("Gabriela", "Hacman ", "21-10-1998," , " F");
		Person p2 = new Person("Valerio", "Corallini ", "02-23-1999", " M");
		Person p3 = new Person("Pippo", "Disney ", null, " M");
		Person p4 = new Person("Pluto", "Disney ", null, " M");

		Console.print(p1);
		Console.print(p2);
		Console.print(p3);
		Console.print(p4);

		// Esercizio #30: Test della classe Student
		Console.print("\n--- Test Student ---");
		Student s1 = new Student("Mario", "Rossi", "15-03-2005", "M", 3, "A");
		Console.print(s1);

		Student s2 = new Student();
		s2.setName("Laura");
		s2.setSurname("Bianchi");
		s2.setDateOfBirth("22-07-2006");
		s2.setGender("F");
		s2.setYear(2);
		s2.setSection("B");
		Console.print(s2);

		// Esercizio #31: Test della classe ForeignEmployee
		Console.print("\n--- Test ForeignEmployee (Esercizio #31) ---");
		ForeignEmployee fe1 = new ForeignEmployee();
		fe1.setName("John");
		fe1.setSurname("Smith");
		fe1.setDateOfBirth("12-05-1985");
		fe1.setGender("M");
		fe1.setSalary(45000.00);
		fe1.setDepartment("IT");
		fe1.setNationality("British");
		fe1.setVisaType("Work Permit");
		fe1.setNativeLanguage("English");
		Console.print(fe1);

		Console.print("\n--- Risposte alle domande ---");
		Console.print("Perché dispone di un nome? Eredita la proprietà 'name' dalla classe Person attraverso Employee");
		Console.print("Perché dispone di un salario? Eredita la proprietà 'salary' dalla classe Employee");
		Console.print("A quanti tipi appartiene l'oggetto creato? 4 tipi: ForeignEmployee, Employee, Person, Object");

		// Esercizio #32: Test toString() con super e verifica lingua nativa
		Console.print("\n--- Test ForeignEmployee (Esercizio #32) ---");
		ForeignEmployee fe2 = new ForeignEmployee("Maria", "Garcia", "08-11-1990", "F",
												   52000.00, "Marketing", "Spanish", "EU Work Permit", "Spanish");
		Console.print("toString() completo:");
		Console.print(fe2.toString());
		Console.print("\nVerifica: tutte le proprietà sono stampate, inclusa la lingua nativa (NativeLanguage: Spanish)");

		// Esercizio #33: Test del metodo equals()
		Console.print("\n=================================");
		Console.print("  ESERCIZIO #33 - Test equals()  ");
		Console.print("=================================\n");

		// STEP 1: Creare due oggetti Employee con lo stesso contenuto
		Console.print("--- STEP 1: Creare due oggetti con lo stesso contenuto ---");
		Employee a = new Employee("Mario", "Rossi", "01/01/1980", "M", 30000.0, "IT");
		Employee b = new Employee("Mario", "Rossi", "01/01/1980", "M", 30000.0, "IT");

		Console.print("a = " + a);
		Console.print("b = " + b);
		Console.print("");

		// STEP 2: Stampare a.equals(b) - ora restituisce TRUE grazie all'override
		Console.print("--- STEP 2: Stampare a.equals(b) ---");
		Console.print("a.equals(b) = " + a.equals(b));
		Console.print("Risultato: TRUE");
		Console.print("Motivo: Il metodo equals() è stato sovrascritto in Employee");
		Console.print("        e confronta TUTTE le proprietà:");
		Console.print("        - name, surname, dateOfBirth, gender (da Person)");
		Console.print("        - salary, department (da Employee)");
		Console.print("");

		// STEP 3: Verifica che siano oggetti diversi in memoria
		Console.print("--- STEP 3: Confronto dei riferimenti ---");
		Console.print("a == b: " + (a == b));
		Console.print("=> FALSO perché sono due oggetti diversi in memoria");
		Console.print("");

		// STEP 4: Test con oggetti con contenuto diverso
		Console.print("--- STEP 4: Test con oggetti diversi ---");
		Employee c = new Employee("Luigi", "Verdi", "15/03/1985", "M", 35000.0, "HR");
		Console.print("c = " + c);
		Console.print("a.equals(c): " + a.equals(c));
		Console.print("=> FALSO perché hanno contenuto diverso");

	}

}
