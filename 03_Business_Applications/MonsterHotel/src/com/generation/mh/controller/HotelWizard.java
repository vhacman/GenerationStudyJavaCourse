package com.generation.mh.controller;

import com.generation.mh.view.HotelView;
import com.generation.util.Console;

/**
 * Classe principale (entry point) del sistema di prenotazioni del Monster Hotel.
 * Coordina le interazioni tra Repository e Service, gestendo il menu principale.
 *
 * ARCHITETTURA:
 * - HotelWizard (Controller) → coordina tutto
 * - BookingService → logica di business
 * - BookingRepository → accesso dati e persistenza
 */
public class HotelWizard
{
	// =========== ATTRIBUTI =====================
	private static BookingRepository repository;
	private static BookingService service;

	// =========== MAIN ==========================

	/**
	 * Punto di ingresso dell'applicazione Monster Hotel.
	 *
	 * COSA FA (dal punto di vista esterno):
	 * - Inizializza il repository (contenitore dati) e il service (logica business)
	 * - Mostra messaggi di benvenuto
	 * - Avvia il loop del menu principale che:
	 *   • Mostra le opzioni disponibili
	 *   • Legge la scelta dell'utente (0-7)
	 *   • Esegue l'azione corrispondente delegando a Service o Repository
	 *   • Continua finché l'utente non sceglie "0 - Esci"
	 * - All'uscita mostra un messaggio di commiato
	 *
	 * MENU DISPONIBILE:
	 * [1] Nuova Prenotazione    → service.createNewBooking()
	 * [2] Elenco Prenotazioni   → service.showAllBookings()
	 * [3] Cerca Prenotazione    → service.searchBookingById()
	 * [4] Salva Prenotazioni    → service.saveBookings()
	 * [5] Carica Prenotazioni   → service.loadBookings()
	 * [6] Genera HTML           → service.generateHTML()
	 * [7] Statistiche Hotel     → service.showStatistics()
	 * [0] Esci                  → termina il programma
	 *
	 * @param args Argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		// Inizializzazione
		repository = new BookingRepository();
		service = new BookingService(repository);

		boolean running = true;

		Console.print("Inizializzazione Monster Hotel System...");
		Console.print("Sistema pronto");

		// Loop principale
		while (running)
		{
			HotelView.showMenu();

			int choice = Console.readIntBetween("Inserisci la tua scelta:", "Scelta non valida! riprova", 0, 7);

			switch (choice)
			{
				case 1:
					service.createNewBooking();
					break;
				case 2:
					service.showAllBookings();
					break;
				case 3:
					service.searchBookingById();
					break;
				case 4:
					service.saveBookings();
					break;
				case 5:
					service.loadBookings();
					break;
				case 6:
					service.generateHTML();
					break;
				case 7:
					service.showStatistics();
					break;
				case 0:
					running = false;
					Console.print("Grazie per aver visitato il Monster Hotel");
					Console.print("Che la notte ti protegga... Arrivederci\n");
					break;
			}
		}
	}
}
