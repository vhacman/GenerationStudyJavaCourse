package com.generation.oc.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.generation.library.Console;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.HealthService;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;
import com.generation.oc.model.repository.HealthServiceRepository;
import com.generation.oc.model.repository.PatientRepository;
import com.generation.oc.model.repository.PrestationRepository;
import com.generation.oc.view.Views;


public class PrestationController 
{
	static HealthServiceRepository healthServiceRepo = Context.getDependency(HealthServiceRepository.class);
	static PatientRepository patientRepo = Context.getDependency(PatientRepository.class);
	static PrestationRepository prestationRepo = Context.getDependency(PrestationRepository.class);
	
	/**
	 * comando numero 1. Algoritmo di inserimento di una nuova prestazione
	 * con lettura e selezione del cliente prima.
	 * Verifichiamo anche di avere spazio nella giornata per farlo. A tale scopo
	 * verifichiamo di avere meno di otto prestazioni in quella giornata.
	 * Se ne abbiamo meno di otto la accettiamo, altrimenti no.
	 * Gestione degli errori...
	 */
	public static void insertPrestation() 
	{
		try
		{
			Prestation p = new Prestation();
			Console.print("Inserire data");
			p.setDate(LocalDate.parse(Console.readString()));
			// vedo se ho spazio in quella data. In caso contrario annullo la procedura
			// devo caricare le prestazioni di quella data
			// e vedere che siano meno di 8
			List<Prestation> prestationsOnThatDate = prestationRepo.findByDate(p.getDate());
			if(prestationsOnThatDate.size()>8)
			{
				Console.print("Data piena");
				return; // termino il metodo insertPrestation
			}
			// scelta del paziente ora
			Patient patient = PatientController.choosePatient(); // metodo statico per ricercare e stampare un paziente
			// stesso vale per lo healthService
			HealthService service = HealthServiceController.chooseHealthService();
			// entrambi possono restituire null, a indicare un annullamento della procedura.
			if(patient==null || service==null)
			{
				Console.print("Annullato");
				return; 
			}
			// in caso contrario imposto come prezzo il prezzo standard del servizio
			// non sto a chiedere...
			p.setPrice(service.getPrice());
			// e collego i due padri
			p.setPatient(patient);
			p.setHealthService(service);
					
			// salvo
			p = prestationRepo.insert(p);
			
			Console.print("Salvata con id "+p);
		}
		catch(SQLException e)
		{
			// errori di lettura o scrittura. Non previsto... stampiamo lo stacktrace
			e.printStackTrace();
		}
		catch(DateTimeParseException e)
		{
			// errore nel parse della data
			Console.print("Data non valida");
		}
	}

	/**
	 * Inserimento di urgenza
	 */
	public static void insertEmergencyPrestation() 
	{
		// TODO:
		// come al metodo insertPrestation di sopra ma:
		// la data è automaticamente quella di oggi
		// non si fa il controllo per verificare se c'è tempo oggi. Accettiamo sempre e comunque la prestazione
		// il prezzo è triplo rispetto al normale
		
	}

	/**
	 * Algoritmo di filtro e riduzione
	 * Carico le prestazioni di una certa data
	 * Le stampo
	 * calcolo la somma del loro prezzo
	 */
	public static void printByDate() 
	{
		try
		{
			Console.print("Inserire data di ricerca");
			LocalDate date = LocalDate.parse(Console.readString()); // caldo.. potrebbe mandarmi nel secondo catch
			List<Prestation> matches = prestationRepo.findByDate(date);
			if(matches.size()==0)
			{
				Console.print("Nessuna prestazione in questa data");
				return;
			}
			Console.print(Views.PRESTATIONVIEW.render(matches));
			int sum = 0;
			for(Prestation m:matches)
				sum+=m.getPrice();
			
			Console.print("Eseguite "+matches.size()+" prestazioni per un totale di "+sum+" euro");
		}
		catch(SQLException e)
		{
			// errore di DB... non dovrebbe accadere. Se accade lo stampo e vado avanti
			e.printStackTrace();
		}
		catch(DateTimeParseException e)
		{
			// l'utente ha sbagliato a inserire la data
			Console.print("Data non valida");
		}
	}

	/**
	 * Filtro e riduzione
	 * trovare tutte le prestazioni di oggi
	 * stamparle in elenco ORDINANDOLE PER ID (usare un comparator o comparable)
	 * dopo averle stampate tutte, calcolare il guadagno previsto per oggi
	 */
	public static void printPrestationsForToday() 
	{
		//TODO
	}

	
	/**
	 * Filtro e riduzione
	 * calcolo il totale guadagnato per un periodo da-a
	 * stampo il guadagno totale e il guadagno giornaliero medio
	 */
	public static void printIncomeByPeriod() 
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate from = LocalDate.parse(Console.readString()); // caldo
			Console.print("Inserire data finale per il periodo");
			LocalDate to = LocalDate.parse(Console.readString()); // caldo
			List<Prestation> matches = prestationRepo.findByPeriod(from,to);	
			if(matches.size()==0)
			{
				Console.print("Nessuna prestazione nel periodo "+from+" "+to);
				return;
			}
			
			Console.print(Views.PRESTATIONVIEW.render(matches));
			double sum = 0;
			for(Prestation p:matches)
				sum+=p.getPrice();
			
