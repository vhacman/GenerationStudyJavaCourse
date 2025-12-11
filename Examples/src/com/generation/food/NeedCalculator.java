package com.generation.food;

import com.generation.library.Console;

/* Abstract: 
** Si vuole calcolare la percentuale di fabbisogno proteico e 
** calorico coperti dall’alimentazione di una giornata tipo del nostro cliente. 
** Il fabbisogno proteico è di 1.5 grammi di proteine per kg di peso corporeo.
** Il fabbisogno calorico è di 150 calorie per kg di peso corporeo.
** Variabili di input: peso in kg del nostro cliente,
** quantitativo di grammi di carboidrati, proteine e grassi ingeriti durante la giornata.
** Variabili di output: fabbisogno calorico, fabbisogno proteico,
** percentuale coperta di fabbisogno calorico, percentuale coperta di fabbisogno proteico 
*/
public class NeedCalculator
{

	public static void main(String[] args)
	{
		//variabili di input
		double	carbsIn; //g of carbs
		double	proteinsIn; // g of proteins
		double	fatsIn; //g of fats
		double	weight; //Weight in kg
		double	caloriesIn; //calories eaten
		double	caloriesNeed; //calories needed
		double	proteinsNeed; //proteins needed
		double 	percCaloric; //percentage of calories
		double	percProteic; //percentage of proteins
		
		//INPUT
		Console.print("Good Morning User! Please Insert information");
		Console.print("How many grams of Carbs? ");
		carbsIn = Console.readDouble();
		Console.print("How many grams of Proteins? ");
		proteinsIn = Console.readDouble();
		Console.print("How many grams of Fats? ");
		fatsIn = Console.readDouble();
		Console.print("What is your weight in kg? ");
		weight = Console.readDouble();
		
		//CALCOLI
		caloriesNeed = 150 * weight;
		proteinsNeed = 1.5 * weight;
		caloriesIn = (carbsIn * 4) + (proteinsIn * 4) + (fatsIn * 9);
		percCaloric = (caloriesIn / caloriesNeed) * 100;
		percProteic = (proteinsIn / proteinsNeed) * 100;

		//OUTPUT
        Console.print("\n========== NUTRITION SUMMARY ==========");
        Console.print("Calories needed: " + caloriesNeed + " kcal");
        Console.print("Total calories already consumed: " + caloriesIn + " kcal");
        Console.print("Proteins needed: " + proteinsNeed + " g");
        Console.print("Caloric coverage : " + percCaloric + "%");
        Console.print("Proteic coverage : " + percProteic + "%");
        Console.print("=========================================\n");
	}
}
