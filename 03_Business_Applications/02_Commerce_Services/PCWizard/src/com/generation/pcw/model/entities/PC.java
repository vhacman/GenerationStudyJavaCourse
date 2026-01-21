package com.generation.pcw.model.entities;

/**
 * Classe che rappresenta un Personal Computer nel sistema.
 * Un PC è composto da sei componenti principali:
 * - name: il nome identificativo del PC
 * - processor: il processore installato (tipo Processor)
 * - gpu: la scheda grafica installata (tipo Gpu)
 * - ram: la memoria RAM installata (tipo Ram)
 * - storage: l'unità di archiviazione primaria installata (tipo Storage)
 * - extraStorage: l'unità di archiviazione secondaria opzionale (tipo Storage)
 *
 * La classe permette di calcolare il prezzo totale del PC
 * sommando i prezzi dei singoli componenti.
 */
public class PC
{
	// Attributi PC
	private String name;
	private Processor processor;
	private Gpu gpu;
	private Ram ram;
	private Storage storage;
	private Storage extraStorage;
	private int totalBenchmark;
	
	// Attributi Cliente
	private String 	clientName;
	private String 	clientSurname;
	private String 	clientPhone;
	private String 	estimateNb;
	private int		materialCost;
	private int		finalPrice;
	private int		labourCost;

	// Costruttori
	
	/**
	 * Costruttore vuoto.
	 * Crea un'istanza di PC senza componenti configurati.
	 */
	public PC() {}

	/**
	 * Costruttore completo.
	 * Crea un'istanza di PC con tutti i componenti specificati.
	 *
	 * @param name         Il nome del PC
	 * @param processor    Il processore da installare
	 * @param gpu          La GPU da installare
	 * @param ram          La RAM da installare
	 * @param storage      Lo storage primario da installare
	 * @param extraStorage Lo storage secondario opzionale
	 * @param totalBenchmark Il benchmark totale del PC
	 */
	public PC(String name, Processor processor, Gpu gpu, Ram ram, Storage storage, Storage extraStorage, int totalBenchmark)
	{
		this.name = name;
		this.processor = processor;
		this.gpu = gpu;
		this.ram = ram;
		this.storage = storage;
		this.extraStorage = extraStorage;
		this.totalBenchmark = totalBenchmark;
	}

	/**
	 * Calcola e restituisce il benchmark totale del PC.
	 * Il benchmark totale è dato dalla somma dei benchmark di tutti i componenti:
	 * processore, GPU, velocità RAM e storage primario.
	 * Lo storage extra non contribuisce al benchmark totale.
	 *
	 * @return Il benchmark totale del PC
	 */
	public int getTotalBenchmark()
	{
		this.totalBenchmark = processor.getBenchmark() + gpu.BENCHMARK + ram.getSpeed() + storage.getBenchmark();
		return totalBenchmark;
	}
	
	/**
	 * Calcola il costo totale dei materiali del PC.
	 * Somma i prezzi di tutti i componenti installati (processore, GPU, RAM, storage primario).
	 * Se è presente uno storage extra, il suo prezzo viene aggiunto al totale.
	 *
	 * @param pc L'oggetto PC di cui calcolare il costo materiali
	 * @return Il costo totale dei materiali
	 */
	public static int calculateMaterialCost(PC pc)
	{
		int cost = pc.getProcessor().getPrice() + pc.getGpu().getPrice() + pc.getRam().getPrice() + pc.getStorage().getPrice();
		if (pc.getExtraStorage() != null) {
			cost += pc.getExtraStorage().getPrice();
		}
		return cost;
	}

	/**
	 * Factory method per creare un PC pre-configurato basato su un tier specifico.
	 * Il PC viene configurato automaticamente con tutti i componenti del tier selezionato.
	 *
	 * @param tier Il livello di configurazione desiderato (ECONOMY, STANDARD, DELUXE)
	 * @return Un nuovo PC configurato secondo il tier specificato
	 */
	public static PC createFromTier(PcTier tier)
	{
		PC pc = new PC();
		pc.setName(tier.getName());
		pc.setProcessor(tier.getProcessor());
		pc.setGpu(tier.getGpu());
		pc.setRam(tier.getRam());
		pc.setStorage(tier.getStorage());
		return pc;
	}

	/**
	 * Verifica se il PC è configurato correttamente.
	 * Un PC è considerato valido se tutti i componenti obbligatori sono stati impostati:
	 * - nome non vuoto
	 * - processore configurato
	 * - GPU configurata
	 * - RAM configurata
	 * - storage primario configurato
	 * Lo storage extra è opzionale e non influisce sulla validità del PC.
	 *
	 * @return true se il PC è valido, false altrimenti
	 */
	public boolean isValid()
	{
		return name != null &&
				!name.isEmpty() &&
				processor != null &&
				gpu != null &&
				ram != null &&
				storage != null;
	}

	// Getter - Componenti PC

	/**
	 * Restituisce il nome del PC.
	 * Se il nome è null, restituisce una stringa vuota.
	 *
	 * @return Il nome del PC o stringa vuota se null
	 */
	public String getName()
	{
		return name == null ? "" : name;
	}

	/**
	 * Restituisce il processore installato nel PC.
	 *
	 * @return Il processore del PC
	 */
	public Processor getProcessor()
	{
		return processor;
	}

