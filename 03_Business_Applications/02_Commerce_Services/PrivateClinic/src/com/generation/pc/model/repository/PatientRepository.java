package com.generation.pc.model.repository;

import java.sql.SQLException;
import java.util.List;

import com.generation.pc.model.entity.Patient;

/*
 * ==================================================================================
 * TEORIA: Interfaccia (Interface)
 * ==================================================================================
 * DEFINIZIONE:
 * Un'interfaccia in Java è un contratto che definisce un insieme di metodi
 * che una classe DEVE implementare, senza specificare COME implementarli.
 *
 * SCOPO:
 * - Astrazione: definire "cosa" fare senza specificare "come" farlo
 * - Polimorfismo: permettere a classi diverse di implementare lo stesso contratto
 * - Disaccoppiamento: il codice client dipende dall'interfaccia, non dall'implementazione
 *
 * PERCHÉ LO FACCIAMO:
 * PatientRepository è un'interfaccia perché vogliamo:
 * 1. Separare il contratto (operazioni disponibili) dall'implementazione (come vengono eseguite)
 * 2. Poter cambiare implementazione (SQL, NoSQL, in-memory) senza modificare il codice client
 * 3. Facilitare il testing: possiamo creare mock/fake implementations per i test
 * 4. Seguire il principio SOLID "Dependency Inversion": dipendere da astrazioni, non da concretizzazioni
 *
 * ESEMPIO:
 * Main usa PatientRepository (interfaccia), non SQLPatientRepository (implementazione).
 * Domani potremmo usare MongoPatientRepository senza modificare Main.
 *
 * ==================================================================================
 * TEORIA: Repository Pattern
 * ==================================================================================
 * DEFINIZIONE:
 * Il Repository pattern media tra il domain model (entità) e il data mapping layer
 * (database), fornendo un'interfaccia collection-like per accedere agli oggetti.
 *
 * SCOPO:
 * - Incapsulare la logica di accesso ai dati
 * - Fornire un'API object-oriented per il database
 * - Centralizzare le query comuni (findAll, findById, etc.)
 *
 * PERCHÉ LO FACCIAMO:
 * Invece di scrivere query SQL sparse in tutto il codice, le centralizziamo nel
 * repository. Questo rende il codice:
 * 1. Più manutenibile: le query sono in un unico posto
 * 2. Più testabile: possiamo mockare il repository
 * 3. Più leggibile: patientRepo.findByCity("Roma") invece di query SQL
 *
 * METODI STANDARD (CRUD):
 * - Create: insert()
 * - Read: findAll(), findById()
 * - Update: update()
 * - Delete: delete()
 *
 * ==================================================================================
 * TEORIA: SQLException e throws
 * ==================================================================================
 * DEFINIZIONE:
 * SQLException è un'eccezione checked (verificata a compile-time) sollevata quando
 * si verifica un errore durante operazioni sul database.
 * "throws SQLException" dichiara che il metodo PUÒ sollevare questa eccezione.
 *
 * SCOPO:
 * - Gestione errori: forzare il chiamante a gestire possibili errori di database
 * - Tracciabilità: sapere quali metodi possono fallire per problemi di I/O
 *
 * PERCHÉ LO FACCIAMO:
 * Operazioni come insert, update, delete possono fallire per:
 * - Violazione di vincoli (es. duplicazione SSN univoco)
 * - Problemi di connessione
 * - Errori di sintassi SQL
 * Dichiarando "throws SQLException" obblighiamo chi chiama questi metodi a gestire
 * questi errori con try-catch o propagarli ulteriormente.
 */
// qui ci starebbe bene una cache parziale...
public interface PatientRepository
{
	/*
	 * ==================================================================================
	 * TEORIA: Metodi di interfaccia (Abstract methods)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * I metodi in un'interfaccia sono implicitamente:
	 * - public: accessibili da qualsiasi classe
	 * - abstract: senza corpo/implementazione (fino a Java 8, poi aggiunti default methods)
	 *
	 * SCOPO:
	 * Definire il contratto che le classi implementatrici devono rispettare.
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Ogni metodo rappresenta un'operazione comune sui pazienti che TUTTE le
	 * implementazioni devono fornire, indipendentemente dalla tecnologia di persistenza.
	 */

	// ognuno sarà un caso del main

	/**
	 * CRUD - READ: Recupera tutti i pazienti
	 * @return lista di tutti i pazienti presenti nel sistema
	 */
	List<Patient>	findAll();

	/**
	 * CRUD - READ: Cerca un paziente per ID
	 * @param id l'identificativo univoco del paziente
	 * @return il paziente trovato o null se non esiste
	 */
	Patient			findById(int id);

	/**
	 * CRUD - CREATE: Inserisce un nuovo paziente
	 * @param p il paziente da inserire
	 * @return il paziente inserito con l'id generato dal database
	 * @throws SQLException se si verifica un errore di database (es. SSN duplicato)
	 */
	Patient			insert(Patient p) throws SQLException;

	/**
	 * CRUD - UPDATE: Aggiorna un paziente esistente
	 * Il main deve permettermi di modificare città e indirizzo del paziente, niente altro
	 * @param p il paziente con i dati modificati
	 * @return il paziente aggiornato
	 * @throws SQLException se si verifica un errore di database
	 */
	Patient			update(Patient p) throws SQLException;

	/**
	 * CRUD - DELETE: Cancella un paziente
	 * @param id l'id del paziente da cancellare
	 * @return true se la cancellazione è avvenuta con successo, false altrimenti
	 * @throws SQLException se si verifica un errore di database
	 */
	boolean			delete(int id) throws SQLException;

	/*
	 * ==================================================================================
	 * TEORIA: Query Methods (Metodi di ricerca personalizzati)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Oltre ai metodi CRUD standard, i repository possono definire metodi di ricerca
	 * specifici del dominio (domain-specific queries).
	 *
	 * NAMING CONVENTION:
	 * - findBy[Campo]: cerca per valore esatto (es. findByCity)
	 * - findBy[Campo]Containing: cerca per pattern LIKE (es. findByLastNameContaining)
	 *
	 * SCOPO:
	 * Fornire query significative per il dominio applicativo senza esporre SQL.
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Invece di forzare Main a fare patientRepo.findAll() e poi filtrare in memoria,
	 * deleghiamo al repository che può eseguire una query SQL ottimizzata:
	 * SELECT * FROM patients WHERE city = 'Roma'
	 */

	/**
	 * Cerca pazienti per città
	 * @param city la città da cercare
	 * @return lista di pazienti che abitano in quella città
	 */
	List<Patient>	findByCity(String city);

	/**
	 * Cerca un paziente per codice fiscale (SSN = Social Security Number)
	 * @param ssn il codice fiscale da cercare (dovrebbe essere univoco)
	 * @return il paziente trovato o null
	 */
	Patient			findBySSN(String ssn);

	/**
	 * Cerca pazienti il cui cognome contiene una determinata stringa
	 * @param part la stringa da cercare nel cognome
	 * @return lista di pazienti il cui cognome contiene la stringa specificata
	 */
	List<Patient>	findByLastNameContaining(String part);
}
