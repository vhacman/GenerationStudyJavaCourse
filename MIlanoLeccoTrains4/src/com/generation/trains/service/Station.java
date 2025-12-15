package com.generation.trains.service;

import com.generation.library.*;

/**
 * Classe che rappresenta una stazione ferroviaria nel sistema di biglietteria.
 * 
 * RESPONSABILITÀ:
 * - Memorizzare il nome della stazione
 * - Validare che la stazione esista nel network ferroviario
 * - Garantire che solo stazioni valide possano essere create/modificate
 * 
 * DESIGN PATTERN:
 * - Value Object: rappresenta un concetto del dominio (una stazione)
 * - Validation Pattern: assicura che l'oggetto sia sempre in uno stato valido
 * 
 * COSTANTI:
 * Definisce le stazioni supportate come costanti pubbliche e statiche,
 * facilitando il riferimento da altre classi e prevenendo errori di digitazione.
 */
public class Station
{
	// ===== ATTRIBUTO DI ISTANZA =====
	
	/**
	 * Nome della stazione in formato lowercase.
	 * 
	 * SCELTA DI DESIGN:
	 * Memorizzare in lowercase garantisce uniformità e semplifica i confronti,
	 * indipendentemente da come l'utente inserisce il dato ("Milano", "MILANO", "milano").
	 */
	private String name;

	// ===== COSTANTI: STAZIONI VALIDE DEL SISTEMA =====
	
	/**
	 * Costanti che definiscono le stazioni valide nel network ferroviario.
	 * 
	 * PERCHÉ COSTANTI?
	 * - Evitano "magic strings" sparse nel codice
	 * - Facilitano modifiche future (cambio nome in un solo punto)
	 * - Prevenzione errori di battitura grazie all'autocompletamento IDE
	 * - Documentazione implicita delle stazioni disponibili
	 * 
	 * PERCHÉ LOWERCASE?
	 * Per coerenza con la memorizzazione interna e semplificare i confronti.
	 * 
	 * PERCHÉ public static final?
	 * - public: accessibili da altre classi
	 * - static: appartengono alla classe, non alle istanze
	 * - final: immutabili (non possono essere modificate)
	 */
	public static final String MILANO = "milano";
	public static final String MONZA = "monza";
	public static final String OSNAGO = "osnago";
	public static final String LECCO = "lecco";

	// ===== COSTRUTTORE =====
	
	/**
	 * Costruttore che crea una stazione con validazione automatica del nome.
	 * 
	 * PROCESSO:
	 * 1. Converte il nome in lowercase per uniformità
	 * 2. Valida che la stazione esista nel sistema
	 * 3. Se non valida, chiede ripetutamente all'utente di reinserire
	 * 4. Una volta validato, assegna il nome alla stazione
	 * 
	 * VALIDAZIONE ROBUSTA:
	 * Il ciclo while garantisce che una Station non possa mai esistere
	 * con un nome non valido. Questo è un esempio di "fail-fast" design:
	 * meglio bloccare la creazione che avere oggetti in stati inconsistenti.
	 * 
	 * INTERAZIONE UTENTE:
	 * Se il nome non è valido, il costruttore interagisce direttamente
	 * con l'utente tramite Console. In un'architettura più sofisticata,
	 * potremmo lanciare un'eccezione e delegare la gestione al chiamante.
	 * 
	 * @param name il nome della stazione da creare (case-insensitive)
	 */
	public Station(String name)
	{
		// STEP 1: Normalizzazione dell'input
		// Convertiamo in lowercase per garantire confronti case-insensitive
		// Es: "Milano", "MILANO", "milano" → tutti diventano "milano"
		String nameLower = name.toLowerCase();

		// STEP 2: Ciclo di validazione
		// Continua finché l'utente non inserisce una stazione valida
		while (!isValidStation(nameLower))
		{
			// FEEDBACK all'utente: il nome inserito non è valido
			Console.print("Stazione non valida, reinserire: ");
			
			// RICHIESTA nuovo input
			// readString() attende input dall'utente
			// toLowerCase() normalizza immediatamente il nuovo input
			nameLower = Console.readString().toLowerCase();
		}
		// Quando usciamo dal ciclo, nameLower è garantito essere valido

		// STEP 3: Assegnazione del nome validato
		// A questo punto siamo sicuri che name contenga una stazione valida
		this.name = nameLower;
	}

