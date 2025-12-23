package com.generation.pcw.controller;

import com.generation.pcw.util.Console;
import com.generation.pcw.view.PcView;
import com.generation.pcw.model.entities.*;

/**
 * Classe wizard per guidare l'utente nella configurazione completa di un PC.
 * Utilizza un menu interattivo che permette all'utente di:
 * 1 - Impostare il processore
 * 2 - Impostare lo storage primario
 * 3 - Impostare lo storage extra
 * 4 - Impostare la GPU
 * 5 - Impostare la RAM
 * 6 - Impostare il nome del PC
 * 7 - Vedere anteprima del PC configurato
 * Q - Uscire dal wizard
 *
 * Il programma continua a richiedere comandi finché l'utente non digita 'Q'.
 */
public class PcWizard
{
	// ==================== METODI DI INSERIMENTO DATI ====================

	/**
	 * Metodo privato e statico che permette all'utente di inserire i dati del cliente.
	 * Richiede in sequenza: nome, cognome, numero di telefono e numero preventivo.
	 * Tutti i dati vengono impostati nell'oggetto PC passato come parametro.
	 *
	 * @param pc L'oggetto PC in cui salvare i dati del cliente
	 */
	private static void insertClientData(PC pc)
	{
		Console.print("=== INSERIMENTO DATI CLIENTE ===");
		Console.print("Inserire nome del cliente: ");
		pc.setClientName(Console.readString());

		Console.print("Inserire cognome del cliente: ");
		pc.setClientSurname(Console.readString());

		Console.print("Inserire numero di telefono: ");
		pc.setClientPhone(Console.readString());

		Console.print("Inserire numero preventivo: ");
		pc.setEstimateNb(Console.readString());

		Console.print("Dati salvati con successo");
	}

	/**
	 * Metodo privato e statico che permette all'utente di inserire il costo della manodopera.
	 * Richiede l'inserimento di un valore numerico che viene salvato nell'oggetto PC.
	 * In caso di errore di formato, viene mostrato un messaggio di errore.
	 *
	 * @param pc L'oggetto PC in cui salvare il costo della manodopera
	 */
	private static void insertLabourCost(PC pc)
	{
		Console.print("=== INSERIMENTO COSTO MANODOPERA ===");
		Console.print("Inserire costo manodopera (€):");

		try
		{
			int labourCost = Console.readInt();
			if(labourCost < 0)
				Console.print("ERRORE: il costo non può essere negativo");
			else
			{
				pc.setLabourCost(labourCost);
				Console.print("Costo manodopera salvato: €" + labourCost);
				if (pc.isValid())
					Console.print("Costo componenti: €" + PC.calculateMaterialCost(pc));
				else
					Console.print("Configura tutti i componenti per vedere il costo totale.");
			}
		}
		catch (Exception e)
		{
			Console.print("ERRORE: Inserire un numero valido");
		}
	}

	// ==================== METODI DI SELEZIONE COMPONENTI ====================

