package com.generation.cp.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.generation.cp.model.entities.Event;
import com.generation.library.repository.FullCacheSQLEntityRepository;

public class SQLEventRepository extends FullCacheSQLEntityRepository<Event> implements EventRepository
{

	Comparator<Event>  byTitle;    // Ordinamento alfabetico per titolo
	Comparator<Event>  byPrice;    // Ordinamento numerico per prezzo
	Comparator<Event>  byDate;     // Ordinamento cronologico per data
	Comparator<Event>  current;    // Comparator attualmente attivo


	/**
	 * Costruisce il repository per gli eventi con ordinamento predefinito per titolo.
	 *
	 * @param table Nome della tabella nel database
	 * @param connection Connessione al database
	 */
	public SQLEventRepository(String table, Connection connection)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Strategy Pattern + Lambda Expressions
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Comparator (interfaccia funzionale) → Lambda expressions (implementazione)
		 *
		 * Questo costruttore inizializza diverse strategie di ordinamento usando
		 * espressioni lambda, che sono implementazioni compatte di interfacce funzionali.
		 *
		 * Lambda vs Classe anonima:
		 * - Lambda: (a,b) -> a.getTitle().compareTo(b.getTitle())
		 * - Anonima: new Comparator<Event>() { public int compare(Event a, Event b) {...} }
		 *
		 * Principi OOP e FP (Functional Programming) applicati:
		 * → Strategy Pattern: Algoritmi intercambiabili incapsulati in Comparator
		 * → First-Class Functions: Le lambda sono oggetti che possono essere assegnati
		 * → Immutabilità: Gli oggetti Event non vengono modificati durante l'ordinamento
		 * → Type Inference: Java deduce i tipi dei parametri delle lambda
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		super(table, connection);

