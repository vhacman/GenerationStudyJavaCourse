package com.generation.library.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.generation.library.Entity;
import com.generation.library.cache.EntityCache;
import com.generation.library.cache.FullCache;

public abstract class FullCacheSQLEntityRepository<X extends Entity> extends SQLEntityRepository<X>
{
	// io contengo una full cache
	EntityCache<X> cache;
	
	public FullCacheSQLEntityRepository(String table, Connection connection) throws SQLException
	{
		super(table, connection);
		// carico tutto dal db e metto in cache
		// prendo il findAll di mio padre perché io non leggo più dal db, io leggo dalla cache
		// super.findAll() => findall di SQLEntityRepository
		// il mio findAll() => legge dalla cache
		cache = new FullCache<X>(super.findAll()); // riempio la cache
	}
	
	/**
	 * Non tocco davvero il db
	 * non uso findWhere
	 * uso la cache
	 */
	@Override
	public X findById(int id) throws SQLException
	{
		// non è lo stesso findById
		// findById del repository => sul DB
		// findById della cache => sulla RAM
		return cache.findById(id);
	}

	@Override
	public List<X> findAll() throws SQLException
	{
		return cache.findAll();
	}
	
	@Override
	public X insert(X x) throws SQLException
	{
		// tocco davvero il db
		x = super.insert(x);
		// aggiorno la cache
		cache.insert(x);
		return x;
	}
	
	@Override
	public boolean delete(int id) throws SQLException
	{
		X x = findById(id);
		boolean success = super.delete(id);
		
		if(success)
			cache.delete(x);
		
		return success;
	}
	
		
	@Override
	public X update(X x) throws SQLException
	{
		x = super.update(x);
		cache.update(x);
		return x;
	}
	
}
