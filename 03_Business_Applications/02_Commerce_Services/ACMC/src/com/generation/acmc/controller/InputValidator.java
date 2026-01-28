package com.generation.acmc.controller;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.acmc.utils.BigDecimalUtil;
import com.generation.library.Console;
/**
 * Classe di utility per la validazione degli input da console
 * Pattern: Input Validation Centralized - evito di duplicare logica nei controller
 * Tutti i metodi usano while(true) con early return quando l'input è valido
 */
public class InputValidator
{    /**
     * Legge un input obbligatorio dall'utente
     * Loop infinito che esce solo con input valido (non vuoto)
     */
    public static String readRequiredInput(String prompt, String error)
    {
        // TEORIA: while(true) con return interno - pattern standard per input validation
        // Alternativa a do-while con flag booleano, più pulito quando serve loop infinito
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
             // Validazione: non-null E non-vuoto (dopo trim)
            if (input != null && !input.trim().isEmpty())
                return input;      
            Console.print(error + "\n");
        }
    }
    /**
     * Legge un enum da una lista di valori validi
     * Validazione case-insensitive - l'utente può scrivere "bronze" o "BRONZE"
     */
    public static String readValidEnum(String prompt, String error, String[] validValues)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();            
            if (input != null && !input.trim().isEmpty())
            {
                // Ciclo sui valori validi - confronto case-insensitive
                for (String valid : validValues)
                {
                    // equalsIgnoreCase - flessibilità per l'utente
                    if (valid.equalsIgnoreCase(input.trim()))
                        return valid.toUpperCase(); // Normalizzo in uppercase
                }
                // Se arrivo qui, nessun match trovato
                Console.print(error + "\n");
            }
            else
                Console.print("Valore obbligatorio. Riprova.\n");
        }
    }
    /**
     * Legge un numero BigDecimal con valore minimo
     * Supporta formato italiano: 1.000,50 (migliaia con punto, decimali con virgola)
     * Supporta formato internazionale: 1000.50 (decimali con punto)
     */
    public static BigDecimal readValidNumeric(String prompt, String error, BigDecimal min)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();            
            if (input != null && !input.trim().isEmpty())
            {
                try
                {
                    // Usa BigDecimalUtil per convertire formato italiano (1.000,50) in formato standard (1000.50)
                    // TEORIA: BigDecimal costruttore accetta solo formato US (punto per decimali)
                    // Devo preprocessare l'input italiano prima di costruire BigDecimal
                    String converted = BigDecimalUtil.convertToString(input.trim());
                    // Check se conversione fallita (string vuota = errore parsing)
                    if (converted.isEmpty())
                    {
                        Console.print(error + "\n");
                        continue; // Torna all'inizio del loop
                    }                    
                    // Costruisco BigDecimal dal formato normalizzato
                    BigDecimal value = new BigDecimal(converted);                    
                    // Validazione range: valore >= minimo
                    // TEORIA: compareTo() per confronto numerico - mai equals()!
                    // compareTo restituisce: -1 (minore), 0 (uguale), 1 (maggiore)
                    // >= 0 significa "maggiore o uguale"
                    if (value.compareTo(min) >= 0)
                        return value;
                    else
                        Console.print("Minimo: " + min + ". Riprova.\n");
                }
                catch (NumberFormatException e)
                {
                    e.printStackTrace();
                    Console.print(error + "\n");
                }
            }
            else
                Console.print("Valore obbligatorio.\n");
        }
    }
    /**
     * Legge una data obbligatoria nel formato YYYY-MM-DD
     * Pattern ISO 8601 - standard internazionale per date
     */
    public static String readValidDate(String prompt, String error)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();            
            if (input != null && !input.trim().isEmpty())
            {
                try
                {
                    // TEORIA: DateTimeFormatter.ofPattern("yyyy-MM-dd") crea parser ISO
                    // LocalDate.parse() lancia DateTimeParseException se formato sbagliato
                    // Lowercase 'yyyy' = year, uppercase 'MM' = month (case-sensitive!)
                    // 'dd' = day of month (01-31)
                    LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));                    
                    // Se arrivo qui, parsing riuscito
                    return input; // Ritorno stringa originale (già validata)
                }
                catch (DateTimeParseException e)
                {
                	e.printStackTrace();
                    // Formato non valido - es. "2024-13-45" o "24/01/2024"
                    Console.print(error + "\n");
                }
            }
            else
                Console.print("Data obbligatoria. Riprova.\n");
        }
    }
    /**
     * Valida una data opzionale, ritorna null se non valida
     * Variante senza loop - valida una volta sola, non richiede input
     * Usato quando ho già l'input e voglio solo validarlo
     */
    public static String readValidDateOptional(String input)
    {
        try
        {
            // Stesso parsing di readValidDate, ma senza loop
            // TEORIA: yyyy = year (4 cifre), MM = month (01-12), dd = day (01-31)
            // Il pattern è case-sensitive: 'MM' per mese, 'mm' sarebbe minuti!
            LocalDate.parse(input.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return input.trim();
        }
        catch (DateTimeParseException e)
        {
        	e.printStackTrace();
            // Non valido - ritorno null invece di lanciare eccezione
            // Il chiamante decide come gestire (fallback, errore, ecc.)
            return null;
        }
    }
    /**
     * Legge un MembershipLevel dall'utente
     * Parsing enum con gestione IllegalArgumentException
     * 
     * TEORIA: valueOf() lancia IllegalArgumentException se la stringa non matcha
     * nessuna costante dell'enum. Devo sempre fare try-catch.
     */
    public static MembershipLevel readMembershipLevel()
    {
        MembershipLevel level = null;
        // Loop finché non ho un livello valido
        while (level == null)
        {
            // Mostro i valori possibili - UX per guidare l'utente
            Console.print("Livelli disponibili: BRONZE, SILVER, GOLD, GRAY, BANNED\n");
            Console.print("Inserisci il livello da cercare: ");
            String levelStr = Console.readString();
            // Check input vuoto
            if (levelStr == null || levelStr.trim().isEmpty())
            {
                Console.print("Livello obbligatorio. Riprova.\n");
                continue; // Salta al prossimo ciclo
            }            
            try
            {
                // TEORIA: Enum.valueOf() converte String -> enum constant
                // Case-sensitive! Per questo faccio toUpperCase() prima
                // Se la stringa non matcha nessuna costante -> IllegalArgumentException
                level = MembershipLevel.valueOf(levelStr.trim().toUpperCase());
                
                // Se arrivo qui, parsing riuscito -> level non è più null
                // Il while terminerà al prossimo controllo
            }
            catch (IllegalArgumentException e)
            {
                // valueOf() ha fallito - stringa non corrisponde a nessuna costante
            	e.printStackTrace();
                Console.print("Livello non valido. Usa: BRONZE, SILVER, GOLD, GRAY, BANNED. Riprova.\n");
                // level resta null -> il loop continua
            }
        }        
        return level; // Garantito non-null quando esco dal loop
    }
}
