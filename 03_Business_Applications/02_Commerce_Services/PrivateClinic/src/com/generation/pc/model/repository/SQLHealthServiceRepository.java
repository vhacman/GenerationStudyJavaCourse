package com.generation.pc.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.generation.library.repository.PartialCacheSQLEntityRepository;
import com.generation.pc.model.entity.HealthService;

/*
 * ==================================================================================
 * TEORIA: Implementazione repository per HealthService
 * ==================================================================================
 * Questa classe segue la stessa architettura di SQLPatientRepository:
 * - Estende PartialCacheSQLEntityRepository<HealthService> per riutilizzare CRUD
 * - Implementa HealthServiceRepository per rispettare il contratto
 * - Fornisce implementazioni specifiche per HealthService:
 *   1. rowToX(): mapping ResultSet → HealthService
 *   2. getUpdateCmd(): genera PreparedStatement per UPDATE
 *   3. getInsertCmd(): genera PreparedStatement per INSERT
 *   4. findByType(): query personalizzata per tipo servizio
 *
 * DIFFERENZE CON SQLPatientRepository:
 * - Tabella diversa: "health_services" invece di "patients"
 * - Campi diversi: description, type, price invece di firstName, lastName, etc.
 * - Query custom diverse: findByType invece di findBySSN, findByCity, etc.
 *
 * Il pattern è lo stesso, cambiano solo i dettagli specifici dell'entità.
 */
/**
 * Implementazione SQL del repository dei servizi sanitari.
 * Utilizza cache parziale per ottimizzare le performance.
 * Estende PartialCacheSQLEntityRepository e implementa HealthServiceRepository.
 */
public class SQLHealthServiceRepository extends PartialCacheSQLEntityRepository<HealthService> implements HealthServiceRepository
{

	public SQLHealthServiceRepository(Connection connection)
	{
		/*
		 ******************************************
		 * Inizializza il repository configurando:
		 * - Tabella: "health_services"
		 * - Connection: connessione al database
		 * - Cache size: 50 (ultimi 50 servizi acceduti)
		 ******************************************
		 */
		super("health_services", connection, 50);
	}

	/**
	 * Cerca servizi sanitari per tipo.
	 * @param type il tipo da cercare
	 * @return lista di servizi trovati
	 */
	@Override
	public List<HealthService> findByType(String type)
	{
		/*
		 ******************************************
		 * Esegue: SELECT * FROM health_services WHERE type='Visita'
		 *
		 * Filtra i servizi per tipo utilizzando
		 * una condizione WHERE con uguaglianza esatta.
		 *
		 * Esempi di tipi: "Visita", "Esame", "Chirurgia", "Diagnostica"
		 ******************************************
		 */
		return findWhere("type='" + type + "'");
	}

	/**
	 * Crea il comando SQL di update per un servizio sanitario.
	 * @param h la nuova versione del servizio
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getUpdateCmd(HealthService h) throws SQLException
	{
		/*
		 ******************************************
		 * UPDATE health_services SET description=?, type=?, price=? WHERE id=?
		 *
		 * Aggiorna i 3 campi del servizio sanitario.
		 * L'ordine dei parametri:
		 * 1 = description
		 * 2 = type
		 * 3 = price
		 * 4 = id (condizione WHERE)
		 *
		 * Nota: usiamo setInt per price perché è definito come int (tipo primitivo)
		 ******************************************
		 */
		String sql = "UPDATE health_services SET description=?, type=?, price=? WHERE id=?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, h.getDescription());
		ps.setString(2, h.getType());
		ps.setInt(3, h.getPrice());
		ps.setInt(4, h.getId());
		return ps;
	}

	/**
	 * Crea il comando SQL di insert per un servizio sanitario.
	 * @param h il servizio da inserire
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	@Override
	protected PreparedStatement getInsertCmd(HealthService h) throws SQLException
	{
		/*
		 ******************************************
		 * INSERT INTO health_services (description, type, price) VALUES (?, ?, ?)
		 *
		 * Inserisce un nuovo servizio con 3 campi.
		 * NON includiamo id perché è AUTO_INCREMENT.
		 *
		 * NOTA:
		 * Non usiamo PreparedStatement.RETURN_GENERATED_KEYS perché la classe base
		 * gestisce il recupero dell'id in modo diverso (probabilmente con una query
		 * separata o un meccanismo specifico del framework).
		 *
		 * ORDINE DEI PARAMETRI:
		 * 1 = description
		 * 2 = type
		 * 3 = price
		 ******************************************
		 */
		String sql = "INSERT INTO health_services (description, type, price) VALUES (?,?,?)";
		PreparedStatement insertCmd = connection.prepareStatement(sql);
		insertCmd.setString(1, h.getDescription());
		insertCmd.setString(2, h.getType());
		insertCmd.setInt(3, h.getPrice());
		return insertCmd;
	}

	/**
	 * Trasforma una riga del database in un oggetto HealthService.
	 * @param rs il ResultSet posizionato sulla riga
	 * @return il servizio creato dalla riga
	 * @throws SQLException in caso di errore
	 */
	@Override
	public HealthService rowToX(ResultSet rs) throws SQLException
	{
		/*
		 ******************************************
		 * Object-Relational Mapping per HealthService
		 *
		 * Converte una riga della tabella health_services in un oggetto Java.
		 *
		 * MAPPING:
		 * id (INT)           → h.setId(int)
		 * description (VARCHAR) → h.setDescription(String)
		 * type (VARCHAR)     → h.setType(String)
		 * price (INT)        → h.setPrice(int)
		 *
		 * Questo metodo è chiamato automaticamente dalla classe base
		 * per ogni riga del ResultSet nelle query SELECT.
		 ******************************************
		 */
		HealthService h = new HealthService();
		h.setId(rs.getInt("id"));
		h.setDescription(rs.getString("description"));
		h.setType(rs.getString("type"));
		h.setPrice(rs.getInt("price"));
		return h;
	}

}
