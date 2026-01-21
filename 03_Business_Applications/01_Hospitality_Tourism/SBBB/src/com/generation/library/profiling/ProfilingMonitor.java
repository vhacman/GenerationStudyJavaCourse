package com.generation.library.profiling;

/**
 * Monitor per tracciare le performance delle query al database.
 * Contiene contatori per il numero di query eseguite e le righe processate.
 */
public class ProfilingMonitor
{

	public static int	queryNumber = 0;
	public static int	rowsNumbers = 0;

	/**
	 * Resetta i contatori delle statistiche.
	 */
	public static void init()
	{
		/*
		 ******************************************
		 * Azzera entrambi i contatori per iniziare
		 * una nuova sessione di profiling
		 ******************************************
		 */
		queryNumber = 0;
		rowsNumbers = 0;
	}

}
