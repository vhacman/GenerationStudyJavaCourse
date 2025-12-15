package com.generation.lessons;

import com.generation.library.*;

public class Lesson005EsempioRettangolo
{

	public static void main(String[] args)
	{

		int	width;
		int height;

		do
		{
			Console.print("Inserire larghezza: ");
			width = Console.readInt();
			if (width <= 0)
				Console.print("Dato non valido");
		} while (width <= 0);

		do
		{
			Console.print("Inserire altezza: ");
			height = Console.readInt();
			if (height <= 0)
				Console.print("Dato non valido");
		} while (height <= 0);

		int	area = width * height;
		Console.print("L'area è: " + area);
	}

}