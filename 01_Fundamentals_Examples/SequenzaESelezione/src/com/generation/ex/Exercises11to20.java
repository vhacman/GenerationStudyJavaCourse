package com.generation.ex;

import com.generation.library.Console;

public class Exercises11to20 
{
	// ESERCIZIO 11: Controllo ingresso (età >= 18)
	public static void esercizio11()
	{
		int age;

		Console.print("Inserisci la tua età: ");
		age = Console.readInt();

		String message = (age >= 18) ? "Ingresso consentito" : "Ingresso negato";
		Console.print(message);
	}

	// ESERCIZIO 12: Biglietto con genere e prezzo
	public static void esercizio12()
	{
		int 	age;
		char	gender;

		Console.print("Inserisci la tua età: ");
		age = Console.readInt();

		Console.print("Inserisci il tuo genere (M/F): ");
		gender = Console.readString().charAt(0);

		if (age > 17)
		{
			String message = (gender == 'M' || gender == 'm')
				? "Costo biglietto 18 euro"
				: "Costo biglietto 10 euro";
			Console.print(message);
		}
		else
		{
			Console.print("Ingresso vietato");
		}
	}

	// ESERCIZIO 13: Biglietto museo
	public static void	esercizio13()
	{
		String 	name;
		String 	date;
		int 	age;
		int 	price;

		Console.print("Inserisci il tuo nome: ");
		name = Console.readString();

		Console.print("Inserisci la data (gg/mm/yyyy): ");
		date = Console.readString();

		Console.print("Inserisci la tua età: ");
		age = Console.readInt();

		// Calcolo prezzo: over 65 e under 12 pagano 5 euro, altri 10
		price = (age > 65 || age < 12) ? 5 : 10;

		printMuseumTicket(name, date, price);
	}

	// ESERCIZIO 14: Biglietto Palazzo Mediceo
	public static void	esercizio14()
	{
		String 		name;
		String 		date;
		int 		age;
		int 		hour;
		int 		price;
		final int 	OPENING_TIME = 9;
		final int 	CLOSING_TIME = 18;

		Console.print("Inserisci il tuo nome: ");
		name = Console.readString();

		Console.print("Inserisci la data (gg/mm/yyyy): ");
		date = Console.readString();

		Console.print("Inserisci la tua età: ");
		age = Console.readInt();

		Console.print("Inserisci l'orario di visita (0-23): ");
		hour = Console.readInt();

		// Verifica orario di apertura
		if (hour >= OPENING_TIME && hour < CLOSING_TIME)
		{
			price = (age > 65 || age < 12) ? 5 : 10;
			printTiketPalazzoMediceo(name, date, hour, price);
		}
		else
		{
			Console.print("Errore: Palazzo Mediceo aperto dalle 9 alle 18");
		}
	}

	// ESERCIZIO 15: Palazzo Mediceo con fasce orarie straordinarie
	public static void	esercizio15()
	{
		String 		name;
		String 		date;
		int 		age;
		int 		hour;
		int 		price;
		final int 	OPENING_TIME = 9;
		final int 	CLOSING_TIME = 18;
		final int 	EXTRA_OPENING = 21;
		final int 	EXTRA_CLOSING = 23;
		final int 	EXTRA_SURCHARGE = 2;
		
		Console.print("Inserisci il tuo nome: ");
		name = Console.readString();
		
		Console.print("Inserisci la data (gg/mm/yyyy): ");
		date = Console.readString();
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Inserisci l'orario di visita (0-23): ");
		hour = Console.readInt();
		
		// Verifica orari di apertura
		if ((hour >= OPENING_TIME && hour < CLOSING_TIME) ||  (hour >= EXTRA_OPENING && hour < EXTRA_CLOSING)) 
		{
			// Calcolo prezzo base
			price = (age > 65 || age < 12) ? 5 : 10;
			
			// Maggiorazione per orario straordinario
			if (hour >= EXTRA_OPENING && hour < EXTRA_CLOSING) 
			{
				price += EXTRA_SURCHARGE;
			}
			printTiketPalazzoMediceo(name, date, hour, price);
		} 
		else
		{
			Console.print("Errore: Palazzo Mediceo aperto 9-18 e 21-23");
		}
	}
	
