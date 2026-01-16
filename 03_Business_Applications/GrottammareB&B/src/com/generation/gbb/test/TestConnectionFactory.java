package com.generation.gbb.test;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.library.Console;

class TestConnectionFactory
{

	@Test
	void test()
	{
		Connection c = ConnectionFactory.make();
		//tipo concreto che stampo in console. 
		Console.print(c.getClass().getName());
		assert (c != null);
	}

}
