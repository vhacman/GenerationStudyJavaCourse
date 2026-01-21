package com.generation.rs.main;

import com.generation.library.Console;
import com.generation.library.FileWriter;
import com.generation.library.Template;
import com.generation.rs.model.entities.Repair;
import com.generation.rs.view.RepairView;

public class Main
{
	public static void main(String[] args)
	{
		boolean goOn = true;
		Repair r = new Repair();

		String menu = Template.load("template/menu.txt");
		do
		{
			Console.print(menu);
			String choice = Console.readStringBetween("Inserisci scelta: ", "Scelta non valida ", "1", "2", "3");

			switch (choice)
			{
			case "1":
				askRepair(r);
			break;
			case "2":
				printRepairList(r);
			break;
			case "3":
				goOn = exitRepairMenu(r);
			break;
			}
		} while (goOn);
	}

	private static boolean exitRepairMenu(Repair r)
	{
		Console.print("\n--- USCITA DAL PROGRAMMA ---");
		Console.print("Totale riparazioni effettuate: " + r.totalRepairsDone);
		Console.print("Arrivederci!");
		return false;
	}

	private static void printRepairList(Repair r) {
		Console.print("\n--- LISTA RIPARAZIONI ---");
		Console.print("Numero riparazione: " + r.id + " cliente: " + r.client );
		Console.print("\n-------------------------");
		Console.print("Totale riparazioni effettuate: " + r.totalRepairsDone);
	}

	private static void askRepair(Repair r)
	{
		Console.print("\n--- NUOVA RIPARAZIONE ---");
		Console.print("Inserire id: ");
		r.id = Console.readString();
		Console.print("Inserire nome cliente: ");
		r.client = Console.readString();
		Console.print("Inserire numero telefono cliente: ");
		r.phone = Console.readString();
		Console.print("Inserire problema da risolvere del cliente ");
		r.fix = Console.readString();
		Console.print("Inserire costo per i materiali in €");
		r.materialPartsCost = Console.readDouble();
		Console.print("Inserire ore di lavoro svolte");
		r.hour = Console.readDouble();

		if (r.isValid())
		{
			printPreviewTXT(r);
			printClientViewHTML(r);
		}
		else
			Console.print("\n✗ ERRORE: Dati non validi. Riparazione non salvata.");
	}

	private static void printClientViewHTML(Repair r)
	{
		RepairView clientView = new RepairView("template/clientView.html");
		String filename = "template/repair_" + r.id + ".html";
		FileWriter.writeTo(filename,  clientView.render(r));
		Console.print("\n✓ File HTML generato con successo: " + filename);
		Console.print("✓ Riparazione salvata!");
	}

	private static void printPreviewTXT(Repair r)
	{
		RepairView viewPreview = new RepairView("template/repairPreView.txt");
		Console.print("\n--- PREVIEW RIPARAZIONE ---");
		Console.print(viewPreview.render(r));
		Console.print("\nInserisci il prezzo finale da praticare (€): ");
	}
}