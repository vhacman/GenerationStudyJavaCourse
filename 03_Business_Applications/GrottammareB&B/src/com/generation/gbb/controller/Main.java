package com.generation.gbb.controller;

import java.util.List;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.model.entities.Room;
import com.generation.gbb.repository.GuestRepository;
import com.generation.gbb.repository.GuestRepositoryFactory;
import com.generation.gbb.repository.RoomRepository;
import com.generation.gbb.repository.RoomRepositoryFactory;
import com.generation.library.Console;

public class Main
{
    static GuestRepository 	guestRepo = GuestRepositoryFactory.make();
    static RoomRepository  	roomRepo  = RoomRepositoryFactory.make();

    /**
     * Application entry point.
     * Initializes repositories and starts interactive menu loop.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args)
    {
    	// Dependency injection validation
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
        
        Console.print("Benvenuti a Grottammare B&B");

        String cmd;
        do
        {
            printMainMenu();
            Console.print("\nInserisci comando:");
            cmd = Console.readString().toUpperCase().trim();

            processCommand(cmd);
        }
        while (!cmd.equals("Q"));
    }
    
    /**
     * Processes user command using switch-case pattern.
     * Supports both numeric and command name inputs.
     * 
     * @param cmd User input command (uppercase)
     */
    private static void processCommand(String cmd)
    {
        switch (cmd)
        {
            case "1":
            case "GUESTLIST":
                guestList();
                break;

            case "2":
            case "FINDGUESTBYID":
                findGuestById();
                break;

            case "3":
            case "FINDGUESTBYSSN":
                findGuestBySSN();
                break;

            case "4":
            case "FINDGUESTBYSURNAMECONTAINING":
                findGuestBySurnameContaining();
                break;

            case "5":
            case "FINDGUESTBYCITY":
                findGuestByCity();
                break;
            case "6":
            case "ROOMLIST":
                roomList();
                break;

            case "7":
            case "FINDROOMBYID":
                findRoomById();
                break;

            case "8":
            case "FINDROOMBYNAMEORDESC":
                findRoomByNameOrDescriptionContaining();
                break;

            case "9":
            case "ROOMSBYPRICE":
                findRoomsByPriceLesserThan();
                break;

            case "Q":
                Console.print("Goodbye");
                break;

            default:
                Console.print("Comando non riconosciuto");
        }
    }

    /**
     * Lists all registered guests with basic info (ID + full name).
     */
    private static void guestList()
    {
        List<Guest> all = guestRepo.findAll();
        for (Guest g : all)
            Console.print(	g.getId() 			+ " - " +
            				g.getFirstName() 	+ " " 	+
            				g.getLastName());
    }

    /**
     * Finds single guest by unique ID.
     * Displays "Non trovato" if guest doesn't exist.
     */
    private static void findGuestById()
    {
        Console.print("Inserire id ospite:");
        int          id = Console.readInt();
        Guest        g  = guestRepo.findById(id);
        if (g == null)
            Console.print("Non trovato");
        else
            Console.print(	g.getId() 			+ " - " +
            				g.getFirstName() 	+ " " 	+
            				g.getLastName());
    }

    /**
     * Finds single guest by Tax Code (Codice Fiscale).
     * Case-insensitive search with exact match.
     */
    private static void findGuestBySSN()
    {
        Console.print("Inserire Codice Fiscale:");
        String       ssn   = Console.readString().trim();
        Guest        guest = guestRepo.findBySSN(ssn);
        if (guest == null)
            Console.print("Non trovato");
        else
            Console.print(	guest.getId() 			+ " - " +
            				guest.getFirstName() 	+ " " 	+
            				guest.getLastName());
    }


    /**
     * Finds guests whose surname contains specified text (case-insensitive).
     * Shows count and city info for matches.
     */
    private static void findGuestBySurnameContaining()
    {
        Console.print("Inserire parte del cognome:");
        String       part = Console.readString().trim();
        List<Guest>  found = guestRepo.findBySurnameContaining(part);
        if (found.isEmpty())
            Console.print("Nessun ospite trovato con cognome contenente '" + part + "'");
        else
        {
            Console.print("\n=== " + found.size() + " Ospiti Trovati ===");
            for (Guest gst : found)
                Console.print(gst.getId() 			+ " - " +
                              gst.getFirstName() 	+ " " 	+
                              gst.getLastName() 	+ " (" 	+
                              gst.getCity() 		+ ")");
        }
    }


