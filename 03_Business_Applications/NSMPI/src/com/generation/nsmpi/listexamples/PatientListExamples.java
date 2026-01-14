package com.generation.nsmpi.listexamples;

import java.time.LocalDate;
import java.util.List;

import com.generation.nsmpi.model.entities.Patient;

/**
 * Esempi di operazioni su liste di pazienti.
 * Dimostra tecniche di scorrimento, riduzione, filtraggio e mappatura.
 */
public class PatientListExamples
{

	/**
	 * Carica pazienti da file ed esegue analisi statistiche.
	 * Dimostra operazioni fondamentali su collezioni: riduzione, filtraggio, mappatura.
	 *
	 * @param args Argomenti da riga di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		// TODO 1: Chiedere all'utente il nome di un file
		// 1. Stampare un messaggio: "Inserire nome file da caricare"
		// 2. Creare una variabile String filename = "pippo.csv"

		// TODO 2: Creare un estrattore di pazienti per quel file (usiamo il dummy)
		// 1. Usare PatientExtractorFactory.make(filename) per creare l'estrattore
		// 2. Salvare il risultato in una variabile di tipo PatientExtractor

		// TODO 3: Estrarre lista di pazienti
		// 1. Chiamare extractor.getPatientsFromFile(filename)
		// 2. Salvare il risultato in una variabile List<Patient> all


		// TODO 4.1: Stampare numero di pazienti letti
		// 1. Usare all.size() per ottenere il numero di pazienti
		// 2. Stampare: "Sono stati letti X pazienti"

		// TODO 4.2: Chiamare countMen(), countWomen(), countNonBinary() e stampare i risultati
		// 1. Chiamare countMen(all) e salvare il risultato in una variabile nMen
		// 2. Chiamare countWomen(all) e salvare il risultato in una variabile nWomen
		// 3. Chiamare countNonBinary(all) e salvare il risultato in una variabile nNonBinary
		// 4. Stampare: "Numero uomini: X, Numero donne: Y, Numero non binari: Z"

		// TODO 4.3: Chiamare countOverweight() e stampare il risultato
		// 1. Chiamare countOverweight(all)
		// 2. Stampare: "Numero di pazienti in sovrappeso: X"

		// TODO 4.4: Chiamare filterWomen() per estrarre le donne
		// 1. Chiamare filterWomen(all) e salvare il risultato in una variabile women
		// 2. Stampare: "Riepilogo delle donne:"
		// 3. Scorrere women con un for-each e stampare nome e cognome di ogni paziente

		// TODO 4.5: Chiamare calculateAverageAge() sulla lista delle donne e stampare il risultato
		// 1. Chiamare calculateAverageAge(women)
		// 2. Stampare: "Età media delle donne: X anni"

		// TODO 4.6: Chiamare generateVaccinationLetters() e stampare tutte le lettere
		// 1. Chiamare generateVaccinationLetters(all) e salvare in una variabile letters
		// 2. Stampare: "Lettere di invito:"
		// 3. Scorrere la lista letters con un for-each
		// 4. Per ogni stringa, stamparla

		// TODO 4.7: Chiamare filterBirthdayToday() e stampare nome e cognome di ogni paziente
		// 1. Chiamare filterBirthdayToday(all) e salvare in patientsToContact
		// 2. Se la lista è vuota, stampare: "Nessun compleanno"
		// 3. Altrimenti, stampare: "Oggi compiono gli anni:"
		// 4. Scorrere patientsToContact con un for-each
		// 5. Per ogni paziente, stampare: p.getFirstName() + " " + p.getLastName()

	}

	/**
	 * Calcola il numero di pazienti uomini nella lista.
	 *
	 * @param patients Lista di pazienti
	 * @return Numero di pazienti uomini
	 */
	public static int countMen(List<Patient> patients)
	{
		// TODO: Implementare conteggio uomini
		// 1. Creare una variabile contatore inizializzata a 0
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il genere è Gender.M
		// 4. Se sì, incrementare il contatore
		// 5. Restituire il contatore
		return 0;
	}

