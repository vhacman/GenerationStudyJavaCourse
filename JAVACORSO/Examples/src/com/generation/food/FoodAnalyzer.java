package com.generation.food;

import com.generation.library.Console;

public class FoodAnalyzer
{
    public static void main(String[] args)
    {
        // DICHIARAZIONE:
        // Dati di input: carbs, fats, proteins
        String name;
        double carbs, proteins, fats;
        double calories;

        // % Grassi Saturi = (Grassi Saturi / Grassi Totali) × 100
        double saturatedFats;
        double percentageSaturatedFats;
        double caloriesFromSaturatedFats;

        // INPUT per nome cibo
        Console.print("What are you eating? ");
        name = Console.readString();

        Console.print("How many Carbs: ");
        carbs = Console.readDouble();
        
        Console.print("How many Proteins: ");
        proteins = Console.readDouble();

        Console.print("How many Fats: ");
        fats = Console.readDouble();

        // Total calories = (Carbs × 4) + (Proteins × 4) + (Fats × 9)
        calories = (carbs * 4) + (proteins * 4) + (fats * 9);

        Console.print("You just ate: " + calories + " calories");
        
        Console.print("Of which (Insert Saturated Fats): ");
        saturatedFats = Console.readDouble();
        
        percentageSaturatedFats = (saturatedFats / fats) * 100;
        caloriesFromSaturatedFats = saturatedFats * 9;
        
        Console.print("The percentage of Saturated Fats is: " + percentageSaturatedFats + "%");
        Console.print("Calories from Saturated Fats: " + caloriesFromSaturatedFats + " kcal");
    }
}