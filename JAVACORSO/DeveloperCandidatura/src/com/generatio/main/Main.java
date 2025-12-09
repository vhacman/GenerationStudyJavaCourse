package com.generatio.main;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class Main {
    
    public static void main(String[] args)
    {
        String 	name;
        String 	surname;
        int 	birthYear;
        String 	province;
        String 	degree;
        String 	subject = "";
        int 	experienceYears;
        boolean availableToTravel;
        boolean knowsJava;
        
        
        Console.print("=== Benvenuto alla candidatura spontanea per programmatore informatico Back-End ===\n");
        //VALIDAZIONE AGE
        Console.print("Inserire anno di nascita: ");
        birthYear = Console.readInt();
        validateAge(birthYear);
        
        Console.print("Inserire nome: ");
        name = Console.readString();        
        Console.print("Inserire cognome: ");
        surname = Console.readString();        
        Console.print("Inserire provincia di residenza: ");
        province = Console.readString().toUpperCase();
        //VALIDAZIONE PROVINCIA
        if (!Application.isProvinceInLazio(province))
        {
            Console.print("Ci dispiace, la candidatura è riservata ai residenti nel Lazio.");
            return;
        }
        
        //VALIDAZIONE TITOLO DI STUDIO
        Console.print("Inserire titolo di studio (DIPLOMA/LAUREA): ");
        degree = Console.readString().toUpperCase();
        if (!degree.equals("DIPLOMA") && !degree.equals("LAUREA"))
        {
            Console.print("Titolo di studio non riconosciuto.");
            return;
        }
        if (degree.equals("LAUREA"))
        {
            Console.print("Inserire materia di laurea (Informatica/Ingegneria Informatica/Matematica/Fisica/Statistica/Logica): ");
            subject = Console.readString().toUpperCase();
        }
        
        //VALIDAZIONE ANNI DI ESPERIENZA
        Console.print("Inserire anni di esperienza come programmatore: ");
        experienceYears = Console.readInt();        
        //VALIDAZIONE TRAVEL
        Console.print("È disponibile a viaggiare? (SI/NO): ");
        String travel = Console.readString().toUpperCase();
        availableToTravel = travel.equals("SI");
        
        //JAVA EXPERIENZE
        Console.print("Conosce Java? (SI/NO): ");
        String java = Console.readString().toUpperCase();
        knowsJava = java.equals("SI");
        
        // Create Application object
        Application user = new Application(name, surname, birthYear, province, degree, subject, 
                             experienceYears, availableToTravel, knowsJava);        
        // Calculate score
        user.calculateScore();
        
        // Print application
        String message = user.getApplicationMessage();
        Console.print("\n" + message);
        
        // Save to file
        Console.print("Inserire numero candidatura: ");
        int candidateNumber = Console.readInt();
        
        String 	outputFileName;
        String 	modelloDiStampa = Template.load("print/template.html");
        String filename = "archive/" + candidateNumber + ".txt";
        FileWriter.writeTo(filename, message);
        Console.print("Candidatura salvata su " + filename);
        
		Console.print("Inserire nome del file in cui stampare l'etichetta: ");
        outputFileName = Console.readString() + ".html";
        
        String	yesTravel = availableToTravel ? "SI" : "NO";
        String	yesJava = knowsJava ? "SI" : "NO";
        modelloDiStampa = modelloDiStampa
                .replace("[name]", name)
                .replace("[surname]", surname)
                .replace("[birthYear]", String.valueOf(birthYear))
                .replace("[degree]", degree)
                .replace("[subject]", subject)
        		.replace("[availableToTravel]", yesTravel)
        		.replace("[yearsOfExperience]", String.valueOf(experienceYears))
        		.replace("[knowsJava]", yesJava);
        		
        FileWriter.writeTo("print/" + outputFileName, modelloDiStampa);
        
        //STAMPA MESSAGGIO DI CONFERMA CHE IL FILE È STATO SALVATO. 
        Console.print("L'etichetta è stata salvata nel file " + outputFileName + " nella cartella print");

    }
    
    private static boolean validateAge(int birthYear)
    {
        int age = 2025 - birthYear;
        if (age >= 18 && age <= 50)
        {
            return true;
        }
        else {
	        Console.print("Ci dispiace ma la candidatura è permessa solo tra i 18 e 50 anni.");
	        return false;
        }
    }
}