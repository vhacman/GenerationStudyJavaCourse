package com.generation.mh.model.dto;

/**
 * DTO (Data Transfer Object) che contiene i dati aggregati delle statistiche.
 * Separa la logica di calcolo dalla visualizzazione.
 */
public class StatisticsData
{
	// Statistiche base
	public int totalBookings;

	// Distribuzione per specie
	public int vampireCount;
	public int werewolfCount;
	public int mermaidCount;
	public int humanCount;

	// Distribuzione per tipo stanza
	public int singolaCount;
	public int doppiaCount;
	public int suiteCount;

	// Statistiche finanziarie
	public double totalRevenue;
	public int totalNights;
	public int transportCount;

	/**
	 * Costruttore vuoto - inizializza tutti i contatori a zero
	 */
	public StatisticsData()
	{
		// Tutti i campi sono già inizializzati a 0 di default
	}

	/**
	 * Calcola la media dei ricavi per prenotazione
	 *
	 * @return Media ricavi, 0 se non ci sono prenotazioni
	 */
	public double getAverageRevenue()
	{
		if (totalBookings == 0)
			return 0.0;
		return totalRevenue / totalBookings;
	}
}
