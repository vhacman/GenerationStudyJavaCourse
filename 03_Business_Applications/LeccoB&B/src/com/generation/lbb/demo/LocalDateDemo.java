package com.generation.lbb.demo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LocalDateDemo 
{
	public static void main(String[] args)
	{
		// ═══════════════════════════════════════════════════════════════
		// CREAZIONE DI UNA DATA SPECIFICA
		// ═══════════════════════════════════════════════════════════════
		LocalDate d;
		
		// LocalDate.of(anno, mese, giorno) crea una data specifica
		// Qui: 21 ottobre 1998
		d = LocalDate.of(1998, 10, 21);
		
		// ═══════════════════════════════════════════════════════════════
		// DATA DI INIZIO (DATA DI NASCITA)
		// ═══════════════════════════════════════════════════════════════
		LocalDate inizio;
		inizio = LocalDate.of(1998, 10, 21);  // 21 ottobre 1998
		
		// ═══════════════════════════════════════════════════════════════
		// DATA DI FINE (DATA ODIERNA)
		// ═══════════════════════════════════════════════════════════════
		LocalDate fine;
		
		// LocalDate.now() restituisce la data corrente del sistema
		// Esempio: se oggi è 17 dicembre 2025 → LocalDate(2025, 12, 17)
		fine = LocalDate.now();
		
		// ═══════════════════════════════════════════════════════════════
		// CALCOLO DIFFERENZA IN GIORNI
		// ═══════════════════════════════════════════════════════════════
		// ChronoUnit.DAYS.between(dataInizio, dataFine) calcola
		// il numero di GIORNI tra due date
		// Esempio: dal 21/10/1998 al 17/12/2025 = circa 9.919 giorni
		long days = ChronoUnit.DAYS.between(inizio, fine);
		
		// ═══════════════════════════════════════════════════════════════
		// CALCOLO DIFFERENZA IN ANNI
		// ═══════════════════════════════════════════════════════════════
		// ChronoUnit.YEARS.between(dataInizio, dataFine) calcola
		// il numero di ANNI COMPLETI tra due date
		// Esempio: dal 21/10/1998 al 17/12/2025 = 27 anni
		// (perché il 21/10/2025 è già passato)
		long years = ChronoUnit.YEARS.between(inizio, fine);
		
		System.out.println("Giorni di vita di Gabriela " + days);
		System.out.println("Anni di vita di Gabriela " + years);
		
		// ═══════════════════════════════════════════════════════════════
		// ALTRE UNITÀ DI TEMPO DISPONIBILI
		// ═══════════════════════════════════════════════════════════════
		
		// Settimane
		// long weeks = ChronoUnit.WEEKS.between(inizio, fine);
		
		// Mesi
		// long months = ChronoUnit.MONTHS.between(inizio, fine);
		
		// Ore (solo se usi LocalDateTime invece di LocalDate)
		// long hours = ChronoUnit.HOURS.between(inizioDateTime, fineDateTime);
		
		// ═══════════════════════════════════════════════════════════════
		// NOTA IMPORTANTE: ANNI COMPLETI vs GIORNI
		// ═══════════════════════════════════════════════════════════════
		// ChronoUnit.YEARS conta solo gli ANNI COMPLETI:
		// - Dal 21/10/1998 al 20/10/2025 → 27 anni
		// - Dal 21/10/1998 al 21/10/2025 → 27 anni
		// - Dal 21/10/1998 al 22/10/2025 → 27 anni (non ancora 28!)
		//
		// Per avere precisione al giorno, usa DAYS e dividi per 365.25:
		// double anniPrecisi = days / 365.25;
	}
}