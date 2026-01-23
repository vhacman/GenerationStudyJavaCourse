package com.generation.acmc.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.generation.acmc.model.repository.SQLDonationRepository;
import com.generation.acmc.model.repository.SQLExpenseRepository;
import com.generation.acmc.model.repository.SQLMemberRepository;
import com.generation.library.database.ConnectionFactory;

/**
 * Application context managing dependency injection container.
 * Provides centralized configuration and retrieval of application components.
 */
public class Context 
{
    static List<Object>     dependencies    = new ArrayList<Object>();

    /**
     * Static initializer block that bootstraps the application context.
     * Configures database connection and registers core dependencies.
     */
    static
    {
        try
        {
            Connection connection = ConnectionFactory.make("acmc.db");
            
            dependencies.add(connection);
            dependencies.add(new SQLMemberRepository("member", connection, 100));
            dependencies.add(new SQLDonationRepository("donation", connection, 100));
            dependencies.add(new SQLExpenseRepository("expense", connection, 100));

        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * Retrieves a registered dependency by its type (autowiring).
     * 
     * @param <T> the type of dependency to retrieve
     * @param dependencyNeeded the Class object representing the requested type
     * @return the registered instance matching the requested type
     * @throws RuntimeException if no matching dependency is found
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
