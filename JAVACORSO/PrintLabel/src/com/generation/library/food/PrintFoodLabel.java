package com.generation.library.food;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class PrintFoodLabel 
{

	public static void main(String[] args) 
	{
		// D
		String name;		// nome del cibo
		String imageURL;	// indirizzo dell'immagine del cibo
		String outputFileName; // nome in cui salvare
		double carbs, proteins, fats, saturatedFats;		// nutrienti
		double calories;
		// I
		Console.print("Inserire nome cibo");
		name = Console.readString();
		Console.print("Inserire immagine cibo");
		imageURL = Console.readString();
		
		Console.print("Inserire grammi di carboidrati");
		carbs = Console.readDouble(); 	// il sottoprogramma readDouble della classe Console legge un valore double da tastiera
										// e io lo salvo nella variabile cabs
		
		Console.print("Inserire grammi di proteine");
		proteins = Console.readDouble();
		
		Console.print("Inserire grammi di grassi");
		fats = Console.readDouble();

		Console.print("Inserire grammi di grassi saturi");
		saturatedFats = Console.readDouble();

		Console.print("Inserire nome del file in cui stampare l'etichetta");
		outputFileName = Console.readString()+".html";
		
		// C 
		calories = carbs * 4 + proteins * 4 + fats * 9;
		String modelloDiStampa = Template.load("print/template.html");
		
		
		modelloDiStampa = 	modelloDiStampa
							.replace("[name]", name)
							.replace("[carbs]", carbs+"")
							.replace("[proteins]", proteins+"")
							.replace("[fats]", fats+"")
							.replace("[saturated]", saturatedFats+"")
							.replace("[calories]", calories+"")
							.replace("[img]", imageURL);
		
		FileWriter.writeTo("print/"+outputFileName, modelloDiStampa);
		// l'output lo stiamo facendo non su schermo ma su FILE stavolta
		// ho caricato un modello da file lo ho modificato e lo ho riscritto su un altro
		// ho caricato da template.html e lo ho salvato su un file di nome outputFileName
		Console.print("L'etichetta è stata salvata nel file "+outputFileName+" nella cartella print");
	}

}
