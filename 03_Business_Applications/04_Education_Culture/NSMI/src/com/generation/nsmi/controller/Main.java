package com.generation.nsmi.controller;

import com.generation.library.Console;
import com.generation.library.FileReader;
import com.generation.nsmi.model.entities.Doctor;
import com.generation.nsmi.model.entities.InsuranceType;
import com.generation.nsmi.model.entities.Patient;
import com.generation.nsmi.model.entities.Person;
import com.generation.nsmi.model.entities.SpecialtyType;
import com.generation.nsmi.repository.PersonRepository;
import com.generation.nsmi.view.PersonView;

public class Main
{
	private static final String 			TEMPLATE_PATH 		= "templateFolder/";
	private static PersonView 				viewTxt 			= new PersonView(TEMPLATE_PATH);
	private static PersonRepository 		personRepo 			= new PersonRepository();
	

	public static void main(String[] args)
	{
		try
		{
			FileReader headerReader = new FileReader(TEMPLATE_PATH + "header.txt");
			while (headerReader.hasNext())
			{
				Console.print(headerReader.readString());
			}
			headerReader.close();
		}
		catch (Exception e)
		{
			Console.print("Errore nel caricamento dell'header");
		}

		String cmd;

		do
		{
			printMenu();
			cmd = Console.readString();

			switch (cmd)
			{
			case "1":
				loadPerson();
				break;
			case "2":
				savePerson();
				break;
			case "3":
				savePatient();
				break;
			case "4":
				saveDoctor();
				break;
			case "Q":
			case "q":
				Console.print("\nArrivederci!");
				break;
			default:
				Console.print("\nComando non riconosciuto. Riprova.");
				break;
			}
		} while(!cmd.equalsIgnoreCase("q"));
	}

	/**
	 * Stampa il menu principale dell'applicazione leggendolo da file
	 */
	private static void printMenu()
	{
		Console.print("");
		try
		{
			FileReader menuReader = new FileReader(TEMPLATE_PATH + "menu.txt");
			while (menuReader.hasNext())
			{
				Console.print(menuReader.readString());
			}
			menuReader.close();
		}
		catch (Exception e)
		{
			Console.print("Errore nel caricamento del menu");
		}
		Console.print("\nInserisci comando: ");
	}


	/**
	 * Carica e visualizza i dati di una persona dal repository
	 */
	private static void loadPerson()
	{
		Console.print("\nInserire codice fiscale: ");
		String ssn = Console.readString();

		Person p = personRepo.load(ssn);

		if (p == null)
		{
			Console.print("\nPersona non trovata.");
		}
		else
		{
			Console.print("\nPersona trovata:");
			Console.print(viewTxt.render(p));
		}
	}

	/**
	 * Crea e salva una nuova persona nel repository
	 */
	private static void savePerson()
	{
		Console.print("\n--- SALVA NUOVA PERSONA ---");

		Console.print("Inserire ID: ");
		int id = Console.readInt();

		Console.print("Inserire nome: ");
		String firstName = Console.readString();

		Console.print("Inserire cognome: ");
		String lastName = Console.readString();

		Console.print("Inserire codice fiscale: ");
		String ssn = Console.readString();

		Console.print("Inserire data di nascita (YYYY-MM-DD): ");
		String dob = Console.readString();

		// Crea un nuovo oggetto Person
		Person newPerson = new Person();
		newPerson.setId(id);
		newPerson.setFirstName(firstName);
		newPerson.setLastName(lastName);
		newPerson.setSSN(ssn);
		newPerson.setDob(dob);

		// Salva la persona
		boolean success = personRepo.save(newPerson);

		if (success)
		{
			Console.print("\nPersona salvata con successo!");
		}
		else
		{
			Console.print("\nErrore durante il salvataggio della persona.");
		}
	}

