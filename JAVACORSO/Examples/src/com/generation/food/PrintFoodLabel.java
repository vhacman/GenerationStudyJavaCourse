package com.generation.food;

import com.generation.library.Console;

public class	PrintFoodLabel
{
    public static void	main(String[] args)
    {
        // DICHIARAZIONE VARIABILI
        String	name; // nome del cibo
        double	carbs, proteins, fats, saturatedFats; // nutrienti cibo in g 
        double	calories; //calorie totali del cibo 
        double	foodWeight; //peso del cibo
        String	label;
        double	caloriesFromSatFats;
        double  percentageSatFats;

        // INPUT DELL'UTENTE
        Console.print("Insert Food Name: ");
        name = Console.readString();
        Console.print("food weight in grams: ");
        foodWeight = Console.readDouble();
        Console.print("Insert Carbs grams: ");
        carbs = Console.readDouble();
        Console.print("Insert Proteins grams: ");
        proteins = Console.readDouble();
        Console.print("Insert Fats grams: ");
        fats = Console.readDouble();
        Console.print("Of which (Insert Saturated Fats): ");
        saturatedFats = Console.readDouble();

        // CALCOLI
        calories = (carbs * 4) + (proteins * 4) + (fats * 9);
        caloriesFromSatFats = saturatedFats * 9;
        percentageSatFats = (caloriesFromSatFats / calories) * 100;

        //COSTRUZIONE DELLA LABEL        
        label = "=======================================\n"    	 		      +
        		"Nutritional Board of: "	 	     + name 	  	 		  + "\n"      +
        		"=======================================\n"      		      +
        		"Weight " 						     + foodWeight 	 		  + " g\n"    +
        		"Carbs: " 						     + carbs      	 		  + " g\n"    +
        		"Proteins: "				 	     + proteins   	 		  + " g\n"    +
        		"Fats: "					 	     + fats       	 		  + " g\n"    +
        		"of which Saturated Fats: "  	     + saturatedFats 		  + " g\n"    +
        		"=======================================\n" 	 		      +	
        		"Total Calories: " 			 	     + calories		 		  + " kcal\n" +
        		//"Calories from Saturated Fats: "   + caloriesFromSatFats    + " kcal\n" +
        		"========================================\n"			      +
        		"Percentage of Saturated Fats: " 	 + percentageSatFats      + "%\n" + 
        		"========================================";
        //OUTPUT --> STAMPA LABEL
        Console.print(label);
    }
}




    /*
        // VERIFICA SE KETO-FIRENDLY
        boolean isKeto = false;
        if (percentageCarbs < 10 && percentageProteins >=  20 && percentageProteins <= 35) {isKeto = true;}
     */
    
    /*
        Console.print("\n--- KETO ANALYSIS ---");
        if (isKeto) {
            Console.print("✓ This food is KETO-FRIENDLY!");
        } else {
            Console.print("✗ This food is NOT keto-friendly.");
            Console.print("  Carbs: " + percentageCarbs + "% (target: < 10%)");
        }
     */
