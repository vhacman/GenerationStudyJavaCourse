package com.generation.pc.model.repository;

/*
 * ==================================================================================
 * TEORIA: Import java.sql.* (JDBC - Java Database Connectivity)
 * ==================================================================================
 * JDBC è l'API standard di Java per interagire con database relazionali.
 *
 * CLASSI PRINCIPALI:
 * - Connection: rappresenta una connessione attiva al database
 * - PreparedStatement: query SQL precompilata con parametri (sicura contro SQL injection)
 * - ResultSet: risultato di una query SELECT, simile a un cursore che scorre le righe
 * - SQLException: eccezione sollevata per errori di database
 *
 * SCOPO:
 * Fornire un'interfaccia unificata per accedere a diversi database (MySQL, PostgreSQL,
 * Oracle, etc.) usando lo stesso codice Java.
 *
 * PERCHÉ LO FACCIAMO:
 * JDBC ci permette di eseguire query SQL dal codice Java e mappare i risultati
 * in oggetti Java (ORM manuale). Usiamo PreparedStatement invece di Statement
 * perché previene SQL injection e migliora le performance.
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.library.repository.PartialCacheSQLEntityRepository;
import com.generation.pc.model.entity.Patient;

/*
 * ==================================================================================
 * TEORIA: Implementazione di interfaccia ed estensione di classe
 * ==================================================================================
 * DEFINIZIONE:
 * SQLPatientRepository:
 * - EXTENDS PartialCacheSQLEntityRepository<Patient>: eredita implementazione base
 * - IMPLEMENTS PatientRepository: implementa il contratto dell'interfaccia
 *
 * DIFFERENZA TRA EXTENDS E IMPLEMENTS:
 * - extends: eredita implementazione (codice riutilizzabile) da una classe
 * - implements: promette di fornire implementazione dei metodi dell'interfaccia
 *
 * SCOPO:
 * - Ereditare logica comune (CRUD, cache) da PartialCacheSQLEntityRepository
 * - Implementare logica specifica (rowToX, getUpdateCmd) per Patient
 * - Rispettare il contratto PatientRepository
 *
 * PERCHÉ LO FACCIAMO:
 * Evitiamo duplicazione di codice: findAll(), findById(), insert(), update(), delete()
 * sono già implementati nella classe base. Dobbiamo solo implementare:
 * 1. rowToX(): come convertire una riga SQL in un oggetto Patient
 * 2. getUpdateCmd(): come generare l'UPDATE SQL per Patient
 * 3. getInsertCmd(): come generare l'INSERT SQL per Patient
 * 4. Query custom: findBySSN(), findByCity(), findByLastNameContaining()
 *
 * ==================================================================================
 * TEORIA: Generics <Patient>
 * ==================================================================================
 * PartialCacheSQLEntityRepository<Patient> usa i generics per essere riutilizzabile
 * con qualsiasi entità. Il <Patient> specifica che questa istanza lavora con Patient.
 */
/**
 * Implementazione SQL del repository dei pazienti.
 * Utilizza cache parziale per ottimizzare le performance.
 * Estende PartialCacheSQLEntityRepository e implementa PatientRepository.
 */
public class SQLPatientRepository extends PartialCacheSQLEntityRepository<Patient> implements PatientRepository
{

	/*
	 * ==================================================================================
	 * TEORIA: Costruttore e chiamata al costruttore della superclasse (super)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * super() chiama il costruttore della classe padre (PartialCacheSQLEntityRepository).
	 *
	 * SCOPO:
	 * Inizializzare i campi ereditati dalla superclasse.
	 *
	 * PARAMETRI:
	 * 1. "patients": nome della tabella SQL
	 * 2. connection: oggetto Connection per comunicare con il database
	 * 3. 50: dimensione massima della cache (ultimi 50 pazienti letti rimangono in memoria)
	 *
	 * PERCHÉ LO FACCIAMO:
	 * La classe base PartialCacheSQLEntityRepository ha bisogno di conoscere:
	 * - Quale tabella interrogare
	 * - Come connettersi al database
	 * - Quanti elementi cachare
	 * Il costruttore configura questi parametri per specializzare il repository generico.
	 */
	public SQLPatientRepository(Connection connection)
	{
		/*
		 ******************************************
		 * Inizializza il repository chiamando il
		 * costruttore della superclasse con tabella,
		 * connessione e dimensione massima della cache
		 ******************************************
		 */
		super("patients", connection, 50);
	}

