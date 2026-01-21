package com.generation.nsmi.model.entities;

import java.time.LocalDate;

/*
 * ═══════════════════════════════════════════════════════════════════════════
 * ANNOTATIONS - IL CUORE DI JAVA
 * ═══════════════════════════════════════════════════════════════════════════
 *
 * Le ANNOTATIONS (annotazioni) sono METADATI che forniscono informazioni
 * sul codice ma NON fanno parte del programma stesso.
 *
 * DEFINIZIONE:
 * Un'annotation è un'etichetta speciale che inizia con @ e fornisce
 * informazioni aggiuntive a:
 * - Compilatore (per rilevare errori o sopprimere warning)
 * - Tool di build/deployment (per generare codice o file di configurazione)
 * - Runtime (per comportamenti specifici durante l'esecuzione)
 * ═══════════════════════════════════════════════════════════════════════════
 * ANNOTATIONS BUILT-IN (Standard di Java)
 * ═══════════════════════════════════════════════════════════════════════════
 *
 * @Override
 * - Indica che un metodo sovrascrive un metodo della superclasse
 * - Il compilatore verifica che esista effettivamente un metodo da sovrascrivere
 * - Se non esiste, genera un errore di compilazione
 * - Previene errori di battitura nei nomi dei metodi
 * Esempio: toString(), equals(), hashCode()
 *
 * @Deprecated
 * - Marca elementi (classi, metodi, campi) come obsoleti/deprecati
 * - Il compilatore genera un warning quando vengono usati
 * - Indica che l'elemento potrebbe essere rimosso in versioni future
 * Esempio: @Deprecated public void oldMethod() { ... }
 *
 * @SuppressWarnings
 * - Dice al compilatore di ignorare specifici warning
 * - Valori comuni: "unchecked", "deprecation", "unused"
 * Esempio: @SuppressWarnings("unchecked")
 *
 * @FunctionalInterface
 * - Indica che un'interfaccia è funzionale (ha un solo metodo astratto)
 * - Usata con lambda expressions
 * - Il compilatore verifica che ci sia esattamente un metodo astratto
 *
 * @SafeVarargs
 * - Sopprime warning per metodi varargs con generics
 * - Usato su metodi final, static o costruttori
 */
public class Person
{
	//stato
	protected int		id;
	protected String	firstName, lastName, SSN;
	protected LocalDate	dob;

	//comportamento
	public Person() {}

	/*
	 * Costruttore con tutti i parametri (all-args constructor)
	 * Permette di inizializzare tutti gli attributi al momento della creazione
	 */
	public Person(int id, String firstName, String lastName, String SSN, LocalDate dob) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.SSN = SSN;
		this.dob = dob;
	}

	// Getters
	public int 			getId() { return id; }
	public String 		getFirstName() { return firstName; }
	public String 		getLastName() { return lastName; }
	public String 		getSSN() { return SSN; }
	public LocalDate 	getDob() { return dob; }

	// Setters
	public void 		setId(int id) { this.id = id; }
	public void			setFirstName(String firstName) { this.firstName = firstName; }
	public void 		setLastName(String lastName) { this.lastName = lastName; }
	public void 		setSSN(String SSN) { this.SSN = SSN; }

	/*
	 * POLIMORFISMO DI METODO (Method Overloading)
	 *
	 * Il polimorfismo è la capacità di un oggetto di assumere forme diverse.
	 * Nel caso dei metodi, il polimorfismo si manifesta in due forme:
	 *
	 * 1. OVERLOAD (Sovraccarico) - Polimorfismo a TEMPO DI COMPILAZIONE:
	 *    - Stesso nome di metodo, STESSA classe
	 *    - Parametri DIVERSI (numero, tipo o ordine)
	 *    - Il compilatore sceglie quale metodo invocare in base ai parametri
	 *    - Esempio: i tre setDob() qui sotto
	 *
	 * 2. OVERRIDE (Sovrascrittura) - Polimorfismo a TEMPO DI ESECUZIONE:
	 *    - Stesso nome di metodo, classe DIVERSA (sottoclasse)
	 *    - Stessa firma (nome + parametri)
	 *    - La sottoclasse ridefinisce il comportamento del metodo della superclasse
	 *    - Richiede ereditarietà e usa @Override
	 *    - Esempio: toString(), equals() che sovrascrivono quelli di Object
	 *
	 * DIFFERENZA CHIAVE:
	 * - Overload = metodi diversi con stesso nome nella stessa classe
	 * - Override = stesso metodo ridefinito in una sottoclasse
	 */

	// Esempio di OVERLOAD: tre versioni di setDob con parametri diversi
	public void setDob(LocalDate dob) 					{ this.dob = dob; }
	public void setDob(int year, int month, int day) 	{ this.dob = LocalDate.of(year, month, day); }
	public void setDob(String dob) 						{ this.dob = LocalDate.parse(dob); }

	/*
	 * SECONDA FORMA DI POLIMORFISMO: OVERRIDE (Sovrascrittura)
	 *
	 * L'override permette a una sottoclasse di fornire una specifica implementazione
	 * di un metodo già definito nella superclasse.
	 *
	 * Caratteristiche dell'OVERRIDE:
	 * - Annotazione @Override (opzionale ma consigliata)
	 * - Stessa firma del metodo della superclasse (nome + parametri)
	 * - Viene eseguito a RUNTIME in base al tipo effettivo dell'oggetto
	 * - Richiede ereditarietà (extends)
	 *
	 * Esempio: toString() override il metodo della classe Object
	 */

	@Override
	public String toString() {
		return "Person [id=" + id + ", firstName=" + firstName +
		       ", lastName=" + lastName + ", SSN=" + SSN + ", dob=" + dob + "]";
	}

}
