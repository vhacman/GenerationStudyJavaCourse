package com.generation.xmas;

import com.generation.library.Console;

public class PrintDueADueFraAeB 
{

	public static void main(String[] args) 
	{
		Console.print("Inserire A");
		int a = Console.readInt();
		Console.print("Inserire B");
		int b = Console.readInt();
		
		// i = a, i<=b, i=i+1
		for(int i=a;i<=b;i+=2)
			Console.print(i);
		
	}

}