		this.byTitle  = (a,b) -> (a.getTitle().compareTo(b.getTitle()));
		this.byPrice  = (a,b) -> (a.getPrice() - b.getPrice());
		this.byDate   = (a,b) -> (a.getDate().compareTo(b.getDate()));
		this.current  = byTitle;  // Default: ordinamento alfabetico
	}


	/**
	 * Recupera gli eventi che soddisfano una condizione specifica, applicando l'ordinamento impostato.
	 *
	 * @param cond Condizione SQL WHERE
	 * @return Lista ordinata di eventi che soddisfano la condizione
	 */
	@Override
	public List<Event> findWhere(String cond)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Decorator Pattern + Strategy Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Classe base (FullCacheSQLEntityRepository) → Comportamento esteso (SQLEventRepository)
		 * Strategy Pattern → Comparator intercambiabile
		 *
		 * Questo metodo dimostra l'uso combinato di due pattern:
		 *
		 * 1. DECORATOR/EXTENSION: Estende il comportamento del findWhere() della classe
		 *    base aggiungendo funzionalità di ordinamento senza modificarne l'implementazione
		 *
		 * 2. STRATEGY: Il Comparator (current) è un algoritmo di ordinamento intercambiabile
		 *    a runtime tramite setOrder(), permettendo diversi criteri senza modificare il codice
		 *
		 * Principi OOP applicati:
		 * → Ereditarietà: Riutilizza la logica di recupero dalla classe base
		 * → Polimorfismo: super.findWhere() risolve dinamicamente alla versione cached
		 * → Incapsulamento: L'ordinamento è nascosto al client, che vede solo eventi ordinati
		 * → Open/Closed: Aperto all'estensione (nuovi criteri), chiuso alla modifica
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		List<Event> events = super.findWhere(cond);
		List<Event> copy   = new ArrayList<Event>();

		copy.addAll(events);

		if(current != null)
			Collections.sort(copy, current);

		return copy;
	}


	/**
	 * Recupera tutti gli eventi applicando l'ordinamento impostato.
	 *
	 * @return Lista ordinata di tutti gli eventi
	 */
	@Override
	public List<Event> findAll()
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Chain of Responsibility
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Client → SQLEventRepository → FullCacheSQLEntityRepository → Cache
		 *
		 * La chiamata attraversa una catena di responsabilità:
		 * 1. SQLEventRepository aggiunge ordinamento
		 * 2. FullCacheSQLEntityRepository legge dalla cache
		 * 3. Cache restituisce i dati in memoria
		 *
		 * Principi OOP applicati:
		 * → Polimorfismo: super.findAll() risolve al metodo della classe cache
		 * → Separazione delle responsabilità: ogni livello ha un compito specifico
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		List<Event> events = super.findAll();
		List<Event> copy   = new ArrayList<Event>();

		copy.addAll(events);

		if(current != null)
			Collections.sort(copy, current);

		return copy;
	}


	/**
	 * Imposta il criterio di ordinamento per le query successive.
	 *
	 * @param field Nome del campo per cui ordinare ("title", "price", "date")
	 */
	@Override
	public void setOrder(String field)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Strategy Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Strategia (Comparator) ← Contesto (SQLEventRepository)
		 *
		 * Questo metodo implementa il pattern Strategy permettendo di cambiare
		 * l'algoritmo di ordinamento a runtime senza modificare il codice client.
		 *
		 * Ogni Comparator rappresenta una strategia di ordinamento diversa:
		 * - byTitle: ordina alfabeticamente per titolo
		 * - byPrice: ordina numericamente per prezzo
		 * - byDate: ordina cronologicamente per data
		 *
		 * Principi OOP applicati:
		 * → Incapsulamento: La logica di selezione della strategia è interna
		 * → Polimorfismo: Tutti i Comparator implementano la stessa interfaccia
		 * → Open/Closed: Nuovi criteri di ordinamento possono essere aggiunti
		 *   senza modificare findAll() o findWhere()
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		switch(field)
		{
			case "title":
				this.current = byTitle;
			break;

			case "price":
				this.current = byPrice;
			break;

			case "date":
				this.current = byDate;
			break;

			default:
				this.current = byTitle;
		}
	}


	/**
	 * Costruisce il comando SQL preparato per aggiornare un evento.
	 *
	 * @param newVersion Evento con i dati aggiornati
	 * @return PreparedStatement configurato per l'update
	 * @throws SQLException Se la preparazione del comando fallisce
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Event newVersion) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contratto astratto (SQLEntityRepository.getUpdateCmd()) → Implementazione concreta
		 *
		 * Questo metodo implementa il pattern Template Method dove:
		 * - La classe base SQLEntityRepository definisce l'algoritmo di update()
		 * - La sottoclasse fornisce i dettagli specifici per Event tramite questo metodo
		 *
		 * La classe base chiama questo metodo senza sapere come costruire
		 * il comando SQL specifico per Event, delegando la responsabilità
		 * alla sottoclasse che conosce la struttura della tabella.
		 *
		 * Principi OOP applicati:
		 * → Astrazione: SQLEntityRepository lavora su Entity generiche
		 * → Polimorfismo: Ogni repository fornisce la propria implementazione
		 * → Incapsulamento: I dettagli SQL sono nascosti nella sottoclasse
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		String sql = "update event set title=?, description=?, price=? where id=?";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, newVersion.getTitle());
		ps.setString(2, newVersion.getDescription());
		ps.setInt(3,    newVersion.getPrice());
		ps.setInt(4,    newVersion.getId());

		return ps;
	}


	/**
	 * Costruisce il comando SQL preparato per inserire un nuovo evento.
	 *
	 * @param x Evento da inserire
	 * @return PreparedStatement configurato per l'insert
	 * @throws SQLException Se la preparazione del comando fallisce
	 */
	@Override
	protected PreparedStatement getInsertCmd(Event x) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contratto astratto (SQLEntityRepository.getInsertCmd()) → Implementazione concreta
		 *
		 * Simile a getUpdateCmd(), questo metodo completa il Template Method
		 * per l'inserimento. La classe base gestisce la validazione e l'esecuzione,
		 * mentre questa sottoclasse fornisce i dettagli SQL specifici per Event.
		 *
		 * Principi OOP applicati:
		 * → Astrazione: La classe base non sa quali campi ha Event
		 * → Riusabilità: La logica di insert() è riutilizzata per tutte le entità
		 * → Incapsulamento: I dettagli della tabella sono confinati qui
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		String sql = "insert into event(title,description,price)values(?,?,?)";

		PreparedStatement ps = connection.prepareStatement(sql);

		ps.setString(1, x.getTitle());
		ps.setString(2, x.getDescription());
		ps.setInt(3,    x.getPrice());

		return ps;
	}


	/**
	 * Converte una riga del ResultSet in un oggetto Event.
	 *
	 * @param rows ResultSet posizionato su una riga valida
	 * @return Oggetto Event costruito dai dati della riga
	 * @throws SQLException Se la lettura dei dati fallisce
	 */
	@Override
	public Event rowToX(ResultSet rows) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Template Method + Object Mapping
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Dati relazionali (SQL) → Oggetti (OOP)
		 *
		 * Questo metodo implementa il pattern di mapping Object-Relational (ORM)
		 * trasformando righe di database in oggetti Java. È parte del Template
		 * Method dove la classe base esegue la query e itera le righe, mentre
		 * la sottoclasse definisce come mappare ogni riga specifica.
		 *
		 * Principi OOP applicati:
		 * → Astrazione: SQLEntityRepository non conosce i campi specifici di Event
		 * → Polimorfismo: Ogni repository implementa il proprio mapping
		 * → Incapsulamento: La struttura del database è nascosta al resto dell'applicazione
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		return new Event(
			rows.getInt("id"),
			rows.getString("title"),
			rows.getString("description"),
			LocalDate.parse(rows.getString("date")),
			rows.getInt("price")
		);
	}

}
