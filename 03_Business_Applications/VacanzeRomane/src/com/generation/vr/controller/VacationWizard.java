package com.generation.vr.controller;

import com.generation.util.*;
import com.generation.vr.view.WeekendTripView;

/**
 * Controller principale dell'applicazione (Main Entry Point).
 * Responsabilità:
 * - Inizializzazione di Repository e Service
 * - Gestione del menu principale
 * - Routing delle scelte utente ai metodi del Service
 * - Gestione del ciclo di vita dell'applicazione
 */
public class VacationWizard
{
	public static void main(String[] args)
	{
		WeekendTripRepository 	repository = new WeekendTripRepository();
		WeekendTripService 		service = new WeekendTripService(repository);
		Console.print(Template.load("template/label_benvenuto.txt"));
		boolean	running = true;

		while (running) {
			WeekendTripView.showMenu();
			int choice = Console.readIntBetween("Scegli un'opzione: ", "Opzione non valida!", 0, 7);
			switch (choice) {
				case 1:
					service.createNewTrip();
					break;
				case 2:
					service.showAllTrips();
					break;
				case 3:
					service.searchTripById();
					break;
				case 4:
					service.saveTrips();
					break;
				case 5:
					service.loadTrips();
					break;
				case 6:
					service.generateHTML();
					break;
				case 7:
					service.showStatistics();
					break;
				case 0:
					Console.print(Template.load("template/label_arrivederci.txt"));
					running = false;
					break;
				default:
					Console.print("\n✗ Opzione non valida! Riprova.\n");
					break;
			}
		}
	}
}