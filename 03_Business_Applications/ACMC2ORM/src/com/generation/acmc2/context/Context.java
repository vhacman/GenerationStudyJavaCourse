package com.generation.acmc2.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.generation.acmc2.repository.DonationRepositorySQL;
import com.generation.acmc2.repository.MemberRepositorySQL;
import com.generation.library.database.ConnectionFactory;


public class Context 
{
	
	static List<Object> dependencies = new ArrayList<Object>();
	static
	{
		try
		{
			//io riempirò la lista delle dipendenze con 
			//qualunque oggetto possa essermi utile
			Connection connection = ConnectionFactory.make("acmc.db");
			dependencies.add(connection);
			dependencies.add(new MemberRepositorySQL("member", connection));
			dependencies.add(new DonationRepositorySQL("donation", connection));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	//				     autowire => chiedere una dipendenza
	public static <T> T getDependency(Class<T> dependencyNeeded) 
	{
	    // Il parametro <T> dice a Java: "Il tipo restituito sarà lo stesso della classe passata"
	    
	    for (Object o : dependencies) 
	        if (dependencyNeeded.isAssignableFrom(o.getClass())) 
	        {
	            // Facciamo il cast qui dentro una volta sola, in modo sicuro
	            return dependencyNeeded.cast(o);
	        }

	    throw new RuntimeException("Dipendenza insoddisfatta per " + dependencyNeeded.getName());
	}

}
