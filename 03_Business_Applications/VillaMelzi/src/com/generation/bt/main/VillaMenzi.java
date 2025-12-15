package com.generation.bt.main;
import com.generation.library.Console;
import com.generation.library.FileWriter;

public class VillaMenzi
{

	public static void main(String[] args)
	{
		/**
		 * Villa Menzi è una bellissima villa con giardino botanico in quel di Bellagio
		 * provincia di Como
		 *
		 * L'utente inserisce dei dati prima di entrare. Li inserisce TUTTI perché li salveremo per ragioni statistiche
		 *
		 * LOGICA:
		 *
		 * i residenti a Bellagio o a Civenna non pagano (prezzo 0)
		 * i residenti in provincia di Como, Lecco, Varese o Bergamo pagano 5 euro
		 * NON CUMULATIVO con gli altri sconti
		 *
		 * Il prezzo pieno è 10 euro a cui si possono applicare (ACCUMULARE) degli sconti successivi:
		 *
		 * Quest'anno ricorre il 70esimo dell'apertura della villa al pubblico
		 * chi ha più di 70 anni ESATTI ha diritto a uno sconto di DUE EURO
		 *
		 * chi ha più di 65 anni o meno di 7 anni ha diritto a uno sconto di 1 euro
		 * NON CUMULABILE CON LO SCONTO DEI 70 ANNI
		 *
		 * CASI:
		 * 70 anni esatti
		 * sotto i 7
		 * sopra i 65 ma non 70
		 * tutti gli altri casi
		 *
		 * Gli studenti universitari hanno diritto a uno sconto di 1 euro
		 */

		String	name, surname, city, province, studentInput;
		int		age;
		int		price;
		boolean isStudent;
		
		// Collect user data
		Console.print("=== VILLA MENZI - SISTEMA DI ACQUISTO BIGLIETTI ===\n");
		
		Console.print("Inserisci il tuo nome: ");
		name = Console.readString();
		
		Console.print("Inserisci il tuo cognome: ");
		surname = Console.readString();
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Inserisci la tua città di residenza: ");
		city = Console.readString();
		
		Console.print("Inserisci la tua provincia: ");
		province = Console.readString();
		
		Console.print("Sei uno studente universitario? (si/no): ");
		studentInput = Console.readString();
		isStudent = studentInput.equalsIgnoreCase("si") || studentInput.equals("sì");		
		// Calculate price
		price = calculatePrice(age, province, isStudent);		
		// Display result
		Console.print("\n=== RIEPILOGO ===");
		Console.print("Nome: " + name + " " + surname);
		Console.print("Età: " + age);
		Console.print("Città: " + city);
		Console.print("Provincia: " + province);
		Console.print("Studente universitario: " + (isStudent ? "Sì" : "No"));
		Console.print("\nPrezzo del biglietto: €" + price);
		
		// Save visitor data to file for statistics
		saveStatistic(name, surname, age, city, province, isStudent, price);
	}
	
	/**
	 * Calculates the ticket price based on residency (provincia), age, and student status
	 */
	public static int	calculatePrice(int age, String province, boolean isStudente) {
		province = province.toLowerCase().trim();
		int	MINPRICE = 0;
		int	CHILD = 7;
		int	ADULT = 65;
		int	SENIOR = 70;
		
		if (province.equals("como") || province.equals("lecco") || province.equals("varese") || province.equals("bergamo")) {
			return (5); // Fixed price, non-cumulative
		}
		
		int price = 10;
		
		if (age > SENIOR) 		{
			price -= 2;
		} 
		else if (age > ADULT || age < CHILD) {
			price -= 1;
		}
		if (isStudente) {
			price -= 1;
		}
		if (price < MINPRICE) {
			price = 0;
		}
		return (price);
	}
	
	/**
	 * Saves visitor data to a file for statistical purposes
	 */
	public static void	saveStatistic(String name, String surname, int eta, String city, String province, boolean isStudent, int price) {
		FileWriter	writer = new FileWriter("statistiche_visitatori.txt");
		String		data = name + "|" + surname + "|" + eta + "|" + city + "|" + province + "|" + isStudent + "|" + price + "\n";
		writer.print(data);
		writer.close();
		Console.print("\nDati salvati per le statistiche.");
	}
}