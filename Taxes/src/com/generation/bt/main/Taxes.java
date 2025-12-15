package com.generation.bt.main;

import com.generation.library.Console;


//sconto sulle tasse per le ditte aperte da meno di 5 anni. 
public class Taxes {

	public static void main(String[] args) {
		int		revenue;
		int		taxes;
		int		MAXREVENUE = 20000;
		String	message;
		
		Console.print("Inserire fatturato annuo: ");
		revenue = Console.readInt();
		
		//condizione booleana --> o vero o falso 
		if (revenue <= MAXREVENUE)
			Console.print("Lei è esente in tasse per quest'anno" );
		else {
			taxes = (revenue - MAXREVENUE) * 20 / 100;
			message = "Lei deve pagare: " + taxes + " €";
			Console.print(message);
			Console.print("Da quanti anni è aperta la tua azienda?: ");
			int	yearsOfBusiness = Console.readInt();
			int	MINYEARS = 5;
			if (yearsOfBusiness < MINYEARS) {
				taxes = taxes * 80 / 100;  // Sconto del 20% (rimane l'80%)
				message = "Con lo sconto per azienda giovane: " + taxes + " €";
				Console.print(message);
			}
		}
		
	}

}
