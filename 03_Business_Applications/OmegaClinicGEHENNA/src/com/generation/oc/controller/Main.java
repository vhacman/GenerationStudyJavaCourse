package com.generation.oc.controller;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import com.generation.library.Console;
import com.generation.oc.model.entities.Prestation;
import com.generation.oc.model.entities.PrestationCalendar;
/**
  * 
 * 4. Arrays.asList()
 * Definizione: Converte un array in una List fixed-size (dimensione immutabile).
 * Caratteristiche:
 *   - Restituisce una vista della List backed dall'array originale
 *   - Modifiche alla List si riflettono sull'array e viceversa
 *   - NON supporta operazioni strutturali: add(), remove() lanciano UnsupportedOperationException
 *   - Supporta set(index, element) per modificare elementi esistenti
 * Uso: ponte tra API basate su array e API basate su Collection
 */
public class Main 
{

	public static void main(String[] args) 
	{
		/** ARRAY INITIALIZER:
		 * Sintassi compatta per creare e popolare un array in una singola espressione.
		 * Il compilatore deduce la dimensione dal numero di elementi forniti.
		 * Tipo: Prestation[] con 3 elementi */
		Prestation[] presV = {
			new	Prestation(LocalDate.of(2026, 1, 1),9),   // 1 gennaio 2026, ore 9
			new	Prestation(LocalDate.of(2026, 1, 1),10),  // 1 gennaio 2026, ore 10
			new	Prestation(LocalDate.of(2026, 1, 2),9)    // 2 gennaio 2026, ore 9
		};		
		/** CONVERSIONE ARRAY -> LIST:
		 * Arrays.asList() crea una List fixed-size backed dall'array presV.
		 * 
		 * IMPORTANTE:
		 * - pres.add() o pres.remove() causerebbero UnsupportedOperationException
		 * - pres.set(0, nuovaPrestation) funzionerebbe e modificherebbe anche presV[0]
		 * - Dimensione: pres.size() == presV.length == 3
		 * 
		 * MOTIVO: PrestationCalendar richiede List<Prestation> nel costruttore,
		 *         quindi convertiamo l'array per compatibilità API.*/
		List<Prestation> pres = Arrays.asList(presV);		
		/** ISTANZIAZIONE PrestationCalendar:
		 * Il costruttore:
		 * 1. Ordina le prestazioni per data (già ordinate in questo caso)
		 * 2. Costruisce la LinkedHashMap<LocalDate, List<Prestation>>:
		 *    - Chiave: 2026-01-01 -> Valore: [Prestation(9h), Prestation(10h)]
		 *    - Chiave: 2026-01-02 -> Valore: [Prestation(9h)]		 */
		PrestationCalendar c = new PrestationCalendar(pres);		
		/**
		 * CHIAMATA getNextFree():
		 * Algoritmo:
		 * 1. Itera sulla prima data (2026-01-01)
		 * 2. Verifica orari: 9 (occupato), 10 (occupato), 11 (libero!)
		 * 3. Restituisce: new Prestation(2026-01-01, 11)
		 * 
		 * OUTPUT ATTESO:
		 * Prestation con data 1 gennaio 2026 alle ore 11 */
		Console.print(c.getNextFree());		
	}
}
