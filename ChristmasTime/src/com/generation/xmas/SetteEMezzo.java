package com.generation.xmas;

import com.generation.library.Console;

public class SetteEMezzo 
{

	public static void main(String[] args) 
	{
	
		// denari, spade, bastoni, coppe
		// valori da 1 a 10
		
		// i numeri da 1 a 7 li prendiamo come sono
		// l'otto (la donna), il nove (il cavallo) e il 10 (il re)
		// valgono 0.5 punti ciascuno
		
		// vince chi si avvicina di più a 7.5 senza sballare

		// pesco una carta
		double player1Score = getCard();
		
		String oneMore;
		do
		{
			Console.print("Punteggio "+player1Score);
			Console.print("Altra carta?");
			oneMore = Console.readString();
			if(oneMore.equalsIgnoreCase("s"))
			{
				double card = getCard();
				Console.print("Hai estratto "+card);
				player1Score+=card;
			}
		}while(oneMore.equals("s") && player1Score<7.5);
		//       							F			=> False
		
		if(player1Score<=7.5)
			Console.print("Il tuo punteggio finale è "+player1Score);
		else
			Console.print("Hai sballato");
	}
	
	/**
	 * Produce un punteggio casuale fra 0.5 e 7
	 * @return
	 */
	private static double getCard()
	{
		int card = (int)(Math.random()*10)+1;
		return card <= 7 ? card : 0.5;
	}

}
