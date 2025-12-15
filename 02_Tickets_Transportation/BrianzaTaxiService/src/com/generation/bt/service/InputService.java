package com.generation.bt.service;

import com.generation.library.Console;

/**
 * InputService: Gestisce tutti gli input dell'utente dalla console
 * RESPONSABILITÀ: Raccogliere e validare i dati di input
 */
public class InputService
{
    public static int askMinutes() {
        int minutes;

        Console.print("Inserire i minuti della corsa: ");
        minutes = Console.readInt();

        while (minutes <= 0)
        {
            Console.print("Errore: i minuti devono essere maggiori di 0!");
            Console.print("Inserire i minuti della corsa: ");
            minutes = Console.readInt();
        }

        return minutes;
    }


    public static int askLevel() {
        int level;

        Console.print("Inserire la classe (1 o 2): ");
        level = Console.readInt();

        while (level != 1 && level != 2)
        {
            Console.print("Errore: la classe deve essere 1 o 2!");
            Console.print("Inserire la classe (1 o 2): ");
            level = Console.readInt();
        }
        return level;
    }

    public static int askHour() {
        int hour;

        Console.print("Inserire l'ora della prenotazione (0-23): ");
        hour = Console.readInt();

        while (hour < 0 || hour > 23)
        {
            Console.print("Errore: l'ora deve essere compresa tra 0 e 23!");
            Console.print("Inserire l'ora della prenotazione (0-23): ");
            hour = Console.readInt();
        }

        return hour;
    }

 
    public static int askMinutesStart() {
        int minutes;

        Console.print("Inserire i minuti di partenza (0-59): ");
        minutes = Console.readInt();

        while (minutes < 0 || minutes > 59)
        {
            Console.print("Errore: i minuti devono essere compresi tra 0 e 59!");
            Console.print("Inserire i minuti di partenza (0-59): ");
            minutes = Console.readInt();
        }

        return minutes;
    }

 
    public static int askHourArrival() {
        int hour;

        Console.print("Inserire l'ora di arrivo (0-23): ");
        hour = Console.readInt();

        while (hour < 0 || hour > 23)
        {
            Console.print("Errore: l'ora deve essere compresa tra 0 e 23!");
            Console.print("Inserire l'ora di arrivo (0-23): ");
            hour = Console.readInt();
        }

        return hour;
    }


    public static int askMinutesArrival() {
        int minutes;

        Console.print("Inserire i minuti di arrivo (0-59): ");
        minutes = Console.readInt();

        while (minutes < 0 || minutes > 59)
        {
            Console.print("Errore: i minuti devono essere compresi tra 0 e 59!");
            Console.print("Inserire i minuti di arrivo (0-59): ");
            minutes = Console.readInt();
        }

        return minutes;
    }


    public static String askOutputFileName() {
        Console.print("Inserire nome del file in cui stampare l'etichetta: ");
        return Console.readString() + ".html";
    }

    // ===== RACCOLTA DATI UTENTE =====

    /**
     * Chiede se si vuole creare un utente per questa corsa
     */
    public static boolean askCreateUser() {
        Console.print("\nVuoi registrare un utente per questa corsa? (s/n): ");
        String response = Console.readString().toLowerCase();
        return response.equals("s") || response.equals("si") || response.equals("y") || response.equals("yes");
    }

    /**
     * Chiede il nome dell'utente
     */
    public static String askUserName() {
        Console.print("Nome: ");
        String name = Console.readString();

        while (name == null || name.trim().isEmpty()) {
            Console.print("Errore: il nome non può essere vuoto!");
            Console.print("Nome: ");
            name = Console.readString();
        }

        return name.trim();
    }

    /**
     * Chiede il cognome dell'utente
     */
    public static String askUserSurname() {
        Console.print("Cognome: ");
        String surname = Console.readString();

        while (surname == null || surname.trim().isEmpty()) {
            Console.print("Errore: il cognome non può essere vuoto!");
            Console.print("Cognome: ");
            surname = Console.readString();
        }

        return surname.trim();
    }

    /**
     * Chiede il numero di telefono
     */
    public static String askPhoneNumber() {
        Console.print("Telefono: ");
        String phone = Console.readString();

        while (phone == null || phone.trim().isEmpty()) {
            Console.print("Errore: il telefono non può essere vuoto!");
            Console.print("Telefono: ");
            phone = Console.readString();
        }

        return phone.trim();
    }

    /**
     * Chiede se si vogliono inserire dati completi
     */
    public static boolean askFullUserData() {
        Console.print("\nVuoi inserire i dati completi (codice fiscale, email, indirizzo)? (s/n): ");
        String response = Console.readString().toLowerCase();
        return response.equals("s") || response.equals("si") || response.equals("y") || response.equals("yes");
    }

    /**
     * Chiede il codice fiscale (opzionale)
     */
    public static String askFiscalCode() {
        Console.print("Codice Fiscale (opzionale): ");
        return Console.readString();
    }

    /**
     * Chiede l'email (opzionale)
     */
    public static String askEmail() {
        Console.print("Email (opzionale): ");
        return Console.readString();
    }

    /**
     * Chiede l'indirizzo (opzionale)
     */
    public static String askAddress() {
        Console.print("Indirizzo (opzionale): ");
        return Console.readString();
    }

    /**
     * Chiede la città (opzionale)
     */
    public static String askCity() {
        Console.print("Città (opzionale): ");
        return Console.readString();
    }

    /**
     * Chiede il CAP (opzionale)
     */
    public static String askZipCode() {
        Console.print("CAP (opzionale): ");
        return Console.readString();
    }
}