package com.generation.lcp.main;

import java.time.LocalDate;

import com.generation.library.Console;
import com.generation.library.FileWriter;

public class TouristGuideApplication
{

	public static void main(String[] args)
	{
		int		birthYear;
		int		age;
		int		score = 0;
		String	province;
		String	degree;
		String	subject = "";
		String	name, surname;
		String	message;


		Console.print("Benvenuto alla candidatura spontanea per guida turistica Lake Como ProLoco" );
		Console.print("Inserire anno di nascita: ");
		birthYear = Console.readInt();
		age = LocalDate.now().getYear() - birthYear;

		if (age < 18 || age > 50)
			Console.print("Ci dispiace ma la candidatura è permessa solo tra i 18 e 50 anni ");
		else
		{
			Console.print("Inserire nome: ");
			name = Console.readString();
			Console.print("Inserire cognome: ");
			surname = Console.readString();

			Console.print("Inserire provincia di residenza: ");
			province = Console.readString();
			switch (province)
			{
				case "CO", "LC":
					score += 30;
					break;
				case "VA", "BG", "MB":
					score += 20;
					break;
				case "MI", "MN", "BR", "SO":
					score += 10;
					break;
			}

			Console.print("inserire titolo di studio ");
			degree = Console.readString();

			switch (degree)
			{
				case "HS":
					score += 20;
					break;
				case "COLLEGE":
					score += 30;
					break;
			}
			
			if (degree.equalsIgnoreCase("HS") || degree.equalsIgnoreCase("COLLEGE"))
			{
				Console.print("Inserire la materia di studio" );
				subject = Console.readString();

				if (subject.equalsIgnoreCase("TOURISM"))
					score += 20;
			}
			
			message = "Candidato/a " + name + " " + surname + "\n" +
						"Nato/a " + birthYear + "(" + age + " anni)\n" +
						"Provincia: " + province + "\n" +
						"Titolo di studio: " + degree +
						((degree.equalsIgnoreCase("HS") || degree.equalsIgnoreCase("COLLEGE")) ? "\nMateria: " + subject : "") + "\n" + 
						"Data della candidatura: " + LocalDate.now() + "\n" + 
						"\nPunteggio finale: " + score;

			Console.print(message);
			Console.print("Inserire numero candidatura ");
			int n = Console.readInt();

			String filename = "archive/" + n + ".txt";
			FileWriter.writeTo(filename, message);
			Console.print("Candidatura salvata su " + filename);

		}
	}
}