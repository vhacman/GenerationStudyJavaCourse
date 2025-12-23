package com.generation.pcw.view;

import com.generation.pcw.util.Console;
import com.generation.pcw.util.FileWriter;
import com.generation.pcw.util.Template;
import com.generation.pcw.model.entities.PC;

/**
 * Classe di visualizzazione per la gestione dell'interfaccia utente del sistema PC.
 * Si occupa di mostrare menu e di renderizzare le informazioni di un PC
 * utilizzando template esterni.
 */
public class PcView
{
	// ==================== ATTRIBUTI ====================

	private String filename;

	// ==================== COSTRUTTORI ====================

	/**
	 * Costruttore che inizializza la view con il percorso del template file.
	 *
	 * @param filename Il percorso del file template da utilizzare per il rendering
	 */
	public PcView(String filename)
	{
		this.filename = filename;
	}

	// ==================== METODI STATICI ====================

	/**
	 * Mostra il menu principale del configuratore PC.
	 * Carica il template del menu da file e lo visualizza sulla console.
	 */
	public static void showMenu()
	{
		String menu = Template.load("template/menu.txt");
		if(menu == null || menu.isEmpty())
		{
			Console.print("Menu non trovato");
		}
		else
		{
			Console.print(menu);
		}
	}

	/**
	 * Metodo pubblico e statico che genera l'anteprima del PC configurato e la salva su file.
	 * Utilizza la classe PcView per renderizzare le informazioni del PC
	 * utilizzando un template e salva il risultato in un file di testo.
	 * Il file viene salvato nella directory "print" con un nome basato sul nome del PC.
	 *
	 * @param pc L'oggetto PC da visualizzare
	 */
	public static void printPreview(PC pc)
	{
		if(!pc.isValid())
		{
			Console.print("Errore: Assicurati di aver impostato tutti i componenti obbligatori.");
			return;
		}

		if(pc.getFinalPrice() == 0)
		{
			Console.print("ATTENZIONE: Il prezzo finale non è stato ancora calcolato (usa comando 8).");
			Console.print("Il file verrà generato comunque, ma il prezzo finale sarà 0.");
		}

		PcView view = new PcView("template/template.txt");
		String preview = view.render(pc);

		try
		{
			String filename = "print/" + pc.getName() + "_preview.txt";
			FileWriter writer = new FileWriter(filename);
			writer.print(preview);
			writer.close();
			Console.print("Anteprima salvata nel file: " + filename);
		}
		catch(Exception e)
		{
			Console.print("Errore durante il salvataggio del file: " + e.getMessage());
		}
	}
	
	
	
	/**
	 * Metodo pubblico e statico che genera l'anteprima HTML del PC configurato e la salva su file.
	 * Utilizza la classe PcView per renderizzare le informazioni del PC
	 * utilizzando un template HTML e salva il risultato in un file .html.
	 * Il file viene salvato nella directory "print" con un nome basato sul nome del PC.
	 *
	 * @param pc L'oggetto PC da visualizzare
	 */
	public static void printHTML(PC pc)
	{
		if(!pc.isValid())
		{
			Console.print("Errore: Assicurati di aver impostato tutti i componenti obbligatori.");
			return;
		}

		if(pc.getFinalPrice() == 0)
		{
			Console.print("ATTENZIONE: Il prezzo finale non è stato ancora calcolato (usa comando 8).");
			Console.print("Il file HTML verrà generato comunque, ma il prezzo finale sarà 0.");
		}

		PcView view = new PcView("template/template.html");
		String htmlPreview = view.render(pc);

		try
		{
			String filename = "print/" + pc.getName() + "_preview.html";
			FileWriter writer = new FileWriter(filename);
			writer.print(htmlPreview);
			writer.close();
			Console.print("Anteprima HTML salvata nel file: " + filename);
		}
		catch(Exception e)
		{
			Console.print("Errore durante il salvataggio del file HTML: " + e.getMessage());
		}
	}
	

	// ==================== METODI DI ISTANZA ====================

	/**
	 * Renderizza i dati del PC utilizzando il template configurato.
	 * Sostituisce i placeholder nel template con i valori effettivi dei componenti del PC.
	 *
	 * @param pc L'oggetto PC da renderizzare
	 * @return La stringa con il template compilato
	 */
	public String render(PC pc)
	{
		return Template
				.load(filename)
				.replace("[name]", pc.getName())
				.replace("[clientName]", pc.getClientName() != null ? pc.getClientName() : "")
				.replace("[clientSurname]", pc.getClientSurname() != null ? pc.getClientSurname() : "")
				.replace("[clientPhone]", pc.getClientPhone() != null ? pc.getClientPhone() : "")
				.replace("[estimateNb]", pc.getEstimateNb() != null ? pc.getEstimateNb() : "")
				.replace("[processor]", pc.getProcessor().toString())
				.replace("[processorBenchmark]", String.valueOf(pc.getProcessor().getBenchmark()))
				.replace("[processorPrice]", String.valueOf(pc.getProcessor().getPrice()))
				.replace("[processorPlatform]", pc.getProcessor().getPlatform())
				.replace("[gpu]", pc.getGpu().toString())
				.replace("[gpuBenchmark]", String.valueOf(pc.getGpu().getBenchmark()))
				.replace("[gpuPrice]", String.valueOf(pc.getGpu().getPrice()))
				.replace("[gpuBrand]", pc.getGpu().getBrand())
				.replace("[ram]", pc.getRam().toString())
				.replace("[ramSize]", String.valueOf(pc.getRam().getSize()))
				.replace("[ramSpeed]", String.valueOf(pc.getRam().getSpeed()))
				.replace("[ramPrice]", String.valueOf(pc.getRam().getPrice()))
				.replace("[ramBrand]", pc.getRam().getBrand())
				.replace("[storage]", pc.getStorage().toString())
				.replace("[storageSize]", String.valueOf(pc.getStorage().getSize()))
				.replace("[storageBenchmark]", String.valueOf(pc.getStorage().getBenchmark()))
				.replace("[storagePrice]", String.valueOf(pc.getStorage().getPrice()))
				.replace("[storageBrand]", pc.getStorage().getBrand())
				.replace("[extraStorage]", (pc.getExtraStorage() != null ? pc.getExtraStorage().toString() : "Nessuno"))
				.replace("[totalBenchmark]", String.valueOf(pc.getTotalBenchmark()))
				.replace("[materialCost]", String.valueOf(pc.getMaterialCost()))
				.replace("[labourCost]", String.valueOf(pc.getLabourCost()))
				.replace("[finalPrice]", String.valueOf(pc.getFinalPrice()));
	}
}