	/*
	 * ==================================================================================
	 * TEORIA: Metodi di query personalizzati - findWhere
	 * ==================================================================================
	 * DEFINIZIONE:
	 * findWhere è un metodo ereditato dalla classe base che esegue:
	 * SELECT * FROM tabella WHERE condizione
	 *
	 * SCOPO:
	 * Evitare di riscrivere tutta la logica di query per ogni filtro.
	 *
	 * PERCHÉ LO FACCIAMO:
	 * La classe base fornisce findWhere(String condition) che:
	 * 1. Esegue la query con la condizione fornita
	 * 2. Converte ogni riga in un oggetto usando rowToX()
	 * 3. Restituisce una List<Patient>
	 *
	 * Noi forniamo solo la condizione WHERE specifica.
	 */

	/**
	 * Cerca un paziente per codice fiscale.
	 * @param ssn il codice fiscale da cercare
	 * @return il paziente trovato o null
	 */
	@Override
	public Patient findBySSN(String ssn)
	{
		/*
		 ******************************************
		 * TEORIA: Ricerca con condizione di uguaglianza
		 ******************************************
		 * Esegue: SELECT * FROM patients WHERE ssn='RSSMRA80A01H501U'
		 *
		 * NOTA SULLA SICUREZZA:
		 * Questo codice è vulnerabile a SQL injection! In produzione si dovrebbe usare
		 * PreparedStatement con parametri. È accettabile qui per semplicità didattica.
		 *
		 * OPERATORE TERNARIO:
		 * condition ? valoreSeTrue : valoreSeFalse
		 * È una forma compatta di if-else su una singola linea.
		 *
		 * results.size() > 0 ? results.get(0) : null
		 * equivale a:
		 * if (results.size() > 0)
		 *     return results.get(0);
		 * else
		 *     return null;
		 ******************************************
		 */
		List<Patient> results = findWhere("ssn='" + ssn + "'");
		return results.size() > 0 ? results.get(0) : null;
	}

	/**
	 * Cerca pazienti il cui cognome contiene una stringa.
	 * @param part la stringa da cercare
	 * @return lista di pazienti trovati
	 */
	@Override
	public List<Patient> findByLastNameContaining(String part)
	{
		/*
		 ******************************************
		 * TEORIA: Operatore LIKE di SQL
		 ******************************************
		 * LIKE è un operatore SQL per pattern matching su stringhe.
		 *
		 * WILDCARDS:
		 * - % : qualsiasi sequenza di caratteri (anche vuota)
		 * - _ : esattamente un carattere
		 *
		 * ESEMPI:
		 * lastName LIKE '%Rossi%'  → trova "Rossi", "DeRossi", "Rossini"
		 * lastName LIKE 'Rossi%'   → trova "Rossi", "Rossini" (inizia con Rossi)
		 * lastName LIKE '%Rossi'   → trova "Rossi", "DeRossi" (finisce con Rossi)
		 * lastName LIKE 'R_ssi'    → trova "Rossi", "Ressi" (R + 1 char + ssi)
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Per implementare funzionalità di ricerca/filtro che permettono all'utente
		 * di trovare pazienti anche conoscendo solo parte del cognome.
		 ******************************************
		 */
		return findWhere("lastName LIKE '%" + part + "%'");
	}

	/**
	 * Cerca pazienti per città.
	 * @param city la città da cercare
	 * @return lista di pazienti trovati
	 */
	@Override
	public List<Patient> findByCity(String city)
	{
		/*
		 ******************************************
		 * Esegue: SELECT * FROM patients WHERE city='Roma'
		 *
		 * Filtra i pazienti per città utilizzando
		 * una condizione WHERE con uguaglianza esatta (=)
		 * a differenza di LIKE che cerca pattern.
		 ******************************************
		 */
		return findWhere("city='" + city + "'");
	}

