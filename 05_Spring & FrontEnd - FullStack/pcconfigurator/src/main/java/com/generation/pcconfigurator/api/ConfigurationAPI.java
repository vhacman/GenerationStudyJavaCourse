package com.generation.pcconfigurator.api;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.pcconfigurator.model.entities.Configuration;
import com.generation.pcconfigurator.model.repository.ConfigurationRepository; 

@RestController		// controller che produce json (API)
@RequestMapping("/pcconfigurator/api/configurations") // indirizzo di base delle chiamate
public class ConfigurationAPI 
{
	
	// richiedi questa dipendenza
	@Autowired
	ConfigurationRepository repo;
	
	// mappatura con post, per creare entità
	// non siamo più nel mondo disney
	// le cose possono andare bene e possono andare male
	// io NON TI DO' LA GARANZIA CHE AVRAI UNA CONFIGURATION!
	// io ti dico che avrai una response con dentro del JSON
	// ma non vi do la garanzia che sarà andata a buon fine!!
	@PostMapping
	public ResponseEntity<Configuration> insert(@RequestBody Configuration c)
	{
		if(!c.isValid())
			return ResponseEntity.badRequest().body(c); // se non è valido io non lo salvo, io do bad request
			//					  400
		c = repo.save(c);
		return ResponseEntity.ok(c); /// in questo caso come risposta darò la configurazione
		//					  200
	}	

}