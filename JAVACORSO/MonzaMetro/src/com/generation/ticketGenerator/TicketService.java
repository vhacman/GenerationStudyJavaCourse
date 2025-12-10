package com.generation.ticketGenerator;

import java.time.LocalDateTime;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class TicketService
{
	public	static	Ticket	generateTicket() 
	{
		Passenger		personalInformation = askPersonalInformation();
		int				ageFlag = ageChecker(personalInformation.getAge());
		double			price = calculateCost(ageFlag);
		String			departureStation = askDepartureStation();
		LocalDateTime	departureTime = askDepartureTime();
		LocalDateTime 	checkInTime = checkInTicket();
		int				id = generateID();
		
		Ticket	ticket = new Ticket(id, personalInformation, checkInTime, price, 
		                            departureStation, departureTime);
		
		return (ticket);
		
	}
	
	public static Passenger askPersonalInformation() 
	{
		String	name;
		String	surname;
		int		age;
		
		Console.print("Nome: ");
		name = Console.readString();
		Console.print("Cognome: ");
		surname = Console.readString();
		Console.print("Età: ");
		age = Console.readInt();

		Passenger	passenger = new Passenger(name, surname, age);
		
		return (passenger);

	}
	
	public static String askDepartureStation()
	{
		String station;
		
		Console.print("Stazione di partenza: ");
		station = Console.readString();
		
		return station;
	}
	
	public static int[] askDepartureDate()
	{
		int[] date = new int[3];
		
		Console.print("Giorno di partenza: ");
		date[0] = Console.readInt();
		
		Console.print("Mese di partenza: ");
		date[1] = Console.readInt();
		
		Console.print("Anno di partenza: ");
		date[2] = Console.readInt();
		
		return date;
	}

	public static LocalDateTime askDepartureTime()
	{
		int[] date = askDepartureDate();
		int[] time = new int[2];
		
		Console.print("Ora di partenza: ");
		time[0] = Console.readInt();
		
		Console.print("Minuti di partenza: ");
		time[1] = Console.readInt();
		
		return LocalDateTime.of(date[2], date[1], date[0], time[0], time[1]);
	}
	
	public static int	ageChecker(int age) 
	{
		int	child = 0;
		int	adult = 1;
		int	old = 2;
		int	error = -1;
		
		if (age < 0)
		{
			Console.print("L'età deve essere un numero positivo");
			return error;
		}
		
		if (age <= 10)
			return (child); // se è un bambino ritorna flag 0
		else if (age >= 11 && age < 70)
			return (adult);// se è un adulto ritorna flag 1
		else
			return(old);// se è anziano ritorna flag 2
	}
	
	public static double calculateCost(int ageFlag) 
	{
		double price;
		
		if(ageFlag == 0)
			price = 1.30; // Bambini
		else if (ageFlag == 1)
			price = 1.90; // Adulti
		else if (ageFlag == 2)
			price = 0.00; // Anziani
		else
			price = 1.90; // Default adulto in caso di errore
		
		return (price);
	}
	
	public static LocalDateTime	checkInTicket()
	{
		LocalDateTime checkInTime = LocalDateTime.now();
		
		Console.print("Il tuo biglietto è stato timbrato!");
		return (checkInTime);
	}
	
	public static int generateID()
	{
		int id = 0;
		// Genera un ID casuale tra 1 e 999999
		id = (int)(Math.random() * 999999) + 1;
		
		return id;
	}
	public static void printTicket(Ticket ticket) 
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		Console.print("\n========================================");
		Console.print("           BIGLIETTO TRENO              ");
		Console.print("========================================");
		Console.print("ID Biglietto: " + ticket.getId());
		Console.print("----------------------------------------");
		Console.print("Passeggero: " + ticket.getPassenger().getName() + " " + 
		              ticket.getPassenger().getSurname());
		Console.print("Età: " + ticket.getPassenger().getAge() + " anni");
		Console.print("----------------------------------------");
		Console.print("Stazione di partenza: " + ticket.getDepartureStation());
		Console.print("Ora di partenza: " + ticket.getDepartureTime().format(timeFormatter));
		Console.print("Data: " + ticket.getDepartureTime().format(dateFormatter));
		Console.print("----------------------------------------");
		Console.print("Ora di timbratura: " + ticket.getCheckInTime().format(timeFormatter));
		Console.print("Data timbratura: " + ticket.getCheckInTime().format(dateFormatter));
		Console.print("----------------------------------------");
		Console.print("COSTO: €" + String.format("%.2f", ticket.getPrice()));
		Console.print("========================================\n");
		
		Console.print("Buon viaggio!");
	}
	
	public static void printTemplate(Ticket ticket) 
	{
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		
		String outputFileName;
		String modelloDiStampa = Template.load("print/templateGenerale.html");
		
		Console.print("Inserire nome del file in cui stampare il biglietto: ");
		outputFileName = Console.readString() + ".html";
		
		modelloDiStampa = modelloDiStampa
				.replace("{{ID}}", String.valueOf(ticket.getId()))
				.replace("{{NOME}}", ticket.getPassenger().getName())
				.replace("{{COGNOME}}", ticket.getPassenger().getSurname())
				.replace("{{ETA}}", String.valueOf(ticket.getPassenger().getAge()))
				.replace("{{STAZIONE_PARTENZA}}", ticket.getDepartureStation())
				.replace("{{ORA_PARTENZA}}", ticket.getDepartureTime().format(timeFormatter))
				.replace("{{DATA_PARTENZA}}", ticket.getDepartureTime().format(dateFormatter))
				.replace("{{ORA_TIMBRATURA}}", ticket.getCheckInTime().format(timeFormatter))
				.replace("{{DATA_TIMBRATURA}}", ticket.getCheckInTime().format(dateFormatter))
				.replace("{{COSTO}}", String.format("%.2f", ticket.getPrice()));
		
		FileWriter.writeTo("print/" + outputFileName, modelloDiStampa);
		Console.print("Il biglietto è stato salvato nel file " + outputFileName + " nella cartella print");
	}
	
}