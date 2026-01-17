package com.generation.gbb.controller;

import com.generation.gbb.repository.factory.ExpenseRepositoryFactory;
import com.generation.gbb.repository.factory.GuestRepositoryFactory;
import com.generation.gbb.repository.factory.RoomRepositoryFactory;
import com.generation.gbb.repository.factory.TripRepositoryFactory;
import com.generation.gbb.repository.interfaces.ExpenseRepository;
import com.generation.gbb.repository.interfaces.GuestRepository;
import com.generation.gbb.repository.interfaces.RoomRepository;
import com.generation.gbb.repository.interfaces.TripRepository;
import com.generation.gbb.view.MenuView;
import com.generation.library.Console;

public class Main
{
	static GuestRepository   guestRepo   = GuestRepositoryFactory.make();
	static RoomRepository    roomRepo    = RoomRepositoryFactory.make();
	static ExpenseRepository expenseRepo = ExpenseRepositoryFactory.make();
	static TripRepository    tripRepo    = TripRepositoryFactory.make();

	static GuestService   guestService;
	static RoomService    roomService;
	static ExpenseService expenseService;
	static TripService    tripService;

	/**
	 * Punto di ingresso dell'applicazione.
	 * Inizializza dipendenze e avvia loop principale.
	 * 
	 * @param args Argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		if (guestRepo == null)
		{
			Console.print("Missing dependency GuestRepository");
			return;
		}

		if (roomRepo == null)
		{
			Console.print("Missing dependency RoomRepository");
			return;
		}

		if (expenseRepo == null)
		{
			Console.print("Missing dependency ExpenseRepository");
			return;
		}

		if (tripRepo == null)
		{
			Console.print("Missing dependency TripRepository");
			return;
		}

		/*
		 * Pattern → Dependency Injection (via costruttore):
		 * Service ricevono Repository come dipendenze
		 * Disaccoppiamento: Service non creano Repository
		 * Facilita testing e manutenibilità
		 */
		guestService   = new GuestService(guestRepo);
		roomService    = new RoomService(roomRepo);
		expenseService = new ExpenseService(expenseRepo);
		tripService    = new TripService(tripRepo);

		Console.print("Benvenuti a Grottammare B&B");

		String cmd;
		MenuView.printMainMenu();
		do
		{
			Console.print("\nInserisci comando:");
			cmd = Console.readString().toUpperCase().trim();
			processCommand(cmd);
		}
		while (!cmd.equalsIgnoreCase("Q/q"));
	}

	/**
	 * Processa il comando utente routing alla funzionalità appropriata.
	 * 
	 * @param cmd Comando inserito dall'utente (numero o stringa)
	 */
	private static void processCommand(String cmd)
	{
		switch (cmd)
		{
			// ==================== GUEST COMMANDS ====================
			case "1":
			case "INSERTGUESTFROMCONSOLE":
				guestService.insertGuestFromConsole();
				break;

			case "2":
			case "IMPORTGUESTS":
				guestService.importGuests();
				break;

			case "3":
			case "GUESTLIST":
				guestService.guestList();
				break;

			case "4":
			case "FINDGUESTBYID":
				guestService.findGuestById();
				break;

			case "5":
			case "FINDGUESTBYSSN":
				guestService.findGuestBySSN();
				break;

			case "6":
			case "FINDGUESTBYSURNAMECONTAINING":
				guestService.findGuestBySurnameContaining();
				break;

			case "7":
			case "FINDGUESTBYCITY":
				guestService.findGuestByCity();
				break;

			case "8":
			case "UPDATEGUESTFROMCONSOLE":
				guestService.updateGuestFromConsole();
				break;

			case "9":
			case "DELETEGUEST":
				guestService.deleteGuest();
				break;

			case "23":
			case "PRINTCLIENTCITIES":
				guestService.printClientCities();
				break;

			// ==================== ROOM COMMANDS ====================
			case "10":
			case "INSERTROOMFROMCONSOLE":
				roomService.insertRoomFromConsole();
				break;

			case "11":
			case "ROOMLIST":
				roomService.roomList();
				break;

			case "12":
			case "FINDROOMBYID":
				roomService.findRoomById();
				break;

			case "13":
			case "FINDROOMBYNAMEORDESC":
				roomService.findRoomByNameOrDescriptionContaining();
				break;

			case "14":
			case "ROOMSBYPRICE":
				roomService.findRoomsByPriceLesserThan();
				break;

			case "15":
			case "UPDATEROOMFROMCONSOLE":
				roomService.updateRoomFromConsole();
				break;

			case "16":
			case "DELETEROOM":
				roomService.deleteRoom();
				break;

			case "25":
			case "MODIFYROOMPRICE":
				roomService.modifyRoomPrice();
				break;

			// ==================== EXPENSE COMMANDS ====================
			case "17":
			case "INSERTEXPENSEFROMCONSOLE":
				expenseService.insertExpenseFromConsole();
				break;

			case "18":
			case "IMPORTEXPENSES":
				expenseService.importExpenses();
				break;

			case "19":
			case "EXPENSELIST":
				expenseService.expenseList();
				break;

			case "20":
			case "FINDEXPENSEBYCATEGORY":
				expenseService.findExpenseByCategory();
				break;

			case "21":
			case "UPDATEEXPENSEFROMCONSOLE":
				expenseService.updateExpenseFromConsole();
				break;

			case "22":
			case "DELETEEXPENSE":
				expenseService.deleteExpense();
				break;

			case "24":
			case "MODIFYEXPENSEPRICE":
				expenseService.modifyExpensePrice();
				break;

			// ==================== TRIP COMMANDS ====================
			case "26":
			case "INSERTTRIPFROMCONSOLE":
				tripService.insertTripFromConsole();
				break;

			case "27":
			case "TRIPLIST":
				tripService.tripList();
				break;

			case "28":
			case "FINDTRIPBYID":
				tripService.findTripById();
				break;

			case "29":
			case "FINDTRIPBYCITY":
				tripService.findTripsByCity();
				break;

			case "30":
			case "FINDTRIPBYYEAR":
				tripService.findTripsByYear();
				break;

			case "31":
			case "DELETETRIP":
				tripService.deleteTrip();
				break;

			// ==================== TABLE CREATION COMMANDS ====================
			case "32":
			case "CREATETABLEGUEST":
				guestService.createTable();
				break;

			case "33":
			case "CREATETABLEROOM":
				roomService.createTable();
				break;

			case "34":
			case "CREATETABLEEXPENSE":
				expenseService.createTable();
				break;

			case "35":
			case "CREATETABLETRIP":
				tripService.createTable();
				break;

			// ==================== SYSTEM COMMANDS ====================
			case "Q":
				Console.print("Goodbye");
				break;

			default:
				Console.print("Comando non riconosciuto");
		}
	}
}
