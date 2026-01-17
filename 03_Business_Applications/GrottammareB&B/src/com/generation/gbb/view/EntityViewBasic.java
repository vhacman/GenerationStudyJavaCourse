package com.generation.gbb.view;

import com.generation.library.Template;
import java.util.List;
import java.util.function.BiFunction;

/**
 * Classe generica che gestisce il rendering (visualizzazione) di qualsiasi tipo di entità.
 * 
 * Invece di creare una classe separata per Guest, Room, Expense e Trip,
 * uso i GENERICS (il <T>) per avere una sola classe che funziona per tutti.
 * 
 * Il <T> è un "segnaposto" per il tipo: quando creo l'oggetto specifico,
 * dico cosa deve essere T (es. EntityViewTxtBasic<Guest> -> T diventa Guest).
 */
public class EntityViewBasic<T>
{
	// Path del template (es. "template/guestsummary.txt")
	protected String template;
	
	// Contenuto effettivo del template caricato (la stringa con i placeholder [id], [name], ecc.)
	protected String templateContent;
	
	/**
	 * Questa è la "strategia di rendering", cioè la FUNZIONE che mi dice
	 * "come trasformare un'entità T in una stringa renderizzata".
	 * 
	 * BiFunction<String, T, String> significa:
	 * - Primo parametro (String): il template caricato (templateContent)
	 * - Secondo parametro (T): l'entità da renderizzare (Guest, Room, ecc.)
	 * - Tipo di ritorno (String): la stringa finale dopo aver sostituito i placeholder
	 * 
	 * Questa variabile conterrà la LAMBDA che passo nel costruttore.
	 * È tipo un "contenitore di comportamento" che posso richiamare dopo.
	 */
	private BiFunction<String, T, String> renderStrategy;
	
	/**
	 * Costruttore: quando creo un oggetto di questa classe, devo passargli:
	 * 1. Il path del template da usare
	 * 2. La funzione (lambda) che dice come renderizzare l'entità
	 * 
	 * @param template
	 *            Path al file template (es. "template/guestsummary.txt")
	 * @param renderStrategy
	 *            Lambda expression che prende (template, entità) e restituisce la stringa renderizzata
	 */
	public EntityViewBasic(String template, BiFunction<String, T, String> renderStrategy)
	{
		// Salvo il path del template
		this.template = template;
		
		// Carico subito il contenuto del file template usando la libreria Template
		// Così non devo rileggerlo ogni volta che renderizzzo un'entità
		this.templateContent = Template.load(template);
		
		// Salvo la lambda che mi è stata passata
		// Questa lambda verrà "chiamata" dentro il metodo render()
		this.renderStrategy = renderStrategy;
	}
	
	/**
	 * Renderizza UNA SINGOLA entità.
	 * 
	 * @param entity
	 *            L'entità da renderizzare (Guest, Room, Expense o Trip)
	 * @return La stringa con il template compilato (placeholder sostituiti con dati reali)
	 * 
	 * COME FUNZIONA:
	 * 1. Chiamo renderStrategy.apply(...)
	 * 2. Questo ESEGUE la lambda che ho salvato nel costruttore
	 * 3. Passo alla lambda due cose:
	 *    - templateContent: il template caricato (es. "Guest: [id] - [firstname] [lastname]")
	 *    - entity: l'oggetto da renderizzare (es. un Guest con id=5, firstname="Mario")
	 * 4. La lambda fa tutti i .replace() e mi restituisce la stringa finale
	 * 5. Ritorno quella stringa
	 * 
	 */
	public String render(T entity)
	{
		// apply() è il metodo di BiFunction che "esegue" la funzione
		// È come chiamare la lambda passandogli i due parametri
		return renderStrategy.apply(templateContent, entity);
	}
	
	/**
	 * Renderizza una LISTA di entità.
	 * 
	 * Questo è un esempio di OVERLOAD: ho due metodi con lo stesso nome (render)
	 * ma parametri diversi. Java sceglie automaticamente quale chiamare in base
	 * a cosa gli passo (un oggetto T singolo, o una List<T>).
	 * 
	 * @param entities
	 *            Lista di entità da renderizzare
	 * @return Stringa con tutte le entità renderizzate, una per riga
	 * 
	 * COME FUNZIONA:
	 * 1. Scorro tutti gli elementi della lista
	 * 2. Per ognuno, chiamo render(entity) - cioè il metodo qui sopra!
	 * 3. Concateno tutti i risultati con un "\n" (a capo) in mezzo
	 * 4. Restituisco la stringa completa
	 * 
	 */
	public String render(List<T> entities)
	{
		String res = "";  // Stringa vuota dove accumulo i risultati
		
		// Per ogni entità nella lista
		for (T entity : entities)
		{
			// Renderizza la singola entità e aggiungila al risultato
			// (con un "\n" alla fine per andare a capo)
			res += render(entity) + "\n";
			// Qui chiamo il metodo render(T entity) qui sopra!
			// Quindi per ogni elemento eseguo la lambda una volta
		}
		
		return res;  // Restituisco la stringa con tutti gli elementi renderizzati
	}
}