	// ===== METODI PRIVATI DI VALIDAZIONE =====
	
	/**
	 * Verifica se un nome di stazione è valido confrontandolo con le costanti.
	 * 
	 * LOGICA:
	 * Una stazione è valida se il suo nome corrisponde a una delle costanti definite.
	 * 
	 * IMPLEMENTAZIONE:
	 * Usa una catena di OR (||) per verificare tutte le possibilità.
	 * Restituisce true appena trova una corrispondenza, altrimenti false.
	 * 
	 * PERCHÉ PRIVATE?
	 * È un metodo di utilità interno usato solo da questa classe.
	 * Non c'è bisogno che codice esterno acceda a questo metodo.
	 * 
	 * NOTA: Il parametro viene convertito nuovamente in lowercase per sicurezza,
	 * anche se tecnicamente dovrebbe già esserlo quando chiamato dal costruttore/setter.
	 * Questo è un esempio di "defensive programming".
	 * 
	 * @param nameLower il nome della stazione da validare (dovrebbe essere lowercase)
	 * @return true se la stazione è valida, false altrimenti
	 */
	private boolean isValidStation(String nameLower)
	{
		// DEFENSIVE PROGRAMMING: riconvertiamo in lowercase per sicurezza
		// anche se il parametro dovrebbe già esserlo
		String lower = nameLower.toLowerCase();

		// VALIDAZIONE: confronto con tutte le stazioni valide
		// Restituisce true se trova una corrispondenza esatta
		return lower.equals(MILANO) || 
		       lower.equals(MONZA) || 
		       lower.equals(OSNAGO) || 
		       lower.equals(LECCO);
		
		// POSSIBILE MIGLIORAMENTO FUTURO:
		// Potremmo usare un Set<String> per rendere più scalabile
		// l'aggiunta di nuove stazioni:
		// 
		// private static final Set<String> VALID_STATIONS = 
		//     Set.of(MILANO, MONZA, OSNAGO, LECCO);
		// 
		// return VALID_STATIONS.contains(lower);
	}

	// ===== GETTER =====
	
	/**
	 * Restituisce il nome della stazione.
	 * 
	 * Il nome è sempre in formato lowercase per garantire coerenza
	 * in tutto il sistema. Se necessario visualizzarlo in formato
	 * "user-friendly" (es. "Milano" invece di "milano"), la conversione
	 * può essere fatta nel punto di presentazione (es. toUpperCase()
	 * della prima lettera nel metodo di stampa).
	 * 
	 * @return il nome della stazione in lowercase
	 */
	public String getName() 
	{
		return name;
	}

	// ===== SETTER =====
	
	/**
	 * Modifica il nome della stazione con validazione automatica.
	 * 
	 * PROCESSO:
	 * Identico al costruttore:
	 * 1. Normalizza l'input in lowercase
	 * 2. Valida il nome
	 * 3. Se non valido, richiede ripetutamente nuovo input
	 * 4. Assegna il nome validato
	 * 
	 * PERCHÉ VALIDARE ANCHE NEL SETTER?
	 * Garantisce che l'oggetto Station rimanga sempre in uno stato valido
	 * durante l'intero ciclo di vita, non solo alla creazione.
	 * 
	 * INVARIANTE DI CLASSE:
	 * Una Station deve sempre avere un nome che corrisponde a una stazione
	 * valida del network. Costruttore e setter cooperano per mantenere
	 * questo invariante.
	 * 
	 * USO TIPICO:
	 * Questo setter potrebbe essere usato se un utente vuole correggere
	 * la stazione di partenza/arrivo dopo averla inserita inizialmente.
	 * 
	 * @param name il nuovo nome della stazione (case-insensitive)
	 */
	public void setName(String name)
	{
		// STEP 1: Normalizzazione
		String nameLower = name.toLowerCase();

		// STEP 2: Ciclo di validazione (identico al costruttore)
		while (!isValidStation(nameLower))
		{
			Console.print("Stazione non valida, reinserire: ");
			nameLower = Console.readString().toLowerCase();
		}

		// STEP 3: Assegnazione del valore validato
		// A questo punto siamo certi che nameLower sia valido
		this.name = nameLower;
	}
}