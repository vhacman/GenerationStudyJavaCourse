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
public abstract class SQLEntityRepository <X extends Entity>
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
			PreparedStatement 	readCmd = connection.prepareStatement("SELECT MAX(id) AS m FROM " + table);
			ResultSet 			rs 		= readCmd.executeQuery();
			int 				res 	=  rs.getInt("m");
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
		List<X> res = new ArrayList<X>();
		try
		{
			String sql = "SELECT * FROM "+table+ " WHERE "+condition;
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
		return findWhere("id > 0");
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
		if(findById(id)==null)
			return false; // non posso ucciderti se non esisti

		String sql = "delete from "+table+" where id="+id;
		Statement deleteCmd = connection.createStatement();
		deleteCmd.execute(sql);
		return true;
	}
	
}
