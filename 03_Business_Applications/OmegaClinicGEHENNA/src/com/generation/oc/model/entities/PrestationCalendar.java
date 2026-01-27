package com.generation.oc.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * PrestationCalendar - Gestisce un calendario di prestazioni organizzate per data.
 * 
 * CONCETTI TEORICI UTILIZZATI:
 * 
 * 1. LIST (java.util.List)
 * Definizione: Interfaccia che rappresenta una collezione ordinata (sequenza) di elementi.
 * Caratteristiche: mantiene l'ordine di inserimento, permette duplicati, accesso posizionale.
 * Metodi principali:
 *   - Inserimento: add(E element), add(int index, E element), addAll(Collection)
 *   - Rimozione: remove(int index), remove(Object), clear()
 *   - Accesso: get(int index), indexOf(Object), lastIndexOf(Object)
 *   - Verifica: contains(Object), isEmpty(), size()
 *   - Ordinamento: sort(Comparator)
 * 
 * 2. ARRAYLIST (java.util.ArrayList)
 * Definizione: Implementazione di List basata su array ridimensionabile dinamicamente.
 * Caratteristiche: accesso veloce O(1), inserimento/rimozione in coda O(1) ammortizzato,
 *                  inserimento/rimozione in mezzo O(n).
 * Uso: ideale quando serve accesso casuale frequente agli elementi.
 * 
 * 3. MAP (java.util.Map)
 * Definizione: Interfaccia che rappresenta una struttura chiave-valore, ogni chiave
 *              è associata ad esattamente un valore. Non permette chiavi duplicate.
 * Caratteristiche: accesso ai valori tramite chiave, nessun ordine garantito (dipende dall'implementazione).
 * Metodi principali:
 *   - Inserimento: put(K key, V value), putAll(Map), putIfAbsent(K key, V value)
 *   - Rimozione: remove(Object key), remove(Object key, Object value), clear()
 *   - Accesso: get(Object key), getOrDefault(Object key, V defaultValue)
 *   - Verifica: containsKey(Object key), containsValue(Object value), isEmpty(), size()
 *   - Iterazione: keySet(), values(), entrySet()
 * 
 * 4. LINKEDHASHMAP (java.util.LinkedHashMap)
 * Definizione: Implementazione di Map che mantiene l'ordine di inserimento delle chiavi
 *              tramite una lista doppiamente concatenata.
 * Caratteristiche: performance simile a HashMap O(1) per operazioni base, mantiene ordine prevedibile.
 * Uso: scelto quando serve iterare sulle entry mantenendo l'ordine di inserimento.
 * 
 * 5. COMPARATOR (java.util.Comparator)
 * Definizione: Interfaccia funzionale che definisce un criterio di ordinamento personalizzato
 *              per oggetti di tipo T.
 * Metodo principale: compare(T o1, T o2) - restituisce negativo se o1<o2, zero se uguali, positivo se o1>o2.
 * Uso con lambda: (a, b) -> a.getProperty().compareTo(b.getProperty())
 * 
 * 6. LOCALDATE (java.time.LocalDate)
 * Definizione: Classe immutabile che rappresenta una data senza informazioni temporali (anno-mese-giorno).
 * Metodi principali:
 *   - Creazione: now(), of(int year, int month, int day), parse(CharSequence)
 *   - Comparazione: compareTo(LocalDate), isBefore(LocalDate), isAfter(LocalDate)
 *   - Manipolazione: plusDays(long), minusDays(long), plusMonths(long)
 */
public class PrestationCalendar 
{
	// Lista principale contenente tutte le prestazioni
	List<Prestation> prestations;

	/**
	 * Mappa che associa ogni data (LocalDate) ad una lista di prestazioni (List<Prestation>).
	 * LinkedHashMap mantiene l'ordine di inserimento delle date, facilitando l'iterazione ordinata.
	 * Struttura: {data1 -> [prest1, prest2], data2 -> [prest3], ...}
	 */
	Map<LocalDate, List<Prestation>> prestationsMap = new LinkedHashMap<LocalDate,List<Prestation>>();
	
	
	/**
	 * Costruttore che inizializza il calendario ordinando le prestazioni per data
	 * e organizzandole in una mappa per accesso efficiente.
	 * 
	 * PROCESSO:
	 * 1. Ordinamento della lista tramite Comparator con lambda expression
	 * 2. Popolazione della mappa raggruppando prestazioni per data
	 */
	public PrestationCalendar(List<Prestation> prestations) 
	{
		this.prestations = prestations;
		
		/**
		 * LAMBDA EXPRESSION per Comparator:
		 * Sintassi: (parametri) -> espressione
		 * Qui: confronta due Prestation in base alla loro data usando compareTo di LocalDate.
		 * compareTo restituisce: <0 se p1.date è prima, 0 se uguali, >0 se p1.date è dopo.
		 */
		Comparator<Prestation> comparator = (p1, p2)->(p1.getDate().compareTo(p2.getDate()));
		prestations.sort(comparator);
		
		/**
		 * ENHANCED FOR LOOP (for-each):
		 * Itera su ogni elemento della collezione senza gestire indici manualmente.
		 * Sintassi: for(Tipo elemento : collezione)
		 */
		for(Prestation p:prestations)
		{
			LocalDate d = p.getDate();
			
			/**
			 * PATTERN: Verifica esistenza chiave prima di inserimento
			 * - containsKey(key): verifica se la chiave esiste nella mappa O(1)
			 * - Se non esiste: crea nuova ArrayList e aggiunge la prima prestation
			 * - Se esiste: recupera la lista esistente e aggiunge la prestation
			 */
			if(!prestationsMap.containsKey(d))
			{
				prestationsMap.put(d, new ArrayList<Prestation>());
				prestationsMap.get(d).add(p); // aggiunto la prestazione alla mia mappa
			}
			else
				prestationsMap.get(d).add(p);
		}
	}

	/**
	 * Restituisce la prima prestation disponibile (non presente nella mappa)
	 * scorrendo le date esistenti e gli orari predefiniti.
	 * 
	 * ALGORITMO:
	 * 1. Itera su tutte le date presenti nella mappa (keySet())
	 * 2. Per ogni data, verifica gli orari disponibili (9-12, 14-17)
	 * 3. Crea una Prestation temporanea per il controllo
	 * 4. Se non esiste nella lista di quella data, la restituisce
	 * 
	 * KEYSET:
	 * Definizione: restituisce un Set contenente tutte le chiavi della mappa.
	 * Uso: permette di iterare sulle chiavi mantenendo l'ordine (LinkedHashMap).
	 * Metodi Set: add(), remove(), contains(), iterator()
	 */
	public Prestation getNextFree()
	{
		int hours[] = {9,10,11,12,14,15,16,17};
		
		/**
		 * NESTED LOOPS:
		 * Loop esterno: itera sulle date (chiavi della mappa)
		 * Loop interno: itera sugli orari disponibili
		 * Complessità: O(n * m) dove n=numero date, m=numero orari
		 */
		for(LocalDate key:prestationsMap.keySet())
			for(int h:hours)
			{
				/**
				 * PATTERN: Creazione oggetto temporaneo per verifica esistenza
				 * contains() usa equals() della classe Prestation per il confronto
				 */
				Prestation p = new Prestation(key, h);
				if(!prestationsMap.get(key).contains(p))
					return new Prestation(key, h);
			}
		return null;			
	}

	/**
	 * Getter per la mappa delle prestazioni.
	 * Espone la struttura interna per accesso esterno.
	 * 
	 * NOTA: Restituisce il riferimento diretto alla mappa (no copia difensiva).
	 * Modifiche esterne alla mappa rifletteranno sullo stato interno dell'oggetto.
	 */
	public Map<LocalDate, List<Prestation>> getPrestationsMap() 
	{
		return prestationsMap;
	}
}
