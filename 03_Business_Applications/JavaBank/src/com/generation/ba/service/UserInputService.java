package com.generation.ba.service;

import com.generation.library.Console;

public class UserInputService {

	
    /**
     * Richiede e valida l'id del cliente
     */
    public static long requestId() {
        Console.print("Inserisci ID del conto:");
        long id = Console.readLong();
        return id;
    }
    /**
     * Richiede e valida il nome del cliente
     */
    public static String requestName() {
        Console.print("Inserisci il nome del cliente:");
        String name = Console.readString();
        return name.trim();
    }

    /**
     * Richiede e valida il nome del cliente
     */
    public static String requestSurname() {
        Console.print("Inserisci il cognome del cliente:");
        String surname = Console.readString();
        return surname.trim();
    }

    /**
     * Richiede e valida la data di nascita
     */
    public static String requestDateOfBirth() {
        Console.print("Inserisci la data di nascita (formato: YYYY-MM-DD):");
        String dob = Console.readString();
        // Potresti aggiungere validazione del formato qui
        return dob;
    }

    /**
     * Richiede il saldo iniziale
     */
    public static String requestInitialBalance() {
        Console.print("Inserisci il saldo iniziale (formato: 00.00):");
        String balance = Console.readString();
        return balance;
    }

    /**
     * Richiede e valida l'SSN con loop fino a validità
     */
    public static String requestValidSSN() {
        Console.print("\nInserisci SSN (Codice Fiscale):");
        String ssn;
        boolean isValid;

        do {
            ssn = Console.readString();
            isValid = SsnService.validateSSN(ssn);

            if (isValid) {
                Console.print("SSN valido!");
            } else {
                Console.print("SSN non valido. Riprova:");
            }
        } while (!isValid);

        return ssn;
    }
    
    /**
     * Richiede un importo in euro e centesimi
     */
    public static int[] requestAmount(String operation) {
        Console.print("\n=== " + operation.toUpperCase() + " ===");
        Console.print("Inserire euro:");
        int euro = Console.readInt();
        Console.print("Inserire centesimi:");
        int cents = Console.readInt();

        return new int[]{euro, cents};
    }
    

    /**
     * Richiede solo euro
     */
    public static int requestEuro(String prompt) {
        Console.print(prompt);
        return Console.readInt();
    }

    /**
     * Richiede solo centesimi
     */
    public static int requestCents(String prompt) {
        Console.print(prompt);
        return Console.readInt();
    }
}