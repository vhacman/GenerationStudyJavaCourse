package com.generation.acmc.controller;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.acmc.model.repository.DonationRepository;
import com.generation.acmc.model.repository.ExpenseRepository;
import com.generation.acmc.model.repository.MemberRepository;
import com.generation.acmc.model.repository.SQLDonationRepository;
import com.generation.acmc.model.repository.SQLExpenseRepository;
import com.generation.acmc.model.repository.SQLMemberRepository;
import com.generation.library.Console;

public class Main
{
    static MemberRepository   memberRepo   = Context.getDependency(SQLMemberRepository.class);
    static DonationRepository donationRepo = Context.getDependency(SQLDonationRepository.class);
    static ExpenseRepository  expenseRepo  = Context.getDependency(SQLExpenseRepository.class);

    public static void main(String[] args)
    {
        boolean running = true;
        while (running)
        {
            displayMenu();
            Console.print("Scegli un'opzione: ");
            int choice = Console.readInt();
            switch (choice)
            {
                case  1: registerNewMember();            break;
                case  2: searchMembersByLastName();      break;
                case  3: searchMembersByLevel();         break;
                case  4: generateGrayMembersHTMLList();  break;
                case  5: searchDonationsByPeriod();      break;
                case  6: searchDonationsByMember();      break;
                case  7: searchDonationsByMemberLastYear(); break;
                case  8: modifyMembershipLevel();        break;
                case  9: registerNewExpense();           break;
                case 10: calculateAnnualProfit();        break;
                case 11: generateMemberProfile();        break;
                case 12: printWelcomeCard();             break;
                case 13: printPromotionCard();           break;
                case  0: running = false;                break;
                default: Console.print("Opzione non valida\n");
            }
        }
        Console.print("Arrivederci!\n");
    }

    private static void displayMenu()
    {
        Console.print("\n========== GESTIONE ASSOCIAZIONE ==========\n");
        Console.print("1. Registra nuovo socio\n");
        Console.print("2. Cerca socio per cognome\n");
        Console.print("3. Cerca socio per livello\n");
        Console.print("4. Elenco soci Gray (HTML)\n");
        Console.print("5. Cerca donazioni in un periodo\n");
        Console.print("6. Cerca donazioni per socio\n");
        Console.print("7. Cerca donazioni per socio (ultimo anno)\n");
        Console.print("8. Modifica livello socio\n");
        Console.print("9. Registra spesa\n");
        Console.print("10. Calcola margine annuale\n");
        Console.print("11. Genera scheda socio\n");
        Console.print("12. Stampa scheda invito\n");
        Console.print("13. Stampa scheda promozione\n");
        Console.print("0. Esci\n");
        Console.print("============================================\n");
    }

