package com.generation.oc.model.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.Prestation;

public class SQLPrestationRepository extends SQLEntityRepository<Prestation> implements PrestationRepository
{
	public SQLPrestationRepository(String table, Connection connection)	{super(table, connection);}
	/**
	 * Recupera una prestazione in base al suo ID, con opzione per caricare o meno
	 * le entità associate (Patient e HealthService).
	 *
	 * @param id l'identificativo univoco della prestazione da cercare
	 * @param complete true per caricare anche Patient e HealthService, false per i soli dati base
	 * @return l'oggetto Prestation richiesto, con o senza relazioni caricate
	 * @throws SQLException se si verifica un errore durante l'accesso al database
	 */
	@Override
	public Prestation findById(int id, boolean complete) throws SQLException
	{
		// Se complete è false, restituisco solo i dati base
		// Se complete è true, carico anche le entità associate
		return !complete ? findByIdNaked(id) : findById(id);
	}
	/**
	 * Recupera una prestazione dal database utilizzando il suo ID, senza caricare le relazioni.
	 * 
	 * @param id l'ID univoco della prestazione da cercare
	 * @return un oggetto Prestation con i soli dati di base se trovato, null altrimenti
	 * @throws SQLException se si verifica un errore durante l'esecuzione della query
	 */
	private Prestation findByIdNaked(int id) throws SQLException
	{
		// Creo uno Statement per eseguire la query SQL
		Statement 	readSQL = connection.createStatement();		
		// Eseguo la SELECT per cercare la prestazione con l'ID specificato
		ResultSet 		rows = readSQL.executeQuery("SELECT * FROM prestation WHERE id = " + id);
		// Se esiste una riga (rows.next() == true), la converto in oggetto Prestation
		// Altrimenti restituisco null per indicare che la prestazione non è stata trovata
		Prestation 	res = rows.next() ? rowToX(rows) : null;		
		// Chiudo le risorse per evitare memory leak
		readSQL.close();
		rows.close();		
		return res;
	}
	/** Crea un PreparedStatement per inserire una nuova prestazione nel database.	 * 
	 * Salva solo i riferimenti agli ID di Patient e HealthService (foreign keys),
	 * non gli oggetti completi. 
	 * @param prestation l'oggetto Prestation da inserire nel database
	 * @return un PreparedStatement configurato pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando */
	@Override
	protected PreparedStatement getInsertCmd(Prestation prestation) throws SQLException
	{	// Query SQL per l'inserimento (l'ID è AUTO_INCREMENT)
		// Assumo campi: patient_id, healthservice_id, date, price
		String 							sql = "INSERT INTO prestation (patientid, healthserviceid, date, price) VALUES (?, ?, ?, ?)";
		PreparedStatement 	ps = connection.prepareStatement(sql);
		// CORRETTO: uso getPatient().getId() invece di prestation.getId()
		// Mappo i valori dell'oggetto Prestation ai placeholder
		ps.setInt(1, prestation.getPatient().getId());           // Foreign key verso Patient
		// CORRETTO: uso getHealthService().getId() invece di prestation.getId()
		ps.setInt(2, prestation.getHealthService().getId());     // Foreign key verso HealthService
		ps.setString(3, prestation.getDate().toString());
		ps.setInt(4, prestation.getPrice());               // Prezzo della prestazione
		return ps;
	}
	/** Crea un PreparedStatement per aggiornare una prestazione esistente nel database.
	 * @param newVersion l'oggetto Prestation con i nuovi valori da salvare
	 * @return un PreparedStatement configurato pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando	 */
	@Override
	protected PreparedStatement getUpdateCmd(Prestation newVersion) throws SQLException
	{// Query SQL per l'update: modifico tutti i campi tranne l'ID (che è nella WHERE)
		String 							sql = "UPDATE prestation SET patientid=?, healthserviceid=?, date=?, price=? WHERE id=?";
		PreparedStatement 	ps = connection.prepareStatement(sql);

		// CORRETTO: uso getPatient().getId() invece di newVersion.getId()
		// Mappo i nuovi valori ai placeholder della SET clause
		ps.setInt(1, newVersion.getPatient().getId());
		// CORRETTO: uso getHealthService().getId() invece di newVersion.getId()
		ps.setInt(2, newVersion.getHealthService().getId());
		ps.setString(3, newVersion.getDate() != null ? newVersion.getDate().toString() : null);
		ps.setInt(4, newVersion.getPrice());
		// Il parametro per la WHERE clause: quale prestazione voglio aggiornare?
		ps.setInt(5, newVersion.getId());
		return ps;
	}
	/**
	 * Converte una riga del ResultSet in un oggetto Prestation.
	 * 
	 * Questo metodo mappa solo i dati diretti della tabella prestation (ID, foreign keys, date, price).
	 * Le entità associate (Patient, HealthService) NON vengono caricate qui,
	 * rimangono null finché non vengono popolate da findWhere o findById.
	 * 
	 * @param rows il ResultSet posizionato sulla riga da convertire
	 * @return un nuovo oggetto Prestation con i dati di base estratti dal database
	 * @throws SQLException se si verifica un errore durante la lettura dei dati o il parsing
	 */
	@Override
	public Prestation rowToX(ResultSet rows) throws SQLException
	{
		Prestation 							prestation = new Prestation();
		// Mappo i campi del database alle proprietà dell'oggetto
		prestation.setId(rows.getInt("id"));
		// CORRETTO: carico direttamente gli oggetti Patient e HealthService dai repository
		// usando gli ID delle foreign key presenti nel ResultSet
		// In questo modo posso poi accedere a prestation.getPatient().getId()
		PatientRepository 			patientRepo = Context.getDependency(PatientRepository.class);
		HealthServiceRepository 	healthServiceRepo = Context.getDependency(HealthServiceRepository.class);
		int 						patientId = rows.getInt("patientid");
		int 						healthServiceId = rows.getInt("healthserviceid");
		// Carico gli oggetti completi (senza le loro relazioni per evitare loop infiniti) --> collegamento ai padri
		prestation.setPatient(patientRepo.findById(patientId, false));
		prestation.setHealthService(healthServiceRepo.findById(healthServiceId));
		prestation.setDate(LocalDate.parse(rows.getString("date")));
		prestation.setPrice(rows.getInt("price"));
		return prestation;
	}
}