	// ESERCIZIO 16: Palazzo Mediceo gratuito per studenti d'arte
	public static void		esercizio16()
	{
		String 		name;
		String 		date;
		int 		age;
		int 		hour;
		int 		price;
		boolean 	isArtStudent;
		boolean 	isTeacher;
		final int 	OPENING_TIME = 9;
		final int 	CLOSING_TIME = 18;
		final int 	EXTRA_OPENING = 21;
		final int 	EXTRA_CLOSING = 23;
		final int 	EXTRA_SURCHARGE = 2;
		
		Console.print("Inserisci il tuo nome: ");
		name = Console.readString();
		
		Console.print("Inserisci la data (gg/mm/yyyy): ");
		date = Console.readString();
		
		Console.print("Inserisci la tua età: ");
		age = Console.readInt();
		
		Console.print("Inserisci l'orario di visita (0-23): ");
		hour = Console.readInt();
		
		Console.print("Sei uno studente d'arte? (1=si, 0=no): ");
		isArtStudent = Console.readInt() == 1;
		
		Console.print("Sei un insegnante? (1=si, 0=no): ");
		isTeacher = Console.readInt() == 1;
		
		// Verifica orari di apertura
		if ((hour >= OPENING_TIME && hour < CLOSING_TIME) || (hour >= EXTRA_OPENING && hour < EXTRA_CLOSING))
		{
			
			// Gratuito per studenti d'arte e insegnanti in orario straordinario
			if ((isArtStudent || isTeacher) && hour >= EXTRA_OPENING && hour < EXTRA_CLOSING)
			{
				price = 0;
				printFreeTicket(name, date, hour);
			} 
			else
			{
				// Calcolo prezzo normale
				price = (age > 65 || age < 12) ? 5 : 10;
				
				// Maggiorazione per orario straordinario
				if (hour >= EXTRA_OPENING && hour < EXTRA_CLOSING) 
				{
					price += EXTRA_SURCHARGE;
				}
				printTiketPalazzoMediceo(name, date, hour, price);
			}
		}
		else
		{
			Console.print("Errore: Palazzo Mediceo aperto 9-18 e 21-23");
		}
	}
	
	// ESERCIZIO 17: Calcolo BMI e fabbisogno calorico
	public static void	 esercizio17()
	{
		double 	height;
		double 	weight;
		String 	gender;
		double 	bmi;
		int 	calorieNeeds;
		
		Console.print("Inserisci la statura (in metri, es. 1.75): ");
		height = Console.readInt() / 100.0; // Leggi in cm e converti in metri
		
		Console.print("Inserisci il peso (in kg): ");
		weight = Console.readInt();
		
		Console.print("Inserisci il genere (M/F): ");
		gender = Console.readString();
		
		// Validazione input
		if (height <= 0 || weight <= 0)
		{
			Console.print("Errore: statura e peso devono essere positivi");
			return ;
		}
		// Calcolo BMI
		bmi = weight / (height * height);
		
		if (bmi < 5)
		{
			Console.print("Errore: BMI non valido");
			return ;
		}
		// Calcolo fabbisogno calorico (semplificato basato sul peso)
		if (gender == "M" || gender == "m") 
		{
			calorieNeeds = (int)(weight * 25); // Uomini: 25 kcal per kg
		} 
		else
		{
			calorieNeeds = (int)(weight * 22); // Donne: 22 kcal per kg
		}
		
		Console.print("========== ANALISI CORPO ==========");
		Console.print("BMI: " + bmi);
		Console.print("Fabbisogno calorico giornaliero: " + calorieNeeds + " kcal");
		
		if (bmi > 28) 
		{
			Console.print("AVVISO: BMI troppo alto!");
		}
		Console.print("====================================");
	}
	
	// ESERCIZIO 18: Costo vacanza
	public static void	esercizio18()
	{
		String destination;
		int 	outboundTicket;
		int		returnTicket;
		int		hotelNightCost;
		int 	nights;
		int 	totalHotel;
		int 	totalTravel;
		int 	totalVacation;
		
		Console.print("Inserisci la destinazione: ");
		destination = Console.readString();
		
		Console.print("Inserisci il costo del biglietto di andata (€): ");
		outboundTicket = Console.readInt();
		
		Console.print("Inserisci il costo del biglietto di ritorno (€): ");
		returnTicket = Console.readInt();
		
		Console.print("Inserisci il costo per notte dell'albergo (€): ");
		hotelNightCost = Console.readInt();
		
		Console.print("Inserisci il numero di notti: ");
		nights = Console.readInt();
		
		// Validazione input
		if (outboundTicket < 0 || returnTicket < 0 || hotelNightCost < 0 || nights < 0)
		{
			Console.print("Errore: tutti i costi devono essere positivi");
			return ;
		}
		
		// Calcoli
		totalTravel = outboundTicket + returnTicket;
		totalHotel = hotelNightCost * nights;
		totalVacation = totalTravel + totalHotel;
		
		// Stampa riepilogo
		Console.print("========== RIEPILOGO VACANZA ==========");
		Console.print("Destinazione: " + destination);
		Console.print("Biglietto andata: " + outboundTicket + " €");
		Console.print("Biglietto ritorno: " + returnTicket + " €");
		Console.print("Costo totale viaggio: " + totalTravel + " €");
		Console.print("Costo notte albergo: " + hotelNightCost + " €");
		Console.print("Numero notti: " + nights);
		Console.print("Costo totale albergo: " + totalHotel + " €");
		Console.print("----------------------------------------");
		Console.print("COSTO TOTALE VACANZA: " + totalVacation + " €");
		Console.print("=======================================");
	}
	
