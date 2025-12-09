package com.generation.checker;

import com.generation.library.*;

public class Main {

	public static void main (String[] args) {
		int choice;
		
		Console.print("=== MENU PRINCIPALE ===");
		Console.print("1. Controllo Età (Adulto)");
		Console.print("2. Controllo Età e Altezza");
		Console.print("3. Controllo Età e RAL");
		Console.print("Scegli un'opzione (1-3): ");
		choice = Console.readInt();
		
		switch(choice) 
		{
			case 1:
				AgeChecker.isAdult();
				break;
			case 2:
				AgeAndHeightChecker.heightAndAge();
				break;
			case 3:
				AgeAndRALChecker.AgeAndRal();
				break;
			default:
				Console.print("Opzione non valida!");
		}
	}
}