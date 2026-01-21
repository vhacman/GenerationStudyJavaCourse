package com.generation.pc.model.entity;

/*
 * ==================================================================================
 * TEORIA: Import di classi Java
 * ==================================================================================
 * Gli import permettono di utilizzare classi definite in altri package senza
 * dover scrivere il fully qualified name (es. java.time.LocalDate ogni volta).
 *
 * - java.time.LocalDate: classe per rappresentare date senza orario (introdotta in Java 8)
 * - java.util.ArrayList: implementazione di List basata su array dinamico
 * - java.util.List: interfaccia per collezioni ordinate
 */
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/*
 * ==================================================================================
 * TEORIA: Ereditarietà (Inheritance)
 * ==================================================================================
 * L'ereditarietà è uno dei pilastri della programmazione orientata agli oggetti (OOP).
 *
 * DEFINIZIONE:
 * Patient "extends" Entity significa che Patient è una sottoclasse (subclass/child class)
 * di Entity (superclasse/parent class). Patient eredita tutti i campi e metodi
 * non-private di Entity.
 *
 * SCOPO:
 * - Riutilizzo del codice: non dobbiamo riscrivere il campo "id" e i metodi già
 *   implementati in Entity
 * - Polimorfismo: un oggetto Patient può essere trattato come un oggetto Entity
 * - Specializzazione: Patient aggiunge campi e comportamenti specifici mantenendo
 *   le caratteristiche comuni di tutte le entità
 *
 * PERCHÉ LO FACCIAMO:
 * In un sistema gestionale, tutte le entità (Patient, HealthService, ecc.) hanno
 * caratteristiche comuni (es. id, validazione). L'ereditarietà ci permette di
 * definire queste caratteristiche una sola volta nella classe base Entity.
 */
public class Patient extends Entity
{
	/*
	 * ==================================================================================
	 * TEORIA: Modificatore di accesso "protected"
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Il modificatore "protected" rende i campi accessibili:
	 * - Dalla stessa classe
	 * - Da sottoclassi (anche in package diversi)
	 * - Da classi nello stesso package
	 *
	 * SCOPO:
	 * Proteggere i dati consentendo l'accesso controllato. Più restrittivo di "public"
	 * ma meno di "private".
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Se in futuro creiamo sottoclassi di Patient (es. InPatient, OutPatient),
	 * queste potranno accedere direttamente ai campi. Con "private" dovrebbero
	 * usare i getter/setter.
	 *
	 * ==================================================================================
	 * TEORIA: LocalDate
	 * ==================================================================================
	 * DEFINIZIONE:
	 * LocalDate è una classe immutabile del package java.time (Java 8+) che rappresenta
	 * una data senza informazioni di orario né fuso orario (es. 2025-01-20).
	 *
	 * SCOPO:
	 * - Thread-safe: essendo immutabile, può essere condivisa tra thread senza problemi
	 * - API moderna: metodi chiari e intuitivi (isAfter, isBefore, parse, etc.)
	 * - Tipo specifico: più sicuro rispetto a String o java.util.Date
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Per rappresentare la data di nascita (dob = date of birth) usiamo LocalDate
	 * invece di String perché:
	 * 1. Validazione automatica: LocalDate.parse("2025-99-99") solleva eccezione
	 * 2. Operazioni temporali: possiamo facilmente calcolare età, confrontare date
	 * 3. Standard ISO: formato YYYY-MM-DD internazionalmente riconosciuto
	 */
	protected String		firstName, lastName, ssn, city, address;
	protected LocalDate		dob;

	/*
	 * ==================================================================================
	 * TEORIA: Costruttore di default (No-args constructor)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Un costruttore senza parametri che inizializza l'oggetto con valori di default.
	 *
	 * SCOPO:
	 * - Necessario per framework che usano reflection (es. Hibernate, Spring)
	 * - Permette di creare oggetti vuoti e popolarli con i setter
	 * - Richiesto da molte librerie di serializzazione/deserializzazione
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Molti repository e framework ORM richiedono un costruttore senza parametri
	 * per istanziare oggetti tramite reflection, specialmente quando si mappano
	 * risultati di query SQL in oggetti Java.
	 */
	// Constructors
	public Patient() {}

