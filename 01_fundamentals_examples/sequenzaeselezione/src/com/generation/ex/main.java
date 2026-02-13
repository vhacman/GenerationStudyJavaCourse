package com.generation.ex;

import com.generation.library.Console;

public class Main 
{
	public static void	main(String[] args)
	{
		int		choice;
		
		Console.print("=== MENU ESERCIZI 11-20 ===");
		Console.print("11. Controllo ingresso (età >= 18)");
		Console.print("12. Biglietto con genere e prezzo");
		Console.print("13. Biglietto museo");
		Console.print("14. Biglietto Palazzo Mediceo");
		Console.print("15. Palazzo Mediceo con fasce orarie straordinarie");
		Console.print("16. Palazzo Mediceo gratuito per studenti d'arte");
		Console.print("17. Calcolo BMI e fabbisogno calorico");
		Console.print("18. Costo vacanza");
		Console.print("19. Costo vacanza con assicurazione");
		Console.print("20. Costo viaggio in treno");
		Console.print("Scegli un esercizio (11-20): ");
		choice = Console.readInt();
		
		switch(choice) {
			case 11:
				Exercises11to20.esercizio11();
				break;
			case 12:
				Exercises11to20.esercizio12();
				break;
			case 13:
				Exercises11to20.esercizio13();
				break;
			case 14:
				Exercises11to20.esercizio14();
				break;
			case 15:
				Exercises11to20.esercizio15();
				break;
			case 16:
				Exercises11to20.esercizio16();
				break;
			case 17:
				Exercises11to20.esercizio17();
				break;
			case 18:
				Exercises11to20.esercizio18();
				break;
			case 19:
				Exercises11to20.esercizio19();
				break;
			case 20:
				Exercises11to20.esercizio20();
				break;
			default:
				Console.print("Opzione non valida!");
		}
	}

}
