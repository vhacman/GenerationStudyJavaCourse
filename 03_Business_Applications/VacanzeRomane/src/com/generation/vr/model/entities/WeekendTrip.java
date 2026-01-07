package com.generation.vr.model.entities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import com.generation.util.Template;

/**
 * Classe di aggregazione: rappresenta un weekend turistico a Roma
 * Contiene: dati cliente, data viaggio, attrazioni, hotel, guida turistica
 */
public class WeekendTrip
{
	// Attributi
	private int 		id;					// ID univoco (gestito dal repository)
	String 				name, surname;		// Nome e cognome cliente
	LocalDate 			date;				// Data inizio weekend (sabato)
	Attraction 			saturdayVisit;		// Attrazione sabato
	Attraction 			sundayVisit;		// Attrazione domenica
	Hotel 				affiliatedHotel;	// Hotel convenzionato
	TourGuide 			tourGuide;			// Guida turistica

	// Costruttore vuoto 
	public WeekendTrip() {}
	// Costruttore completo
	public WeekendTrip(String name, String surname, LocalDate date, Attraction saturdayVisit,
					   Attraction sundayVisit, Hotel affiliatedHotel, TourGuide tourGuide)
	{
		this.name = name;
		this.surname = surname;
		this.date = date;
		this.saturdayVisit = saturdayVisit;
		this.sundayVisit = sundayVisit;
		this.affiliatedHotel = affiliatedHotel;
		this.tourGuide = tourGuide;
	}

	// GETTER E SETTER
	public int 			getId() { return id; }
	public String 		getName() { return name; }
	public String 		getSurname() { return surname; }
	public LocalDate 	getDate() { return date; }
	public Attraction 	getSaturdayVisit() { return saturdayVisit; }
	public Attraction 	getSundayVisit() { return sundayVisit; }
	public Hotel 		getAffiliatedHotel() { return affiliatedHotel; }
	public TourGuide	getTourGuide() { return tourGuide; }
	
	public void 		setId(int id) { this.id = id; }
	public void 		setName(String name) { this.name = name; }
	public void 		setSurname(String surname) { this.surname = surname; }
	public void 		setDate(LocalDate date) { this.date = date; }
	public void 		setSaturdayVisit(Attraction saturdayVisit) { this.saturdayVisit = saturdayVisit; }
	public void 		setSundayVisit(Attraction sundayVisit) { this.sundayVisit = sundayVisit; }
	public void 		setAffiliatedHotel(Hotel affiliatedHotel) { this.affiliatedHotel = affiliatedHotel; }
	public void 		setTourGuide(TourGuide tourGuide) { this.tourGuide = tourGuide; }

	/**
	 * Validazione che restituisce TUTTI gli errori in una stringa
	 * 
	 * === STRINGBUILDER  teoria ===
	 * StringBuilder è una classe mutabile per costruire stringhe in modo efficiente.
	 * 
	 * Differenza con String normale:
	 * - String è IMMUTABILE: ogni concatenazione crea un nuovo oggetto in memoria
	 *   Esempio: str = str + "a" + "b" + "c" → crea 3 oggetti String diversi
	 * 
	 * - StringBuilder è MUTABILE: modifica lo stesso oggetto in memoria
	 *   Esempio: sb.append("a").append("b").append("c") → 1 solo oggetto
	 * 
	 * Perché usarlo qui:
	 * In questo metodo concateniamo potenzialmente 7+ stringhe di errore.
	 * Con StringBuilder: 7 append = 1 solo oggetto = efficiente
	 * 
	 * Operazioni principali:
	 * - new StringBuilder() → crea un contenitore vuoto
	 * - append(stringa) → aggiunge testo alla fine
	 * - toString() → converte in String normale da restituire
	 * @return stringa errori (vuota se valido)
	 */
	public String		validate() {
		StringBuilder	errors = new StringBuilder();

		if (name == null || name.trim().isEmpty())
			errors.append("- Il nome non può essere vuoto\n");	
		if (surname == null || surname.trim().isEmpty())
			errors.append("- Il cognome non può essere vuoto\n");		
		if (date == null)
			errors.append("- La data non può essere nulla\n");
		else if (!date.isAfter(LocalDate.now()))
			errors.append("- La data deve essere nel futuro\n");
		if (saturdayVisit == null)
			errors.append("- L'attrazione del sabato non può essere nulla\n");
		else if (!saturdayVisit.isValid())
			errors.append("- L'attrazione del sabato non è valida\n");
		if (sundayVisit == null)
			errors.append("- L'attrazione della domenica non può essere nulla\n");
		else if (!sundayVisit.isValid())
			errors.append("- L'attrazione della domenica non è valida\n");
		if (affiliatedHotel == null)
			errors.append("- L'hotel non può essere nullo\n");
		else if (!affiliatedHotel.isValid())
			errors.append("- L'hotel non è valido\n");
		if (tourGuide == null)
			errors.append("- La guida turistica non può essere nulla\n");
		else if (!tourGuide.isValid())
			errors.append("- La guida turistica non è valida\n");
		return errors.toString();
	}

