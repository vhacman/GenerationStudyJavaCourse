package com.generation.vault.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.vault.model.entities.MembershipRequest;
import com.generation.vault.model.repository.MembershipRequestRepository; 


@RestController
@RequestMapping("/vault/api/membershiprequests")
// INDIRIZZO DI BASE A CUI TROVERO' I METODI MAPPATI DI QUESTO CONTROLLER
public class MembershipRequestAPI 
{
	
	@Autowired
	MembershipRequestRepository repo;
	
	// requestbody vuol dire che i dati che mi arrivano sono in json e vengono trasformati nell'oggetto newMembership
	// I DATI DELLA FORM diventano newMembership
	// i dati grezzi inviati dal javascript che li aveva presi dalla form che li aveva ricevuti dall'utente
	// diventano finalmente newMembership
	@PostMapping 
	public MembershipRequest save(@RequestBody MembershipRequest newMembership)
	{
		newMembership.setStatus("pending");
		return repo.save(newMembership);		
	}
	
	@GetMapping("/pending")
	public List<MembershipRequest> getPendingRequests()
	{
		return repo.findByStatus("pending");
	}

	@GetMapping
	public List<MembershipRequest> getAllRequests()
	{
		return repo.findAll();
	}

}