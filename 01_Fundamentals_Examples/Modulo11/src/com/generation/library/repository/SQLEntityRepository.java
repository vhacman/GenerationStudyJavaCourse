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
	
	
	public X findById(int id)
	{
		List<X> matches = findWhere("id="+id);
		return matches.size()>0 ? matches.get(0) : null;
	}
	
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
	

	public List<X> findWhere(String condition)
	{
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
	
	
	public List<X> findAll()
	{
		return findWhere("id>0");
	}
	
	/**
	 * Metodo che trasformerà una riga in un X
	 * ma io cosa sia X non lo so ancora
	 * lo deciderà la mia SOTTOCLASSE
	 * @param rows
	 * @return
	 */
	public abstract X rowToX(ResultSet rows) throws SQLException;


	/**
	 * Cancella una riga dalla tabella
	 * @param id
	 * @return
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
