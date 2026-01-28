package com.generation.oc.model.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import com.generation.library.repository.SQLEntityRepository;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;

public class SQLPatientRepository extends SQLEntityRepository<Patient> implements PatientRepository
{
	public SQLPatientRepository(String table, Connection connection)  { super(table, connection); }
	/**
	 * Recupera una lista di pazienti che soddisfano una condizione specifica,
	 * caricando anche tutte le prestazioni associate a ciascun paziente.
	 *
	 * @param cond la condizione SQL da applicare nella query (es. "name = 'Mario'")
	 * @return una lista di oggetti Patient con le relative prestazioni caricate
	 * @throws SQLException se si verifica un errore durante l'accesso al database
	 */
	@Override
	public List<Patient> findWhere(String cond) throws SQLException
	{
	    // Ottengo il repository delle prestazioni tramite dependency injection
	    PrestationRepository 	prestationRepo = Context.getDependency(PrestationRepository.class);
	    /*Polimorfismo tramite Ereditarietà:
	     * PatientRepository (Sottoclasse) → BaseRepository (Superclasse) 
	     * L'uso di super.findWhere() sfrutta il polimorfismo: la classe figlia
	     * riutilizza la logica della classe padre e la estende aggiungendo
	     * il caricamento delle prestazioni (Template Method Pattern).*/
	    List<Patient> 				res = super.findWhere(cond);
	    List<Prestation> 			allPrestations = prestationRepo.findAll();	    
	     /* Associazione Bidirezionale tra Entità:
	     * Patient (1) → (*) Prestation   
	     * Il pattern di associazione viene costruito manualmente confrontando gli ID.
	     * Questo approccio materializza la relazione one-to-many tra pazienti e
	     * prestazioni, popolando la collezione interna di ogni Patient. */
	    for (Patient patient : res)
			for (Prestation prestation : allPrestations)
				// CORRETTO: confronto prestation.getPatient().getId() con l'ID del paziente
				// Accedo all'ID del paziente tramite l'oggetto patient dentro prestation
	            if(prestation.getPatient().getId() == patient.getId())
					patient.addPrestations(prestation);
	    return res;
	}
	/** Recupera un paziente in base al suo ID, con opzione per caricare o meno
	 * le entità associate (prestazioni).
	 *
	 * @param id l'identificativo univoco del paziente da cercare
	 * @param complete true per caricare anche le prestazioni associate, false per i soli dati base
	 * @return l'oggetto Patient richiesto, con o senza relazioni caricate
	 * @throws SQLException se si verifica un errore durante l'accesso al database
	 */
	@Override
	public Patient findById(int id, boolean complete) throws SQLException
	{
		// Se complete è false, chiamo il metodo "naked" che restituisce solo i dati base del paziente
		// Se complete è true, chiamo il metodo che carica anche tutte le relazioni (prestazioni, ecc.)
	    return !complete ? findByIdNaked(id) : findById(id);
	}	
	/**
	 * Recupera un paziente dal database utilizzando il suo ID, senza caricare le relazioni associate.
	 * Questo metodo esegue una query diretta sulla tabella patient e restituisce solo i dati
	 * di base del paziente (senza prestazioni o altre entità collegate).
	 * @param id l'ID univoco del paziente da cercare
	 * @return un oggetto Patient con i soli dati di base se trovato, null se non esiste un paziente con quell'ID
	 * @throws SQLException se si verifica un errore durante l'esecuzione della query o la connessione al database
	 */
	private Patient findByIdNaked(int id) throws SQLException
	{
	    // Creo uno Statement per eseguire la query SQL
	    Statement 	readSQL = connection.createStatement();	    
	    // Eseguo la SELECT per cercare il paziente con l'ID specificato
	    ResultSet 		rows = readSQL.executeQuery("SELECT * FROM patient WHERE id =" + id);	    
	    // Se esiste una riga (rows.next() == true), la converto in oggetto Patient
	    // Altrimenti restituisco null per indicare che il paziente non è stato trovato
	    Patient 			res = rows.next() ? rowToX(rows) : null;	    
	    // Chiudo le risorse per evitare memory leak
	    readSQL.close();
	    rows.close();	    
	    return res;
	}	
	/** Prepara il comando SQL per l'inserimento di un nuovo paziente nel database.
	 * L'ID viene generato automaticamente dal database (AUTO_INCREMENT).
	 * @param patient l'oggetto Patient da inserire
	 * @return un PreparedStatement configurato e pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando */
	@Override
	protected PreparedStatement getInsertCmd(Patient patient) throws SQLException
	{  // Query SQL per l'inserimento (l'ID viene generato automaticamente dal database)
	    String								sql = "INSERT INTO patient (firstname, lastname, ssn, dob) VALUES (?, ?, ?, ?)";   
	    // Creo il PreparedStatement
	    PreparedStatement 	ps = connection.prepareStatement(sql);
	    ps.setString(1, patient.getFirstName());// Primo ? → firstName
	    ps.setString(2, patient.getLastName()); // Secondo ? → lastName
	    ps.setString(3, patient.getSsn());        // Terzo ? → ssn
	    // Converto LocalDate in String formato ISO (YYYY-MM-DD) per il database
	    // toString() su LocalDate produce automaticamente il formato corretto
	    ps.setString(4, patient.getDob().toString());  // Quarto ? → dob	    
	    // Restituisco il PreparedStatement pronto per essere eseguito
	    // Il metodo chiamante (nella superclasse) farà ps.executeUpdate()
	    return ps;
	}
	/** Prepara il comando SQL per l'aggiornamento di un paziente esistente.
	 * Tutti i campi vengono aggiornati, identificando il record tramite l'ID.
	 *
	 * @param newVersion l'oggetto Patient con i nuovi valori da salvare
	 * @return un PreparedStatement configurato e pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando */
	@Override
	protected PreparedStatement getUpdateCmd(Patient newVersion) throws SQLException
	{  // Query SQL per l'update: modifico tutti i campi tranne l'ID (che è nella WHERE)
	    String 							sql = "UPDATE patient SET firstname=?, lastname=?, ssn=?, dob=? WHERE id=?";
	    // Creo il PreparedStatement
	    PreparedStatement 	ps = connection.prepareStatement(sql);	    
	    // Mappo i nuovi valori ai placeholder della SET clause
	    ps.setString(1, newVersion.getFirstName());
	    ps.setString(2, newVersion.getLastName());
	    ps.setString(3, newVersion.getSsn());	    
	    // Gestione LocalDate: controllo se è null per evitare NullPointerException
	    // Se null, inserisco NULL nel database, altrimenti converto in String ISO-8601
	    ps.setString(4, newVersion.getDob() != null ? newVersion.getDob().toString() : null);	    
	    // Il parametro per la WHERE clause: quale paziente voglio aggiornare?
	    // IMPORTANTE: questo va SEMPRE per ultimo perché è l'ultimo ? nella query
	    ps.setInt(5, newVersion.getId());	    
	    return ps;
	}
	/** Converte una riga del ResultSet in un oggetto Patient, mappando i campi
	 * del database alle proprietà dell'entità del dominio.
	 *
	 * @param rows il ResultSet posizionato sulla riga da convertire
	 * @return un nuovo oggetto Patient con i dati estratti dal database
	 * @throws SQLException se si verifica un errore durante la lettura dei dati o il parsing*/
	@Override
	public Patient rowToX(ResultSet rows) throws SQLException
	{
		Patient 			patient = new Patient();
		//Mappo i campi del database alle proprietà dell'oggetto
		patient.setId(rows.getInt("id"));
		patient.setFirstName(rows.getString("firstname"));
		patient.setLastName(rows.getString("lastname"));
		patient.setDob(LocalDate.parse(rows.getString("dob")));
		patient.setSsn(rows.getString("ssn"));
		// La lista prestations rimane vuota (sarà popolata da findWhere o findById se necessario)
		return patient;
	}
}