	/**
	 * Metodo privato e statico che permette all'utente di selezionare un processore.
	 * Mostra le opzioni disponibili e le informazioni dettagliate sul processore inserito
	 * (benchmark, prezzo e piattaforma) e chiede conferma prima di procedere.
	 * Se l'utente digita 'S', il processore viene confermato e restituito.
	 * Altrimenti il metodo richiede nuovamente l'inserimento.
	 * Il ciclo continua finché non viene selezionato un processore valido e confermato.
	 *
	 * @return Il processore selezionato e confermato dall'utente
	 */
	private static Processor selectProcessor()
	{
		Processor res = null;
		do
		{
			try
			{
				Console.print("Processori disponibili: M1, M4, I714, I515");
				Console.print("Inserire processore");
				res = Processor.valueOf(Console.readString());
				Console.print("Riepilogo processore: BENCHMARK: " + res.getBenchmark() +
													" PRICE: €"+ res.getPrice() +
													" PIATTAFORMA: " + res.getPlatform());
				Console.print("Confermare la scelta?");
				if(!Console.readString().equalsIgnoreCase("S"))
				{
					res = null;
				}
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res == null);

		return res;
	}

	/**
	 * Metodo privato e statico che permette all'utente di selezionare una GPU.
	 * Mostra le opzioni disponibili e le informazioni dettagliate sulla GPU inserita
	 * (benchmark, prezzo e marca) e chiede conferma prima di procedere.
	 * Se l'utente digita 'S', la GPU viene confermata e restituita.
	 * Altrimenti il metodo richiede nuovamente l'inserimento.
	 * Il ciclo continua finché non viene selezionata una GPU valida e confermata.
	 *
	 * @return La GPU selezionata e confermata dall'utente
	 */
	private static Gpu selectGpu()
	{
		Gpu res = null;
		do
		{
			try
			{
				Console.print("GPU disponibili: GPU6750, GPU4060, GPU4070, GPUS4070S");
				Console.print("Inserire GPU");
				res = Gpu.valueOf(Console.readString());
				Console.print("Riepilogo GPU: BENCHMARK " + res.getBenchmark() +
											" € PRICE " + res.getPrice() +
											" BRAND " + res.getBrand());
				Console.print("Confermare la scelta?");
				if(!Console.readString().equalsIgnoreCase("S"))
				{
					res = null;
				}
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res == null);

		return res;
	}

	/**
	 * Metodo privato e statico che permette all'utente di selezionare una RAM.
	 * Mostra le opzioni disponibili e le informazioni dettagliate sulla RAM inserita
	 * (capacità, velocità, prezzo e marca) e chiede conferma prima di procedere.
	 * Se l'utente digita 'S', la RAM viene confermata e restituita.
	 * Altrimenti il metodo richiede nuovamente l'inserimento.
	 * Il ciclo continua finché non viene selezionata una RAM valida e confermata.
	 *
	 * @return La RAM selezionata e confermata dall'utente
	 */
	private static Ram selectRam()
	{
		Ram res = null;
		do
		{
			try
			{
				Console.print("RAM disponibili: RAM8GB, RAM16GB, RAM32GB, RAM64GB");
				Console.print("Inserire RAM");
				res = Ram.valueOf(Console.readString());
				Console.print("Riepilogo RAM: SIZE " + res.getSize() +
										" MHz SPEED: " + res.getSpeed() +
										" € PRICE: " + res.getPrice() +
										" BRAND: " + res.getBrand());
				Console.print("Confermare la scelta?");
				if(!Console.readString().equalsIgnoreCase("S"))
				{
					res = null;
				}
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res == null);

		return res;
	}

	/**
	 * Metodo privato e statico che permette all'utente di selezionare uno storage.
	 * Mostra le opzioni disponibili e le informazioni dettagliate sullo storage inserito
	 * (dimensione, benchmark, prezzo e marca) e chiede conferma prima di procedere.
	 * Se l'utente digita 'S', lo storage viene confermato e restituito.
	 * Altrimenti il metodo richiede nuovamente l'inserimento.
	 * Il ciclo continua finché non viene selezionato uno storage valido e confermato.
	 *
	 * @return Lo storage selezionato e confermato dall'utente
	 */
	private static Storage selectStorage()
	{
		Storage res = null;
		do
		{
			try
			{
				Console.print("Storage disponibili: SSDBASIC, SSDLARGE, SSDSCAMOSCIUS");
				Console.print("Inserire storage");
				res = Storage.valueOf(Console.readString());
				Console.print("Riepilogo storage: SIZE " + res.getSize() +
												"GB BENCHMARK " + res.getBenchmark() +
												" € PRICE " + res.getPrice() +
												" BRAND " + res.getBrand());
				Console.print("Confermare la scelta?");
				if(!Console.readString().equalsIgnoreCase("S"))
				{
					res = null;
				}
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res == null);

		return res;
	}

	// ==================== METODI DI CONFIGURAZIONE PRE-BUILD ====================

	/**
	 * Metodo privato e statico che permette all'utente di selezionare una configurazione pre-build.
	 * Mostra le tre configurazioni disponibili (ECONOMY, STANDARD, DELUXE) con i dettagli
	 * e permette all'utente di scegliere quale applicare al PC corrente.
	 *
	 * @return Un PC configurato secondo il tier selezionato, o null se l'operazione è annullata
	 */
	private static PC selectPrebuiltConfiguration()
	{
		Console.print("\n╔════════════════════════════════════════════════════════════════╗");
		Console.print("║         CONFIGURAZIONI PRE-BUILD DISPONIBILI                   ║");
		Console.print("╚════════════════════════════════════════════════════════════════╝\n");

		// Mostra tutte le configurazioni disponibili
		for (PcTier tier : PcTier.values())
		{
			Console.print(tier.getDescription());
			Console.print("");
		}

		Console.print("Scegli una configurazione (ECONOMY/STANDARD/DELUXE) o 'Q' per annullare:");

		PcTier selectedTier = null;
		do
		{
			try
			{
				String choice = Console.readString().toUpperCase();

				if (choice.equals("Q"))
				{
					Console.print("Operazione annullata.");
					return null;
				}

				selectedTier = PcTier.valueOf(choice);
				Console.print("\n✓ Configurazione " + selectedTier.getName() + " selezionata!");
				Console.print("Vuoi confermare? (S/N)");

				if (!Console.readString().equalsIgnoreCase("S"))
				{
					selectedTier = null;
					Console.print("Scegli una configurazione (ECONOMY/STANDARD/DELUXE) o 'Q' per annullare:");
				}
			}
			catch (Exception e)
			{
				Console.print("Valore non valido. Scegli: ECONOMY, STANDARD o DELUXE");
			}
		} while (selectedTier == null);

		return PC.createFromTier(selectedTier);
	}

	// ==================== METODI DI CALCOLO PREZZI ====================

	/**
	 * Metodo privato e statico che calcola e imposta il prezzo finale del preventivo.
	 * Mostra il costo dei materiali e della manodopera, poi richiede all'utente
	 * di inserire il prezzo finale. Valida che il prezzo finale non sia inferiore
	 * al costo dei materiali.
	 *
	 * @param pc L'oggetto PC per cui calcolare il prezzo finale
	 */
	private static void calculateFinalPrice(PC pc)
	{
		if (!pc.isValid())
		{
			Console.print("ERRORE: Configurare prima tutti i componenti!");
			return;
		}

		int materialCost = PC.calculateMaterialCost(pc);
		pc.setMaterialCost(materialCost);

		Console.print("Costo materiali: €" + materialCost);
		Console.print("Costo manodopera: €" + pc.getLabourCost());
		Console.print("Inserire prezzo finale: ");
		try
		{
			int finalPrice = Console.readInt();
			if (finalPrice < materialCost + pc.getLabourCost())
				Console.print("ERRORE: Il prezzo non può essere sotto il costo totale!");
			else
			{
				pc.setFinalPrice(finalPrice);
				Console.print("Prezzo finale salvato: €" + finalPrice);
			}
		}
		catch (Exception e)
		{
			Console.print("ERRORE: inserire un numero valido.");
		}
	}

	// ==================== MAIN ====================
	public static void main(String[] args)
	{
	    PC pc = new PC();
	    Console.print("Benvenuto alla configurazione del tuo PC");
	    String cmd;

	    do
	    {
	        PcView.showMenu();
	        cmd = Console.readString();
	        switch(cmd)
	        {
	            case "0":
	                insertClientData(pc);
	                break;
	            case "1":
	                pc.setProcessor(selectProcessor());
	                break;
	            case "2":
	                pc.setStorage(selectStorage());
	                break;
	            case "3":
	                pc.setExtraStorage(selectStorage());
	                break;
	            case "4":
	                pc.setGpu(selectGpu());
	                break;
	            case "5":
	                pc.setRam(selectRam());
	                break;
	            case "6":
	                Console.print("Inserire nome pc");
	                pc.setName(Console.readString());
	                break;
	            case "7":
	                insertLabourCost(pc);
	                break;
	            case "8":
	                calculateFinalPrice(pc);
	                break;
	            case "9":
	                PcView.printPreview(pc);
	                break;
	            case "10":
	                PcView.printHTML(pc);
	                break;
	            case "11":
	                PC prebuiltPc = selectPrebuiltConfiguration();
	                if (prebuiltPc != null)
	                {
	                    pc = prebuiltPc;
	                    Console.print("✓ Configurazione applicata con successo!");
	                    Console.print("Puoi modificare singoli componenti se necessario.");
	                }
	                break;
	            default:
	                Console.print("Grazie per aver utilizzato il configuratore PC. Arrivederci!");
	                break;
	        }

	    } while(!cmd.equalsIgnoreCase("Q"));
	}}