	/*
	 * ==================================================================================
	 * TEORIA: Costruttore parametrizzato
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Un costruttore che accetta parametri per inizializzare i campi dell'oggetto.
	 *
	 * SCOPO:
	 * Creare oggetti già popolati in un'unica istruzione, garantendo che tutti
	 * i campi necessari siano forniti.
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Per creare un Patient completo in modo conciso:
	 *   Patient p = new Patient("Mario", "Rossi", "RSSMRA80A01H501U", "Roma", "Via XX", LocalDate.of(1980, 1, 1));
	 * invece di:
	 *   Patient p = new Patient();
	 *   p.setFirstName("Mario");
	 *   p.setLastName("Rossi");
	 *   ... (6 linee totali)
	 *
	 * ==================================================================================
	 * TEORIA: Parola chiave "this"
	 * ==================================================================================
	 * DEFINIZIONE:
	 * "this" è un riferimento all'istanza corrente dell'oggetto.
	 *
	 * SCOPO:
	 * Disambiguare tra parametri e campi quando hanno lo stesso nome.
	 *
	 * ESEMPIO:
	 * this.firstName = firstName;
	 *   ↑ campo           ↑ parametro
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Senza "this", il compilatore non saprebbe se "firstName = firstName" si
	 * riferisce al campo o al parametro. "this.firstName" indica chiaramente
	 * il campo della classe.
	 */
	public Patient(String firstName, String lastName, String ssn, String city, String address, LocalDate dob)
	{
		this.firstName	= firstName;
		this.lastName	= lastName;
		this.ssn		= ssn;
		this.city		= city;
		this.address	= address;
		this.dob		= dob;
	}

	/*
	 * ==================================================================================
	 * TEORIA: Annotazione @Override
	 * ==================================================================================
	 * DEFINIZIONE:
	 * @Override è un'annotazione che indica al compilatore che stiamo sovrascrivendo
	 * (overriding) un metodo della superclasse o implementando un metodo astratto.
	 *
	 * SCOPO:
	 * - Sicurezza: il compilatore verifica che il metodo esista veramente nella superclasse
	 * - Documentazione: rende esplicito che stiamo sovrascrivendo un metodo
	 * - Prevenzione errori: se cambiamo firma nella superclasse, il compilatore segnala l'errore
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Entity ha un metodo astratto getErrors() che DEVE essere implementato da ogni
	 * sottoclasse. @Override assicura che stiamo implementando correttamente il metodo.
	 *
	 * ==================================================================================
	 * TEORIA: List<String> e ArrayList<String> (Generics)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * List è un'interfaccia che rappresenta una collezione ordinata di elementi.
	 * ArrayList è un'implementazione concreta di List basata su array dinamico.
	 * <String> indica il tipo degli elementi (Generics - introdotti in Java 5).
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Vogliamo restituire una lista di errori di validazione. Usiamo List<String> perché:
	 * 1. Può contenere più errori (a differenza di una singola String)
	 * 2. Type-safe: garantito che contiene solo String
	 * 3. Ordinata: gli errori appaiono nell'ordine in cui sono aggiunti
	 * 4. Dinamica: possiamo aggiungere errori senza dichiarare una dimensione fissa
	 */
	// Implementation of abstract method from Entity
	@Override
	public List<String> getErrors()
	{
		List<String> errors = new ArrayList<>();

		/*
		 * ==================================================================================
		 * TEORIA: Validazione dei dati
		 * ==================================================================================
		 * DEFINIZIONE:
		 * La validazione è il processo di verifica che i dati rispettino determinate regole
		 * di business prima di essere persistiti nel database.
		 *
		 * SCOPO:
		 * - Integrità dei dati: garantire che solo dati validi entrino nel sistema
		 * - User feedback: fornire messaggi chiari su cosa non va
		 * - Prevenzione errori: evitare SQLException o stati inconsistenti
		 *
		 * PERCHÉ LO FACCIAMO:
		 * Validare a livello di entità (invece che solo nel database) ci permette di:
		 * 1. Fornire feedback immediato all'utente
		 * 2. Centralizzare le regole di validazione
		 * 3. Testare la logica senza dipendere dal database
		 *
		 * isMissing() è un metodo ereditato da Entity che verifica se una stringa
		 * è null, vuota o contiene solo spazi.
		 */
		if (isMissing(firstName))
			errors.add("First name is required");

		if (isMissing(lastName))
			errors.add("Last name is required");

		if (isMissing(ssn))
			errors.add("SSN is required");

		if (isMissing(city))
			errors.add("City is required");

		if (isMissing(address))
			errors.add("Address is required");

		/*
		 * Validazione di LocalDate:
		 * - Verifica che non sia null
		 * - Verifica che non sia nel futuro usando isAfter() e LocalDate.now()
		 *
		 * LocalDate.now() restituisce la data corrente del sistema.
		 * isAfter() è un metodo di LocalDate che verifica se una data è successiva a un'altra.
		 */
		if (dob == null)
			errors.add("Date of birth is required");
		else if (dob.isAfter(LocalDate.now()))
			errors.add("Date of birth cannot be in the future");

		return errors;
	}

