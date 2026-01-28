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
	static HealthServiceRepository healthServiceRepo = Context.getDependency(HealthServiceRepository.class);
	static PatientRepository patientRepo = Context.getDependency(PatientRepository.class);
	static PrestationRepository prestationRepo = Context.getDependency(PrestationRepository.class);
	
	
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
	        
	        if(matches.isEmpty()) {
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
	        {
	            Console.print("ID non presente tra i servizi selezionati");
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
	        Console.print("ID non numerico");
	        return null;
	    }
	}
}
