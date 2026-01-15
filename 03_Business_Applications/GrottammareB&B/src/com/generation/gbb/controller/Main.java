package com.generation.gbb.controller;

import com.generation.gbb.repository.ExpenseRepository;
import com.generation.gbb.repository.ExpenseRepositoryFactory;
import com.generation.gbb.repository.GuestRepository;
import com.generation.gbb.repository.GuestRepositoryFactory;
import com.generation.gbb.repository.RoomRepository;
import com.generation.gbb.repository.RoomRepositoryFactory;
import com.generation.gbb.view.MenuView;
import com.generation.library.Console;

/**
 * Classe principale dell'applicazione Grottammare B&B.
 * Gestisce il ciclo principale del programma e coordina i Service.
 *
 * Pattern → Front Controller + Command Pattern:
 *      Main (Front Controller)  →  riceve comandi utente
 *      processCommand()         →  routing comandi ai Service appropriati
 *      Service Layer            →  esecuzione logica di business
 */
public class Main
{
    static GuestRepository   guestRepo   = GuestRepositoryFactory.make();
    static RoomRepository    roomRepo    = RoomRepositoryFactory.make();
    static ExpenseRepository expenseRepo = ExpenseRepositoryFactory.make();

    static GuestService   guestService;
    static RoomService    roomService;
    static ExpenseService expenseService;

    /**
     * Punto di ingresso dell'applicazione.
     * Inizializza dipendenze e avvia loop principale.
     *
     * @param args Argomenti da linea di comando (non utilizzati)
     */
    public static void main(String[] args)
    {
        /*
         * Pattern → Fail-Fast (Validazione Precoce):
         *      Verifica dipendenze critiche all'avvio
         *      Termina immediatamente se mancano componenti essenziali
         *      Previene errori Runtime durante l'esecuzione
         */
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

        /*
         * Pattern → Dependency Injection (via costruttore):
         *      Service ricevono Repository come dipendenze
         *      Disaccoppiamento: Service non creano Repository
         *      Facilita testing e manutenibilità
         */
        guestService   = new GuestService(guestRepo);
        roomService    = new RoomService(roomRepo);
        expenseService = new ExpenseService(expenseRepo);

        Console.print("Benvenuti a Grottammare B&B");

        /*
         * Pattern → Event Loop (Ciclo Eventi):
         *      do-while loop  →  esegue almeno una volta
         *      Legge comando  →  processa comando  →  ripete
         *      Termina quando utente sceglie "Q" (quit)
         */
        String cmd;
        do
        {
            MenuView.printMainMenu();
            Console.print("\nInserisci comando:");
            cmd = Console.readString().toUpperCase().trim();

            processCommand(cmd);
        }
        while (!cmd.equals("Q"));
    }

    /**
     * Processa il comando utente routing alla funzionalità appropriata.
     *
     * @param cmd Comando inserito dall'utente (numero o stringa)
     */
    private static void processCommand(String cmd)
    {
        /*
         * Pattern → Command Pattern + Strategy Pattern:
         *      switch/case        →  routing comando (Command)
         *      Service.metodo()   →  strategia di esecuzione (Strategy)
         *      Separazione tra richiesta e esecuzione
         */
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

            // ==================== SYSTEM COMMANDS ====================
            case "Q":
                Console.print("Goodbye");
                break;

            default:
                Console.print("Comando non riconosciuto");
        }
    }
}
