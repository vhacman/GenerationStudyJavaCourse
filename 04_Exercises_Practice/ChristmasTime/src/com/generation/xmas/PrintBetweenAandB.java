package com.generation.xmas;

import com.generation.library.Console;

public class PrintBetweenAandB {

	public static void main(String[] args) 
	{
		Console.print("Inserire A");
		int a = Console.readInt();
		Console.print("Inserire B");
		int b = Console.readInt();
		
		// i = a, i<=b, i=i+1
		for(int i=a;i<=b;i++)
			Console.print(i);
		
		/*
		 * 	riga			a			b				i		i<=b				output
		 *  10				7			-				-
		 *  12				7			11				-
		 *  15				7			11				7		7<=11 => true
		 *  16				7			11				7							7
		 *  15				7			11				8		8<=11 => true		
		 *  16				7			11				8							8
		 *  15				7			11				9		9<=11 => true			
		 *  16																		9
		 *  15											10		10<=11
		 *  16																		10
		 *  15											11		11<=11				
		 *  16																		11
		 *  15											12		12<=11=> falso, ESCO vado alla riga 17
		 */
	}

}
