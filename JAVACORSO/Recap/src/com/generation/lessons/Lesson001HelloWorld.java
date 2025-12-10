package com.generation.lessons;

// "siamo vivi per usarci"
// in Java le classi si usano a vicenda. la classe Lesson001HelloWorld ha bisogno di usare Console
// per essere precisi di usare i sottoprogrammi (metodi) di Console
import com.generation.library.Console;

public class Lesson001HelloWorld 
{

	// il main è il SOTTOPROGRAMMA di avvio. Da qui può partire l'esecuzione delle istruzioni.
	public static void main(String[] args) 
	{
	
		// Console => prendi la CLASSE Console
		// Console.print => della CLASSE Console prendi il metodo print
		// Console.print("Hello world"); // quello che c'è fra parentesi è il PARAMETRO che invio al metodo print
		// quello che c'è fra parentesi è l'INPUT del metodo print
		// CHIAMATA al metodo Console.print
		// "Hello world" è un LITERAL di tipo String
		Console.print("Hello World");
		// input: Hello World output: NESSUNO
		
		
	}

}