package com.generation.bt.main;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class PrintTicket 
{

	public static void main(String[] args) 
	{
		String 	departure, arrival;
		boolean valid;
		String 	goOn;
		int 	km = 0;
		int		level; // Chiedere la classe del viaggio. 15 centesimi per la seconda, 20 centesimi per la prima
		String	fullName;
		double	classPrice;
		double	price;
		String 	outputFileName;
		String	label;
		
		do
		{ // CORPO DEL CICLO PRINCIPALE. ogni volta che viene eseguito stampiamo un nuovo biglietto
			
			Console.print("Inserire nome e cognome ");
			fullName = Console.readString();
			do
			{ // ciclo dell'inserimento partenza, ripete finchè non ho una partenza valida
				Console.print("Inserire partenza");
				departure = Console.readString();
				valid = departure.equals("Milano") || departure.equals("Monza") || departure.equals("Osnago") || departure.equals("Lecco");
				if(!valid)
					Console.print("Stazione non valida: scegliere Milano, Monza, Osnago o Lecco");
			}
			while(!valid); // se non è valido torno a inizio ciclo, sotto il do, altrimenti esco, torno a riga 18
			
			do
			{
				// ciclo inserimento destinazione
				Console.print("Inserire destinazione");
				arrival = Console.readString();
				valid = (arrival.equalsIgnoreCase("Milano") || arrival.equalsIgnoreCase("Monza")
							|| arrival.equalsIgnoreCase("Osnago") || arrival.equalsIgnoreCase("Lecco"))
								&& !arrival.equalsIgnoreCase(departure);
				if(!valid)
					Console.print("Stazione non valida: scegliere Milano, Monza, Osnago o Lecco e non scegliere "+departure);
			}
			while(!valid); // se non è valido torno a inizio ciclo, sotto il do, altrimenti esco, torno a riga 27
			
			// io NON SONO un ciclo ma servo a calcolare i km
			switch(departure)
			{
				case "Milano":
					switch(arrival)
					{
						case "Monza":
							km = 15;
						break;
						case "Osnago":
							km = 45;
						break;
						case "Lecco":
							km = 65;
					}
				break;
				case "Monza":
					switch(arrival)
					{
						case "Milano":
							km = 15;
						break;
						case "Osnago":
							km = 30;
						break;
						case "Lecco":
							km = 50;
					}
				break;
				case "Osnago":
					switch(arrival)
					{
						case "Lecco":
							km = 20;
						break;
						case "Monza":
							km = 30;
						break;
						case "Milano":
							km = 45;
					}
				break;
				case "Lecco":
					switch(arrival)
					{
						case "Milano":
							km = 65;
						break;
						case "Monza":
							km = 50;
						break;
						case "Osnago":
							km = 20;
					}
			}
						
			do
			{
				// chiedere finchè non vi darà 1 o 2
				Console.print("Inserire classe scelta: 1 o 2");
				level = Console.readInt();

			}
			while ( !(level == 1 || level == 2) );
			
			// calcolare il prezzo
			classPrice = (level == 1) ?  0.20 : 0.15;
			price = km * classPrice;		
			// stampare il biglietto a schermo
			// stampare in un file html con id del biglietto
						
			
			Console.print("Inserire un altro biglietto?");
			goOn = Console.readString();
			
			
			/*				
				label = Template.load("print/template.html");
				Carica il file template HTML dalla cartella print. 
				Il template contiene i placeholder (come [fullName], [departure], ecc.) che verranno sostituiti con i dati reali.
				
				Console.print("Inserire nome del file in cui stampare l'etichetta: ");
				Chiede all'utente come vuole chiamare il file di output.
				
				String outputFileName = Console.readString() + ".html";
				Legge il nome inserito dall'utente e aggiunge l'estensione .html automaticamente. Se scrivi "biglietto1", diventa "biglietto1.html".
				
				label = label.replace(...).replace(...)
				Sostituisce tutti i placeholder nel template con i dati reali:
				
				[fullName] → il nome del passeggero
				[level] → la classe scelta (1 o 2)
				[departure] → la stazione di partenza
				[arrival] → la stazione di arrivo
				[price] → il prezzo calcolato
				
				
				FileWriter.writeTo("print/" + outputFileName, label);
				Salva il file HTML nella cartella print con il nome scelto e il contenuto sostituito.
				
				
				Console.print("L'etichetta è stata salvata...
				Stampa un messaggio di conferma dicendo dove è stato salvato il file.
				
				In pratica: prende un template vuoto, lo riempie con i tuoi dati, e lo salva come file HTML nella cartella print.
			*/			
			label = Template.load("print/template.html");
			Console.print("Inserire nome del file in cui stampare l'etichetta: ");
	        outputFileName = Console.readString() + ".html";			
			label = label 
					 .replace("[fullName]", fullName)
		                .replace("[level]", String.valueOf(level))
		                .replace("[departure]", departure)
		                .replace("[arrival]", arrival)
		                .replace("[km]", String.valueOf(km))
		                .replace("[price]", String.valueOf(price));
	        FileWriter.writeTo("print/" + outputFileName, label);
	        Console.print("L'etichetta è stata salvata nel file " + outputFileName + " nella cartella print");
		}
		while(goOn.equals("S")); // torna a riga 15:
		
	}
}