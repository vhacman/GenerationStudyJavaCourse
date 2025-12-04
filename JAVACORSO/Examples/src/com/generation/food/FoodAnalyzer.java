package com.generation.food;

import com.generation.library.Console;

public class	FoodAnalyzer
{
    public static void	main(String[] args)
    {
        // DICHIARAZIONE VARIABILI
        String	name;
        double	carbs, proteins, fats;
        double	calories;
        double	saturatedFats;
        double	percentageSaturatedFats;
        double	percentageProteins;
        double	percentageCarbs;
        
        // INPUT DELL'UTENTE
        Console.print("What are you eating? ");
        name = Console.readString();

        Console.print("How many Carbs: ");
        carbs = Console.readDouble();

        Console.print("How many Proteins: ");
        proteins = Console.readDouble();

        Console.print("How many Fats: ");
        fats = Console.readDouble();

        // CALCOLO DELLE CALORIE TOTALI 
        calories = (carbs * 4) + (proteins * 4) + (fats * 9);

        Console.print("Of which (Insert Saturated Fats): ");
        saturatedFats = Console.readDouble();

        // CALCOLO DEI MACRONUTRIENTI
        double	caloriesFromProteins = proteins * 4;
        double	caloriesFromCarbs = carbs * 4;
        double	caloriesFromFats = fats * 9;
        double	caloriesFromSaturatedFats = saturatedFats * 9;
        percentageSaturatedFats = (saturatedFats / fats) * 100;
        percentageProteins = (caloriesFromProteins / calories) * 100;
        percentageCarbs = (caloriesFromFats / calories) * 100;
        

        // OUTPUT 
        Console.print("\n========== NUTRITION SUMMARY ==========");
        Console.print("Food: " + name);
        Console.print("Total Calories: " + calories + " kcal");
        Console.print("\n--- Calories Breakdown ---");
        Console.print("Calories from Proteins: " + caloriesFromProteins + " kcal");
        Console.print("Calories from Carbs: " + caloriesFromCarbs + " kcal");
        Console.print("Calories from Fats: " + caloriesFromFats + " kcal");
        Console.print("Calories from Saturated Fats: " + caloriesFromSaturatedFats + " kcal");
        
        Console.print("\n--- Additional Percentage Info ---");
        Console.print("Percentage of Saturated Fats: " + percentageSaturatedFats + "%");
        Console.print("Percentage of Proteins: " + percentageProteins + " %");
        Console.print("Percentage of Carbs: " + percentageCarbs + " %");
        Console.print("=========================================\n");
    }
}