    private static void registerNewMember()
    {
        Member member = new Member();

        member.setFirstName(readRequiredInput(
            "Inserire Nome: ",
            "Nome obbligatorio."
        ));

        member.setLastName(readRequiredInput(
            "Inserire Cognome: ",
            "Cognome obbligatorio."
        ));

        member.setGender(readRequiredInput(
            "Genere (M/F/N): ",
            "Genere obbligatorio."
        ));

        member.setDob(readValidDate(
            "Data nascita (YYYY-MM-DD): ",
            "Formato data non valido."
        ));

        member.setIncomeEst(readValidNumeric(
            "Reddito (>=1.000.000): ",
            "Numero non valido.",
            new BigDecimal("1000000")
        ).toString());

        String[] levels = {"BRONZE", "SILVER", "GOLD", "GRAY"};
        member.setLevel(readValidEnum(
            "Livello (B/S/G/GRAY): ",
            "Livello non valido.",
            levels
        ));

        List<String> errors = member.getErrors();
        if (!errors.isEmpty())
        {
            Console.print("Errori di validazione:\n");
            for (String error : errors)
                Console.print("- " + error + "\n");
            return;
        }

        try
        {
            Member insertedMember = memberRepo.insert(member);
            Console.print("Socio ID " + insertedMember.getId() + " inserito!\n");
            Console.print(insertedMember + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    private static String readRequiredInput(String prompt, String error)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
            if (input != null && !input.trim().isEmpty())
                return input;
            Console.print(error + "\n");
        }
    }

    private static String readValidEnum(String prompt, String error, String[] validValues)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
            if (input != null && !input.trim().isEmpty())
            {
                for (String valid : validValues)
                    if (valid.equalsIgnoreCase(input.trim()))
                        return valid.toUpperCase();
                Console.print(error + "\n");
            }
            else
                Console.print("Livello membership obbligatorio. Riprova.\n");
        }
    }

    private static BigDecimal readValidNumeric(String prompt, String error, BigDecimal min)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
            if (input != null && !input.trim().isEmpty())
            {
                try
                {
                    BigDecimal value = new BigDecimal(input);
                    if (value.compareTo(min) >= 0)
                        return value;
                    else
                        Console.print("Minimo: " + min + ". Riprova.\n");
                }
                catch (NumberFormatException e)
                {
                    Console.print(error + "\n");
                }
            }
            else
                Console.print("Valore obbligatorio.\n");
        }
    }

    private static String readValidDate(String prompt, String error)
    {
        while (true)
        {
            Console.print(prompt);
            String input = Console.readString();
            if (input != null && !input.trim().isEmpty())
            {
                try
                {
                    LocalDate.parse(input, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    return input;
                }
                catch (DateTimeParseException e)
                {
                    Console.print(error + "\n");
                }
            }
            else
                Console.print("Data obbligatoria. Riprova.\n");
        }
    }

    private static void searchMembersByLastName()
    {
        Console.print("Inserisci il cognome (o parte di esso) da cercare: ");
        String lastName = Console.readString();

        if (lastName == null || lastName.trim().isEmpty())
        {
            Console.print("Cognome obbligatorio. Operazione annullata.\n");
            return;
        }

        List<Member> members = memberRepo.findByLastNameContaining(lastName.trim());
        if (members.isEmpty())
            Console.print("Nessun socio trovato.\n");
        else
        {
            Console.print("Soci trovati:\n");
            members.forEach(member -> Console.print("- ID: " + member.getId() +
                ", Nome: " + member.getFirstName() + " " + member.getLastName() +
                ", Livello: " + member.getLevel() +
                ", Data di nascita: " + member.getDob() +
                ", Reddito: " + member.getIncomeEst() + "\n")
            );
            Console.print("Totale soci trovati: " + members.size() + "\n");
        }
    }
    private static void searchMembersByLevel()
    {
        Console.print("Livelli disponibili: BRONZE, SILVER, GOLD, GRAY, BANNED\n");
        Console.print("Inserisci il livello da cercare: ");
        String levelStr = Console.readString();

        if (levelStr == null || levelStr.trim().isEmpty())
        {
            Console.print("Livello obbligatorio. Operazione annullata.\n");
            return;
        }

        try
        {
            MembershipLevel level = MembershipLevel.valueOf(levelStr.trim().toUpperCase());
            List<Member> members = memberRepo.findByLevel(level);

            if (members.isEmpty())
            {
                Console.print("Nessun socio con questo livello.\n");
            }
            else
            {
                Console.print("Soci trovati:\n");
                members.forEach(member ->
                    Console.print("- ID: " + member.getId() +
                                  ", Nome: " + member.getFirstName() + " " + member.getLastName() +
                                  ", Data nascita: " + member.getDob() +
                                  ", Reddito: " + member.getIncomeEst() + "\n")
                );
                Console.print("Totale soci con livello " + level + ": " + members.size() + "\n");
            }
        }
        catch (IllegalArgumentException e)
        {
            Console.print("Livello non valido. Usa: BRONZE, SILVER, GOLD, GRAY, BANNED.\n");
        }
    }
    private static void generateGrayMembersHTMLList()
    {
        // TODO: Chiamare memberRepo.findByLevel(MembershipLevel.GRAY)
        // TODO: Verificare se ci sono soci GRAY
        // TODO: Creare un StringBuilder per costruire il contenuto HTML
        // TODO: Aggiungere intestazione HTML con DOCTYPE, head, title "Soci Gray"
        // TODO: Aggiungere tag body e titolo h1
        // TODO: Creare una tabella HTML con intestazioni: ID, Nome, Cognome, Data Nascita, Reddito
        // TODO: Iterare sulla lista e per ogni socio aggiungere una riga <tr> con celle <td>
        // TODO: Chiudere i tag table, body e html
        // TODO: Salvare il contenuto in un file "soci_gray.html" nella directory corrente
        // TODO: Gestire IOException con try-catch
        // TODO: Stampare conferma con percorso del file generato
    }
    private static void searchDonationsByPeriod()
    {
        // TODO: Chiedere data inizio periodo (formato YYYY-MM-DD)
        // TODO: Convertire in LocalDate usando LocalDate.parse()
        // TODO: Chiedere data fine periodo (formato YYYY-MM-DD)
        // TODO: Convertire in LocalDate usando LocalDate.parse()
        // TODO: Validare che data inizio sia prima di data fine
        // TODO: Recuperare tutte le donazioni con donationRepo.findAll()
        // TODO: Filtrare le donazioni che hanno data compresa tra inizio e fine
        // TODO: Per ogni donazione filtrata, stampare: data, importo, note, ID socio
        // TODO: Calcolare e stampare il totale delle donazioni nel periodo
        // TODO: Stampare il numero di donazioni trovate
    }
    private static void searchDonationsByMember()
    {
        // TODO: Chiedere l'ID del socio
        // TODO: Verificare che il socio esista usando memberRepo.findById()
        // TODO: Se non esiste, stampare errore e uscire
        // TODO: Chiamare donationRepo.findByMemberId() passando l'ID
        // TODO: Verificare se la lista è vuota
        // TODO: Se vuota, stampare "Nessuna donazione trovata per questo socio"
        // TODO: Se non vuota, stampare intestazione con nome del socio
        // TODO: Iterare e stampare per ogni donazione: ID, data, importo, note
        // TODO: Calcolare e stampare il totale donato dal socio
        // TODO: Stampare il numero di donazioni effettuate
    }
    private static void searchDonationsByMemberLastYear()
    {
        // TODO: Chiedere l'ID del socio
        // TODO: Verificare che il socio esista usando memberRepo.findById()
        // TODO: Se non esiste, stampare errore e uscire
        // TODO: Chiamare donationRepo.findByMemberIdLastYear() passando l'ID
        // TODO: Verificare se la lista è vuota
        // TODO: Se vuota, stampare "Nessuna donazione nell'ultimo anno"
        // TODO: Se non vuota, stampare intestazione con nome socio e periodo (ultimo anno)
        // TODO: Iterare e stampare per ogni donazione: ID, data, importo, note
        // TODO: Calcolare e stampare il totale donato nell'ultimo anno
        // TODO: Stampare il numero di donazioni nell'ultimo anno
    }
    private static void modifyMembershipLevel()
    {
        // TODO: Chiedere l'ID del socio da modificare
        // TODO: Recuperare il Member usando memberRepo.findById()
        // TODO: Verificare che il socio esista, altrimenti stampare errore
        // TODO: Stampare dati attuali del socio (nome, cognome, livello corrente)
        // TODO: Mostrare elenco livelli disponibili (BRONZE, SILVER, GOLD, GRAY, BANNED)
        // TODO: Chiedere il nuovo livello da assegnare
        // TODO: Convertire in MembershipLevel usando valueOf()
        // TODO: Gestire IllegalArgumentException se livello non valido
        // TODO: Impostare il nuovo livello con member.setLevel()
        // TODO: Aggiornare nel database con memberRepo.update()
        // TODO: Gestire SQLException con try-catch
        // TODO: Stampare conferma modifica con vecchio e nuovo livello
    }
    private static void registerNewExpense()
    {
        // TODO: Creare un nuovo oggetto Expense vuoto
        // TODO: Chiedere la descrizione/motivo della spesa e impostarla con setReason
        // TODO: Chiedere l'importo della spesa e impostarlo con setCost
        // TODO: Chiedere la data della spesa (formato YYYY-MM-DD, può essere vuota)
        // TODO: Se data inserita, impostarla con setDate, altrimenti lasciare null
        // TODO: Validare i dati chiamando expense.getErrors()
        // TODO: Se ci sono errori, stamparli e chiedere di reinserire
        // TODO: Se validazione OK, inserire nel database con expenseRepo.insert()
        // TODO: Gestire eventuali SQLException con try-catch
        // TODO: Stampare conferma inserimento con ID generato e dettagli spesa
    }
    private static void calculateAnnualProfit()
    {
        // TODO: Chiedere all'utente l'anno per cui calcolare il margine
        // TODO: Creare un'istanza di FinancialService passando donationRepo e expenseRepo
        // TODO: Chiamare il metodo calculateAnnualProfit(anno) del servizio
        // TODO: Stampare il risultato formattato con valuta (es. "Margine 2025: € 15.000,00")
        // TODO: Gestire il caso di margine negativo con messaggio appropriato (perdita)
    }
    private static void generateMemberProfile()
    {
        // TODO: Chiedere l'ID del socio di cui generare la scheda
        // TODO: Recuperare il Member dal repository usando findById
        // TODO: Verificare che il socio esista, altrimenti stampare messaggio di errore
        // TODO: Recuperare tutte le donazioni del socio usando findByMemberId
        // TODO: Calcolare il totale delle donazioni del socio
        // TODO: Stampare intestazione con dati anagrafici (nome, cognome, livello, reddito)
        // TODO: Stampare elenco di tutte le donazioni con data, importo e note
        // TODO: Stampare il totale complessivo donato
        // TODO: Opzionale: salvare la scheda in un file HTML o TXT
    }
    private static void printWelcomeCard()
    {
        // TODO: Chiedere l'ID del nuovo socio da accogliere
        // TODO: Recuperare il Member dal repository usando findById
        // TODO: Verificare che il socio esista, altrimenti stampare messaggio di errore
        // TODO: Stampare intestazione di benvenuto ("Benvenuto/a nell'Associazione!")
        // TODO: Stampare nome completo del socio
        // TODO: Stampare il livello di membership assegnato (usando toString() per nome localizzato)
        // TODO: Stampare messaggio motivazionale in base al livello
        // TODO: Stampare data di iscrizione (data odierna)
        // TODO: Opzionale: generare un codice socio univoco da stampare
    }
    private static void printPromotionCard()
    {
        // TODO: Chiedere l'ID del socio da promuovere
        // TODO: Recuperare il Member dal repository usando findById
        // TODO: Verificare che il socio esista, altrimenti stampare messaggio di errore
        // TODO: Verificare che il socio sia attivo (non BANNED) usando level.isActive()
        // TODO: Ottenere il livello successivo usando level.nextLevel()
        // TODO: Verificare che ci sia effettivamente una promozione (livello diverso dall'attuale)
        // TODO: Stampare intestazione di congratulazioni per la promozione
        // TODO: Stampare nome completo del socio
        // TODO: Stampare livello attuale e nuovo livello (usando toString() per nomi localizzati)
        // TODO: Stampare data della promozione
        // TODO: Aggiornare il livello del socio nel database usando setLevel e update
        // TODO: Stampare conferma dell'avvenuta promozione
    }
}
