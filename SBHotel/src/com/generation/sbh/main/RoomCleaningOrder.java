package com.generation.sbh.main;

import com.generation.library.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class RoomCleaningOrder
{

	public static void orderCleaningService()
	{
		// chiede input da utente per numero di stanza
		Console.print("Numero stanza da pulire (1-4): ");
		int	roomNumber = Console.readInt();

		// controllo su che stanza sceglie, SOLO DA 1 A 4
		if (roomNumber < 1 || roomNumber > 4)
		{
			Console.print("Errore: stanze valide da 1 a 4");
			return;
		}

		// mi prendo dati attuali
		LocalDate today = LocalDate.now();
		int year = today.getYear();

		// Variabili per la data
		int cleanDay, cleanMonth, cleanYear;
		boolean dataValida = false;

		// Ciclo finché la data non è valida
		do
		{
			// Chiedi GIORNO
			do
			{
				Console.print("Inserire giorno (1-31): ");
				cleanDay = Console.readInt();
				
				if(cleanDay < 1 || cleanDay > 31)
				{
					Console.print("Giorno non valido. Riprova.");
				}
			} while(cleanDay < 1 || cleanDay > 31);

			// Chiedi MESE
			do
			{
				Console.print("Inserire mese (1-12): ");
				cleanMonth = Console.readInt();
				
				if(cleanMonth < 1 || cleanMonth > 12)
				{
					Console.print("Mese non valido. Riprova.");
				}
			} while(cleanMonth < 1 || cleanMonth > 12);

			// Chiedi ANNO
			do
			{
				Console.print("Inserire anno (" + year + " o successivo): ");
				cleanYear = Console.readInt();
				
				if(cleanYear < year)
				{
					Console.print("Anno non valido. Deve essere " + year + " o successivo. Riprova.");
				}
			} while(cleanYear < year);

			// Determina il numero massimo di giorni con switch e operatore ternario
			int maxDays;
			switch(cleanMonth)
			{
				case 1: case 3: case 5: case 7: case 8: case 10: case 12:
					maxDays = 31;
				break;

				case 4: case 6: case 9: case 11:
					maxDays = 30;
				break;

				case 2:
					// Operatore ternario per anno bisestile
					maxDays = (cleanYear % 4 == 0) ? 29 : 28;
				break;

				default:
					maxDays = 31;
			}

			// Valida la combinazione giorno-mese
			if(cleanDay > maxDays)
			{
				Console.print("ERRORE: Il giorno " + cleanDay + " non esiste nel mese " + cleanMonth + "/" + cleanYear + ". Reinserire la data.");
				dataValida = false;
			}
			else
			{
				dataValida = true;
			}

		} while(!dataValida);

		// Creo la data di pulizia e controllo che sia presente o futura
		LocalDate cleaningDate = LocalDate.of(cleanYear, cleanMonth, cleanDay);
		
		if(cleaningDate.isBefore(today))
		{
			Console.print("Data non valida. La data deve essere presente o futura. Errore");
			return;
		}

		// Ora pulizia (13-21) con validazione
		int cleanTime;
		do
		{
			Console.print("Ora pulizia (13-21): ");
			cleanTime = Console.readInt();
			
			if(cleanTime < 13 || cleanTime > 21)
			{
				Console.print("Errore: ora deve essere tra 13 e 21. Riprova.");
			}
		} while(cleanTime < 13 || cleanTime > 21);

		// Nome addetto con validazione
		String cleaner;
		do
		{
			Console.print("Nome addetto: ");
			cleaner = Console.readString();
			
			if(cleaner.trim().isEmpty())
			{
				Console.print("Errore: nome addetto obbligatorio. Riprova.");
			}
		} while(cleaner.trim().isEmpty());

		// Creates a formatter using the specified pattern
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		// This date will be passed to the formatter to produce a string
		String cleaningDateStr = cleaningDate.format(formatter);
		String currentDate = today.format(formatter);

		String template = Template.load("assets/template/cleaningTemplate.html");
		template = template
				.replace("[roomNumber]", roomNumber + "")
				.replace("[date]", cleaningDateStr)
				.replace("[time]", cleanTime + "")
				.replace("[cleaner]", cleaner)
				.replace("[creationDate]", currentDate);

		String filename = "print/CLEAN_Room" + roomNumber + "_" + cleanDay + "-" + cleanMonth + "-" + cleanYear + ".html";
		FileWriter.writeTo(filename, template);
		Console.print("Ordine di pulizia salvato in " + filename);
	}
}