	/**
	 * Restituisce la GPU installata nel PC.
	 *
	 * @return La GPU del PC
	 */
	public Gpu getGpu()
	{
		return gpu;
	}

	/**
	 * Restituisce la RAM installata nel PC.
	 *
	 * @return La RAM del PC
	 */
	public Ram getRam()
	{
		return ram;
	}

	/**
	 * Restituisce l'unità di storage primaria installata nel PC.
	 *
	 * @return Lo storage del PC
	 */
	public Storage getStorage()
	{
		return storage;
	}

	/**
	 * Restituisce l'unità di storage secondaria installata nel PC.
	 *
	 * @return Lo storage extra del PC
	 */
	public Storage getExtraStorage()
	{
		return extraStorage;
	}

	// Getter - Dati Cliente

	/**
	 * Restituisce il nome del cliente.
	 *
	 * @return Il nome del cliente
	 */
	public String getClientName()
	{
		return clientName;
	}

	/**
	 * Restituisce il cognome del cliente.
	 *
	 * @return Il cognome del cliente
	 */
	public String getClientSurname()
	{
		return clientSurname;
	}

	/**
	 * Restituisce il numero di telefono del cliente.
	 *
	 * @return Il telefono del cliente
	 */
	public String getClientPhone()
	{
		return clientPhone;
	}

	/**
	 * Restituisce il numero del preventivo.
	 *
	 * @return Il numero del preventivo
	 */
	public String getEstimateNb()
	{
		return estimateNb;
	}

	/**
	 * Restituisce il prezzo finale concordato.
	 *
	 * @return Il prezzo finale
	 */
	public int getFinalPrice()
	{
		return finalPrice;
	}



	/**
	 * Restituisce il costo del materiale.
	 * Se i componenti sono configurati, calcola il costo dinamicamente.
	 * Altrimenti restituisce il valore salvato.
	 *
	 * @return Il costo del materiale
	 */
	public int getMaterialCost()
	{
		if (processor != null && gpu != null && ram != null && storage != null)
		{
			return calculateMaterialCost(this);
		}
		return materialCost;
	}

	// Setter - Componenti PC

	/**
	 * Imposta il nome del PC.
	 * Se il parametro è null, imposta una stringa vuota.
	 *
	 * @param name Il nome da assegnare al PC
	 */
	public void setName(String name)
	{
		this.name = name == null ? "" : name;
	}

	/**
	 * Imposta il processore del PC.
	 *
	 * @param processor Il processore da installare
	 */
	public void setProcessor(Processor processor)
	{
		this.processor = processor;
	}

	/**
	 * Imposta la GPU del PC.
	 *
	 * @param gpu La GPU da installare
	 */
	public void setGpu(Gpu gpu)
	{
		this.gpu = gpu;
	}

	/**
	 * Imposta la RAM del PC.
	 *
	 * @param ram La RAM da installare
	 */
	public void setRam(Ram ram)
	{
		this.ram = ram;
	}

	/**
	 * Imposta l'unità di storage primaria del PC.
	 *
	 * @param storage Lo storage da installare
	 */
	public void setStorage(Storage storage)
	{
		this.storage = storage;
	}

	/**
	 * Imposta l'unità di storage secondaria del PC.
	 *
	 * @param extraStorage Lo storage extra da installare
	 */
	public void setExtraStorage(Storage extraStorage)
	{
		this.extraStorage = extraStorage;
	}

	/**
	 * Imposta il benchmark totale del PC.
	 *
	 * @param totalBenchmark Il benchmark totale da impostare
	 */
	public void setTotalBenchmark(int totalBenchmark)
	{
		this.totalBenchmark = totalBenchmark;
	}

	// Setter - Dati Cliente

	/**
	 * Imposta il nome del cliente.
	 *
	 * @param clientName Il nome del cliente
	 */
	public void setClientName(String clientName)
	{
		this.clientName = clientName;
	}

	/**
	 * Imposta il cognome del cliente.
	 *
	 * @param clientSurname Il cognome del cliente
	 */
	public void setClientSurname(String clientSurname)
	{
		this.clientSurname = clientSurname;
	}

	/**
	 * Imposta il numero di telefono del cliente.
	 *
	 * @param clientPhone Il telefono del cliente
	 */
	public void setClientPhone(String clientPhone)
	{
		this.clientPhone = clientPhone;
	}

	/**
	 * Imposta il numero del preventivo.
	 *
	 * @param estimateNb Il numero del preventivo
	 */
	public void setEstimateNb(String estimateNb)
	{
		this.estimateNb = estimateNb;
	}

	/**
	 * Imposta il prezzo finale concordato.
	 *
	 * @param finalPrice Il prezzo finale
	 */
	public void setFinalPrice(int finalPrice)
	{
		this.finalPrice = finalPrice;
	}

	/**
	 * Imposta il costo del materiale extra.
	 *
	 * @param materialCost Il costo del materiale extra
	 */
	public void setMaterialCost(int materialCost)
	{
		this.materialCost = materialCost;
	}

	public int getLabourCost() {
		return labourCost;
	}

	public void setLabourCost(int labourCost) {
		this.labourCost = labourCost;
	}
}