package com.generation.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.database.ConnectionFactory;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.pl.security.MD5PasswordHasher;


public class Context
{
	static List<Object> dependencies = new ArrayList<>();
	static
	{
		try
		{
			Connection 			connection = ConnectionFactory.make("pl.db");
			dependencies.add(connection);
			dependencies.add(new MD5PasswordHasher());
			dependencies.add(new TeacherRepositorySQL("teacher", connection));
			dependencies.add(new StudentRepositorySQL("student", connection));
			dependencies.add(new LessonRepositorySQL("lesson", connection));
			dependencies.add(new AdminRepositorySQL("admin", connection));


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
			if (dependencyNeeded.isAssignableFrom(o.getClass()))
				return dependencyNeeded.cast(o);
		throw new RuntimeException("Dipendenza insoddisfatta per " + dependencyNeeded.getName());
	}

	
}
