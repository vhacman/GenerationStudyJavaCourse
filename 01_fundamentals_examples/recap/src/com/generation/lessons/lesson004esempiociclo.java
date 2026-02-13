package com.generation.lessons;

import com.generation.library.*;

public class Lesson004EsempioCiclo {

	public static void main(String[] args) {
		
		int side;
		
		do
		{
			Console.print("Inserire lato");
			side = Console.readInt();
			if (side <= 0)
				Console.print("Dato non valido");
		} while (side <= 0);
			
		int area = side * side;
		Console.print("L'area è: " + area);
		
		
	}

}