	/**
	 * Validazione boolean semplificata
	 * @return true se valido, false altrimenti
	 */
	public boolean isValid() { return validate().isEmpty(); }

	/**
	 * Calcola prezzo totale: hotel (2 notti) + biglietti attrazioni
	 * @return prezzo totale (BigDecimal per precisione monetaria)
	 */
	public BigDecimal	getTotalPrice()
	{
		BigDecimal		total;
		
		total = BigDecimal.ZERO;
		// Hotel: 2 notti
		if (affiliatedHotel != null && affiliatedHotel.getPrice() != null)
			total = total.add(affiliatedHotel.getPrice().multiply(new BigDecimal("2")));
		// Attrazione sabato
		if (saturdayVisit != null && saturdayVisit.getTicketPrice() != null)
			total = total.add(saturdayVisit.getTicketPrice());
		// Attrazione domenica
		if (sundayVisit != null && sundayVisit.getTicketPrice() != null)
			total = total.add(sundayVisit.getTicketPrice());
		return total;
	}

	/**
	 * Restituisce prezzo formattato con 2 decimali (es: "250.50")
	 */
	public String getFormattedTotalPrice(){ return getTotalPrice().setScale(2, RoundingMode.HALF_UP).toString(); }

	/**
	 * Genera il codice HTML del trip usando il template
	 *
	 * Funzionamento:
	 * 1. Carica il template HTML dal file template/template.html
	 * 2. Sostituisce tutti i placeholder con i valori del trip:
	 *    - [name] → nome cliente
	 *    - [surname] → cognome cliente
	 *    - [date] → data del viaggio
	 *    - [saturday] → nome attrazione sabato
	 *    - [sunday] → nome attrazione domenica
	 *    - [hotel] → nome hotel
	 *    - [tourguide] → nome guida turistica
	 *    - [rating] → stelle guida (★★★★)
	 *    - [totalprice] → prezzo totale formattato
	 * 3. Restituisce l'HTML completo pronto per essere salvato
	 *
	 * @return stringa contenente l'HTML completo del trip
	 */
	public String generateHTML()
	{
		String	template;
		String	stars;

		template = Template.load("template/template.html");
		// Genera le stelle per il rating della guida
		stars = "";
		if (tourGuide != null)
		{
			for (int i = 0; i < tourGuide.getRating(); i++)
				stars += "★";
		}
		// Sostituzione placeholders
		template = template.replace("[name]", name != null ? name : "N/A");
		template = template.replace("[surname]", surname != null ? surname : "N/A");
		template = template.replace("[date]", date != null ? date.toString() : "N/A");
		template = template.replace("[saturday]", saturdayVisit != null ? saturdayVisit.getName() : "N/A");
		template = template.replace("[sunday]", sundayVisit != null ? sundayVisit.getName() : "N/A");
		template = template.replace("[hotel]", affiliatedHotel != null ? affiliatedHotel.getName() : "N/A");
		template = template.replace("[tourguide]", tourGuide != null ? tourGuide.getName() : "N/A");
		template = template.replace("[rating]", stars);
		template = template.replace("[totalprice]", getFormattedTotalPrice());
		return template;
	}
}