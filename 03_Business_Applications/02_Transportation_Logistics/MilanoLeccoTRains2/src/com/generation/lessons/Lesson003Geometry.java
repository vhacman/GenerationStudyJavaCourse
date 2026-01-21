package com.generation.lessons;

import com.generation.library.Console;

public class Lesson003Geometry 
{

	public static void main(String[] args) 
	{
		Console.print("Inserire Q per il quadrato, R per il rettangolo");
		String type = Console.readString();
		
		switch(type)
		{
			case "Q":
				gestioneQuadrato();
			break;
			case "R":
				gestioneRettangolo();
			break;
			case "C":
				gestioneCirconferenza();
			break;
			default:
				Console.print("Valore non valido");
		}
		Console.print("Finito");
	}
	
	private static void gestioneRettangolo()
	{
		Console.print("Inserire lato1");
		int side1 = Console.readInt();
		Console.print("Inserire lato2");
		int side2 = Console.readInt();
		
		int area = side1 * side2;
		int perimeter = side1 * 2 + side2 * 2;
		Console.print("Area "+area+" perimeter "+perimeter);
	}
	
	private static void gestioneQuadrato()
	{
		Console.print("Inserire lato");
		int side = Console.readInt();
		int area = side * side;
		int perimeter = side * 4;
		Console.print("Area "+area+" perimeter "+perimeter);
	}
	
	private static void	gestioneCirconferenza () 
	{
		Console.print("Inserire raggio: ");
		double	raggio = Console.readDouble();
		double	area = Math.PI * Math.pow(raggio, 2);
		double	circonferenza = 2 * Math.PI * raggio;

		Console.print("Area "+area+" circonferenza "+circonferenza);
	}

}
