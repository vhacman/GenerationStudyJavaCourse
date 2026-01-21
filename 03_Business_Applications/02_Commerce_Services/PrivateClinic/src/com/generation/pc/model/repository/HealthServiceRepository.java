package com.generation.pc.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.pc.model.entity.HealthService;

/*
 * ==================================================================================
 * TEORIA: Interfaccia Repository per HealthService
 * ==================================================================================
 * Questa interfaccia segue gli stessi principi di PatientRepository:
 * - Astrazione del layer di persistenza
 * - Contratto indipendente dall'implementazione
 * - Operazioni CRUD + query domain-specific
 *
 * DIFFERENZA CON PatientRepository:
 * Come suggerisce il commento, per i servizi sanitari potrebbe essere utile
 * una "total cache" (cache completa) invece di una parziale, perché:
 * - Il numero di servizi è limitato e cambia raramente
 * - Vengono letti molto più spesso di quanto vengano modificati
 * - Caricare tutti i servizi in memoria migliora le performance
 *
 * CACHE STRATEGIES:
 * - Partial Cache: carica solo gli elementi più usati (es. ultimi N pazienti)
 * - Full Cache: carica tutti gli elementi all'avvio e mantiene sincronizzazione
 * - No Cache: ogni operazione va direttamente al database
 */
// qui ci starebbe bene una total cache
public interface HealthServiceRepository
{
	/**
	 * CRUD - READ: Recupera tutti i servizi sanitari
	 * @return lista di tutti i servizi disponibili
	 */
	List<HealthService>		findAll();

	/**
	 * CRUD - READ: Cerca un servizio per ID
	 * @param id l'identificativo univoco del servizio
	 * @return il servizio trovato o null se non esiste
	 */
	HealthService			findById(int id);

	/**
	 * CRUD - CREATE: Inserisce un nuovo servizio sanitario
	 * @param h il servizio da inserire
	 * @return il servizio inserito con l'id generato dal database
	 * @throws SQLException se si verifica un errore di database
	 */
	HealthService			insert(HealthService h) throws SQLException;

	/**
	 * CRUD - UPDATE: Aggiorna un servizio esistente
	 * Il main deve permettermi di cambiare il PREZZO di un servizio
	 * @param h il servizio con i dati modificati
	 * @return il servizio aggiornato
	 * @throws SQLException se si verifica un errore di database
	 */
	HealthService			update(HealthService h) throws SQLException;

	/**
	 * CRUD - DELETE: Cancella un servizio
	 * @param id l'id del servizio da cancellare
	 * @return true se la cancellazione è avvenuta con successo, false altrimenti
	 * @throws SQLException se si verifica un errore di database
	 */
	boolean					delete(int id) throws SQLException;

	/**
	 * Query personalizzata: cerca servizi per tipo
	 * @param type il tipo di servizio da cercare (es. "Visita", "Esame", "Chirurgia")
	 * @return lista di servizi del tipo specificato
	 */
	List<HealthService>		findByType(String type);
}
