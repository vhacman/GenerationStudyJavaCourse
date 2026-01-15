package com.generation.school;

import java.util.Map;
import java.util.HashMap;
import com.generation.library.Console;

/**
 * ESERCIZIO #36 - GESTIONE CONTEGGIO NOMI CON MAP
 *
 * Questa classe dimostra l'utilizzo dell'interfaccia Map per contare le occorrenze
 * di nomi inseriti dall'utente, implementando un pattern comune di aggregazione dati.
 */
public class Lesson020 {

	public static void main(String[] args)
	{
		/*
		 * Il Java Collections Framework (JCF) fornisce un'architettura unificata per
		 * rappresentare e manipolare collezioni di oggetti. Si basa su interfacce,
		 * implementazioni concrete e algoritmi per operazioni comuni.
		 *
		 * GERARCHIA PRINCIPALE:
		 *
		 * Iterable<E>
		 *    └── Collection<E>
		 *           ├── List<E>        [Sequenze ordinate con duplicati]
		 *           │      ├── ArrayList       (array ridimensionabile)
		 *           │      ├── LinkedList      (lista doppiamente concatenata)
		 *           │      └── Vector          (thread-safe, legacy)
		 *           │
		 *           ├── Set<E>         [Insiemi senza duplicati]
		 *           │      ├── HashSet         (basato su HashMap)
		 *           │      ├── LinkedHashSet   (mantiene ordine inserimento)
		 *           │      └── TreeSet         (ordinato, basato su TreeMap)
		 *           │
		 *           └── Queue<E>       [Code FIFO/priorità]
		 *                  ├── PriorityQueue   (heap binario)
		 *                  └── LinkedList      (implementa anche Queue)
		 *
		 * Map<K,V>           [Associazioni chiave-valore, NON estende Collection]
		 *    ├── HashMap<K,V>          (tabella hash)
		 *    ├── LinkedHashMap<K,V>    (mantiene ordine inserimento)
		 *    ├── TreeMap<K,V>          (ordinato per chiave)
		 *    └── Hashtable<K,V>        (thread-safe, legacy)
		 *
		 * =====================================================================
		 * DIFFERENZE TRA LIST, SET E MAP
		 * =====================================================================
		 *
		 * ┌──────────────┬─────────────┬──────────────┬───────────────────────┐
		 * │ Caratterist. │    LIST     │     SET      │         MAP           │
		 * ├──────────────┼─────────────┼──────────────┼───────────────────────┤
		 * │ Duplicati    │ Permessi    │ NON permessi │ Chiavi uniche,        │
		 * │              │             │              │ valori duplicabili    │
		 * ├──────────────┼─────────────┼──────────────┼───────────────────────┤
		 * │ Ordine       │ Mantiene    │ Non garantito│ Non garantito         │
		 * │              │ inserimento │ (eccetto     │ (eccetto              │
		 * │              │             │ LinkedHashSet│ LinkedHashMap/TreeMap)│
		 * ├──────────────┼─────────────┼──────────────┼───────────────────────┤
		 * │ Accesso      │ Per indice  │ Per valore   │ Per chiave            │
		 * │              │ get(int i)  │ contains(o)  │ get(key)              │
		 * ├──────────────┼─────────────┼──────────────┼───────────────────────┤
		 * │ Null         │ Multipli    │ Un solo null │ Una chiave null       │
		 * │              │ null        │ (HashSet)    │ (HashMap)             │
		 * ├──────────────┼─────────────┼──────────────┼───────────────────────┤
		 * │ Uso tipico   │ Sequenze    │ Unicità,     │ Lookup, dizionari,    │
		 * │              │ ordinate    │ membership   │ cache, conteggi       │
		 * └──────────────┴─────────────┴──────────────┴───────────────────────┘
		 *
		 * ESEMPI DI UTILIZZO:
		 *
		 * LIST - Sequenze con duplicati e ordine:
		 *   List<String> nomi = new ArrayList<>();
		 *   nomi.add("Mario");
		 *   nomi.add("Luigi");
		 *   nomi.add("Mario");  // OK: duplicato permesso
		 *   String primo = nomi.get(0);  // Accesso indicizzato
		 *   // Output: [Mario, Luigi, Mario]
		 *
		 * SET - Insiemi senza duplicati:
		 *   Set<String> nomiUnici = new HashSet<>();
		 *   nomiUnici.add("Mario");
		 *   nomiUnici.add("Luigi");
		 *   nomiUnici.add("Mario");  // Ignorato: già presente
		 *   // Output: [Mario, Luigi] (ordine non garantito)
		 *
		 * MAP - Associazioni chiave-valore:
		 *   Map<String, Integer> età = new HashMap<>();
		 *   età.put("Mario", 30);
		 *   età.put("Luigi", 25);
		 *   int etàMario = età.get("Mario");  // 30
		 *   // Output: {Mario=30, Luigi=25}
		 *
		 * =====================================================================
		 * INTERFACCIA MAP<K,V> - DEFINIZIONE TEORICA
		 * =====================================================================
		 *
		 * L'interfaccia Map<K,V> rappresenta una collezione di coppie chiave-valore,
		 * dove ogni chiave è associata univocamente a un valore. A differenza delle
		 * Collection, Map non può contenere chiavi duplicate: ogni chiave identifica
		 * un solo elemento nella struttura dati.
		 *
		 * CARATTERISTICHE PRINCIPALI:
		 * - Ogni chiave (K) mappa esattamente un valore (V)
		 * - Le chiavi sono uniche: inserire una chiave già esistente sovrascrive il valore
		 * - I valori possono essere duplicati
		 * - Non mantiene un ordine specifico di inserimento (dipende dall'implementazione)
		 *
		 * OPERAZIONI PRINCIPALI:
		 * 1. VERIFICA PRESENZA:
		 *    - containsKey(Object key): boolean -> Verifica se la chiave esiste - O(1) in HashMap
		 *    - containsValue(Object value): boolean -> Verifica se il valore esiste - O(n)
		 *    - isEmpty(): boolean -> Verifica se la Map è vuota
		 *
		 * 2. INSERIMENTO:
		 *    - put(K key, V value): V -> Inserisce/aggiorna coppia chiave-valore
		 *      Restituisce il valore precedente o null se la chiave non esisteva
		 *    - putIfAbsent(K key, V value): V -> Inserisce solo se la chiave non esiste
		 *
		 * 3. RECUPERO:
		 *    - get(Object key): V -> Restituisce il valore associato alla chiave o null
		 *    - getOrDefault(Object key, V defaultValue): V -> Come get ma con valore di default
		 *
		 * 4. RIMOZIONE:
		 *    - remove(Object key): V -> Rimuove la coppia e restituisce il valore rimosso
		 *    - remove(Object key, Object value): boolean -> Rimuove solo se chiave=valore
		 *    - clear(): void -> Rimuove tutte le coppie
		 *
		 * 5. VISTE E ITERAZIONE:
		 *    - keySet(): Set<K> -> Restituisce un Set di tutte le chiavi
		 *    - values(): Collection<V> -> Restituisce una Collection di tutti i valori
		 *    - entrySet(): Set<Map.Entry<K,V>> -> Restituisce un Set di tutte le coppie
		 *
		 * 6. DIMENSIONE:
		 *    - size(): int -> Restituisce il numero di coppie chiave-valore
		 *
		 * =====================================================================
		 * IMPLEMENTAZIONE HASHMAP - DETTAGLI TECNICI
		 * =====================================================================
		 *
		 * HashMap è l'implementazione più comune di Map, basata su una tabella hash.
		 *
		 * COMPLESSITÀ COMPUTAZIONALE:
		 * - Inserimento (put): O(1) medio, O(n) caso pessimo (collisioni)
		 * - Ricerca (get, containsKey): O(1) medio, O(n) caso pessimo
		 * - Rimozione (remove): O(1) medio, O(n) caso pessimo
		 * - Iterazione (entrySet): O(n) dove n è il numero di elementi
		 *
		 * FUNZIONAMENTO INTERNO:
		 * - Utilizza l'hashCode() della chiave per determinare la posizione (bucket)
		 * - Gestisce le collisioni (chiavi con stesso hash) tramite liste concatenate
		 *   o alberi binari (Java 8+) quando le liste superano una soglia
		 * - Capacità iniziale predefinita: 16 elementi
		 * - Load factor predefinito: 0.75 (rehashing al 75% di riempimento)
		 *
		 * CARATTERISTICHE:
		 * - Permette una chiave null e multipli valori null
		 * - Non garantisce ordine di iterazione
		 * - Non è thread-safe (usare ConcurrentHashMap per concorrenza)
		 * - Le prestazioni dipendono dalla qualità della funzione hashCode()
		 *
		 * ALTERNATIVE:
		 * - LinkedHashMap: mantiene l'ordine di inserimento - O(1) operazioni
		 * - TreeMap: mantiene le chiavi ordinate - O(log n) operazioni
		 * - ConcurrentHashMap: thread-safe senza blocco totale
		 *
		 * =====================================================================
		 */

		// Dichiarazione Map con tipo generico <String, Integer>
		// La chiave (String) rappresenta il nome, il valore (Integer) il conteggio
		Map<String, Integer> nameCount = new HashMap<>();

		Console.print("Inserisci nomi (stringa vuota per terminare):");

		while (true) {
			Console.print("Nome: ");
			String nome = Console.readString();

			// OPERAZIONE 1: Verifica terminazione (stringa vuota)
			if (nome.isEmpty()) {
				break;
			}

			/*
			 * PATTERN: INSERIMENTO CONDIZIONALE CON AGGIORNAMENTO
			 *
			 * Questo blocco implementa un pattern comune per il conteggio di occorrenze:
			 * 1. Verifica se la chiave esiste già nella Map
			 * 2. Se esiste: recupera il valore attuale, incrementa e aggiorna
			 * 3. Se non esiste: inserisce una nuova coppia con valore iniziale 1
			 *
			 * METODI UTILIZZATI:
			 * - containsKey(nome): O(1) - Verifica presenza chiave
			 * - get(nome): O(1) - Recupera valore associato alla chiave
			 * - put(nome, valore): O(1) - Inserisce o aggiorna la coppia
			 *
			 * ALTERNATIVE PIÙ MODERNE (Java 8+):
			 * - nameCount.merge(nome, 1, Integer::sum);
			 * - nameCount.put(nome, nameCount.getOrDefault(nome, 0) + 1);
			 * - nameCount.compute(nome, (k, v) -> v == null ? 1 : v + 1);
			 */
			if (nameCount.containsKey(nome)) {
				// OPERAZIONE 2: Incremento contatore esistente
				// get() restituisce l'Integer associato, viene fatto unboxing, incremento e boxing
				nameCount.put(nome, nameCount.get(nome) + 1);
			} else {
				// OPERAZIONE 3: Inserimento sicuro di nuova coppia
				// Prima occorrenza del nome: inizializza il conteggio a 1
				nameCount.put(nome, 1);
			}
		}

		/*
		 * =====================================================================
		 * INTERFACCIA MAP.ENTRY<K,V> E ITERAZIONE CON ENTRYSET()
		 * =====================================================================
		 *
		 * Map.Entry<K,V> è un'interfaccia interna che rappresenta una singola
		 * coppia chiave-valore contenuta nella Map.
		 *
		 * METODI PRINCIPALI DI MAP.ENTRY:
		 * - getKey(): K -> Restituisce la chiave della coppia
		 * - getValue(): V -> Restituisce il valore della coppia
		 * - setValue(V value): V -> Modifica il valore (modifica anche la Map originale)
		 *
		 * METODO ENTRYSET():
		 * - Restituisce un Set<Map.Entry<K,V>> contenente tutte le coppie
		 * - La vista è "backed by the map": modifiche al Set si riflettono sulla Map
		 * - Permette iterazione efficiente senza chiamate separate a get()
		 * - Complessità: O(n) dove n è il numero di elementi
		 *
		 * CONFRONTO TRA METODI DI ITERAZIONE:
		 *
		 * 1. Via entrySet() [CONSIGLIATO]:
		 *    for (Map.Entry<K,V> entry : map.entrySet()) {
		 *        K key = entry.getKey();
		 *        V value = entry.getValue();
		 *    }
		 *    Complessità: O(n) - Una sola iterazione
		 *
		 * 2. Via keySet():
		 *    for (K key : map.keySet()) {
		 *        V value = map.get(key);  // Chiamata extra
		 *    }
		 *    Complessità: O(n) ma meno efficiente per accesso ripetuto a get()
		 *
		 * 3. Via forEach (Java 8+):
		 *    map.forEach((key, value) -> { ... });
		 *    Complessità: O(n) - Sintassi più compatta
		 *
		 * =====================================================================
		 */

		// OPERAZIONE 4: Vista chiavi-valori e iterazione
		Console.print("\nConteggio nomi:");

		// entrySet() restituisce un Set di tutte le coppie chiave-valore
		// Ogni elemento è un oggetto Entry che contiene una coppia <String, Integer>
		for (Map.Entry<String, Integer> entry : nameCount.entrySet()) {
			// getKey() e getValue() estraggono rispettivamente chiave e valore
			// Complessità totale del ciclo: O(n) dove n = numero di elementi nella Map
			Console.print(entry.getKey() + ": " + entry.getValue());
		}
	}

}
