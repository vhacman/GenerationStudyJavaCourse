package com.generation.lessons;

import com.generation.library.Console;

public class Lesson003EsempiSelezioni 
{

	public static void main(String[] args) 
	{
		// variabile score, tipo int, valore 0
		int score = 0;
		
		// Console.print riceve in input "Quanti anni hai?" e non produce niente
		Console.print("Quanti anni hai?");
		//        classe Console, metodo readInt()
		//		  readInt non ha PARAMETRI, fra le parentesi non c'è niente
		//		  readInt non RICEVE input, ma readInt PRODUCE un intero
		// 		  readInt non ha INPUT e ha come OUTPUT un intero
		//        e quell'intero lo metto in age
		int age = Console.readInt();
		
		if(age>40)
		{
			score-=10; // score = score - 10;
		}
		// 45>=30  && 45<=35 => T && F => F
		if(age>=30 && age<=35)
		{
			score+=5;
		}
		// score è l'input di console.print
		
		Console.print("Vuoi impegnarti?");
		String engagement = Console.readString();
		
		if(engagement.equals("S"))
			score+=20;
		else
			score-=10;
		
		Console.print("Inserire hobby");
		String hobby = Console.readString();
		
		
		switch(hobby)
		{
			case "videogame":
				score-=10;
			break;
			case "book":
				score+=10;
			break;							// salta fuori dallo switch, quindi 58
			case "fishing", "hunting":
				score-=10;
			break;
		}		
		Console.print(score);
		
		
	}

}