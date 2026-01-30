package com.generation.oc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.generation.library.Console;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.HealthService;
import com.generation.oc.model.repository.HealthServiceRepository;
import com.generation.oc.model.repository.PatientRepository;
import com.generation.oc.model.repository.PrestationRepository;
import com.generation.oc.view.Views;

public class HealthServiceController
{
	static HealthServiceRepository	healthServiceRepo	= Context.getDependency(HealthServiceRepository.class);
	static PatientRepository		patientRepo			= Context.getDependency(PatientRepository.class);
	static PrestationRepository		prestationRepo		= Context.getDependency(PrestationRepository.class);

	public static HealthService chooseHealthService()
	{
		try
		{
			// Mappa per associare l'ID al servizio, garantendo che l'utente scelga tra i risultati mostrati
			Map<Integer, HealthService> selectedServices = new HashMap<Integer, HealthService>();
			Console.print("Inserire il nome del servizio o il suo ID");
			String part = Console.readString();
			// Se l'input è un numero, cerco direttamente per ID
			if(Util.isInteger(part))
			{
				HealthService res = healthServiceRepo.findById(Integer.parseInt(part));
				if(res == null)
					Console.print("Servizio non trovato");
				else
					Console.print("Selezionato "+Views.HEALTHSERVICERECAPVIEW.render(res));
				return res;
			}
			// Altrimenti cerco i servizi che contengono la stringa inserita nel nome
			List<HealthService> matches = healthServiceRepo.findByNameContaining(part);
			if(matches.isEmpty())
			{
				Console.print("Nessun servizio corrispondente trovato");
				return null;
			}
			// Popolo la mappa di controllo e mostro i risultati
			for(HealthService hs : matches)
				selectedServices.put(hs.getId(), hs);
			// Utilizzo una vista ipotetica per mostrare l'elenco (seguendo il tuo schema Views)
			Console.print(Views.HEALTHSERVICERECAPVIEW.render(matches));
			Console.print("Inserire ID del servizio da selezionare");
			int id = Console.readInt();
			// Recupero il servizio dalla mappa usando l'ID inserito
			HealthService res = selectedServices.get(id);
			if(res == null)
				Console.print("ID non presente tra i servizi selezionati");
			return res;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(NumberFormatException e)
		{
			Console.print("ID non numerico");
			return null;
		}
	}

	/**
	 * Inserisce un nuovo servizio sanitario nel database dopo aver verificato che non esistano duplicati.
	 *
	 * Il metodo richiede interattivamente all'utente il nome e il prezzo del servizio,
	 * verifica che non esista già un servizio con lo stesso nome (case-insensitive),
	 * valida i dati inseriti e procede al salvataggio nel database.
	 * @throws SQLException se si verifica un errore durante l'accesso al database
	 * @throws NumberFormatException se il prezzo inserito non è un numero intero valido
	 */
	public static void insertHealthService()
	{
		try
		{
			Console.print("Inserire nome del servizio: ");
			String name = Console.readString();
			Console.print("Inserire prezzo del servizio: ");
			int price = Integer.parseInt(Console.readString());
			//VALIDAZIONE DUPLICATI
			List<HealthService> matches = healthServiceRepo.findByNameContaining(name);
			//controllo se esiste già un servizio con lo stesso nome
			for (HealthService existing : matches)
			{
				if(existing.getName().equalsIgnoreCase(name))
				{
					Console.print("Servizio già esistente con questo nome");
					return; 
				}
			}
			 // 3. CREAZIONE OGGETTO
	        HealthService service = new HealthService();
	        service.setName(name);
	        service.setPrice(price);
	        //4. VALIDAZIONE DEI DATI
	        List<String> errors = service.getErrors();
	        if (!errors.isEmpty())
	        {
	            Console.print("Errori di validazione:");
	            for (String error : errors)
	                Console.print("- " + error);
	            return;
	        }
	        // 5. SALVATAGGIO
	        service = healthServiceRepo.insert(service);
	        Console.print("Servizio inserito con ID: " + service.getId());
			
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			Console.print("Errore durante l'inserimento del servizio nel database");
		}
		catch(NumberFormatException e)
		{
			Console.print("Prezzo non valido. Inserire un numero intero");
		}
	}

	/**
	 * TODO: Modifica prezzo di un servizio esistente
	 *
	 * PSEUDOCODICE:
	 * 1. SELEZIONE SERVIZIO:
	 *    - Usare chooseHealthService() per selezionare un servizio
	 *    - Se null: uscire
	 *
	 * 2. VISUALIZZAZIONE DATI ATTUALI:
	 *    - Stampare: "Servizio selezionato: [name]"
	 *    - Stampare: "Prezzo attuale: [price] €"
	 *
	 * 3. INPUT NUOVO PREZZO:
	 *    - Chiedere: "Inserire nuovo prezzo (0 per annullare):"
	 *    - Leggere il nuovo prezzo
	 *    - Se prezzo == 0: annullare operazione e uscire
	 *
	 * 4. VALIDAZIONE PREZZO:
	 *    - Verificare che prezzo > 0
	 *    - Se non valido: stampare "Prezzo non valido" e uscire
	 *
	 * 5. CONFERMA MODIFICA:
	 *    - Chiedere: "Confermare modifica da [vecchioPrezzo] a [nuovoPrezzo]? (s/n)"
	 *    - Se risposta != "s": annullare e uscire
	 *
	 * 6. AGGIORNAMENTO:
	 *    - Impostare il nuovo prezzo: service.setPrice(nuovoPrezzo)
	 *    - Salvare usando healthServiceRepo.update(service)
	 *    - Stampare: "Prezzo aggiornato con successo"
	 *
	 * 7. GESTIONE ECCEZIONI:
	 *    - SQLException: stampare errore database
	 *    - NumberFormatException: stampare "Prezzo non numerico"
	 */
	public static void updateServicePrice()
	{
		// TODO: implementare secondo pseudocodice
	}
}