    /**
     * Finds all guests living in specified city (exact match).
     * Displays count and basic guest info.
     */
    private static void findGuestByCity()
    {
        Console.print("Inserire nome città:");
        String           city        = Console.readString().trim();
        List<Guest>      foundByCity = guestRepo.findByCity(city);
        if (foundByCity.isEmpty())
            Console.print("Nessun ospite trovato nella città '" + city + "'");
        else
        {
            Console.print("\n=== " + foundByCity.size() + " Ospiti Trovati ===");
            for (Guest gst : foundByCity)
                Console.print(gst.getId()      		+ " - " +
                              gst.getFirstName() 	+ " " 	+
                              gst.getLastName()  	+ " (" 	+
                              gst.getCity()      	+ ")");
        }
    }

    /**
     * Finds single room by unique ID.
     * Shows room details including size, floor, and price.
     */
    private static void findRoomById()
    {
        Console.print("Inserire id stanza:");
        int    id = Console.readInt();
        Room   r  = roomRepo.findById(id);
        if (r == null)
            Console.print("Stanza non trovata");
        else
            Console.print(	r.getId() 		+ " - " 		+
            				r.getName() 	+ " (" 			+
            				r.getSize() 	+ "mq, Piano " 	+
            				r.getFloor() 	+ ") €" 		+
            				r.getPrice());
    }

    /**
     * Finds rooms whose name OR description contains specified text.
     * Case-insensitive partial match search.
     */
    private static void findRoomByNameOrDescriptionContaining()
    {
        Console.print("Inserire parte del nome o della descrizione: ");
        String      part  = Console.readString().trim();
        List<Room>  found = roomRepo.findByNameOrDescriptionContaining(part);
        if (found.isEmpty())
            Console.print("Nessuna stanza trovata con '" + part + "'");
        else
        {
            Console.print("\n=== " + found.size() + " Stanze Trovate ===");
            for (Room room : found)
                Console.print(room.getId()    	+ " - " 		+
                              room.getName()   	+ " (" 			+
                              room.getSize()   	+ "mq, Piano " 	+
                              room.getFloor()  	+ ") €" 		+
                              room.getPrice());
        }
    }

    /**
     * Finds all rooms cheaper than specified budget.
     * Shows available affordable rooms with full details.
     */
    private static void findRoomsByPriceLesserThan()
    {
        Console.print("Inserire budget massimo:");
        int          budget = Console.readInt();
        List<Room>   rooms  = roomRepo.findByPriceLesserThan(budget);
        if (rooms.isEmpty())
            Console.print("Nessuna stanza sotto €" + budget);
        else
        {
            Console.print("\n=== " + rooms.size() + " Stanze sotto €" + budget + " ===");
            for (Room room : rooms)
                Console.print(room.getId()    	+ " - " 		+
                              room.getName()   	+ " (" 			+
                              room.getSize()   	+ "mq, Piano " 	+
                              room.getFloor()  	+ ") €" 		+
                              room.getPrice());
        }
    }

    /**
     * Lists all available rooms with complete details.
     * Shows total count and room specifications.
     */
    private static void roomList()
    {
        List<Room>	all = roomRepo.findAll();
        Console.print("\n=== Lista Stanze Disponibili (" + all.size() + ") ===");
        for (Room r : all)
            Console.print(r.getId()    	+ " - " 		+
                          r.getName()   + " (" 			+
                          r.getSize()   + "mq, Piano " 	+
                          r.getFloor()  + ") €" 		+
                          r.getPrice());
    }
    
    /**
     * Displays the main interactive menu.
     * Shows all available guest and room management options.
     */
    private static void printMainMenu()
    {
        Console.print("\n=== MENU PRINCIPALE ===");
        Console.print("1 - Lista tutti gli ospiti (GUESTLIST)");
        Console.print("2 - Cerca ospite per ID (FINDGUESTBYID)");
        Console.print("3 - Cerca ospite per Codice Fiscale (FINDGUESTBYSSN)");
        Console.print("4 - Cerca ospiti per Cognome (FINDGUESTBYSURNAMECONTAINING)");
        Console.print("5 - Cerca ospiti per Città (FINDGUESTBYCITY)");
        Console.print("6 - Lista tutte le stanze (ROOMLIST)");
        Console.print("7 - Cerca stanza per ID (FINDROOMBYID)");
        Console.print("8 - Cerca stanze per nome/descrizione (FINDROOMBYNAMEORDESC)");
        Console.print("9 - Stanze sotto budget (ROOMSBYPRICE)");
        Console.print("Q - Esci");
    }
}
