package com.generation.acmc.controller;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Donation;
import com.generation.acmc.model.entities.Expense;
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
            String choice = Console.readString();
            switch (choice)
            {
                case  "1": registerNewMember();            		break;
                case  "2": searchMembersByLastName();      		break;
                case  "3": searchMembersByLevel();         		break;
                case  "4": generateGrayMembersHTMLList();  		break;
                case  "5": Console.print("prova");
                			searchDonationsByPeriod();      	break;
             
                case  "6": searchDonationsByMember();      		break;
                case  "7": searchDonationsByMemberLastYear(); 	break;
                case  "8": modifyMembershipLevel();        		break;
                case  "9": registerNewExpense();           		break;
                case "10": calculateAnnualProfit();        		break;
                case "11": generateMemberProfile();        		break;
                case "12": printWelcomeCard();             		break;
                case "13": printPromotionCard();           		break;
                case  "0": running = false;                		break;
                default: Console.print("Opzione non valida\n");
            }
        }
        Console.print("Arrivederci!\n");
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
			{
				Console.print("- " + error + "\n");
			}
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
			{
				return input;
			}
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
				{
					if (valid.equalsIgnoreCase(input.trim()))
					{
						return valid.toUpperCase();
					}
				}
                Console.print(error + "\n");
            } else
			{
				Console.print("Livello membership obbligatorio. Riprova.\n");
			}
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
					{
						return value;
					} else
					{
						Console.print("Minimo: " + min + ". Riprova.\n");
					}
                }
                catch (NumberFormatException e)
                {
                    Console.print(error + "\n");
                }
            } else
			{
				Console.print("Valore obbligatorio.\n");
			}
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
            } else
			{
				Console.print("Data obbligatoria. Riprova.\n");
			}
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
		{
			Console.print("Nessun socio trovato.\n");
		} else
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

    private static MembershipLevel readMembershipLevel()
    {
        MembershipLevel level = null;

        while (level == null)
        {
            Console.print("Livelli disponibili: BRONZE, SILVER, GOLD, GRAY, BANNED\n");
            Console.print("Inserisci il livello da cercare: ");
            String levelStr = Console.readString();

            if (levelStr == null || levelStr.trim().isEmpty())
            {
                Console.print("Livello obbligatorio. Riprova.\n");
                continue;
            }
            try
            {
                level = MembershipLevel.valueOf(levelStr.trim().toUpperCase());
            }
            catch (IllegalArgumentException e)
            {
                Console.print("Livello non valido. Usa: BRONZE, SILVER, GOLD, GRAY, BANNED. Riprova.\n");
            }
        }
        return level;
    }

    private static void searchMembersByLevel()
    {
        MembershipLevel level = readMembershipLevel();
        List<Member>	members = memberRepo.findByLevel(level);
        if (members.isEmpty())
		{
			Console.print("Nessun socio con questo livello.\n");
		} else
        {
            Console.print("Soci trovati:\n");
            members.forEach(member -> Console.print("- ID: " + member.getId() +
                              ", Nome: " + member.getFirstName() + " " + member.getLastName() +
                              ", Data nascita: " + member.getDob() +
                              ", Reddito: " + member.getIncomeEst() + "\n")
            );
            Console.print("Totale soci con livello " + level + ": " + members.size() + "\n");
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
        Console.print("Inserisci data inizio (YYYY-MM-DD): ");
        String startStr = Console.readString();
        Console.print("Inserisci data fine (YYYY-MM-DD): ");
        String endStr = Console.readString();

        if (startStr == null || endStr == null || startStr.trim().isEmpty() || endStr.trim().isEmpty())
        {
            Console.print("Date obbligatorie. Operazione annullata.\n");
            return;
        }
        try
        {
            LocalDate start = LocalDate.parse(startStr.trim());
            LocalDate end = LocalDate.parse(endStr.trim());
            if (start.isAfter(end))
            {
                Console.print("Data inizio deve essere prima di data fine.\n");
                return;
            }
            List<Donation> donations = donationRepo.findDateBetween(start, end);
            if (donations.isEmpty())
			{
				Console.print("Nessuna donazione trovata.\n");
			} else
            {
                donations.forEach(d -> Console.print("- Data: " + d.getDate() +
                                                ", Importo: " + d.getAmount() +
                                                ", Note: " + d.getNotes() + "\n"));
                BigDecimal total = BigDecimal.ZERO;
                for (Donation d : donations)
				{
					total = total.add(d.getAmount());
				}
                Console.print("Totale: " + total + ", Numero: " + donations.size() + "\n");
            }
        }
        catch (DateTimeParseException e)
        {
            Console.print("Formato data non valido. Usa YYYY-MM-DD.\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
        catch (Exception e)
		{
            Console.print("Errore DB: " + e.getMessage() + "\n");
			e.printStackTrace();
		}
    }


    private static void searchDonationsByMember()
    {
        try
        {
            Console.print("Inserisci l'ID del socio: ");
            int 		memberId 	= Console.readInt();
            Member 		member 		= memberRepo.findById(memberId);
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return;
            }
            List<Donation> donations = donationRepo.findByMemberId(memberId);
            if (donations.isEmpty())
            {
                Console.print("Nessuna donazione trovata per questo socio.\n");
                return;
            }
            Console.print("Donazioni per il socio ID " 				+
            								memberId 				+ " (" 	+
            								member.getFirstName() 	+ " " 	+
            								member.getLastName() 	+ ")\n");
            donations.forEach(d -> Console.print("- ID: " 			+ d.getId() 					+
                                                 ", Data: " 		+ d.getDate() 					+
                                                 ", Importo: " 		+ d.getAmount().doubleValue() 	+
                                                 ", Note: " 		+ d.getNotes() 					+ "\n"));
            double	total = 0;
            for (Donation d : donations)
			{
				total += d.getAmount().doubleValue();
			}
            Console.print("Totale donato: " 		+ total 			+ "\n");
            Console.print("Numero di donazioni: " 	+ donations.size() 	+ "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " 	+
            				e.getMessage() 	+ "\n");
        }
    }

    private static void searchDonationsByMemberLastYear()
    {
        try
        {
            Console.print("Inserisci l'ID del socio: ");
            int memberId = Console.readInt();
            Member member = memberRepo.findById(memberId);
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return;
            }
            List<Donation> donations = donationRepo.findByMemberIdLastYear(memberId);
            if (donations.isEmpty())
            {
                Console.print("Nessuna donazione nell'ultimo anno.\n");
                return;
            }
            Console.print("Donazioni nell'ultimo anno per il socio ID " 						+
            											memberId + " (" 						+
            											member.getFirstName() 					+ " " 	+
            											member.getLastName() 					+ ")\n");
            donations.forEach(d -> Console.print("- ID: " 		+ d.getId() 					+
                                                 ", Data: " 	+ d.getDate() 					+
                                                 ", Importo: " 	+ d.getAmount().doubleValue() 	+
                                                 ", Note: " 	+ d.getNotes() 					+ "\n"));
            double total = 0;
            for (Donation d : donations)
			{
				total += d.getAmount().doubleValue();
			}
            Console.print("Totale donato nell'ultimo anno: " + total + "\n");
            Console.print("Numero di donazioni nell'ultimo anno: " + donations.size() + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    private static void modifyMembershipLevel()
    {
        try
        {
            Console.print("Inserisci l'ID del socio: ");
            int 	memberId = Console.readInt();
            Member 	member = memberRepo.findById(memberId);
            if (member == null)
            {
                Console.print("Errore: socio non trovato.\n");
                return;
            }
            Console.print("Socio: " + member.getFirstName() + " " + member.getLastName() + "\n");
            Console.print("Livello attuale: " + member.getLevel() + "\n\n");
            Console.print("Livelli disponibili:\n");
            Arrays.asList(MembershipLevel.values())
                  .forEach(level -> Console.print("- " + level + "\n"));
            Console.print("\n");
            Console.print("Inserisci il nuovo livello: ");
            String 	levelInput = Console.readString().toUpperCase();
            try
            {
                MembershipLevel	oldLevel = member.getLevel();
                MembershipLevel newLevel = MembershipLevel.valueOf(levelInput);
                member.setLevel(newLevel);
                memberRepo.update(member);
                Console.print("Livello modificato con successo!\n");
                Console.print("Vecchio livello: " + oldLevel + "\n");
                Console.print("Nuovo livello: " + newLevel + "\n");
            }
            catch (IllegalArgumentException e)
            {
                Console.print("Errore: livello non valido.\n");
            }
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }


    private static void registerNewExpense()
    {
        Expense	expense = new Expense();
        expense.setReason(readRequiredInput( "Inserire descrizione/motivo della spesa: ", "Descrizione obbligatoria."));
        expense.setCost(readValidNumeric("Inserire importo della spesa: ", "Importo non valido.", BigDecimal.ZERO));
        Console.print("Inserire data della spesa (YYYY-MM-DD) [invio per data odierna]: ");
        String	dateInput = Console.readString();
        if (dateInput != null && !dateInput.trim().isEmpty())
        {
            String validDate = readValidDateOptional(dateInput);
            if (validDate != null)
			{
				expense.setDate(validDate);
			} else
            {
                Console.print("Formato data non valido. Operazione annullata.\n");
                return;
            }
        }
        List<String> errors = expense.getErrors();
        if (!errors.isEmpty())
        {
            Console.print("Errori di validazione:\n");
            errors.forEach(error -> Console.print("- " + error + "\n"));
            return;
        }
        try
        {
            Expense insertedExpense = expenseRepo.insert(expense);
            Console.print("Spesa ID " 		+ insertedExpense.getId() 		+ " inserita!\n"												);
            Console.print("Descrizione: " 	+ insertedExpense.getReason() 	+ "\n"															);
            Console.print("Importo: " 		+ insertedExpense.getCost() 	+ "\n"															);
            Console.print("Data: " 			+ (insertedExpense.getDate() 	!= null ? insertedExpense.getDate() : "non specificata") + "\n"	);
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    private static String readValidDateOptional(String input)
    {
        try
        {
            LocalDate.parse(input.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            return input.trim();
        }
        catch (DateTimeParseException e)
        {
            return null;
        }
    }
    private static void calculateAnnualProfit()
    {
        try
        {
            Console.print("Inserisci l'anno per cui calcolare il margine: ");
            int year = Console.readInt();
            if (year < 1900 || year > 2100)
            {
                Console.print("Anno non valido.\n");
                return;
            }
            List<Donation> 	donations = donationRepo.findAll();
            List<Expense> 	expenses = expenseRepo.findAll();
            BigDecimal 		totalDonations = BigDecimal.ZERO;
            for (Donation d : donations)
			{
				if (d.getDate().getYear() == year)
				{
					totalDonations = totalDonations.add(d.getAmount());
				}
			}

            BigDecimal 		totalExpenses = BigDecimal.ZERO;
            for (Expense e : expenses)
			{
				if (e.getDate() != null && e.getDate().getYear() == year)
				{
					totalExpenses = totalExpenses.add(e.getCost());
				}
			}

            BigDecimal 		profit = totalDonations.subtract(totalExpenses);
            String formattedProfit = String.format("€ %,.2f", profit);
            if (profit.compareTo(BigDecimal.ZERO) >= 0)
			{
				Console.print("Margine " + year + ": " + formattedProfit + "\n");
			} else
            {
                Console.print("Margine " + year + ": " + formattedProfit + "\n");
                Console.print("ATTENZIONE: Perdita registrata per l'anno " + year + "\n");
            }
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
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
        // TODO: Stampare conferma dell'avvenuta promoz
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
}