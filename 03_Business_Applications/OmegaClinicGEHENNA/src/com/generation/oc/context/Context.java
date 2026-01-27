package com.generation.oc.context;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.database.ConnectionFactory;
import com.generation.oc.model.repository.SQLHealthServiceRepository;
import com.generation.oc.model.repository.SQLPatientRepository;
import com.generation.oc.model.repository.SQLPrestationRepository;
public class Context
{
	static List<Object> dependencies = new ArrayList<>();
	static
	{
		try
		{
			Connection 			connection = ConnectionFactory.make("omegaclinic.db");
			dependencies.add(connection);
			dependencies.add(new SQLPatientRepository("patient", connection));
			dependencies.add(new SQLHealthServiceRepository("healthservice", connection));
			dependencies.add(new SQLPrestationRepository("prestation", connection));
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.exit(-1);
		}
	}
	/**
	 * Recupera una dipendenza dal contenitore IoC (Inversion of Control).
	 * Questo meccanismo è noto come Dependency Injection o Autowiring.
	 *
	 * @param <T> Tipo della dipendenza richiesta
	 * @param dependencyNeeded Classe della dipendenza da recuperare
	 * @return L'istanza della dipendenza richiesta
	 * @throws RuntimeException Se la dipendenza non è registrata nel contenitore
	 */
	public static <T> T getDependency(Class<T> dependencyNeeded)
	{
		for (Object o : dependencies)
		{
			if (dependencyNeeded.isAssignableFrom(o.getClass()))
			{
				return dependencyNeeded.cast(o);
			}
		}
		throw new RuntimeException("Dipendenza insoddisfatta per " + dependencyNeeded.getName());
	}

}
