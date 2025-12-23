package com.generation.pcw.model.entities;

/**
 * Enumerazione che rappresenta i diversi livelli di configurazione PC pre-definiti.
 * Ogni tier definisce una combinazione specifica di componenti ottimizzata per
 * diverse fasce di prezzo e utilizzo.
 *
 * I tier disponibili sono:
 * - ECONOMY: Configurazione entry-level per uso base (ufficio, navigazione)
 * - STANDARD: Configurazione bilanciata per uso quotidiano e multitasking
 * - DELUXE: Configurazione high-end per gaming, editing e applicazioni professionali
 */
public enum PcTier
{
	ECONOMY("PC Economy", Processor.M1, Gpu.GPU6750, Ram.RAM8GB, Storage.SSDBASIC),
	STANDARD("PC Standard", Processor.I515, Gpu.GPU4070, Ram.RAM16GB, Storage.SSDLARGE),
	DELUXE("PC Deluxe", Processor.I714, Gpu.GPUS4070S, Ram.RAM32GB, Storage.SSDSCAMOSCIUS);

	private final String name;
	private final Processor processor;
	private final Gpu gpu;
	private final Ram ram;
	private final Storage storage;

	/**
	 * Costruttore privato dell'enum.
	 * Inizializza la configurazione con i componenti specificati.
	 *
	 * @param name      Nome descrittivo della configurazione
	 * @param processor Processore da installare
	 * @param gpu       GPU da installare
	 * @param ram       RAM da installare
	 * @param storage   Storage da installare
	 */
	private PcTier(String name, Processor processor, Gpu gpu, Ram ram, Storage storage)
	{
		this.name = name;
		this.processor = processor;
		this.gpu = gpu;
		this.ram = ram;
		this.storage = storage;
	}

	/**
	 * Restituisce il nome descrittivo della configurazione.
	 *
	 * @return Il nome della configurazione
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Restituisce il processore della configurazione.
	 *
	 * @return Il processore
	 */
	public Processor getProcessor()
	{
		return processor;
	}

	/**
	 * Restituisce la GPU della configurazione.
	 *
	 * @return La GPU
	 */
	public Gpu getGpu()
	{
		return gpu;
	}

	/**
	 * Restituisce la RAM della configurazione.
	 *
	 * @return La RAM
	 */
	public Ram getRam()
	{
		return ram;
	}

	/**
	 * Restituisce lo storage della configurazione.
	 *
	 * @return Lo storage
	 */
	public Storage getStorage()
	{
		return storage;
	}

	/**
	 * Calcola e restituisce il costo totale della configurazione.
	 *
	 * @return Il costo totale dei componenti
	 */
	public int getTotalCost()
	{
		return processor.getPrice() + gpu.getPrice() + ram.getPrice() + storage.getPrice();
	}

	/**
	 * Restituisce una descrizione dettagliata della configurazione.
	 *
	 * @return Stringa con tutti i dettagli della configurazione
	 */
	public String getDescription()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("=== ").append(name.toUpperCase()).append(" ===\n");
		sb.append("Processore: ").append(processor).append(" (").append(processor.getPlatform()).append(")\n");
		sb.append("GPU:        ").append(gpu).append(" (").append(gpu.getBrand()).append(")\n");
		sb.append("RAM:        ").append(ram).append(" (").append(ram.getSize()).append(" GB)\n");
		sb.append("Storage:    ").append(storage).append(" (").append(storage.getSize()).append(" GB)\n");
		sb.append("COSTO TOTALE: €").append(getTotalCost());
		return sb.toString();
	}
}