	/**
	 * Crea e salva un nuovo paziente nel repository
	 */
	private static void savePatient()
	{
		Console.print("\n--- SALVA NUOVO PAZIENTE ---");

		Console.print("Inserire ID: ");
		int 		id = Console.readInt();

		Console.print("Inserire nome: ");
		String 		firstName = Console.readString();

		Console.print("Inserire cognome: ");
		String 		lastName = Console.readString();

		Console.print("Inserire codice fiscale: ");
		String 		ssn = Console.readString();

		Console.print("Inserire data di nascita (YYYY-MM-DD): ");
		String 		dob = Console.readString();

		Console.print("Inserire storia clinica: ");
		String 		history = Console.readString();

		// Mostra le opzioni disponibili per InsuranceType
		Console.print("Inserire tipo di assicurazione. Opzioni disponibili:");
		for (InsuranceType type : InsuranceType.values())
		{
			Console.print("  - " + type);
		}
		Console.print("Scelta: ");
		String 		insuranceStr = Console.readString().toUpperCase();

		// Crea un nuovo oggetto Patient
		Patient 	newPatient = new Patient();
		newPatient.setId(id);
		newPatient.setFirstName(firstName);
		newPatient.setLastName(lastName);
		newPatient.setSSN(ssn);
		newPatient.setDob(dob);
		newPatient.setHistory(history);
		newPatient.setInsurance(InsuranceType.valueOf(insuranceStr));

		boolean 		success = personRepo.save(newPatient);
		if (success)
		{
			Console.print("\nPaziente salvato con successo!");
		}
		else
		{
			Console.print("\nErrore durante il salvataggio del paziente.");
		}
	}

	private static void saveDoctor()
	{
		Console.print("\n--- SALVA NUOVO DOTTORE ---");

		Console.print("Inserire ID: ");
		int 		id = Console.readInt();

		Console.print("Inserire nome: ");
		String 		firstName = Console.readString();

		Console.print("Inserire cognome: ");
		String 		lastName = Console.readString();

		Console.print("Inserire codice fiscale: ");
		String 		ssn = Console.readString();

		Console.print("Inserire data di nascita (YYYY-MM-DD): ");
		String 		dob = Console.readString();

		// Mostra le opzioni disponibili per SpecialtyType con i range salariali
		Console.print("\nInserire specializzazione. Opzioni disponibili:");
		for (SpecialtyType type : SpecialtyType.values())
		{
			Console.print("  - " + type.toString());
		}
		Console.print("Scelta: ");
		String 		specialization = Console.readString().toUpperCase();

		SpecialtyType specialty = SpecialtyType.valueOf(specialization);

		// Mostra il range salariale valido per la specializzazione scelta
		Console.print("\nRange salariale per " + specialty.name() + ":");
		Console.print("  Minimo: €" + String.format("%.2f", specialty.getMinSalary()));
		Console.print("  Massimo: €" + String.format("%.2f", specialty.getMaxSalary()));

		Console.print("\nInserire RAL: ");
		double 		salary = Console.readDouble();

		// Crea un nuovo oggetto Doctor
		Doctor 	newDoctor = new Doctor();
		newDoctor.setId(id);
		newDoctor.setFirstName(firstName);
		newDoctor.setLastName(lastName);
		newDoctor.setSSN(ssn);
		newDoctor.setDob(dob);
		newDoctor.setSpecialization(specialty);

		// Gestisce la validazione dello stipendio
		try {
			newDoctor.setSalary(salary);

			boolean success = personRepo.save(newDoctor);
			if (success)
			{
				Console.print("\n✓ Dottore salvato con successo!");
				Console.print(newDoctor.getSalaryInfo());
			}
			else
			{
				Console.print("\nErrore durante il salvataggio del dottore.");
			}
		}
		catch (IllegalArgumentException e)
		{
			Console.print("\n✗ ERRORE: " + e.getMessage());
			Console.print("Il dottore NON è stato salvato. Riprovare con uno stipendio valido.");
		}
	}
}
