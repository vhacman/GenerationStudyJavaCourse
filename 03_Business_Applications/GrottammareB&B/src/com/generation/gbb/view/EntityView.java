package com.generation.gbb.view;

import java.util.List;
import com.generation.library.Template;

/**
 * Classe ASTRATTA generica per gestire le view di qualsiasi tipo di entità.
 * 
 * COSA SIGNIFICA "ASTRATTA":
 * - Non posso creare direttamente oggetti di questa classe (new EntityView<Guest>() NON funziona)
 * - Devo creare una SOTTOCLASSE che la estende e implementa i metodi astratti
 * - È tipo un "contratto" che dice "tutte le view devono avere questi metodi"
 * 
 * COSA SIGNIFICA "<T>":
 * - È un GENERIC (tipo parametrizzato)
 * - T è un "segnaposto" per il tipo di entità che voglio gestire
 * - Quando estendo questa classe, specifico cosa è T (es. EntityView<Guest>)
 * - Così posso riutilizzare la stessa logica per Guest, Room, Expense, Trip...
 * 
 * QUANDO LA USO:
 * - Quando voglio che TUTTE le view abbiano la stessa struttura base
 * - Ma lascio che ogni sottoclasse decida COME renderizzare la propria entità
 * - È il pattern "TEMPLATE METHOD": definisco lo scheletro, le sottoclassi riempiono i dettagli
 */
public abstract class EntityView<T>
{
	// Path del template file (es. "template/guestsummary.txt")
	protected String template;
	
	// Contenuto del template caricato (es. "Guest: [id] - [firstname] [lastname]")
	// È "protected" così le sottoclassi possono accederci
	protected String templateContent;
	
	/**
	 * Costruttore: quando una sottoclasse viene creata, deve passare il path del template.
	 * 
	 * @param template Path al file template da caricare
	 */
	public EntityView(String template)
	{
		// Salvo il path
		this.template = template;
		
		// Carico subito il contenuto del file usando la libreria Template
		// Così non devo rileggerlo ogni volta che renderizzzo
		this.templateContent = Template.load(template);
	}
	
	/**
	 * Metodo ASTRATTO per renderizzare una singola entità.
	 * 
	 * COSA SIGNIFICA "ASTRATTO":
	 * - Non ha implementazione qui (niente corpo con {})
	 * - OGNI sottoclasse DEVE implementarlo, altrimenti non compila
	 * - È il metodo che cambia da entità a entità
	 * 
	 * PERCHÉ È ASTRATTO:
	 * - Ogni entità ha campi diversi (Guest ha firstname/lastname, Room ha name/floor, ecc.)
	 * - Quindi i .replace() da fare sono diversi per ognuna
	 * - Non posso scrivere qui il codice, devo lasciare che ogni sottoclasse lo faccia
	 * 
	 * @param entity L'entità da renderizzare (Guest, Room, ecc.)
	 * @return La stringa con il template compilato
	 */
	public abstract String render(T entity);
	
	/**
	 * Metodo CONCRETO (con implementazione) per renderizzare una LISTA di entità.
	 * 
	 * PERCHÉ NON È ASTRATTO:
	 * - La logica è SEMPRE la stessa per tutte le entità:
	 *   1. Scorro la lista
	 *   2. Chiamo render() su ogni elemento
	 *   3. Concateno i risultati
	 * - Non serve che ogni sottoclasse lo riscriva
	 * 
	 * OVERLOAD:
	 * - Ho due metodi con lo stesso nome "render" ma parametri diversi
	 * - render(T entity) -> per un singolo elemento (astratto, da implementare)
	 * - render(List<T> entities) -> per una lista (concreto, già implementato)
	 * - Java sceglie automaticamente quale chiamare in base a cosa gli passo
	 * 
	 * COME FUNZIONA:
	 * 1. Creo una stringa vuota
	 * 2. Per ogni elemento della lista:
	 *    - Chiamo render(entity) -> che è il metodo astratto implementato dalla sottoclasse
	 *    - Aggiungo il risultato alla stringa + "\n" (a capo)
	 * 3. Restituisco la stringa completa
	 * 
	 * @param entities Lista di entità da renderizzare
	 * @return Stringa con tutte le entità renderizzate, una per riga
	 */
	public String render(List<T> entities)
	{
		String res = "";  // Accumulo qui i risultati
		
		// Per ogni entità nella lista
		for (T entity : entities)
		{
			// Chiamo render(entity) - il metodo ASTRATTO che ogni sottoclasse implementa!
			// Questo è il "trucco": io qui non so come si renderizza un'entità specifica,
			// ma so che la sottoclasse ha implementato quel metodo, quindi lo chiamo
			res += render(entity) + "\n";
		}
		
		return res;
	}
}