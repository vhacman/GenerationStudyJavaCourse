package com.generation.vault.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.generation.vault.model.entities.MembershipRequest;

/*
 * Sto estendendo una interfaccia di base di Spring Data JPA
 * Contiene tutti i dati di base (findAll, findById, ecc...)
 * E gli devo solo dire cosa gestire
 * Esatto. Ma non devi dare nessun tipo di metodo
 * fa tutto lui
 * anche il caffÃ¨.
 * crea la classe che implementa questa interfaccia
 * istanzia un oggetto
 * lo mette nel context
 * tutto in maniera trasparente
* 
 */
public interface MembershipRequestRepository extends JpaRepository<MembershipRequest, Integer>
{

	List<MembershipRequest> findByStatus(String string);

}