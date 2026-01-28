package com.generation.oc.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.library.Console;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.repository.HealthServiceRepository;
import com.generation.oc.model.repository.PatientRepository;
import com.generation.oc.model.repository.PrestationRepository;
import com.generation.oc.view.Views;

/**
 * Il mio povero main era troppo grande
 * Divido il mio lavoro in controller secondari
 * che condividono le stesse dipendenze
 * il main richiama i metodi dei controller secondari
 */
public class PatientController 
{
	static HealthServiceRepository healthServiceRepo = Context.getDependency(HealthServiceRepository.class);
	static PatientRepository patientRepo = Context.getDependency(PatientRepository.class);
	static PrestationRepository prestationRepo = Context.getDependency(PrestationRepository.class);
	
	/**
	 * "pagina web" per scegliere un paziente a partire dal suo cognome, codice fiscale o id
	 * @return
	 */
	public static Patient choosePatient() 
	{
		try
		{
			// scelgo un paziente, a partire da una lista
			// salverò i pazienti caricati in questa mappa
			// questa mappa è fatta di coppia e assocerò l'id del paziente al paziente stesso
			// l'utente sarà costretto a scegliere un id fra questi, o ad annullare
			Map<Integer,Patient> selectedPatients = new HashMap<Integer,Patient>();
			
			Console.print("Inserire parte del cognome del paziente, il suo id o il suo codice fiscale");
			String part = Console.readString();
			// ha inserito un numero intero, lo uso come id
			if(Util.isInteger(part))
			{
				Patient res = patientRepo.findById(Integer.parseInt(part));
				if(res==null)
					Console.print("Non trovato");
				else
					Console.print("Selezionato: " + Views.PATIENTRECAPVIEW.render(res));
				
				return res;
			}
			// non ha inserito un numero, lo tratto come un pezzo di cognome o il codice fiscale
			List<Patient> matches = patientRepo.findBySSNorSurnameContaining(part);
			// svuoto il set di id
			for(Patient p:matches)
				selectedPatients.put(p.getId(),p); // mappatura: da una lista di patient (matches) a una Map<Integer,Patient>
			
			// stampo l'elenco dei pazienti usando una vista apposita, che ho chiamato choose list
			// per comodità ho messo queste liste all'interno di una classe View nel package view
			Console.print(Views.PATIENTRECAPVIEW.render(matches));
			Console.print("Inserire id del paziente da selezionare");
			int id = Console.readInt();
			// vedo se lo trovo nella mappa caricata prima:
			Patient res = selectedPatients.get(id); // carico il paziente dalla mappa, vedo se c'è un paziente associato a questo id
			if(res==null)
			{
				Console.print("Id non presente fra i selezionati");
				// gli do un solo tentativo. annullo se non lo becca subito
			}
			return res;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		catch(NumberFormatException e)
		{
			// ha sbagliato a inserire l'id...
			Console.print("Id non numerico");
			return null;
		}
	}

	/**
	 * Caricamento di oggetto padre con figli collegati
	 * il paziente viene sempre caricatto con le sue prenotazioni
	 * e prepariamo una vista apposita per stamparlo
	 */
	public static void printPatientFull() 
	{
		// richiamiamo il metodo scritto sopra, che era tanto comodo per trovare un paziente
		Patient p = choosePatient();
		if(p==null)
			return; // ma potrebbe darmi null
		// notiamo che il bello è che il paziente contiene le sue prestazioni...
		Console.print(Views.FULLPATIENTVIEW.render(p));		
	}

}
