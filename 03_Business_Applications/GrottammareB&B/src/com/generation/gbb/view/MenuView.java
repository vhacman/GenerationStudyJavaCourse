package com.generation.gbb.view;

import com.generation.library.Console;

/**
 * MenuView class responsible for displaying the main menu.
 * Handles the presentation layer of the application menu.
 */
public class MenuView
{
    /**
     * Prints the main menu with all available commands.
     * Displays options grouped by entity type (Guest, Room, Expense).
     */
    public static void printMainMenu()
    {
        Console.print("\n=== MENU PRINCIPALE ===");
        Console.print("--- GUEST ---");
        Console.print("1  - Inserire nuovo ospite (INSERTGUESTFROMCONSOLE)");
        Console.print("2  - Importare ospiti da CSV (IMPORTGUESTS)");
        Console.print("3  - Lista tutti gli ospiti (GUESTLIST)");
        Console.print("4  - Cerca ospite per ID (FINDGUESTBYID)");
        Console.print("5  - Cerca ospite per Codice Fiscale (FINDGUESTBYSSN)");
        Console.print("6  - Cerca ospiti per Cognome (FINDGUESTBYSURNAMECONTAINING)");
        Console.print("7  - Cerca ospiti per Città (FINDGUESTBYCITY)");
        Console.print("8  - Modifica ospite (UPDATEGUESTFROMCONSOLE)");
        Console.print("9  - Elimina ospite (DELETEGUEST)");
        Console.print("23 - Mostra città ospiti (PRINTCLIENTCITIES)");

        Console.print("--- ROOM ---");
        Console.print("10 - Inserire nuova stanza (INSERTROOMFROMCONSOLE)");
        Console.print("11 - Lista tutte le stanze (ROOMLIST)");
        Console.print("12 - Cerca stanza per ID (FINDROOMBYID)");
        Console.print("13 - Cerca stanze per nome/descrizione (FINDROOMBYNAMEORDESC)");
        Console.print("14 - Stanze sotto budget (ROOMSBYPRICE)");
        Console.print("15 - Modifica stanza (UPDATEROOMFROMCONSOLE)");
        Console.print("16 - Elimina stanza (DELETEROOM)");
        Console.print("25 - Modifica prezzo stanza (MODIFYROOMPRICE)");

        Console.print("--- EXPENSE ---");
        Console.print("17 - Inserire nuova spesa (INSERTEXPENSEFROMCONSOLE)");
        Console.print("18 - Importare spese da CSV (IMPORTEXPENSES)");
        Console.print("19 - Lista tutte le spese (EXPENSELIST)");
        Console.print("20 - Cerca spese per categoria (FINDEXPENSEBYCATEGORY)");
        Console.print("21 - Modifica spesa (UPDATEEXPENSEFROMCONSOLE)");
        Console.print("22 - Elimina spesa (DELETEEXPENSE)");
        Console.print("24 - Modifica prezzo spesa (MODIFYEXPENSEPRICE)");
        Console.print("---");
        Console.print("Q  - Esci");
    }
}