	/*
	 * ==================================================================================
	 * TEORIA: PreparedStatement e parametri SQL (?)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * PreparedStatement è una query SQL precompilata con segnaposto (?) per i parametri.
	 *
	 * SCOPO:
	 * - Sicurezza: previene SQL injection (i parametri vengono escapati automaticamente)
	 * - Performance: la query viene precompilata una volta e riutilizzata
	 * - Leggibilità: separa la struttura SQL dai valori
	 *
	 * COME FUNZIONA:
	 * 1. Definiamo la query con ? al posto dei valori: "UPDATE ... SET name=? WHERE id=?"
	 * 2. Creiamo il PreparedStatement: connection.prepareStatement(sql)
	 * 3. Impostiamo i parametri: ps.setString(1, valore) - indice parte da 1, non 0!
	 * 4. Eseguiamo: ps.executeUpdate() o ps.executeQuery()
	 *
	 * METODI SET:
	 * - setString(index, String): per VARCHAR, CHAR, TEXT
	 * - setInt(index, int): per INT, SMALLINT, TINYINT
	 * - setDouble(index, double): per DOUBLE, DECIMAL
	 * - setObject(index, Object): generico, auto-converte (es. LocalDate → DATE)
	 * - setNull(index, type): per valori NULL
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Senza PreparedStatement (concatenazione stringhe):
	 *   "UPDATE patients SET city='" + city + "'"
	 * Se city = "Roma'; DROP TABLE patients; --" → SQL INJECTION!
	 *
	 * Con PreparedStatement:
	 *   ps.setString(4, city)
	 * La stringa viene automaticamente escapata, rendendo impossibile l'injection.
	 */

