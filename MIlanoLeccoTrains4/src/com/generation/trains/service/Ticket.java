package com.generation.trains.service;

/*
 * Classe che rappresenta un biglietto ferroviario
 * Contiene tutte le informazioni relative a un SINGOLO BIGLIETTO*/
public class Ticket
{
	private String	fullName;
	private	int		age;
	private	String	departure;
	private	String	arrival;
	private	int		level;
	private	String	date;
	private	String	hour;
	private	boolean	greenCard;
	private	double	price;
	private	int		distance;
	
	
	/**
	 * Costruttore con argomenti della classe Ticket
	 *
	 * @param fullName nome completo del passeggero
	 * @param age età del passeggero
	 * @param departure stazione di partenza
	 * @param arrival stazione di arrivo
	 * @param level livello del biglietto (1 per prima classe, altro per seconda classe)
	 * @param date data del viaggio
	 * @param hour orario del viaggio
	 * @param hasGreenCard indica se il passeggero possiede la carta verde
	 * @param price prezzo base del biglietto
	 * @param distance distanza tra le stazioni
	 */
	public	Ticket(String fullName, int age, String departure, String arrival, int level,
					String date, String hour, boolean greenCard, int distance, double price)
	{
		this.fullName = fullName;
		this.age = age;
		this.departure = departure;
		this.arrival = arrival;
		this.level = level;
		this.date = date;
		this.hour = hour;
		this.greenCard = greenCard;
		this.distance = calculateDistance();
		this.price = calculatePrice();
	}
	
		
	/**
	 * Calcola il prezzo del biglietto in base alla distanza, livello, età e carta verde
	 *
	 * @return il prezzo calcolato del biglietto
	 */
	private double calculatePrice()
	{
    	double price = (level == 1 ? 0.2 : 0.15) * distance;
   	 
    	if (age > 75)
    		return 0;
    	
    	if(greenCard)
    		price = price * 0.8;
    	return price;
	}

	
	/**
	 * Calcola la distanza in km tra la stazione di partenza e quella di arrivo
	 *
	 * @return la distanza in km, o 0 se la tratta non è riconosciuta
	 */
	private int calculateDistance() {
        String	fromLower = departure.toLowerCase();
        String	toLower = arrival.toLowerCase();
        
        if (fromLower.equals("milano"))
        {
            switch(toLower) {
                case "monza": return 15;
                case "osnago": return 30;
                case "lecco": return 45;
            }
        }
        else if (fromLower.equals("monza"))
        {
            switch(toLower){
                case "milano": return 15;
                case "osnago": return 15;
                case "lecco": return 30;
            }
        }
        else if (fromLower.equals("osnago"))
        {
            switch(toLower) {
                case "milano": return 30;
                case "monza": return 15;
                case "lecco": return 15;
            }
        }
        else if (fromLower.equals("lecco"))
        {
            switch(toLower) {
                case "milano": return 45;
                case "monza": return 30;
                case "osnago": return 15;
            }
        }
        return (0);
	}
	

	/**
	 * Restituisce il nome completo del passeggero
	 *
	 * @return il nome completo
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * Restituisce l'età del passeggero
	 *
	 * @return l'età
	 */
	public int	getAge() {
		return age;
	}

	/**
	 * Restituisce la stazione di arrivo
	 *
	 * @return la stazione di arrivo
	 */
	public String getArrival() {
		return arrival;
	}

	/**
	 * Restituisce il livello del biglietto
	 *
	 * @return il livello (1 per prima classe, altro per seconda classe)
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Restituisce la data del viaggio
	 *
	 * @return la data
	 */
	public String	getDate() {
		return date;
	}

	/**
	 * Restituisce l'orario del viaggio
	 *
	 * @return l'orario
	 */
	public String getHour () {
		return hour;
	}

	/**
	 * Restituisce la stazione di partenza
	 *
	 * @return la stazione di partenza
	 */
	public String getDeparture() {
		return departure;
	}

	/**
	 * Restituisce il prezzo del biglietto
	 *
	 * @return il prezzo
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Restituisce la distanza tra le stazioni
	 *
	 * @return la distanza in km
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * Verifica se il passeggero possiede la carta verde
	 *
	 * @return true se possiede la carta verde, false altrimenti
	 */
	public boolean hasGreenCard() {
		return greenCard;
	}

	/**
	 * Imposta la stazione di partenza
	 *
	 * @param departure la nuova stazione di partenza
	 */
	public void setDeparture(String departure) {
		this.departure = departure;
	}

	/**
	 * Imposta la stazione di arrivo
	 *
	 * @param arrival la nuova stazione di arrivo
	 */
	public void setArrival(String arrival) {
		this.arrival = arrival;
	}

	/**
	 * Imposta lo stato della carta verde
	 *
	 * @param hasGreenCard true se il passeggero possiede la carta verde, false altrimenti
	 */
	public void setHasGreenCard(boolean hasGreenCard) {
		this.greenCard = hasGreenCard;
	}

	/**
	 * Imposta la distanza tra le stazioni
	 *
	 * @param distance la nuova distanza in km
	 */
	public void setDistance(int distance) {
		this.distance = distance;
	}

	/**
	 * Imposta il nome completo del passeggero
	 *
	 * @param fullName il nuovo nome completo
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * Imposta l'età del passeggero
	 *
	 * @param age la nuova età
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * Imposta il prezzo del biglietto
	 *
	 * @param price il nuovo prezzo
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Imposta il livello del biglietto
	 *
	 * @param level il nuovo livello (1 per prima classe, altro per seconda classe)
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * Imposta la data del viaggio
	 *
	 * @param date la nuova data
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Imposta l'orario del viaggio
	 *
	 * @param hour il nuovo orario
	 */
	public void setHour(String hour) {
		this.hour = hour;
	}
	
}
