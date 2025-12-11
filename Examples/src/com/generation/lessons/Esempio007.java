package com.generation.lessons;

import com.generation.library.Console;

public class Esempio007
{

	public static void main(String[] args)
	{

		int sum = 0;
		int a, b;
		do
		{
			Console.print("Inserire a");
			a = Console.readInt();
			Console.print("Inserire b");
			b = Console.readInt();
			sum+=(a*b);			// sum = sum + (a*b)
		} while (a + b < 0 && sum < 1000);
		Console.print(sum);
	}

	/*
	 * i miei input sono
	 * a = 5 b = 4
	 * a = 3 b = 2
	 * a = 7 b = 15
	 *
	 * riga		a		b		sum				a+b<20 && sum<1000
	 * 1*giro
	 * 12		-		-		0					-
	 * 16		5		0		0					-
	 * 18		5		4		0					-
	 * 19		5		4		0+(5*4) = 20		
	 * 20		5		4		20				(5+4<20 && 20<1000) = (9<20 && 20<1000) = (true && true) = true
	 * 
	 * 2*giro
	 * 16		3		4		20
	 * 18		3		2		20
	 * 19		3		2		20+(3*2) = 26
	 * 20		3		2		26				(3+2<20 && 26<1000) = (5<20 && 26<1000) = (true && true) = true
	 * 
	 * 3*giro
	 * 16		7		2		26
	 * 18		7		15		26
	 * 19		7		15		26+(7*15) = 131
	 * 20		7		15		131				(7+15<20 && 131<1000) = (22<20 && 131<1000) = (false && true) = false
	 * 21		7		15		131
	 *
	 */


}