	/**
	 * Calcola il numero di pazienti donne nella lista.
	 *
	 * @param patients Lista di pazienti
	 * @return Numero di pazienti donne
	 */
	public static int countWomen(List<Patient> patients)
	{
		// TODO: Implementare conteggio donne
		// 1. Creare una variabile contatore inizializzata a 0
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il genere è Gender.F
		// 4. Se sì, incrementare il contatore
		// 5. Restituire il contatore
		return 0;
	}

	/**
	 * Calcola il numero di pazienti non binari nella lista.
	 *
	 * @param patients Lista di pazienti
	 * @return Numero di pazienti non binari
	 */
	public static int countNonBinary(List<Patient> patients)
	{
		// TODO: Implementare conteggio non binari
		// 1. Creare una variabile contatore inizializzata a 0
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il genere NON è M e NON è F
		// 4. Se sì, incrementare il contatore
		// 5. Restituire il contatore
		return 0;
	}

	/**
	 * Conta i pazienti in sovrappeso (BMI > 26.5).
	 *
	 * @param patients Lista di pazienti
	 * @return Numero di pazienti in sovrappeso
	 */
	public static int countOverweight(List<Patient> patients)
	{
		// TODO: Implementare conteggio pazienti in sovrappeso
		// 1. Creare una variabile contatore inizializzata a 0
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il BMI > 26.5
		//    Usare: p.getBMI() > 26.5
		// 4. Se sì, incrementare il contatore
		// 5. Restituire il contatore
		return 0;
	}

	/**
	 * Estrae tutte le pazienti donne dalla lista.
	 *
	 * @param patients Lista di pazienti
	 * @return Lista di pazienti donne
	 */
	public static List<Patient> filterWomen(List<Patient> patients)
	{
		// TODO: Implementare filtro per estrarre le donne
		// 1. Creare una nuova ArrayList<Patient> vuota per le donne
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il genere è Gender.F
		// 4. Se sì, aggiungere il paziente alla nuova lista
		// 5. Restituire la lista delle donne
		return null;
	}

	/**
	 * Calcola l'età media dei pazienti nella lista.
	 *
	 * @param patients Lista di pazienti
	 * @return Età media
	 */
	public static int calculateAverageAge(List<Patient> patients)
	{
		// TODO: Implementare calcolo età media
		// 1. Creare una variabile sum inizializzata a 0
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, sommare la sua età (p.getAge()) a sum
		// 4. Calcolare la media: sum / patients.size()
		// 5. Restituire il risultato
		return 0;
	}

	/**
	 * Genera lista di lettere di invito alla vaccinazione.
	 *
	 * @param patients Lista di pazienti
	 * @return Lista di stringhe con le lettere di invito
	 */
	public static List<String> generateVaccinationLetters(List<Patient> patients)
	{
		// TODO: Implementare mappatura da Patient a String (lettera di invito)
		// 1. Creare una nuova ArrayList<String> vuota per le lettere
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, creare una stringa con il messaggio di invito
		//    Es: "Sig/a " + p.getFirstName() + " " + p.getLastName() + " la invitiamo a vaccinarsi per il covid"
		// 4. Aggiungere la stringa alla lista delle lettere
		// 5. Restituire la lista delle lettere
		return null;
	}

	/**
	 * Estrae i pazienti che compiono gli anni oggi.
	 *
	 * @param patients Lista di pazienti
	 * @return Lista di pazienti che compiono gli anni oggi
	 */
	public static List<Patient> filterBirthdayToday(List<Patient> patients)
	{
		// TODO: Implementare filtro per pazienti che compiono gli anni oggi
		// 1. Creare una nuova ArrayList<Patient> vuota per i compleanni
		// 2. Scorrere la lista dei pazienti con un for-each
		// 3. Per ogni paziente, controllare se il mese e il giorno di nascita coincidono con oggi
		//    Usare: p.getDob().getMonthValue() == LocalDate.now().getMonthValue()
		//           && p.getDob().getDayOfMonth() == LocalDate.now().getDayOfMonth()
		// 4. Se sì, aggiungere il paziente alla nuova lista
		// 5. Restituire la lista dei compleanni
		return null;
	}

}