	// ESERCIZIO 19: Costo vacanza con assicurazione
	public static void 	esercizio19()
	{
		String 	destination;
		int		outboundTicket;
		int 	returnTicket;
		int 	hotelNightCost;
		int 	nights;
		boolean wantInsurance;
		int 	totalHotel;
		int 	totalTravel;
		double 	totalVacation;
		double 	insurance;
		
		Console.print("Inserisci la destinazione: ");
		destination = Console.readString();
		
		Console.print("Inserisci il costo del biglietto di andata (€): ");
		outboundTicket = Console.readInt();
		
		Console.print("Inserisci il costo del biglietto di ritorno (€): ");
		returnTicket = Console.readInt();
		
		Console.print("Inserisci il costo per notte dell'albergo (€): ");
		hotelNightCost = Console.readInt();
		
		Console.print("Inserisci il numero di notti: ");
		nights = Console.readInt();
		
		Console.print("Vuoi aggiungere un'assicurazione? (1=si, 0=no): ");
		wantInsurance = Console.readInt() == 1;
		
		// Validazione input
		if (outboundTicket < 0 || returnTicket < 0 || hotelNightCost < 0 || nights < 0)
		{
			Console.print("Errore: tutti i costi devono essere positivi");
			return;
		}
		
		// Calcoli
		totalTravel = outboundTicket + returnTicket;
		totalHotel = hotelNightCost * nights;
		totalVacation = totalTravel + totalHotel;
		insurance = wantInsurance ? (totalVacation * 0.05) : 0;
		totalVacation += insurance;
		
		// Stampa riepilogo
		Console.print("========== RIEPILOGO VACANZA ==========");
		Console.print("Destinazione: " + destination);
		Console.print("Biglietto andata: " + outboundTicket + " €");
		Console.print("Biglietto ritorno: " + returnTicket + " €");
		Console.print("Costo totale viaggio: " + totalTravel + " €");
		Console.print("Costo notte albergo: " + hotelNightCost + " €");
		Console.print("Numero notti: " + nights);
		Console.print("Costo totale albergo: " + totalHotel + " €");
		
		if (wantInsurance)
		{
			Console.print("Assicurazione (5%): " +  insurance + " €");
		}
		
		Console.print("----------------------------------------");
		Console.print("COSTO TOTALE VACANZA: " + totalVacation + " €");
		Console.print("=======================================");
	}
	
	// ESERCIZIO 20: Costo viaggio in treno
	public static void esercizio20()
	{
		String 		departure;
		String 		destination;
		int 		distance;
		int 		trainType;
		int 		cost;
		final int 	REGIONAL_RATE = 30;      // 30 centesimi per km
		final int 	INTERCITY_RATE = 50;     // 50 centesimi per km
		final int 	HIGHSPEED_RATE = 80;     // 80 centesimi per km
		
		Console.print("Inserisci la stazione di partenza: ");
		departure = Console.readString();
		
		Console.print("Inserisci la stazione di destinazione: ");
		destination = Console.readString();
		
		Console.print("Inserisci la distanza in km: ");
		distance = Console.readInt();
		
		Console.print("Scegli il tipo di treno:");
		Console.print("1. Regionale (0.30€/km)");
		Console.print("2. Intercity (0.50€/km)");
		Console.print("3. Alta velocità (0.80€/km)");
		Console.print("Scelta (1-3): ");
		trainType = Console.readInt();
		
		// Validazione distanza
		if (distance < 0)
		{
			Console.print("Errore: la distanza deve essere positiva");
			return;
		}
		
		// Calcolo costo (in centesimi, poi convertiamo in euro)
		switch(trainType)
		{
			case 1:
				cost = distance * REGIONAL_RATE;
				break;
			case 2:
				cost = distance * INTERCITY_RATE;
				break;
			case 3:
				cost = distance * HIGHSPEED_RATE;
				break;
			default:
				Console.print("Errore: tipo di treno non valido");
				return;
		}
		
		// Stampa biglietto (costo in centesimi convertito in euro)
		Console.print("========== BIGLIETTO TRENO ==========");
		Console.print("Partenza: " + departure);
		Console.print("Destinazione: " + destination);
		Console.print("Distanza: " + distance + " km");
		Console.print("Costo: " + (cost / 100.0) + " €");
		Console.print("====================================");
	}

	// Metodo helper per stampare il biglietto del museo
	public static void printMuseumTicket(String name, String date, int price)
	{
		Console.print("========== BIGLIETTO MUSEO ==========");
		Console.print("Nome: " + name);
		Console.print("Data: " + date);
		Console.print("Prezzo: " + price + " euro");
		Console.print("====================================");
	}

	// Metodo helper per stampare il biglietto del Palazzo Mediceo
	public static void printTiketPalazzoMediceo(String name, String date, int hour, int price)
	{
		Console.print("===== BIGLIETTO PALAZZO MEDICEO =====");
		Console.print("Nome: " + name);
		Console.print("Data: " + date);
		Console.print("Ora: " + hour + ":00");
		Console.print("Prezzo: " + price + " euro");
		Console.print("====================================");
	}

	// Metodo helper per stampare il biglietto gratuito
	public static void printFreeTicket(String name, String date, int hour)
	{
		Console.print("===== BIGLIETTO PALAZZO MEDICEO =====");
		Console.print("Nome: " + name);
		Console.print("Data: " + date);
		Console.print("Ora: " + hour + ":00");
		Console.print("Ingresso libero per ragioni di studio");
		Console.print("====================================");
	}
}