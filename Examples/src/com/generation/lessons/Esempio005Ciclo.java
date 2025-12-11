package com.generation.lessons;
import com.generation.library.Console;

public class Esempio005Ciclo
{

	public static void main(String[] args)
	{

		String ticket = "Wine Bar Villasanta \n";

		// dati per ogni prodotto
		String description, category;
		double price;
		double total = 0;
		double ivaTotal = 0;
		do
		{
			Console.print("Inserire prodotto, premere invio per terminare");
			description = Console.readString();
			if(description.isBlank())
				break;		// termina prima il ciclo, GOTO 37
			Console.print("Inserire F per Food, N per non Food");
			category = Console.readString();
			Console.print("Inserire prezzo");
			price = Console.readDouble();

			double ivaMultiplier = category.equals("F") ? 0.11 : 0.22;
			double iva = price * ivaMultiplier;
			double fullPrice = price + iva;
			total+=fullPrice;
			ivaTotal+=iva;
			ticket+=description+"\t"+fullPrice+" euro (IVA "+iva+")+\n";

		}while(true); // ciclo infinito, lo uccideremo in maniera
		// proditoria quando l'utente inserirà una stringa bianca come descrizione
		ticket+="Totale "+total+" \t di cui IVA euro "+ivaTotal+" euro";
		Console.print(ticket);
	}

	// Ferdinando entra in un bar, Caffè lungo e pacchetto di sigari al mentolo da 2 euro
	//ESECUZIONE SIMULATA
	/*
	 * RIGA		iva		ivaMultiplier	description			category	price	fullPrice		total			ivaTotal
	 * 16																						0
	 * 17																						0				0
	 * 21									Caffè												0				0
	 * 22			FALSA, salto la 23
	 * 23			SALTO
	 * 25									Caffè				F								0				0
	 * 27									Caffè				F			1					0				0
	 * 29					0.11			Caffè				F			1					0				0
	 * 30			0.11	0.11			Caffè				F			1					0				0
	 * 31			0.11	0.11			Caffè				F			1		1.11		0				0
	 * 32			0.11	0.11			Caffè				F			1		1.11		0+1.11=1.11		0
	 * 33			0.11	0.11			Caffè				F			1		1.11		1.11			0+0.11 = 0.11
	 * 36 => salto alla 20
	 * 
	 * 20 --> LEGGO INPUT DA UTENTE quindi mi prendo la descrizione 
	 * 21									Sigari mentolo							1.11		0.11
	 * 22			FALSA, salto la 23
	 * 23			SALTO
	 * 25									Sigari mentolo		N								1.11			0.11
	 * 27									Sigari mentolo		N			2					1.11			0.11
	 * 29					0.22			Sigari mentolo		N			2					1.11			0.11
	 * 30			0.44	0.22			Sigari mentolo		N			2					1.11			0.11
	 * 31			0.44	0.22			Sigari mentolo		N			2		2.44		1.11			0.11
	 * 32			0.44	0.22			Sigari mentolo		N			2		2.44		1.11+2.44=3.55	0.11
	 * 33			0.44	0.22			Sigari mentolo		N			2		2.44		3.55			0.11+0.44=0.55
	 * 36 => salto alla 20
	 * 
	 * 21									""(stringa vuota)									3.55			0.55
	 * 22			VERA, break => GOTO 37
	 * 37			stampa ticket finale
	 *
	 */


}