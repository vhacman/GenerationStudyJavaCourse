package com.generation.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.generation.library.database.ConnectionFactory;
import com.generation.pl.model.repository.SQLRepository.AdminRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.LessonRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.StudentRepositorySQL;
import com.generation.pl.model.repository.SQLRepository.TeacherRepositorySQL;
import com.generation.library.security.MD5PasswordHasher;

/**
 * IoC (Inversion of Control) Container for Dependency Injection.
 * 
 * Design Pattern: Dependency Injection / Service Locator
 * 
 * Purpose:
 * - Centralizes creation and management of application dependencies
 * - Implements IoC principle: objects don't create their dependencies, they receive them
 * - Provides loose coupling: classes depend on interfaces, not concrete implementations
 * - Simplifies testing: dependencies can be easily mocked or replaced
 * 
 * How it works:
 * 1. Static block runs once when class is first loaded by JVM
 * 2. Creates all application dependencies (Connection, Repositories, Security)
 * 3. Stores them in a List for later retrieval
 * 4. getDependency() method retrieves dependencies by type when needed
 * 
 * Lifecycle:
 * - All dependencies are SINGLETONS (created once, shared across application)
 * - Same Connection instance used by all repositories
 * - Same repository instances used by all controllers
 */
public class Context
{
	static List<Object> dependencies = new ArrayList<>();
	
	/**
	 * Static initialization block - runs once when Context class is first loaded.
	 * 
	 * Error handling:
	 * - If ANY dependency creation fails (database connection, etc.):
	 *   - Catches Exception (prevents app startup with broken dependencies)
	 *   - Prints stack trace for debugging
	 *   - Calls System.exit(-1) to terminate application immediately
	 *   - Exit code -1 signals abnormal termination to OS
	 * Static block execution timing:
	 * - Runs ONCE when Context class first referenced in code
	 * - Happens before any Context methods are called
	 * - Guaranteed thread-safe initialization by JVM
	 */
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
	 * Retrieves a dependency from the IoC container (Dependency Injection/Autowiring).
	 * 
	 * @param <T> Type of the dependency to retrieve (inferred from parameter)
	 * @param dependencyNeeded Class object of the dependency to retrieve
	 * @return Instance of the requested dependency
	 * @throws RuntimeException If dependency is not registered in the container
	 */
	public static <T> T getDependency(Class<T> dependencyNeeded)
	{
		for (Object o : dependencies)
			if (dependencyNeeded.isAssignableFrom(o.getClass()))
				return dependencyNeeded.cast(o);
		throw new RuntimeException("Unsatisfied dependency for " + dependencyNeeded.getName());
	}
}
