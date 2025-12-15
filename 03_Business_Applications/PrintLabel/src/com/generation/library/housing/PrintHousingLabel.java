package com.generation.library.housing;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class PrintHousingLabel
{
	public static void main(String[] args)
	{
		// D
		double roomSide1, roomSide2;
		double balconySide1, balconySide2;
		double bathSide1, bathSide2;
		String city, address;
		String imageURL;
		String outputFileName;
		double squareMeterPrice;
		
		// I
		Console.print("Inserire città: ");
		city = Console.readString();
		
		Console.print("Inserire indirizzo: ");
		address = Console.readString();
		
		Console.print("Inserire URL immagine abitazione: ");
		imageURL = Console.readString();

		Console.print("Side Room 1: ");
		roomSide1 = Console.readDouble();
		Console.print("Side Room 2: ");
		roomSide2 = Console.readDouble();
		
		Console.print("Side Bath 1: ");
		bathSide1 = Console.readDouble();
		Console.print("Side Bath 2: ");
		bathSide2 = Console.readDouble();
		
		Console.print("Side Balcony 1: ");
		balconySide1 = Console.readDouble();
		Console.print("Side Balcony 2: ");
		balconySide2 = Console.readDouble();
		
		Console.print("Prezzo al metro quadro: ");
		squareMeterPrice = Console.readDouble();

		// Calcoli
		double roomArea = roomSide1 * roomSide2;
		double bathArea = bathSide1 * bathSide2;
		double balconyArea = balconySide1 * balconySide2;
		double internalArea = roomArea + bathArea;
		double totalPrice = (internalArea * squareMeterPrice) + (balconyArea * squareMeterPrice * 0.5);

		Console.print("Inserire nome del file in cui stampare l'etichetta: ");
		outputFileName = Console.readString() + ".html";

		String modelloDiStampa = Template.load("print/templateHousing.html");

		modelloDiStampa = modelloDiStampa
			    .replace("[city]", city)
			    .replace("[address]", address)
			    .replace("[roomArea]", String.valueOf(roomArea))
			    .replace("[bathArea]", String.valueOf(bathArea))
			    .replace("[balconyArea]", String.valueOf(balconyArea))
			    .replace("[squareMeterPrice]", String.valueOf(squareMeterPrice))
			    .replace("[totalPrice]", String.valueOf(totalPrice))
			    .replace("[img]", imageURL);

		FileWriter.writeTo("print/" + outputFileName, modelloDiStampa);
		
		Console.print("L'etichetta è stata salvata nel file " + outputFileName + " nella cartella print");
	}
}