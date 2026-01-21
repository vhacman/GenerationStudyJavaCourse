package com.generation.cp.model.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

import com.generation.library.Console;

/**
 * Classe dimostrativa per l'uso di Lambda Expressions, Predicate e Comparator.
 *
 * Questo esempio mostra come filtrare e ordinare una lista di eventi
 * utilizzando programmazione funzionale in Java 8+.
 */
public class DemoOrdinamentoEventi
{

	/**
	 * Metodo main che dimostra filtraggio e ordinamento con lambda expressions.
	 *
	 * @param args Argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * Functional Programming in Java
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * OOP tradizionale → Programmazione funzionale (Lambda + Streams)
		 *
		 * Questo esempio dimostra il paradigma di programmazione funzionale in Java:
		 *
		 * 1. LAMBDA EXPRESSIONS: Sintassi compatta per interfacce funzionali
		 *    → Predicate<Event>: interfaccia funzionale per test booleani
		 *    → Comparator<Event>: interfaccia funzionale per confronti
		 *    → Consumer (forEach): interfaccia funzionale per azioni
		 *
		 * 2. FILTRAGGIO CON PREDICATE:
		 *    → Predicate<Event> tempiBui = e -> e.price > 15
		 *    → res.removeIf(tempiBui) rimuove tutti gli eventi che soddisfano il predicato
		 *
		 * 3. ORDINAMENTO CON COMPARATOR:
		 *    → Comparator<Event> ordineAlfabetico = (a,b) -> a.title.compareTo(b.title)
		 *    → res.sort(ordineAlfabetico) ordina la lista secondo il criterio
		 *
		 * 4. METHOD REFERENCE:
		 *    → Console::print è equivalente a: e -> Console.print(e)
		 *    → Sintassi più compatta quando la lambda chiama un solo metodo
		 *
		 * Principi applicati:
		 * → Immutabilità: Le lambda non modificano lo stato esterno
		 * → First-Class Functions: Le funzioni sono trattate come valori
		 * → Declarative Programming: Descriviamo COSA fare, non COME
		 * → Separation of Concerns: Logica di filtraggio separata dai dati
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */

		List<Event> res = new ArrayList<Event>();

