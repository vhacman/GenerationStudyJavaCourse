package com.generation.bw.controller;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.bw.context.Context;
import com.generation.bw.extractor.BikeDailyExtractor;
import com.generation.bw.model.entities.Bike;
import com.generation.bw.model.entities.BikeStatus;
import com.generation.bw.repository.BikeRepository;
import com.generation.library.Console;
import com.generation.library.view.EntityView;

public class Main 
{

	static BikeDailyExtractor 	extractor 		= Context.getDependency(BikeDailyExtractor.class);
	static BikeRepository 		bikeRepo 		= Context.getDependency(BikeRepository.class);
		
	static EntityView<Bike> 	basicBikeView 	= b-> b.getId()+" "+b.getBrand()+" "+b.getModel()+"\n";
	static EntityView<Bike>		detailedBikeView = b -> 
													    "\nDettaglio moto:\n" +
													    "ID: " + b.getId() + "\n" +
													    "Brand: " + b.getBrand() + "\n" +
													    "Model: " + b.getModel() + "\n" +
													    "Plate: " + b.getPlate() + "\n" +
													    "Power: " + b.getPower() + " cc\n" +
													    "Cost: " + b.getCost() + " €\n" +
													    "Work: " + b.getWork() + " €\n" +
													    "Price: " + b.getPrice() + " €\n" +
													    "Status: " + b.getStatus() + "\n";

	public static void main(String[] args) 
	{
		
		Console.print(basicBikeView.render(bikeRepo.findProcessing()));
		
		// fatelo diventare un CASO dello switch importDaily();
		
		/*
		 * funzioni richieste:
		 * 
		 * - inserire una nuova moto manualmente (raro)
		 * - avanzare lo stato di una moto 
		 * (chiamare il metodo advance e poi invocare update)
		 * - stampare elenco delle moto in lavorazione
		 * - stampare dettaglio moto per id o per targa
		 * - 
		 */
		boolean running = true;
		while(running)
		{
			displayMenu();
			Console.print("Scegli un'opzione: ");
			int choice = Console.readInt();
			switch (choice)
			{
				case 1: insertManualBike();         break;
				case 2: advanceBikeStatus();        break;
	            case 3: listProcessingBikes();      break;
	            case 4: showBikeDetails();          break;
	            case 5: importDaily();              break;
	            case 0: running = false;            break;
	            default: Console.print("Opzione non valida\n");
			 }
		}
        Console.print("Arrivederci!\n");
	
	}

	private static void showBikeDetails()
	{
	    Console.print("Cerca moto per:\n1. ID\n2. Targa\n");
	    int choice = Console.readInt();
	    Bike bike = null;
	    if (choice == 1)
	    {
	        Console.print("Inserisci ID: ");
	        bike = bikeRepo.findById(Console.readInt());
	    }
	    else if (choice == 2)
	    {
	        Console.print("Inserisci targa: ");
	        bike = bikeRepo.findByPlate(Console.readString());
	    }
	    else
	    {
	        Console.print("Opzione non valida.\n");
	        return;
	    }
	    if (bike == null)
	    {
	        Console.print("Moto non trovata.\n");
	        return;
	    }
	    Console.print(detailedBikeView.render(bike));
	}



	private static void listProcessingBikes()
	{
	    List<Bike> bikes = bikeRepo.findProcessing();

	    if (bikes == null || bikes.isEmpty())
	    {
	        Console.print("Nessuna moto in lavorazione.\n");
	        return;
	    }
	    Console.print("\n========== MOTO IN LAVORAZIONE ==========\n");
	    Console.print(basicBikeView.render(bikes));
	    Console.print("Totale: " + bikes.size() + " moto\n");
	}


	/**
    * Inserts a new bike manually through user input.
    */
	private static void insertManualBike()
	{
		
		try
		{
			Console.print("Brand: ");
			String	brand = Console.readString();
			Console.print("Model: ");
			String model = Console.readString();	
			Console.print("Plate: ");
			String plate = Console.readString();			
			Console.print("Power(cc): ");
			int power = Console.readInt();			
			Console.print("Cost (€): ");
			int cost = Console.readInt();			
			Console.print("Work (€): ");
			int work = Console.readInt();			
			Console.print("Price (€): ");
			int price = Console.readInt();
			Bike bike = new Bike(brand, model, plate, power, cost, work, price, BikeStatus.ARRIVED);
			
			List<String> errors = bike.getErrors();
			if (errors != null && !errors.isEmpty())
	        {
				Console.print("\nErrori di Validazione: \n");
				errors.forEach(e -> Console.print("- " + e + "\n"));
				return ;
	        }			
			bike =  bikeRepo.insert(bike);
			Console.print("\n Moto inserita con successo! ID:" + bike.getId());
			
		}
		catch (SQLException e)
		{
			Console.print("\nErrore database durante l'inserimento: " + e.getMessage() + "\n");
		}
		catch (NumberFormatException e)
		{
			Console.print("\nErrore: inserire valori numerici validi per power, cost, work e price\n");
		}

	}

	/**
    * Advances a bike's status to the next workflow stage.
    */
	private static void advanceBikeStatus()
	{
		try
		{
			Console.print("Id moto da avanzare:");
			int	id = Console.readInt();	
			Bike	bike = bikeRepo.findById(id);
			if (bike == null)
			{
				Console.print("Moto non trovata \n");
				return ;
			}
			/**
			* Bike.status  →  BikeStatus.getNext()  →  Repository.update()
	        *  
	        *  Sfrutta la catena di responsabilità implementata nell'enum
	        *  BikeStatus per avanzare automaticamente allo stato successivo.
	        *  La transizione è type-safe e rispetta il workflow definito.
			*/
			BikeStatus nextStatus = bike.getStatus().getNext();
			if(nextStatus == null)
			{
				Console.print("La moto è già nello stato finale (DELIVERED)\n");
                return;
			}
			bike.setStatus(nextStatus);
			bike = bikeRepo.update(bike);
		}
		catch (SQLException e)
		{
           Console.print("Errore durante l'aggiornamento: " + e.getMessage() + "\n");
		}
	}

	private static void importDaily() 
	{
		int month = LocalDate.now().getMonthValue();
		String monthString = month<10 ? "0"+month : month+"";
		int day = LocalDate.now().getDayOfMonth();
		String dayString = day<10 ? "0"+day : day+"";

		
		String filename =
				LocalDate.now().getYear() 	+ 
				monthString 				+
				dayString					+
				".csv";
		try
		{
			List<Bike> loaded = extractor.extractFrom(filename);
			for(Bike b:loaded)
				b.setId(0);
			for(Bike b:loaded)
				try
				{
					b = bikeRepo.insert(b);
					Console.print("Importata moto con id "+b.getId());
				}
				catch(SQLException e)
				{
					Console.print("Errore di importazione per "+b);
					e.printStackTrace();
				}
		}
		catch(FileNotFoundException e)
		{
			Console.print("Nessun file da caricare oggi");
		}
		catch(RuntimeException e)
		{
			Console.print("File corrotto");
		}
	}

    /**
     * Displays the main menu options.
     */
    private static void displayMenu()
    {
        Console.print("\n========== BIKE MANAGEMENT SYSTEM ==========\n");
        Console.print("1. Inserisci nuova moto manualmente\n");
        Console.print("2. Avanza stato moto\n");
        Console.print("3. Elenco moto in lavorazione\n");
        Console.print("4. Dettaglio moto (per ID o targa)\n");
        Console.print("5. Importa file giornaliero CSV\n");
        Console.print("0. Esci\n");
        Console.print("============================================\n");
    }
}
