package com.generation.cp.context;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import com.generation.cp.cypher.XORCypher;
import com.generation.cp.model.repository.SQLEventRepository;
import com.generation.library.database.ConnectionFactory;

/**
 * Contenitore IoC (Inversion of Control) per la gestione centralizzata delle dipendenze.
 *
 * CASSONE CON DENTRO ROBA CHE SERVIRA' AL PROGRAMMA
 * CASSETTA DEGLI ATTREZZI
 */
public class Context
{

	static List<Object> dependencies = new ArrayList<Object>();


	/**
	 * Blocco statico di inizializzazione: viene eseguito una sola volta
	 * al caricamento della classe, prima di qualsiasi altro codice.
	 */
	static
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Dependency Injection Container
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Contenitore IoC → Dipendenze registrate → Fornite su richiesta
		 *
		 * Questo blocco statico implementa la fase di registrazione del
		 * pattern Dependency Injection Container (simile a Spring ApplicationContext).
		 *
		 * VANTAGGI dell'approccio:
		 * 1. SINGLE POINT OF CONFIGURATION: Tutte le dipendenze in un unico posto
		 * 2. FAIL-FAST: Errori di configurazione vengono rilevati all'avvio
		 * 3. LAZY LOADING: Il blocco si esegue solo quando Context viene usato
		 * 4. THREAD-SAFETY: L'inizializzazione statica è garantita thread-safe da JVM
		 *
		 * Principi architetturali:
		 * → IoC (Inversion of Control): Il framework gestisce le dipendenze
		 * → DI (Dependency Injection): Le dipendenze vengono iniettate, non create
		 * → Service Locator: Context funge da registro centralizzato
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		try
		{
			// Io riempirò la lista delle dipendenze con qualunque oggetto possa essermi utile
			Connection connection = ConnectionFactory.make("carroponte.db");

			dependencies.add(connection);
			dependencies.add(new XORCypher("Marco"));
			dependencies.add(new SQLEventRepository("event", connection));
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
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Dependency Injection + Service Locator
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Client → Context (Service Locator) → Dipendenze registrate
		 *
		 * Questo metodo implementa il pattern Dependency Injection attraverso
		 * un Service Locator centralizzato. I vantaggi architetturali:
		 *
		 * 1. INVERSION OF CONTROL (IoC):
		 *    → Il client non crea le dipendenze (new SQLEventRepository(...))
		 *    → Il Context fornisce le istanze già configurate
		 *    → Le dipendenze sono gestite centralmente
		 *
		 * 2. DECOUPLING:
		 *    → Il client dipende dall'interfaccia (EventRepository)
		 *    → Il Context decide quale implementazione fornire (SQLEventRepository)
		 *    → Cambiare implementazione non richiede modifiche al client
		 *
		 * 3. GENERICS PER TYPE SAFETY:
		 *    → <T> garantisce che il tipo restituito corrisponda alla richiesta
		 *    → isAssignableFrom() verifica compatibilità (interfacce, sottoclassi)
		 *    → cast() esegue il casting in modo type-safe
		 *
		 * Principi OOP applicati:
		 * → Astrazione: Client lavora su interfacce, non implementazioni concrete
		 * → Polimorfismo: Richiesta per EventRepository restituisce SQLEventRepository
		 * → Incapsulamento: La logica di creazione è centralizzata e nascosta
		 * → Single Responsibility: Context gestisce solo le dipendenze
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		// Il parametro <T> dice a Java: "Il tipo restituito sarà lo stesso della classe passata"

		for (Object o : dependencies)
		{
			if (dependencyNeeded.isAssignableFrom(o.getClass()))
			{
				// Facciamo il cast qui dentro una volta sola, in modo sicuro
				return dependencyNeeded.cast(o);
			}
		}

		throw new RuntimeException("Dipendenza insoddisfatta per " + dependencyNeeded.getName());
	}

}
