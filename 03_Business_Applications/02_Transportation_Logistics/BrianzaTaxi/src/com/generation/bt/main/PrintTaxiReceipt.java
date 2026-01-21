package com.generation.bt.main;

import com.generation.bt.utility.TaxiUtility;
import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;

public class PrintTaxiReceipt
{
    public static void main(String[] args)
    {
    	//INPUT
        int	minutes = TaxiUtility.askMinutes();
        int	level = TaxiUtility.askLevel();
        int hour = TaxiUtility.askHour();
        int minutesStart = TaxiUtility.askMinutesStart();
        int hourArrival = TaxiUtility.askHourArrival();
        int minutesArrival = TaxiUtility.askMinutesArrival();
        String outputFileName;

        // ELABORAZIONE
        double price = TaxiUtility.calculatePrice(minutes, level, hour);

        TaxiUtility.printTicket(price);
        String modelloDiStampa = Template.load("print/template.html");

        Console.print("Inserire nome del file in cui stampare l'etichetta: ");
        outputFileName = Console.readString() + ".html";

        //SOSTITUZIONE DEI PLACEHOLDER NEL TEMPLATE
        modelloDiStampa = modelloDiStampa
                .replace("[minutes]", String.valueOf(minutes))
                .replace("[level]", String.valueOf(level))
                .replace("[hour]", String.format("%02d", hour))
                .replace("[minutesStart]", String.format("%02d", minutesStart))
                .replace("[hourArrival]", String.format("%02d", hourArrival))
                .replace("[minutesArrival]", String.format("%02d", minutesArrival))
                .replace("[price]", String.format("%.2f", price));

        //SALVATAGGIO FILE
        FileWriter.writeTo("print/" + outputFileName, modelloDiStampa);

        //STAMPA MESSAGGIO DI CONFERMA CHE IL FILE È STATO SALVATO. 
        Console.print("L'etichetta è stata salvata nel file " + outputFileName + " nella cartella print");
    }
}