package com.generation.lessons;
import java.time.LocalDate;
import com.generation.library.Console;

public class Lesson002PrimeEspressioni 
{

	public static void main(String[] args) 
	{
	
		String fullName = "Ferdinando" + " " + "Primerano";
							// espressione di tipo stringa, diventerà "Ferdinando Primerano" e il valore verrà salvato in fullName
		
		Console.print(fullName);
		
		// int					int
		int age = LocalDate.now().getYear() - 1980;
		/*		  classe
		 * 					data
		 * 						  int
		 *        ------------------------
		 * 					2025			- 1980	= 45
		 * 
		 * 
		 * 
		 */
		
		boolean working = age >=18	&& age <=70;
		/*
		 * 				  45 >= 18	&& 45 <= 70
		 * 				    true	&& true
		 * 						true
		 * 
		 */
		
		
		String origin = "south";
		
		String profession = "teacher";
		
		String hobby = "videogames";
		
		
		//										 A										B				C
		boolean r = (profession.equals("teacher") || profession.equals("soldier")) && age>=18 && !origin.equals("north");
		/*
		 * A= (profession.equals("teacher") || profession.equals("soldier")) 
		 *    ("teacher".equals("teacher")  || "teacher".equals("soldier"))
		 *    (          true				||			false			   )
		 * 	 						       true
		 * 
		 * 
		 * B = age >= 18
		 *     45  >= 18
		 * 		  true
		 * 
		 * C = !origin.equals("north")
		 *     !("south".equals("north"))
		 * 	   !(false)
		 * 	   true
		 * 
		 * A && B && C = true && true && true => true
		 * 
		 */
		
		Console.print(r);
		//						  A					B								C													D
		boolean fidanzatoIdeale = age>=30 && !origin.equals("Marte") && !profession.equals("programmer") && !(hobby.equals("videogames") || hobby.equals("fishing"));
		
		//	A = age >= 30 => 45 >= 30 => true
		//  B = !origin.equals("Marte") => !(origin.equals("Marte")) => !(false) => true
		//  C = !profession.equals("programmer") => !("teacher".equals("programmer")) => !(false) => true
		//  D !("videogames.equals("videogames") || "videogames".equals("fishing")) => !(T || F) => !(T) => F
		//																   (cond	 ? valore se vero : valore se falso)
		Console.print(fullName 						+ " " 	+ age 	+ " "+ (age >=18 ? "Maggiorenne" : "Minorenne"));
		/*			 "Ferdinando Primerano"
		 *			 "Ferdinando Primerano " 
		 * 			 "Ferdinando Primerano 45"
		 * 			 "Ferdinando Primerano 45 "
		 * 			 "Ferdinando Primerano 45 Maggiorenne"						=> String
		 * 
		 * 
		 */
		
	}

}