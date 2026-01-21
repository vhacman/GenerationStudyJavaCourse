package com.generation.library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;
import com.generation.library.profiling.ProfilingMonitor;

/**
 * @param <X> che sarà un qualche tipo di entità
 */
public abstract class SQLEntityRepository<X extends Entity> 
{

	protected Connection connection;
	protected String table;
	
	public SQLEntityRepository(String table, Connection connection)
	{
		this.table = table;
		this.connection = connection;
	}
	
	
	/**
	 * Recupera un'entità dal database tramite il suo identificativo.
	 *
	 * @param id Identificativo dell'entità
	 * @return L'entità corrispondente, null se non trovata
	 */
	public X findById(int id)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Riutilizzo interno: findById() → findWhere()
		 *
		 * Questo metodo dimostra il riutilizzo attraverso composizione:
		 * invece di duplicare la logica di query SQL, delega a findWhere()
		 * che è più generico e può essere sovrascritto dalle sottoclassi.
		 *
		 * Benefici:
		 * → DRY: Nessuna duplicazione di codice SQL
		 * → Estensibilità: Le sottoclassi possono override findWhere()
		 *   e findById() eredita automaticamente il nuovo comportamento
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Opera su X generiche senza conoscerne il tipo
		 * → Polimorfismo: findWhere() può essere sovrascritto
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		List<X> matches = findWhere("id="+id);
		return matches.size()>0 ? matches.get(0) : null;
	}
	
	/**
	 * Inserisce una nuova entità nel database.
	 *
	 * @param x Entità da inserire (con id=0)
	 * @return L'entità inserita con l'id assegnato dal database
	 * @throws SQLException Se l'entità non è valida, ha già un id, o l'inserimento fallisce
	 */
	public X insert(X x) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contratto astratto → Implementazione concreta
		 *
		 * Questo metodo definisce l'algoritmo di inserimento:
		 * 1. Validazione (isValid() - delegato alla sottoclasse Entity)
		 * 2. Verifica precondizioni (id deve essere 0)
		 * 3. getInsertCmd(x) - ASTRATTO, implementato dalla sottoclasse
		 * 4. Esecuzione SQL
		 * 5. Recupero nuovo id
		 *
		 * Template Method:
		 * → Classe base: definisce lo scheletro dell'algoritmo (questo metodo)
		 * → Sottoclasse: fornisce i passi specifici (getInsertCmd)
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Non sa come costruire il comando SQL specifico
		 * → Polimorfismo: getInsertCmd() risolve alla sottoclasse a runtime
		 * → Validazione: Garantisce che solo entità valide siano inserite
		 * → Incapsulamento: La logica di insert è uniforme per tutte le entità
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		if(!x.isValid())
			throw new SQLException("Invalid entity");

		if(x.getId()!=0)
			throw new SQLException("Entity already has an id");

		PreparedStatement insertCmd = getInsertCmd(x); // ASTRATTO

		insertCmd.execute(); // inserisco
		insertCmd.close();
		x.setId(getNewId());

		return x;

	}
	
	/**
	 * Aggiorna un'entità esistente nel database.
	 *
	 * @param newVersion Entità con i dati aggiornati (deve avere un id valido)
	 * @return L'entità aggiornata
	 * @throws SQLException Se l'entità non è valida, non esiste, o l'aggiornamento fallisce
	 */
	public X update(X newVersion) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Algoritmo di aggiornamento:
		 * 1. Validazione dati (isValid())
		 * 2. Verifica id valido (!=0)
		 * 3. Verifica esistenza (findById)
		 * 4. getUpdateCmd() - ASTRATTO, delegato alla sottoclasse
		 * 5. Esecuzione SQL
		 *
		 * Guard Clauses:
		 * → Fail-fast: Valida tutte le precondizioni prima di procedere
		 * → Previene corruzioni dati
		 * → Fornisce messaggi di errore specifici
		 *
		 * Principi OOP applicati:
		 * → Template Method: Schema fisso, dettagli delegati
		 * → Polimorfismo: getUpdateCmd() risolve alla sottoclasse
		 * → Incapsulamento: Logica di validazione centralizzata
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		if(!newVersion.isValid())
			throw new SQLException("Invalid entity");

		if(newVersion.getId()==0)
			throw new SQLException("Invalid id");

		if(findById(newVersion.getId())==null)
			throw new SQLException("Unknown entity");

		PreparedStatement updateCmd = getUpdateCmd(newVersion);

		updateCmd.execute();
		updateCmd.close();

		return newVersion;
	}
	
	
	protected abstract PreparedStatement getUpdateCmd(X newVersion) throws SQLException;


	// produce il comando SQL di Insert per l'entità 
	protected abstract PreparedStatement getInsertCmd(X x) throws SQLException;

	private int getNewId()
	{
		try
		{
			PreparedStatement readCmd = connection.prepareStatement("select max(id) as m from "+table);
			ResultSet rs = readCmd.executeQuery();
			int res =  rs.getInt("m");
			readCmd.close();
			rs.close();
			return res;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error reading");
		}
	}
	

	/**
	 * Recupera le entità che soddisfano una condizione WHERE specifica.
	 *
	 * @param condition Condizione SQL (senza la keyword WHERE)
	 * @return Lista di entità che soddisfano la condizione
	 */
	public List<X> findWhere(String condition)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Template Method Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Query SQL → ResultSet → List<X>
		 *
		 * Questo metodo orchestra la conversione da dati relazionali a oggetti:
		 * 1. Costruisce query SQL dinamica con la condizione
		 * 2. Esegue la query
		 * 3. Itera le righe del ResultSet
		 * 4. rowToX(rows) - ASTRATTO, converte ogni riga in oggetto X
		 * 5. Raccoglie profiling metrics
		 *
		 * Template Method:
		 * → Questo metodo definisce il flusso (query → iterate → convert)
		 * → rowToX() è delegato alla sottoclasse che conosce la struttura di X
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Non sa quali campi ha X, delega a rowToX()
		 * → Polimorfismo: rowToX() risolve al tipo concreto (Event, Guest, ecc.)
		 * → Generics: List<X> è type-safe, non serve casting
		 * → Separation of Concerns: SQL query separata da object mapping
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		List<X> res = new ArrayList<X>();
		try
		{
			String sql = "select * from "+table+ " where "+condition;
			Statement readCmd = connection.createStatement();
			ResultSet rows = readCmd.executeQuery(sql);
			while(rows.next())
				res.add(rowToX(rows));
			ProfilingMonitor.queryNumber++;
			ProfilingMonitor.rowsNumbers+=res.size();

			return res;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return res;
		}
	}
	
	
	/**
	 * Recupera tutte le entità dalla tabella.
	 *
	 * @return Lista di tutte le entità
	 */
	public List<X> findAll()
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - DRY Principle
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Riutilizzo: findAll() → findWhere("id>0")
		 *
		 * Invece di duplicare la logica SQL, riutilizza findWhere() con
		 * una condizione sempre vera (assumendo id>0 per tutte le righe).
		 *
		 * Benefici:
		 * → DRY: Nessuna duplicazione
		 * → Manutenibilità: Modifiche a findWhere() si riflettono qui
		 * → Consistenza: Stesso comportamento di profiling e error handling
		 *
		 * Principio OOP applicato:
		 * → Riusabilità: Compone comportamenti esistenti
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		return findWhere("id>0");
	}
	
	/**
	 * Converte una riga del ResultSet in un'entità di tipo X.
	 * Metodo astratto implementato dalle sottoclassi specifiche.
	 *
	 * @param rows ResultSet posizionato su una riga valida
	 * @return Oggetto di tipo X costruito dai dati della riga
	 * @throws SQLException Se la lettura dei dati fallisce
	 */
	public abstract X rowToX(ResultSet rows) throws SQLException;


	/**
	 * Elimina un'entità dal database tramite il suo identificativo.
	 *
	 * @param id Identificativo dell'entità da eliminare
	 * @return true se l'eliminazione è avvenuta con successo, false se l'entità non esiste
	 * @throws SQLException Se l'eliminazione fallisce
	 */
	public boolean delete(int id) throws SQLException
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * RIFLESSIONE ARCHITETTURALE - Guard Clause Pattern
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Verifica esistenza prima di eliminare:
		 * → findById(id) controlla se l'entità esiste
		 * → Se non esiste, restituisce false senza eseguire SQL
		 * → Previene errori e operazioni inutili
		 *
		 * Idempotenza:
		 * → Eliminare un'entità inesistente è sicuro (restituisce false)
		 * → Non solleva eccezioni per entità già eliminate
		 *
		 * Principi applicati:
		 * → Fail-fast: Valida prima di agire
		 * → Defensive programming: Previene stati inconsistenti
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		if(findById(id)==null)
			return false; // non posso ucciderti se non esisti

		String sql = "delete from "+table+" where id="+id;
		Statement deleteCmd = connection.createStatement();
		deleteCmd.execute(sql);
		return true;
	}
	
}