	/*
	 * ==================================================================================
	 * TEORIA: toString() override
	 * ==================================================================================
	 * DEFINIZIONE:
	 * toString() è un metodo ereditato da Object (superclasse di tutte le classi Java)
	 * che restituisce una rappresentazione testuale dell'oggetto.
	 *
	 * SCOPO:
	 * - Debugging: visualizzare facilmente lo stato dell'oggetto
	 * - Logging: registrare informazioni sull'oggetto
	 * - User interface: mostrare l'oggetto in modo leggibile
	 *
	 * PERCHÉ LO FACCIAMO:
	 * L'implementazione di default di Object.toString() restituisce qualcosa come
	 * "Patient@1a2b3c4d" (nome classe + hashcode), poco utile. Sovrascrivendolo
	 * otteniamo una rappresentazione significativa come:
	 * "Patient [id=1, firstName=Mario, lastName=Rossi, ...]"
	 *
	 * Concatenazione di stringhe con +:
	 * L'operatore + concatena stringhe. Java converte automaticamente i tipi primitivi
	 * e oggetti in String quando necessario.
	 */
	@Override
	public String toString()
	{
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName +
		       ", ssn=" + ssn + ", city=" + city + ", address=" + address +
		       ", dob=" + dob + "]";
	}

	/*
	 * ==================================================================================
	 * TEORIA: Getters e Setters (Encapsulation)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * Getters (accessori) e Setters (mutatori) sono metodi pubblici che permettono
	 * di accedere e modificare campi privati/protected di una classe.
	 *
	 * SCOPO:
	 * - Encapsulation: nascondere l'implementazione interna
	 * - Controllo: possiamo aggiungere validazione nei setter
	 * - Flessibilità: possiamo cambiare l'implementazione interna senza modificare l'API pubblica
	 * - JavaBeans convention: necessario per framework come Spring, Hibernate
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Invece di rendere i campi pubblici (accesso diretto), usiamo getter/setter perché:
	 * 1. Possiamo validare i valori nei setter (es. verificare che il prezzo non sia negativo)
	 * 2. Possiamo cambiare il tipo interno senza rompere il codice che usa la classe
	 * 3. Possiamo aggiungere logging, caching o altre logiche
	 * 4. È uno standard JavaBeans richiesto da molti framework
	 *
	 * NAMING CONVENTION:
	 * - Getter: get + NomeCampo con prima lettera maiuscola (es. getFirstName)
	 * - Setter: set + NomeCampo con prima lettera maiuscola (es. setFirstName)
	 * - Per boolean: is + NomeCampo (es. isValid)
	 */
	// Getters and Setters
	public String		getFirstName()					{ return firstName;				}
	public void			setFirstName(String firstName)	{ this.firstName = firstName;	}

	public String		getLastName()					{ return lastName;				}
	public void			setLastName(String lastName)	{ this.lastName = lastName;		}

	public String		getSsn()						{ return ssn;					}
	public void			setSsn(String ssn)				{ this.ssn = ssn;				}

	public String		getCity()						{ return city;					}
	public void			setCity(String city)			{ this.city = city;				}

	public String		getAddress()					{ return address;				}
	public void			setAddress(String address)		{ this.address = address;		}

	public LocalDate	getDob()						{ return dob;					}
	public void			setDob(LocalDate dob)			{ this.dob = dob;				}

	/*
	 * ==================================================================================
	 * TEORIA: Method Overloading (Sovraccarico di metodi)
	 * ==================================================================================
	 * DEFINIZIONE:
	 * L'overloading permette di definire più metodi con lo stesso nome ma parametri diversi
	 * (numero, tipo o ordine differenti).
	 *
	 * SCOPO:
	 * - Flessibilità: permettere di chiamare lo stesso metodo con argomenti di tipo diverso
	 * - API intuitiva: stesso nome per operazioni concettualmente simili
	 * - Convenienza: evitare nomi diversi per fare la stessa cosa (es. setDobFromString)
	 *
	 * PERCHÉ LO FACCIAMO:
	 * Abbiamo due versioni di setDob():
	 * 1. setDob(LocalDate) - accetta direttamente un LocalDate
	 * 2. setDob(String) - accetta una stringa e la converte in LocalDate
	 *
	 * Questo è utile quando leggiamo dati dal database (che restituisce String)
	 * o dall'input utente, senza dover fare il parsing manualmente ogni volta.
	 *
	 * LocalDate.parse():
	 * Converte una stringa in formato ISO (YYYY-MM-DD) in un oggetto LocalDate.
	 * Solleva DateTimeParseException se il formato è invalido.
	 *
	 * isBlank():
	 * Metodo di String (Java 11+) che verifica se una stringa è vuota o contiene
	 * solo caratteri whitespace (spazi, tab, newline). Più robusto di isEmpty()
	 * che verifica solo se la lunghezza è 0.
	 */
	public void setDob(String dobString)
	{
		if (dobString != null && !dobString.isBlank())
			this.dob = LocalDate.parse(dobString);
		else
			this.dob = null;
	}
}
