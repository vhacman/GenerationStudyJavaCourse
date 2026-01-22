package com.generation.bw.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import com.generation.bw.extractor.BikeDailyExtractor;
import com.generation.bw.repository.SQLBikeRepository;
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
        /*
         * ═════════════════════════════════════════════════════════════════
         *  Pattern: Dependency Injection Container (Service Locator)
         * ═════════════════════════════════════════════════════════════════
         * 
         *  Context  →  Dependencies Registry  →  Component Retrieval
         *  
         *  Lo static initializer crea e registra i componenti applicativi:
         *  1. Connection (singleton per app lifecycle)
         *  2. SQLBikeRepository (dipende da Connection)
         *  3. BikeDailyExtractor (stateless component)
         *  
         *  Implementa Inversion of Control (IoC): i componenti non creano
         *  le proprie dipendenze ma le ricevono dal container.
         *  Questo disaccoppia i layer e facilita testing e manutenibilità.
         *  
         * ═════════════════════════════════════════════════════════════════
         */
        try
        {
            Connection connection = ConnectionFactory.make("bike.db");
            
            dependencies.add(connection);
            dependencies.add(new SQLBikeRepository("bike", connection, 100));
            dependencies.add(new BikeDailyExtractor());
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
