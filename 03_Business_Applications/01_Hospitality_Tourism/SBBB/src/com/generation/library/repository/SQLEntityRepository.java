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
 * Repository base per entità SQL.
 * Fornisce operazioni CRUD standard per qualsiasi tipo di entità.
 * @param <X> il tipo di entità gestita dal repository
 */
public abstract class SQLEntityRepository<X extends Entity>
{

	protected Connection	connection;
	protected String		table;

	public SQLEntityRepository(String table, Connection connection)
	{
		/*
		 ******************************************
		 * Inizializza il repository con il nome
		 * della tabella e la connessione al database
		 ******************************************
		 */
		this.table = table;
		this.connection = connection;
	}


	/**
	 * Cerca un'entità per id nel database.
	 * @param id l'identificativo da cercare
	 * @return l'entità trovata o null
	 */
	public X findById(int id)
	{
		/*
		 ******************************************
		 * Utilizza findWhere per costruire una
		 * query con condizione id=? e restituisce
		 * il primo elemento trovato
		 ******************************************
		 */
		List<X> matches = findWhere("id="+id);
		return matches.size()>0 ? matches.get(0) : null;
	}

	/**
	 * Inserisce una nuova entità nel database.
	 * @param x l'entità da inserire
	 * @return l'entità con id assegnato
	 * @throws SQLException se l'entità non è valida o ha già un id
	 */
	public X insert(X x) throws SQLException
	{
		/*
		 ******************************************
		 * Valida l'entità verificando che sia valida
		 * e non abbia già un id. Esegue l'insert
		 * e assegna il nuovo id generato dal database
		 ******************************************
		 */
		if(!x.isValid())
			throw new SQLException("Invalid entity");

		if(x.getId()!=0)
			throw new SQLException("Entity already has an id");

		PreparedStatement insertCmd = getInsertCmd(x);

		insertCmd.execute();
		insertCmd.close();
		x.setId(getNewId());

		return x;

	}

	/**
	 * Aggiorna un'entità esistente nel database.
	 * @param newVersion la nuova versione dell'entità
	 * @return l'entità aggiornata
	 * @throws SQLException se l'entità non è valida o non esiste
	 */
	public X update(X newVersion) throws SQLException
	{
		/*
		 ******************************************
		 * Valida l'entità, verifica che esista
		 * nel database e esegue l'update con
		 * i nuovi valori forniti
		 ******************************************
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


	/**
	 * Produce il comando SQL di update per l'entità.
	 * @param newVersion l'entità da aggiornare
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	protected abstract PreparedStatement getUpdateCmd(X newVersion) throws SQLException;


	/**
	 * Produce il comando SQL di insert per l'entità.
	 * @param x l'entità da inserire
	 * @return il PreparedStatement configurato
	 * @throws SQLException in caso di errore
	 */
	protected abstract PreparedStatement getInsertCmd(X x) throws SQLException;

	/**
	 * Recupera l'ultimo id generato dalla tabella.
	 * @return l'id massimo corrente
	 */
	private int getNewId()
	{
		/*
		 ******************************************
		 * Esegue una query per ottenere il valore
		 * massimo della colonna id dalla tabella
		 ******************************************
		 */
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
	 * Cerca entità che soddisfano una condizione SQL.
	 * @param condition la condizione WHERE
	 * @return lista di entità trovate
	 */
	public List<X> findWhere(String condition)
	{
		/*
		 ******************************************
		 * Esegue una query SELECT con la condizione
		 * specificata, trasforma ogni riga in entità
		 * e aggiorna le statistiche di profiling
		 ******************************************
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
	 * Restituisce tutte le entità dalla tabella.
	 * @return lista completa di entità
	 */
	public List<X> findAll()
	{
		/*
		 ******************************************
		 * Utilizza findWhere con una condizione
		 * sempre vera per recuperare tutte le righe
		 ******************************************
		 */
		return findWhere("id>0");
	}

	/**
	 * Trasforma una riga del ResultSet in un'entità.
	 * @param rows il ResultSet posizionato sulla riga
	 * @return l'entità creata dalla riga
	 * @throws SQLException in caso di errore
	 */
	public abstract X rowToX(ResultSet rows) throws SQLException;


	/**
	 * Cancella un'entità dal database.
	 * @param id l'identificativo dell'entità da cancellare
	 * @return true se la cancellazione ha successo
	 * @throws SQLException in caso di errore
	 */
	public boolean delete(int id) throws SQLException
	{
		/*
		 ******************************************
		 * Verifica che l'entità esista prima di
		 * cancellarla. Esegue il comando DELETE
		 * con la condizione sul campo id
		 ******************************************
		 */
		if(findById(id)==null)
			return false;

		String sql = "delete from "+table+" where id="+id;
		Statement deleteCmd = connection.createStatement();
		deleteCmd.execute(sql);
		return true;
	}

}
