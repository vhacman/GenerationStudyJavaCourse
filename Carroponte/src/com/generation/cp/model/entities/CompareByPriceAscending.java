package com.generation.cp.model.entities;

import java.util.Comparator;

/**
 * Comparatore per ordinare gli eventi per prezzo in ordine crescente.
 *
 * Questo componente si occupa di dirmi se A è maggiore, uguale o inferiore a B.
 */
public class CompareByPriceAscending implements Comparator<Event>
{

	/**
	 * Compara due eventi in base al loro prezzo.
	 *
	 * @param o1 Primo evento da confrontare
	 * @param o2 Secondo evento da confrontare
	 * @return Un numero positivo se o1 > o2, zero se o1 == o2, negativo se o1 < o2
	 */
	@Override
	public int compare(Event o1, Event o2)
	{
		/*
		 * ═══════════════════════════════════════════════════════════════
		 * (Comparator)
		 * ═══════════════════════════════════════════════════════════════
		 *
		 * Interfaccia funzionale (Comparator<Event>) → Implementazione concreta (CompareByPriceAscending)
		 *
		 * Questo comparatore implementa il pattern Strategy per l'ordinamento:
		 * - Comparator<Event> definisce il contratto: compare(Event, Event)
		 * - CompareByPriceAscending fornisce una strategia specifica: ordina per prezzo
		 * - Il client (Collections.sort, List.sort) usa il comparatore senza conoscere i dettagli
		 *
		 * Contratto del metodo compare():
		 * → Ritorna POSITIVO se o1 > o2  (o1 ha prezzo maggiore)
		 * → Ritorna ZERO     se o1 == o2 (prezzi uguali)
		 * → Ritorna NEGATIVO se o1 < o2  (o1 ha prezzo minore)
		 *
		 * Implementazione:
		 * - o1.price - o2.price calcola la differenza
		 * - Se o1 ha un prezzo maggiore di o2, restituirà un numero positivo → o1 > o2
		 * - Se o1 ha un prezzo minore di o2, restituirà un numero negativo → o1 < o2
		 *
		 * Alternative moderne:
		 * - Lambda: (e1, e2) -> e1.getPrice() - e2.getPrice()
		 * - Method reference: Comparator.comparingInt(Event::getPrice)
		 *
		 * Principi OOP applicati:
		 * → Strategy Pattern: Algoritmo di confronto intercambiabile
		 * → Single Responsibility: Questa classe si occupa solo di confrontare per prezzo
		 * → Open/Closed: Nuovi criteri possono essere aggiunti senza modificare il codice esistente
		 *
		 * ═══════════════════════════════════════════════════════════════
		 */
		return o1.price - o2.price;
	}

}
