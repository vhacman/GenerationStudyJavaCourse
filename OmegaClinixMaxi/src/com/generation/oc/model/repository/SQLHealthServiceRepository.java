package com.generation.oc.model.repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.generation.library.repository.SQLEntityRepository;
import com.generation.oc.model.entities.HealthService;

public class SQLHealthServiceRepository extends SQLEntityRepository<HealthService> implements HealthServiceRepository
{
	public SQLHealthServiceRepository(String table, Connection connection) {super(table, connection);}	
	// Non serve Override di findWhere(String cond), findAll() e findById(int id)
	// HealthService non ha relazioni da caricare, quindi uso direttamente
	// i metodi della superclasse senza aggiungere logica extra
	/**
	 * Crea un PreparedStatement per inserire un nuovo servizio sanitario nel database.
	 * 
	 * @param service l'oggetto HealthService da inserire
	 * @return un PreparedStatement configurato pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando
	 */
	@Override
	protected PreparedStatement getInsertCmd(HealthService service) throws SQLException
	{
		// Query SQL per l'inserimento (l'ID è AUTO_INCREMENT)
		String 							sql = "INSERT INTO healthservice (name, price) VALUES (?, ?)";
		PreparedStatement 	ps = connection.prepareStatement(sql);		
		// Mappo i valori ai placeholder
		ps.setString(1, service.getName());   // Primo ? → name
		ps.setInt(2, service.getPrice());     // Secondo ? → price		
		return ps;
	}
	/**
	 * Crea un PreparedStatement per aggiornare un servizio sanitario esistente.
	 * 
	 * @param newVersion l'oggetto HealthService con i nuovi valori
	 * @return un PreparedStatement configurato pronto per l'esecuzione
	 * @throws SQLException se si verifica un errore durante la preparazione del comando
	 */
	@Override
	protected PreparedStatement getUpdateCmd(HealthService newVersion) throws SQLException
	{		// Query SQL per l'update
		String 							sql = "UPDATE healthservice SET name=?, price=? WHERE id=?";
		PreparedStatement 	ps = connection.prepareStatement(sql);		
		// Mappo i nuovi valori ai placeholder della SET clause
		ps.setString(1, newVersion.getName());
		ps.setInt(2, newVersion.getPrice());		
		// Il parametro per la WHERE clause
		ps.setInt(3, newVersion.getId());		
		return ps;
	}
	/**
	 * Converte una riga del ResultSet in un oggetto HealthService.
	 * 
	 * @param rows il ResultSet posizionato sulla riga da convertire
	 * @return un nuovo oggetto HealthService con i dati estratti dal database
	 * @throws SQLException se si verifica un errore durante la lettura dei dati
	 */
	@Override
	public HealthService rowToX(ResultSet rows) throws SQLException
	{
		HealthService 	service = new HealthService();		
		// Mappo i campi del database alle proprietà dell'oggetto
		service.setId(rows.getInt("id"));
		service.setName(rows.getString("name"));
		service.setPrice(rows.getInt("price"));		
		return service;
	}
}
