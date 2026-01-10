package com.generation.nsmi.repository;

import java.io.FileNotFoundException;

import com.generation.library.FileReader;
import com.generation.library.FileWriter;
import com.generation.nsmi.model.entities.Doctor;
import com.generation.nsmi.model.entities.InsuranceType;
import com.generation.nsmi.model.entities.Patient;
import com.generation.nsmi.model.entities.Person;
import com.generation.nsmi.model.entities.SpecialtyType;

/**
 * Repository per la gestione della persistenza delle entità Person.
 *
 * Questa classe si occupa di salvare e recuperare oggetti Person dal file system,
 * utilizzando file di testo come meccanismo di archiviazione.
 */
public class PersonRepository
{
	/** Percorso della directory dove vengono archiviati i file delle persone */
	final String		archivePath = "archive/people/";
	
	
	/**
	 * Carica una persona dal sistema di archiviazione.
	 *
	 * Cerca un file di testo con nome "{ssn}.txt" nella directory "archive/people/".
	 * Se il file esiste, legge il contenuto riga per riga e ricostruisce l'oggetto Person
	 * corrispondente. Il tipo specifico di Person dipende dalle informazioni contenute nel file.
	 *
	 * @param ssn il codice fiscale (Social Security Number) della persona da caricare
	 * @return l'oggetto Person corrispondente al codice fiscale fornito, o null se non trovato
	 */
	public Person load(String ssn)
	{
		String filename = archivePath + ssn.toUpperCase() + ".txt";

		try
		{
			FileReader 		reader	 = new FileReader(filename);
			String 			firstRow = reader.readString();

			// Determina il tipo di oggetto Person da istanziare basandosi sulla prima riga del file
			// Implementa il pattern Late Binding: il tipo concreto viene determinato a runtime
			switch (firstRow)
			{
				case "PERSON":
					// Istanzia un oggetto Person generico e popola i suoi campi
					Person	person = new Person();
					person.setId		(reader.readInt());
					person.setFirstName	(reader.readString());
					person.setLastName	(reader.readString());
					person.setDob		(reader.readString());
					person.setSSN		(reader.readString());
					reader.close();
					return person;
	
				case "PATIENT":
					// Istanzia un oggetto Patient (sottoclasse di Person) e popola i suoi campi
					Patient	patient = new Patient();
					patient.setId		(reader.readInt());
					patient.setFirstName(reader.readString());
					patient.setLastName	(reader.readString());
					patient.setDob		(reader.readString());
					patient.setSSN		(reader.readString());
					patient.setHistory	(reader.readString());
					patient.setInsurance(InsuranceType.valueOf(reader.readString()));
					reader.close();
					return patient;
	
				case "DOCTOR":
					// Istanzia un oggetto Doctor (sottoclasse di Person) e popola i suoi campi
					Doctor	doctor = new Doctor();
					doctor.setId			(reader.readInt());
					doctor.setFirstName		(reader.readString());
					doctor.setLastName		(reader.readString());
					doctor.setDob			(reader.readString());
					doctor.setSSN			(reader.readString());

					// Leggi specializzazione prima dello stipendio per permettere la validazione
					double salary = reader.readDouble();
					SpecialtyType specialty = SpecialtyType.valueOf(reader.readString());
					doctor.setSpecialization(specialty);

					// Gestisce il caso in cui lo stipendio salvato non sia più valido
					try {
						doctor.setSalary(salary);
					} catch (IllegalArgumentException e) {
						System.err.println("ATTENZIONE: Stipendio non valido nel file per " +
						                   doctor.getSSN() + ": " + e.getMessage());
						// Imposta comunque lo stipendio forzando la validazione
						doctor.setSpecialization(null);
						doctor.setSalary(salary);
						doctor.setSpecialization(specialty);
					}

					reader.close();
					return doctor;
	
				default:
					// Tipo non riconosciuto, restituisce null
					reader.close();
					return null;
			}
		}
		catch (FileNotFoundException e)
		{
			// File non trovato: stampa lo stack trace per debugging
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			// In caso di altri errori (errore di lettura, parsing, ecc.), restituisce null
			e.printStackTrace();
			return null;
		}

		/*
		 * Formato del file di archiviazione:
		 * Linea 1: Tipo di persona (PERSON, PATIENT, ecc.)
		 * Linea 2: Numero identificativo
		 * Linea 3: Nome
		 * Linea 4: Cognome
		 * Linea 5: Data di nascita (formato: YYYY-MM-DD)
		 * Linea 6: Codice fiscale (SSN)
		 *
		 * Esempio:
		 * PERSON
		 * 1
		 * Ismaele
		 * Grazioni
		 * 2006-01-26
		 * GRZSME06A260123S
		 */
	}

	/**
	 * Salva una persona nel sistema di archiviazione.
	 *
	 * Questa persona che mi arriva potrebbe NON ESSERE una Person e basta,
	 * potrebbe essere Patient, Doctor o qualunque altra cosa...
	 *
	 * @param p l'oggetto Person da salvare
	 * @return true se il salvataggio ha avuto successo, false altrimenti
	 */
	public boolean	save(Person p)
	{
		if (p == null || p.getSSN() == null)
		{
			return false;
		}

		if 		(p instanceof Doctor) 		{return saveDoctor((Doctor)p);}
		else if (p instanceof Patient) 		{return savePatient((Patient)p);}
		else 								{return savePerson(p);}
	}

	/**
	 * Salva un oggetto Person generico
	 */
	private boolean savePerson(Person p)
	{
		try
		{
			String 		filename = archivePath + p.getSSN().toUpperCase() + ".txt";
			FileWriter 	writer = new FileWriter(filename);
			writer.print("PERSON\n");
			writer.print(p.getId()+"\n");
			writer.print(p.getFirstName()+"\n");
			writer.print(p.getLastName()+"\n");
			writer.print(p.getDob()+"\n");
			writer.print(p.getSSN());
			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Salva un oggetto Patient
	 */
	private boolean savePatient(Patient p)
	{
		try
		{
			String 		filename = archivePath + p.getSSN().toUpperCase() + ".txt";
			FileWriter	writer = new FileWriter(filename);
			writer.print("PATIENT\n");
			writer.print(p.getId()+"\n");
			writer.print(p.getFirstName()+"\n");
			writer.print(p.getLastName()+"\n");
			writer.print(p.getDob()+"\n");
			writer.print(p.getSSN()+"\n");
			writer.print(p.getHistory()+"\n");
			writer.print(p.getInsurance());
			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Salva un oggetto Doctor
	 */
	private boolean saveDoctor(Doctor d)
	{
		try
		{
			String 		filename = archivePath + d.getSSN().toUpperCase() + ".txt";
			FileWriter 	writer = new FileWriter(filename);
			writer.print("DOCTOR\n");
			writer.print(d.getId()+"\n");
			writer.print(d.getFirstName()+"\n");
			writer.print(d.getLastName()+"\n");
			writer.print(d.getDob()+"\n");
			writer.print(d.getSSN()+"\n");
			writer.print(d.getSalary()+"\n");
			writer.print(d.getSpecialization());
			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}
}
