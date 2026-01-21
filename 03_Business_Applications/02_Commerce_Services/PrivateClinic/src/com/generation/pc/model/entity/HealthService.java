package com.generation.pc.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/*
 * ==================================================================================
 * TEORIA: Classe Entity per il Domain Model
 * ==================================================================================
 * DEFINIZIONE:
 * HealthService rappresenta un servizio sanitario nel dominio applicativo.
 * È un'entità (Entity) del modello dati che corrisponde a una tabella nel database.
 *
 * SCOPO:
 * - Incapsulare i dati di un servizio sanitario (descrizione, tipo, prezzo)
 * - Fornire validazione della logica di business
 * - Separare il modello dei dati dalla logica di persistenza (Repository pattern)
 *
 * PERCHÉ LO FACCIAMO:
 * Seguiamo il pattern Model-View-Controller (MVC) dove:
 * - Model (HealthService): rappresenta i dati e le regole di business
 * - Repository: gestisce la persistenza
 * - Controller (Main): coordina l'interazione tra model e view
 *
 * Questo pattern ci permette di:
 * 1. Modificare la persistenza (es. da SQL a NoSQL) senza toccare il model
 * 2. Testare la logica di validazione senza database
 * 3. Riutilizzare il model in contesti diversi (web, desktop, mobile)
 */
public class HealthService extends Entity
{
	/*
	 * ==================================================================================
	 * TEORIA: Tipi primitivi vs Reference types
	 * ==================================================================================
	 * DEFINIZIONE:
	 * - int è un tipo primitivo: contiene direttamente il valore (32 bit)
	 * - String è un reference type: contiene un riferimento a un oggetto in memoria
	 *
	 * DIFFERENZE:
	 * - Primitivi: int, double, boolean, char, etc. → valore diretto, performanti
	 * - Reference: String, LocalDate, List, etc. → riferimento a oggetto, possono essere null
	 *
	 * PERCHÉ USARE INT PER IL PREZZO:
	 * Usiamo int invece di double per rappresentare il prezzo in centesimi (es. 1000 = €10.00)
	 * per evitare problemi di precisione con i decimali (es. 0.1 + 0.2 != 0.3 con double).
	 * In applicazioni reali si userebbe BigDecimal per calcoli monetari.
	 */
	protected String	description;
	protected String	type;
	protected int		price;

	// Constructors
	public HealthService() {}

	public HealthService(String description, String type, int price)
	{
		this.description	= description;
		this.type			= type;
		this.price			= price;
	}

	/*
	 * ==================================================================================
	 * TEORIA: Validazione personalizzata per tipo di entità
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Ogni entità implementa getErrors() con le proprie regole di validazione specifiche.
	 *
	 * SCOPO:
	 * - Business rules: il prezzo deve essere > 0 (regola specifica di HealthService)
	 * - Consistenza: description e type sono obbligatori
	 *
	 * PERCHÉ LO FACCIAMO:
	 * La validazione a livello di entità (invece che solo nel database o nell'UI)
	 * garantisce che le regole di business siano sempre rispettate, indipendentemente
	 * da dove arrivano i dati (API REST, form web, import file, etc.).
	 *
	 * Operatori di confronto:
	 * <= significa "minore o uguale". Usiamo (price <= 0) per verificare che il prezzo
	 * sia positivo. Altri operatori: < > >= == !=
	 */
	// Implementation of abstract method from Entity
	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();

		if (isMissing(description))
			errors.add("Description is required");

		if (isMissing(type))
			errors.add("Type is required");

		if (price <= 0)
			errors.add("Price must be greater than zero");

		return errors;
	}

	@Override
	public String toString()
	{
		return "HealthService [id=" + id + ", description=" + description +
		       ", type=" + type + ", price=" + price + "]";
	}

	/*
	 * ==================================================================================
	 * TEORIA: JavaBeans Pattern (Getters/Setters)
	 * ==================================================================================
	 * I getter e setter seguono lo standard JavaBeans, una convenzione che definisce:
	 * 1. Campi privati/protected
	 * 2. Costruttore no-args pubblico
	 * 3. Getter pubblici: getTipoCampo() per leggere
	 * 4. Setter pubblici: setTipoCampo(valore) per scrivere
	 *
	 * VANTAGGI:
	 * - Compatibilità con framework: Spring, JSF, EJB riconoscono automaticamente le proprietà
	 * - Serializzazione: JSON/XML libraries usano getter/setter per convertire oggetti
	 * - Reflection: permette l'accesso dinamico alle proprietà per nome
	 * - IDE support: autocompletamento e refactoring funzionano meglio
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Il pattern JavaBeans è lo standard de facto in Java per rappresentare oggetti
	 * con proprietà modificabili. Facilita l'integrazione con librerie e framework.
	 */
	// Getters and Setters
	public String	getDescription()					{ return description;				}
	public void		setDescription(String description)	{ this.description = description;	}

	public String	getType()							{ return type;						}
	public void		setType(String type)				{ this.type = type;					}

	public int		getPrice()							{ return price;						}
	public void		setPrice(int price)					{ this.price = price;				}
}
