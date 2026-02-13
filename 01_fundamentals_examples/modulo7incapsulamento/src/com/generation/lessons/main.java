package com.generation.lessons;

import com.generation.library.Console;

public class Main {

	public static void main(String[] args)
	{
		Person p1 = new Person("Gabriela", "Hacman ", "21-10-1998," , " F");
		Person p2 = new Person("Valerio", "Corallini ", "02-23-1999", " M");
		Person p3 = new Person("Pippo", "Disney ", null, " M");
		Person p4 = new Person("Pluto", "Disney ", null, " M");

		Console.print(p1);
		Console.print(p2);
		Console.print(p3);
		Console.print(p4);


	}

}
