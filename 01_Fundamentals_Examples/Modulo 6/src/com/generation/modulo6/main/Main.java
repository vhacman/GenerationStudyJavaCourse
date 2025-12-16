package com.generation.modulo6.main;

import com.generation.library.Console;

public class Main
{

	public static void main(String[] args)
	{
		Person p1 = new Person();
		
		p1.name = "George";
		p1.surname = "Romano";
		p1.dateOfBirth = "1950-01-01";
		p1.gender = "Male";
		
		
		
		Person p2 = new Person();
		p2.name = "Jane";
		p2.surname = "Doe";
		p2.dateOfBirth = "1960-02-01";
		p2.gender = "Female";
		
		Console.print(p1.toString());
		Console.print(p2.toString());
	}

}
