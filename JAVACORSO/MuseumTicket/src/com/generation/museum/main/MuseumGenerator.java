package com.generation.museum.main;

import com.generation.library.*;
import com.generation.mg.utility.MGUtility;
import com.generation.museum.*;


public class MuseumGenerator {

	public static void main(String[] args)
	{
		String	fullName = MGUtility.getName();
		int		age = MGUtility.getAge();
		double	RAL = MGUtility.getRAL();
		int		classChoice = MGUtility.getClassChoice();
		String 	outputFileName;
		
		String modelloDiStampa = Template.load("print/template.html");
		
		Console.print("Inserire nome del file in cui stampare l'etichetta: ");
        outputFileName = Console.readString() + ".html";
		double	finalPrice = MGUtility.calculateCost(fullName, age, RAL, classChoice);
		
        modelloDiStampa = modelloDiStampa
                .replace("[fullName]", fullName)
                .replace("[age]", String.valueOf(age))
                .replace("[classChoice]", String.valueOf(classChoice))
                .replace("[price]", String.valueOf(finalPrice));
        
        //SALVATAGGIO FILE
        FileWriter.writeTo("print/" + outputFileName, modelloDiStampa);
        
        //STAMPA MESSAGGIO DI CONFERMA CHE IL FILE È STATO SALVATO. 
        Console.print("L'etichetta è stata salvata nel file " + outputFileName + " nella cartella print");
	}
}