			long days = ChronoUnit.DAYS.between(from, to);
			Console.print("Guadagno totale "+sum + " euro per "+days+" giorni");
			Console.print("Guadagno medio giornaliero "+(sum / days)+ " euro ");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(DateTimeParseException e)
		{
			Console.print("Data non valida");
		}		
		
	}

	/**
	 * Come al metodo precedente, ma si chiede all'utente solo un anno
	 * l'utente potrebbe sbagliare a inserire (NumberFormatException)
	 */
	public static void printIncomeByYear() 
	{
		// TODO Auto-generated method stub
	}

	// metodo 8
	// caricamento di oggetto figlio con due padri
	public static void printPrestationById() 
	{
		// vedere il metodo 7
		// selezionare una prestazione tramite id
		// stamparla con la vista delle prestazioni già usata in precedenza
		// assicurarsi di stampare anche il paziente e il tipo di servizio
	}

	public static void printEarningDayByDay() 
	{
		try
		{
			// simile al metodo printIncomeByPeriod, ma stavolta voglio calcolare il totale giorno per giorno
			// a tale scopo dovrò farlo in una mappa
			// passo 1: carico i dati di un certo periodo. In questo è uguale a printIncomeByPeriod
			Console.print("Inserire data iniziale per il periodo");
			LocalDate from = LocalDate.parse(Console.readString()); // caldo
			Console.print("Inserire data finale per il periodo");
			LocalDate to = LocalDate.parse(Console.readString()); // caldo
			List<Prestation> matches = prestationRepo.findByPeriod(from,to);	
			if(matches.size()==0)
			{
				Console.print("Nessuna prestazione nel periodo "+from+" "+to);
				return;
			}
			// ordino per data
			Comparator<Prestation> comparator = (p1,p2)->p1.getDate().compareTo(p2.getDate());
			matches.sort(comparator);
			
			// ho una lista di Prestation: prestazione 1, prestazione 2, ecc...
			// ora la devo trasformare (ridurre) a una mappa che ha come chiave una data e come valore un intero
			// qualcosa del tipo
			
			// giorno1 => 1000 euro, giorno 2 => 2000 euro ecc...
			Map<LocalDate, Integer>	incomeMap = new LinkedHashMap<LocalDate, Integer>();
			// ora scorro le prestazioni e calcolo quanto abbiamo guadagnato
			for(Prestation p:matches)
			{
				// isolo la data
				LocalDate d = p.getDate();
				// vedo se c'è già come chiave nella mappa
				if(incomeMap.containsKey(d))
				{
					// la ho: allora ho una nuova prestazione in stessa data e sommo il valore al vecchio
					incomeMap.put(d, incomeMap.get(d)+p.getPrice());
					//			      vecchio valore	nuovo
				}
				else
				{
					// prima volta che trovo quella data
					incomeMap.put(d, p.getPrice()); // mi limito a prendere il primo giorno
				}
			}
			// ora ho una mappa con chiave data e valore totale guadagnato
			// scorro le chiavi (le date) e per ogni data stampo il totale corrispondente
			
			Console.print("Guadagni giorno per giorno nel periodo "+from+" "+to);
			for(LocalDate d:incomeMap.keySet())
				Console.print(d+"\t"+incomeMap.get(d)+" euro");
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(DateTimeParseException e)
		{
			Console.print("Data non valida");
		}		
		
	}

	/**
	 * Vedere print EarningDayByDay
	 * partite da List<Prestation> findAll(), ma 
	 * in questo caso dovete produrre una Map<String,Integer>
	 * la chiave (String) è il nome del tipo di intervento (prestation.getHealthService().getName())
	 * il valore è la somma di prestation.getPrice()
	 * la mappa che deve uscire deve avere circa questa forma:
	 * Diet 				1000
	 * Visit				2000			
	 * 
	 * 
	 * ecc. la mappa è una tabella con due colonne: in questo caso la chiave è una stringa
	 * e il valore è un intero
	 * 
	 */
	public static void printEarningByServiceName() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Filtro e riduzione, da list a set
	 * 1 - carico tutti le prestazioni di un periodo
	 * 2 - estraggo tutti i pazienti e li metto in un set
	 * 3 - il set elimina le ripetizioni
	 * ATTENZIONE: perchè funzioni DOVETE sovrascrivere equals e hashCode in Patient (l'ho fatto io per voi)
	 * e dovrete farlo nel metodo successivo per HealthService
	 */
	public static void printDistinctPatientsForPeriod() 
	{
		try
		{
			Console.print("Inserire data iniziale per il periodo");
			LocalDate from = LocalDate.parse(Console.readString()); // caldo
			Console.print("Inserire data finale per il periodo");
			LocalDate to = LocalDate.parse(Console.readString()); // caldo
			List<Prestation> matches = prestationRepo.findByPeriod(from,to);	
			if(matches.size()==0)
			{
				Console.print("Nessuna prestazione nel periodo "+from+" "+to);
				return;
			}
			
			// ora devo ridurre da List<Prestation> a Set<Patient>
			Set<Patient> distinctPatients = new HashSet<Patient>();
			// se un paziente compare due volte verrà scartato SE avete sovrascritto correttamente hashCode ed equals
			for(Prestation p:matches)
				distinctPatients.add(p.getPatient());
			
			Console.print(Views.PATIENTRECAPVIEW.render(distinctPatients));
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		catch(DateTimeParseException e)
		{
			Console.print("Data non valida");
		}		
		
	}

	/**
	 * Come metodo sopra
	 * ma stavolta dovete stampare un Set<HealthService>
	 * per evitare che si ripetano. Non mi interessa vedere 10 volte "carie"
	 * mi basta che compaia una volta
	 */
	public static void printDistinctServiceForPeriod() 
	{
		// TODO Auto-generated method stub
		
	}

	
	
}
