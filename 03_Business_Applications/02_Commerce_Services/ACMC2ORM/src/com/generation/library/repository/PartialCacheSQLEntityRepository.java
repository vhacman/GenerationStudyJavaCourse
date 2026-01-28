package com.generation.library.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.generation.library.Entity;
import com.generation.library.cache.EntityCache;
import com.generation.library.cache.PartialCache;

public abstract class PartialCacheSQLEntityRepository<X extends Entity> extends SQLEntityRepository<X>
{
	EntityCache<X> cache;
	
	
	public PartialCacheSQLEntityRepository(String table, Connection connection, int maxSize) 
	{
		super(table, connection);
		cache = new PartialCache<X>(maxSize); // a questo giro la cache nasce vuota
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public X findById(int id) throws SQLException
	{
		X cached = cache.findById(id);
		if(cached!=null)
			return cached;
		// tocco il db
		X res = super.findById(id);
			
		if(res!=null)
			cache.insert(res); // lo tengo in cache per dopo
		return res;		
	}
	
	@Override
	public X insert(X x) throws SQLException
	{
		X res = super.insert(x);
		cache.insert(res);
		return res;
	}
	
	@Override
	public X update(X x) throws SQLException
	{
		x = super.update(x);
		cache.update(x);
		return x;
	}
	
	@Override
	public boolean delete(int id) throws SQLException
	{
		X x = findById(id);
		boolean success = super.delete(id);	// DB
		if(success)
			cache.delete(x);				// CACHE (RAM)
		return success;
	}
	
	
	

}