	/**
	 * Crea il comando SQL di update per un paziente.
	 * @param p la nuova versione del paziente
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getUpdateCmd(Patient p) throws SQLException
	{
		/*
		 ******************************************
		 * TEORIA: Query UPDATE SQL
		 ******************************************
		 * UPDATE tabella SET campo1=valore1, campo2=valore2 WHERE condizione
		 *
		 * Aggiorna le righe che soddisfano la condizione WHERE.
		 * Senza WHERE, aggiorna TUTTE le righe (pericoloso!).
		 *
		 * I ? vengono sostituiti dai valori nell'ordine:
		 * ?, ?, ?, ?, ?, ?, ?
		 * 1  2  3  4  5  6  7
		 * firstName, lastName, ssn, city, address, dob, id
		 ******************************************
		 */
		String sql = "UPDATE patients SET firstName=?, lastName=?, ssn=?, city=?, address=?, dob=? WHERE id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, p.getFirstName());
		ps.setString(2, p.getLastName());
		ps.setString(3, p.getSsn());
		ps.setString(4, p.getCity());
		ps.setString(5, p.getAddress());
		ps.setObject(6, p.getDob());  // setObject converte LocalDate in SQL DATE automaticamente
		ps.setInt(7, p.getId());      // Ultimo parametro: WHERE id=?
		return ps;
	}

	/**
	 * Crea il comando SQL di insert per un paziente.
	 * @param p il paziente da inserire
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getInsertCmd(Patient p) throws SQLException
	{
		/*
		 ******************************************
		 * TEORIA: Query INSERT SQL
		 ******************************************
		 * INSERT INTO tabella (campo1, campo2, ...) VALUES (valore1, valore2, ...)
		 *
		 * Inserisce una nuova riga nella tabella.
		 * - NON includiamo 'id' perché è AUTO_INCREMENT (generato dal database)
		 * - L'ordine dei campi deve corrispondere all'ordine dei VALUES
		 *
		 * NOTA:
		 * Non usiamo PreparedStatement.RETURN_GENERATED_KEYS perché la classe base
		 * gestisce il recupero dell'id in modo diverso (probabilmente con una query
		 * separata o un meccanismo specifico del framework).
		 *
		 * ORDINE DEI PARAMETRI:
		 * 1 = firstName
		 * 2 = lastName
		 * 3 = ssn
		 * 4 = city
		 * 5 = address
		 * 6 = dob (LocalDate convertito automaticamente in DATE con setObject)
		 ******************************************
		 */
		String sql = "INSERT INTO patients (firstName, lastName, ssn, city, address, dob) VALUES (?,?,?,?,?,?)";
		PreparedStatement insertCmd = connection.prepareStatement(sql);
		insertCmd.setString(1, p.getFirstName());
		insertCmd.setString(2, p.getLastName());
		insertCmd.setString(3, p.getSsn());
		insertCmd.setString(4, p.getCity());
		insertCmd.setString(5, p.getAddress());
		insertCmd.setObject(6, p.getDob());
		return insertCmd;
	}

	/*
	 * ==================================================================================
	 * TEORIA: ResultSet - leggere dati dal database
	 * ==================================================================================
	 * DEFINIZIONE:
	 * ResultSet è un oggetto che rappresenta il risultato di una query SELECT.
	 * Funziona come un cursore che scorre le righe del risultato.
	 *
	 * SCOPO:
	 * Accedere ai dati restituiti da una query SQL riga per riga.
	 *
	 * COME FUNZIONA:
	 * 1. Eseguiamo query: ResultSet rs = ps.executeQuery()
	 * 2. Scorriamo le righe: while (rs.next()) { ... }
	 * 3. Leggiamo i campi: rs.getString("nome"), rs.getInt("id"), etc.
	 *
	 * METODI PRINCIPALI:
	 * - next(): sposta il cursore alla riga successiva, ritorna true se esiste
	 * - getString("campo"): legge un campo VARCHAR/TEXT come String
	 * - getInt("campo"): legge un campo INT come int
	 * - getDouble("campo"): legge un campo DOUBLE/DECIMAL come double
	 * - getObject("campo"): legge un campo generico come Object
	 *
	 * NOTA:
	 * Si può usare anche rs.getString(1) (indice colonna, parte da 1) invece del nome,
	 * ma è meno leggibile e più fragile a cambiamenti nello schema.
	 *
	 * PERCHÉ LO FACCIAMO:
	 * rowToX() è il metodo che converte una riga SQL in un oggetto Java (mapping).
	 * È chiamato automaticamente dalla classe base per ogni riga del ResultSet.
	 */

	/**
	 * Trasforma una riga del database in un oggetto Patient.
	 * @param rs il ResultSet posizionato sulla riga
	 * @return il paziente creato dalla riga
	 * @throws SQLException in caso di errore
	 */
	@Override
	public Patient rowToX(ResultSet rs) throws SQLException
	{
		/*
		 ******************************************
		 * TEORIA: Object-Relational Mapping (ORM) manuale
		 ******************************************
		 * Questo metodo implementa il mapping tra:
		 * - Riga del database (tabella patients) → Oggetto Patient
		 *
		 * PROCESSO:
		 * 1. Creiamo un oggetto Patient vuoto
		 * 2. Leggiamo ogni campo dal ResultSet
		 * 3. Impostiamo i campi dell'oggetto con i setter
		 * 4. Restituiamo l'oggetto popolato
		 *
		 * MAPPING:
		 * Database (SQL)          →  Java (Object)
		 * id (INT)               →  p.setId(int)
		 * firstName (VARCHAR)    →  p.setFirstName(String)
		 * dob (DATE)             →  p.setDob(String) - il setter fa il parse
		 *
		 * NOTA:
		 * Per dob usiamo getString invece di getDate/getObject perché abbiamo
		 * un setter overloaded setDob(String) che fa il parsing LocalDate.parse().
		 * Potremmo anche fare:
		 *   p.setDob(rs.getObject("dob", LocalDate.class))
		 * ma richiederebbe JDBC 4.2+
		 ******************************************
		 */
		Patient p = new Patient();
		p.setId(rs.getInt("id"));
		p.setFirstName(rs.getString("firstName"));
		p.setLastName(rs.getString("lastName"));
		p.setSsn(rs.getString("ssn"));
		p.setCity(rs.getString("city"));
		p.setAddress(rs.getString("address"));
		p.setDob(rs.getString("dob"));  // Il setter setDob(String) fa LocalDate.parse()

		return p;
	}

}
