package com.generation.acmc.controller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.acmc.model.repository.SQLMemberRepository;
import com.generation.library.Console;
import com.generation.library.Template;

/**
 * Controller per la gestione dei soci (membri) dell'associazione
 */
public class MemberController
{
    private static SQLMemberRepository memberRepo = Context.getDependency(SQLMemberRepository.class);

    /**
     * Menu di gestione soci
     */
    public static void manageMembersMenu()
    {
        boolean back = false;
        while (!back)
        {
            displayMembersMenu();
            Console.print("Scegli un'opzione: ");
            String choice = Console.readString();
            switch (choice)
            {
                case "1": registerNewMember();          break;
                case "2": searchMembersByLastName();    break;
                case "3": searchMembersByLevel();       break;
                case "4": modifyMembershipLevel();      break;
                case "0": back = true;                  break;
                default: Console.print("Opzione non valida\n");
            }
        }
    }

    private static void displayMembersMenu()
    {
        String menu = Template.load("template/menu/members_menu.txt");
        if (menu == null || menu.isEmpty())
            Console.print("Menu non trovato");
        else
            Console.print(menu);
    }

    /**
     * Registra un nuovo socio nell'associazione
     */
    private static void registerNewMember()
    {
        Member member = new Member();

        member.setFirstName(InputValidator.readRequiredInput(
            "Inserire Nome: ",
            "Nome obbligatorio."
        ));

        member.setLastName(InputValidator.readRequiredInput(
            "Inserire Cognome: ",
            "Cognome obbligatorio."
        ));

        member.setGender(InputValidator.readRequiredInput(
            "Genere (M/F/N): ",
            "Genere obbligatorio."
        ));

        member.setDob(InputValidator.readValidDate(
            "Data nascita (YYYY-MM-DD): ",
            "Formato data non valido."
        ));

        member.setIncomeEst(InputValidator.readValidNumeric(
            "Reddito (>=1.000.000): ",
            "Numero non valido.",
            new BigDecimal("1000000")
        ).toString());

        String[] levels = {"BRONZE", "SILVER", "GOLD", "GRAY"};
        member.setLevel(InputValidator.readValidEnum(
            "Livello (BRONZE/SILVER/GOLD/GRAY): ",
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
            Console.print("Socio ID " + insertedMember.getId() + " inserito con successo!\n");
            Console.print(insertedMember + "\n");
        }
        catch (SQLException e)
        {
            Console.print("Errore DB: " + e.getMessage() + "\n");
        }
    }

    /**
     * Ricerca soci per cognome (o parte di esso)
     */
    private static void searchMembersByLastName()
    {
        Console.print("Inserisci il cognome (o parte di esso) da cercare: ");
        String lastName = Console.readString();

        if (lastName == null || lastName.trim().isEmpty())
        {
            Console.print("Cognome obbligatorio. Operazione annullata.\n");
            return;
        }

        List<Member> members;
		try
		{
			members = memberRepo.findByLastNameContaining(lastName.trim());
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
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
    }

    /**
     * Ricerca soci per livello di membership
     */
    private static void searchMembersByLevel()
    {
        MembershipLevel level = InputValidator.readMembershipLevel();
        List<Member> members;
		try
		{
			members = memberRepo.findByLevel(level);
	        if (members.isEmpty())
	            Console.print("Nessun socio con questo livello.\n");
	        else
	        {
	            Console.print("Soci trovati:\n");
	            members.forEach(member -> Console.print("- ID: " + member.getId() +
	                ", Nome: " + member.getFirstName() + " " + member.getLastName() +
	                ", Data nascita: " + member.getDob() +
	                ", Reddito: " + member.getIncomeEst() + "\n")
	            );
	            Console.print("Totale soci con livello " + level + ": " + members.size() + "\n");
	        }
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    /**
     * Modifica il livello di membership di un socio (promozione/retrocessione/ban)
     */
    private static void modifyMembershipLevel()
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

            Console.print("Socio: " + member.getFirstName() + " " + member.getLastName() + "\n");
            Console.print("Livello attuale: " + member.getLevel() + "\n\n");
            Console.print("Livelli disponibili:\n");
            Arrays.asList(MembershipLevel.values())
                  .forEach(level -> Console.print("- " + level + " (" + level.name() + ")\n"));
            Console.print("\n");
            Console.print("Inserisci il nuovo livello (italiano o inglese): ");
            String levelInput = Console.readString().trim();

            try
            {
                MembershipLevel oldLevel = member.getLevel();
                MembershipLevel newLevel = parseMembershipLevel(levelInput);

                if (newLevel == null)
                {
                    Console.print("Errore: livello non valido.\n");
                    Console.print("Usa uno dei seguenti: BRONZE/Bronzo, SILVER/Argento, GOLD/Oro, GRAY/Grigio, BANNED\n");
                    return;
                }

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

    /**
     * Metodo pubblico per ottenere un member by ID (usato da altri controller)
     */
    public static Member findMemberById(int memberId) throws SQLException
    {
        return memberRepo.findById(memberId);
    }

    /**
     * Metodo pubblico per ottenere tutti i soci di un certo livello
     */
    public static List<Member> findMembersByLevel(MembershipLevel level) throws SQLException
    {
        return memberRepo.findByLevel(level);
    }

    /**
     * Metodo pubblico per aggiornare un membro (usato da altri controller)
     */
    public static void updateMember(Member member) throws SQLException
    {
        memberRepo.update(member);
    }

    /**
     * Parser per MembershipLevel che accetta input sia in italiano che in inglese
     *
     * @param input Stringa da convertire in MembershipLevel
     * @return Il MembershipLevel corrispondente, null se non valido
     */
    private static MembershipLevel parseMembershipLevel(String input)
    {
        if (input == null || input.isEmpty()) 
            return null;

        String normalized = input.trim().toUpperCase();

        // Prova prima con il nome inglese della costante enum
        try
        {
            return MembershipLevel.valueOf(normalized);
        }
        catch (IllegalArgumentException e)
        {
            // Se fallisce, prova con la traduzione italiana
            return switch(normalized)
            {
                case "BRONZO" -> MembershipLevel.BRONZE;
                case "ARGENTO" -> MembershipLevel.SILVER;
                case "ORO" -> MembershipLevel.GOLD;
                case "GRIGIO" -> MembershipLevel.GRAY;
                default -> null;
            };
        }
    }
}
