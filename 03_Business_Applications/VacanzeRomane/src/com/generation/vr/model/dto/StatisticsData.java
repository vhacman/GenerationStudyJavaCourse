package com.generation.vr.model.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;


//NUOVI ARGOMENTI DI TEORIA - HASHMAP / DTO - approfondimento personale.

/**
 * DTO (Data Transfer Object) per trasferire dati statistici aggregati
 * tra Repository e View passando per Service.
 * 
 * Contiene solo dati pubblici e rispetta la separazione delle responsabilità:
 * - Repository: calcola e aggrega i dati grezzi
 * - DTO: trasporta i dati
 * - View: visualizza i dati formattati
 * 
 * === STRUTTURA DATI: HashMap ===
 * 
 * HashMap è una struttura dati che associa chiavi univoche a valori, come un dizionario.
 * Sintassi: HashMap<TipoChiave, TipoValore>
 * 
 * Nel nostro caso:
 * - Chiave (String): nome dell'entità (es. "GRAND_HOTEL", "COLOSSEO")
 * - Valore (Integer): conteggio delle occorrenze (es. 5 prenotazioni)
 * 
 * Operazioni principali:
 * - put(chiave, valore): inserisce o aggiorna un valore
 * - get(chiave): recupera il valore associato alla chiave
 * - getOrDefault(chiave, default): recupera il valore, o restituisce default se la chiave non esiste
 * 
 * Esempio pratico di conteggio:
 * hotelCounts.getOrDefault("GRAND_HOTEL", 0) + 1
 * - Prima occorrenza: 0 + 1 = 1
 * - Seconda occorrenza: 1 + 1 = 2
 * - Terza occorrenza: 2 + 1 = 3
 * 
 * Perché usiamo HashMap qui:
 * 1. Accesso veloce: trovare un conteggio è quasi istantaneo O(1)
 * 2. Chiavi uniche: ogni hotel/attrazione/guida appare una sola volta come chiave
 * 3. Dinamica: si espande automaticamente quando aggiungiamo nuovi elementi
 * 4. Ideale per conteggi:   getOrDefault() per incrementare contatori
 * 5. Flessibile: non serve sapere in anticipo quanti hotel/attrazioni/guide ci son
 */
public class StatisticsData
{
	public HashMap<String, Integer> hotelCounts = new HashMap<>();
	public HashMap<String, Integer> saturdayAttractionCounts = new HashMap<>();
	public HashMap<String, Integer> sundayAttractionCounts = new HashMap<>();
	public HashMap<String, Integer> guideCounts = new HashMap<>();
	public BigDecimal totalRevenue = BigDecimal.ZERO;
	public int totalTrips = 0;

	/**
	 * Calcola il ricavo medio per trip
	 * Arrotonda a 2 decimali con metodo HALF_UP (0.5 arrotonda per eccesso)
	 * 
	 * @return ricavo medio, o ZERO se non ci sono trip
	 */
	public BigDecimal getAverageRevenue()
	{
		if (totalTrips == 0)
			return BigDecimal.ZERO;

		return totalRevenue.divide(new BigDecimal(totalTrips), 2, RoundingMode.HALF_UP);
	}
}