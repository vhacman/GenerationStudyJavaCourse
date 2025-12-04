package com.generation.housing;
import com.generation.library.Console;

public class PrintHouseLabel
{
	public static void main(String[] args)
	{
		double	roomSide1, roomSide2;
		double	balconySide1, balconySide2;
		double	bathSide1, bathSide2;
		double	squareMeterPrice;
		String	city, address;
		String	label;

		//INPUT
		Console.print("Insert City: ");
		city = Console.readString();

		Console.print("Insert Address: ");
		address = Console.readString();

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

		Console.print("Square Meter Price in euros: ");
		squareMeterPrice = Console.readDouble();

		//CALCOLI
		double roomArea = roomSide1 * roomSide2;
		double bathArea = bathSide1 * bathSide2;
		double balconyArea = balconySide1 * balconySide2;
		double internalArea = roomArea + bathArea;
		double price = (internalArea * squareMeterPrice) + (balconyArea * squareMeterPrice * 2);

		//OUTPUT
		label = "=======================================\n" +
				"Studio Apartment: " 						+ "\n" +
				"=======================================\n" +
				"General information proprety: "            + "\n" +
				"=======================================\n" +
				"City: " 									+ city + "\n" +
				"Address: "									+ address +"\n" +
				"Room: " 									+ roomArea + " sqm\n" +
				"Bath: " 									+ bathArea + " sqm\n" +
				"Balcony: " 								+ balconyArea + " sqm\n" +
				"=======================================\n" +
				"Square Meters Price: " 					+ squareMeterPrice + " euro\n" +
				"=======================================\n" +
				"Total Price: " 							+ price + " euro\n" +
				"========================================";
		//OUTPUT --> STAMPA LABEL
		Console.print(label);
	}
}