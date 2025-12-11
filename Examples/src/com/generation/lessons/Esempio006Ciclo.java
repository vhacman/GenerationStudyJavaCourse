package com.generation.lessons;

import com.generation.library.Console;

public class Esempio006Ciclo
{

	public static void main(String[] args)
	{

		int sum = 0;

		while(sum < 100)
		{
			Console.print("Inserire un numero");
			int n = Console.readInt();
			sum += n;
		}
		Console.print(sum);

		/*
		 * Esempio:
		 * n parte da 46, poi inserisce 26, poi inserisce 27
		 *
		 * RIGA		sum				n		sum < 100
		 * 11		0				-
		 * 13		0				-		true
		 * 16		0				46		true
		 * 17		0+46 = 46		46		true
		 * 13		46				46		true
		 * 16		46				26		true
		 * 17		46+26 = 72		26		true
		 * 13		72				26		true
		 * 16		72				27		true
		 * 17		72+27 = 99		27		true
		 * 13		99				27		true
		 * 16		99				5		true
		 * 17		99+5 = 104		5		true
		 * 13		104				5		false
		 * 19		104				5
		 *
		 */
	}

}