		// Popolamento lista eventi
		res.add(new Event(1,  "Litfiba",                    "ouhhh",              LocalDate.of(2026, 7, 7),   10));
		res.add(new Event(2,  "Vasco Rossi",                "rock italiano",      LocalDate.of(2026, 6, 15),  20));
		res.add(new Event(3,  "Ligabue",                    "live tour",          LocalDate.of(2026, 6, 20),  15));
		res.add(new Event(4,  "Negramaro",                  "estate live",        LocalDate.of(2026, 7, 1),   25));
		res.add(new Event(5,  "Subsonica",                  "electro rock",       LocalDate.of(2026, 7, 3),   18));
		res.add(new Event(6,  "Afterhours",                 "alternative night",  LocalDate.of(2026, 7, 5),   12));
		res.add(new Event(7,  "Maneskin",                   "world tour",         LocalDate.of(2026, 7, 8),   30));
		res.add(new Event(8,  "Pinguini Tattici Nucleari",  "pop rock",           LocalDate.of(2026, 7, 10),  22));
		res.add(new Event(9,  "Elisa",                      "acoustic set",       LocalDate.of(2026, 7, 12),  16));
		res.add(new Event(10, "Giorgia",                    "voce live",          LocalDate.of(2026, 7, 14),  14));
		res.add(new Event(11, "Cesare Cremonini",           "stadium tour",       LocalDate.of(2026, 7, 16),  28));
		res.add(new Event(12, "Jovanotti",                  "party show",         LocalDate.of(2026, 7, 18),  35));
		res.add(new Event(13, "Tiziano Ferro",              "emotional live",     LocalDate.of(2026, 7, 20),  24));
		res.add(new Event(14, "Francesco De Gregori",       "cantautorato",       LocalDate.of(2026, 7, 22),  10));
		res.add(new Event(15, "Lucio Corsi",                "indie vibes",        LocalDate.of(2026, 7, 24),  12));
		res.add(new Event(16, "Calcutta",                   "indie pop",          LocalDate.of(2026, 7, 26),  18));
		res.add(new Event(17, "The Kolors",                 "summer hits",        LocalDate.of(2026, 7, 28),  20));
		res.add(new Event(18, "Emma",                       "live show",          LocalDate.of(2026, 7, 30),  15));
		res.add(new Event(19, "Rkomi",                      "urban night",        LocalDate.of(2026, 8, 1),   22));
		res.add(new Event(20, "Marracash",                  "rap live",           LocalDate.of(2026, 8, 3),   30));
		res.add(new Event(21, "Sfera Ebbasta",              "trap show",          LocalDate.of(2026, 8, 5),   35));
		res.add(new Event(22, "Fabri Fibra",                "hip hop",            LocalDate.of(2026, 8, 7),   25));
		res.add(new Event(23, "Noemi",                      "pop soul",           LocalDate.of(2026, 8, 9),   14));
		res.add(new Event(24, "Arisa",                      "voice concert",      LocalDate.of(2026, 8, 11),  12));
		res.add(new Event(25, "Levante",                    "indie pop",          LocalDate.of(2026, 8, 13),  16));
		res.add(new Event(26, "Modà",                       "romantic live",      LocalDate.of(2026, 8, 15),  20));
		res.add(new Event(27, "Caparezza",                  "show totale",        LocalDate.of(2026, 8, 17),  28));
		res.add(new Event(28, "Baustelle",                  "alternative pop",    LocalDate.of(2026, 8, 19),  18));
		res.add(new Event(29, "Fast Animals and Slow Kids", "rock live",          LocalDate.of(2026, 8, 21),  15));
		res.add(new Event(30, "La Rappresentante di Lista", "electro pop",        LocalDate.of(2026, 8, 23),  17));
		res.add(new Event(31, "Litfiba",                    "reunion show",       LocalDate.of(2026, 8, 25),  10));


		/*
		 * ═══════════════════════════════════════════════════════════════
		 * SCENARIO: Ferdinando selvatico con pochi soldi in tasca,
		 * allergico al rap, con un pessimo carattere, che vuole vedere
		 * i suoi risultati ordinati per prima lettera del titolo
		 * ═══════════════════════════════════════════════════════════════
		 */

		// VERSIONE ESPLICITA: Con tipi e variabili dichiarate

		// Ho usato la lambda per ottenere un oggetto di tipo Predicate<Event>
		Predicate<Event> aMorteIlRap = e -> e.description.contains("rap");
		res.removeIf(aMorteIlRap);

		Predicate<Event> tempiBui = e -> e.price > 15;
		res.removeIf(tempiBui);

		// Ordinamento alfabetico
		Comparator<Event> ordineAlfabetico = (a,b) -> a.title.compareTo(b.title);
		res.sort(ordineAlfabetico);

		res.forEach(Console::print);


		// ═══════════════════════════════════════════════════════════════
		// VERSIONE COMPATTA: Senza tipi esplicitati (Type Inference)
		// ═══════════════════════════════════════════════════════════════

		res.removeIf(e -> e.price >= 15 || e.description.contains("rap"));
		res.sort((a,b) -> a.title.compareTo(b.title));
		res.forEach(Console::print);

		/*
		 * Equivalenza tra le versioni:
		 * - Versione esplicita: Più leggibile per principianti, tipi chiari
		 * - Versione compatta: Idiomatica, sfrutta type inference di Java
		 *
		 * Type Inference: Il compilatore deduce i tipi dai parametri generici
		 * - res è List<Event>, quindi removeIf accetta Predicate<Event>
		 * - Java deduce che 'e' è di tipo Event automaticamente
		 */
	}

}
