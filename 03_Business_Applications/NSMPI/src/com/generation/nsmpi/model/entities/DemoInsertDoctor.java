package com.generation.nsmpi.model.entities;

import com.generation.library.Console;
import java.util.List;

/**
 * Demo per l'inserimento e validazione di un medico.
 * Raccoglie i dati da console e verifica la validità dell'entità Doctor.
 */
public class DemoInsertDoctor
{
    public static void main(String[] args)
    {
        Doctor                    d = new Doctor();

        Console.print("=== Inserimento Medico ===\n");
        Console.print("Inserisci il nome: ");
        String                    firstName = Console.readString();
        d.setFirstName(firstName);

        Console.print("Inserisci il cognome: ");
        String                    lastName  = Console.readString();
        d.setLastName(lastName);

        Console.print("Inserisci la data di nascita (formato YYYY-MM-DD): ");
        String                    dob       = Console.readString();
        d.setDob(dob);

        Console.print("Inserisci il genere (M/F/N): ");
        String                    gender    = Console.readString();
        d.setGender(gender);

        Console.print("Inserisci il salario: ");
        int                       salary    = Console.readInt();
        d.setSalary(salary);

        // MOSTRA POSSIBILITÀ SPECIALIZZAZIONI
        Console.print("\nSpecializzazioni disponibili:\n");
        Specialty[] tutte = Specialty.values();
        for (int i = 0; i < tutte.length; i++)
        {
            Console.print("  " + (i+1) + ". " + tutte[i] + "\n");
        }

        String                    risposta;
        do
        {
            Console.print("\nVuoi inserire una specializzazione? (s/n): ");
            risposta = Console.readString().toLowerCase();

            if (risposta.equalsIgnoreCase("s"))
            {
                boolean                 added = false;
                while (!added)
                {
                    Console.print("Inserisci la specializzazione: ");
                    String                specialtyStr = Console.readString().toUpperCase();
                    try
                    {
                        Specialty            specialty    = Specialty.valueOf(specialtyStr);
                        List<Specialty>      specialties  = d.getSpecialties();
                        
                        if (specialties.contains(specialty))
                        {
                            Console.print("ERRORE: Specializzazione già inserita: " + specialty + "\n");
                            continue;
                        }
                        
                        d.addSpecialty(specialty);
                        Console.print("Specializzazione aggiunta: " + specialty + "\n");
                        added = true;
                    }
                    catch (Exception e)
                    {
                        Console.print("Specializzazione non valida: " + specialtyStr + "\n");
                    }
                }
            }

        } while (risposta.equalsIgnoreCase("s"));

        // MOSTRA SPECIALIZZAZIONI FINALI
        Console.print("\n=== Specializzazioni del Dottore ===\n");
        List<Specialty> finalSpecialties = d.getSpecialties();
        if (finalSpecialties.isEmpty())
        {
            Console.print("Nessuna specializzazione inserita.\n");
        }
        else
        {
            for (int i = 0; i < finalSpecialties.size(); i++)
            {
                Console.print("  " + (i+1) + ". " + finalSpecialties.get(i) + "\n");
            }
        }

        Console.print("\n=== Risultato Validazione ===\n");

        if (d.isValid())
        {
            Console.print(" Medico valido!\n");
            Console.print(d.toString());
        }
        else
        {
            Console.print(" Medico non valido. Errori rilevati:\n");
            Console.print("\n--- Stampa con foreach ---\n");
            for (String error : d.getErrors())
            {
                Console.print("  - " + error + "\n");
            }

            Console.print("\n--- Stampa con for normale ---\n");
            for (int i = 0; i < d.getErrors().size(); i++)
            {
                String error = d.getErrors().get(i);
                Console.print("  - " + error + "\n");
            }
        }
    }
}
