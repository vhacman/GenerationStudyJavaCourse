package com.generation.mh.model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking
{
	//============ ATTRIBUTI ===================
	private int			id;
	private String		firstName;
	private String		lastName;
	private LocalDate	dob;
	private	Species		species;
	private	RoomType	roomType;
	private	int			floor;
	private	LocalDate	arrivalDate;
	private	LocalDate	departureDate;
	private	boolean		transportService;
	public int getId() {
		return id;
	}
	
	// ================ GETTER ===============
	public String 		getFirstName() {return firstName; }
	public String 		getLastName() {return lastName;}
	public LocalDate 	getDob() {return dob;}
	public Species 		getSpecies() {return species;}
	public RoomType 	getRoomType() {return roomType;}
	public int 			getFloor() { return floor; }
	public LocalDate 	getArrivalDate() {return arrivalDate; }
	public LocalDate 	getDepartureDate() {return departureDate;}
	public boolean 		isTransportService() {return transportService;}
	
	// ======== SETTER =============
	public void 		setId(int id) { this.id = id;}
	public void 		setFirstName(String firstName) {this.firstName = firstName;}
	public void 		setLastName(String lastName) {this.lastName = lastName;}
	public void 		setDob(LocalDate dob) {this.dob = dob;}
	public void 		setSpecies(Species species) {this.species = species;}
	public void 		setRoomType(RoomType roomType) {this.roomType = roomType;}
	public void 		setFloor(int floor) {this.floor = floor;}
	public void 		setArrivalDate(LocalDate arrivalDate) {this.arrivalDate = arrivalDate;}
	public void 		setDepartureDate(LocalDate departureDate) {	this.departureDate = departureDate;}
	public void 		setTransportService(boolean transportService) {	this.transportService = transportService;}
	
	// ================ COSTRUTTORE =================
	public Booking() {}
	/**
     * Costruttore completo per una prenotazione
     * 
     * @param id Identificativo univoco
     * @param firstName Nome del cliente
     * @param lastName Cognome del cliente
     * @param dob Data di nascita
     * @param species Specie del cliente
     * @param roomType Tipo di stanza
     * @param floor Piano della stanza
     * @param arrivalDate Data di arrivo
     * @param departureDate Data di partenza
     * @param transportService Servizio navetta (true/false)
     */
	public Booking (int id,
					String firstName,
					String lastName,
					LocalDate dob,
					Species species,
					RoomType roomType,
					int floor,
					LocalDate arrivalDate,
					LocalDate departureDate,
					boolean transportService)
	{
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.species = species;
		this.roomType = roomType;
		this.floor = floor;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
		this.transportService = transportService;
	}
	
	// =============== BUSINESS LOGIC  E METODI DI CALCOLO ==================
	public int getNumberOfNights()
	{
		if (arrivalDate == null || departureDate == null)
			return 0;
		return (int)ChronoUnit.DAYS.between(arrivalDate, departureDate);
	}
	
	 /**
     * Calcola il costo base della stanza (senza extra specie)
     * 
     * @return Costo totale della stanza per tutte le notti
     */
	public double getRoomCost() {return roomType.getTotalRoomCost(getNumberOfNights());	}
	
    /**
     * Calcola il costo del servizio navetta
     * 
     * @return 100€ se attivo, 0€ altrimenti
     */
	public double getTransportCost() { return transportService == true ? 100.0 : 0.0; }
	
	/**
     * Calcola il costo base (stanza + navetta, senza extra specie)
     * 
     * @return Somma di costo stanza e costo navetta
     */
	public double getBaseCost() { return getRoomCost() + getTransportCost(); }
	
	/**
     * Calcola l'extra costo dovuto alla specie
     * 
     * @return Costo extra specifico della specie
     */
	public double getSpeciesExtraCost() { return species.calculateExtraCost(getBaseCost()); }
	
    /**
     * Calcola il costo totale della prenotazione
     * Formula: (Costo Stanza + Navetta) + Extra Specie
     * 
     * @return Costo totale finale
     */
	public double calculateTotalCost() { return species.calculateTotalCost(getBaseCost());}
	
	
	// ================= METODI DI VALIDAZIONE ================
	
	/**
	 * Valida la prenotazione secondo le regole di business
	 * 
	 * Controlla:
	 * - Dati obbligatori (nome, cognome, specie, tipo stanza, date)
	 * - Date valide (partenza > arrivo, arrivo >= oggi)
	 * - Vincoli specie (piano e tipo stanza compatibili)
	 * 
	 * @return Stringa con tutti gli errori trovati (vuota se tutto OK)
	 */
	public String validate()
	{
		String errors = "";
		
		if (firstName == null || firstName.isBlank())
			errors += "Nome del cliente obbligatorio \n";
		if(lastName == null || lastName.isBlank())
			errors += "Cognome del cliente obbligatorio \n";
		if (species == null)
			errors += "Specie del cliente obbligatoria \n";
		if (roomType == null)
			errors += "Tipo di stanza del cliente obbligatoria \n";
		if (arrivalDate == null)
			errors += "Data di arrivo del cliente obbligatoria \n";
		if (departureDate == null)
			errors += "Data di partenza del cliente obbligatoria \n";
		
		
	    // Validazione date
		if (arrivalDate != null && departureDate != null)
		{
			if (!departureDate.isAfter(arrivalDate))
				errors += "La data di partenza deve essere successiva alla data di arrivo \n";
			if (arrivalDate.isBefore(LocalDate.now()))
				errors += "La data di arrivo non può essere nel passato \n";
		}
		
	    // Validazione vincoli specie
		if (species != null && roomType != null)
		{
			if(!species.canStayOnFloor(floor)) {
				String msg = species.getFloorErrorMessage(floor);
				if(!msg.isEmpty())
					errors += msg + "\n";
			}
			if(!species.canStayInRoomType(roomType)) {
				String msg = species.getRoomTypeErrorMessage(roomType);
				if (!msg.isEmpty())
					errors += msg + "\n";
			}
		}
		return errors;
	}
	
	// ========== OVERRIDE TOSTRING ==========

	/**
	 * Rappresentazione testuale sintetica della prenotazione
	 */
	@Override
	public String toString()
	{
		return "Booking #" + id + " - " + firstName + " " + lastName + " (" + species + ") - " +
				roomType + " - Piano " + floor + " - " + arrivalDate + " -> " + departureDate;
	}

	// ========== GENERAZIONE HTML ==========

	/**
	 * Genera il codice HTML della prenotazione usando il template
	 *
	 * @return Stringa con il codice HTML completo
	 */
	public String generateHTML()
	{
		String template = com.generation.util.Template.load("template/template.html");

		// Sostituzione placeholders base
		template = template.replace("[id]", String.valueOf(id));
		template = template.replace("[firstName]", firstName);
		template = template.replace("[lastName]", lastName);
		template = template.replace("[species]", species.toString());
		template = template.replace("[dob]", dob.toString());
		template = template.replace("[arrivalDate]", arrivalDate.toString());
		template = template.replace("[departureDate]", departureDate.toString());
		template = template.replace("[nights]", String.valueOf(getNumberOfNights()));
		template = template.replace("[roomType]", roomType.toString());
		template = template.replace("[floor]", String.valueOf(floor));
		template = template.replace("[pricePerNight]", String.format("%.2f", roomType.getPricePerNight()));
		template = template.replace("[roomCost]", String.format("%.2f", getRoomCost()));
		template = template.replace("[totalCost]", String.format("%.2f", calculateTotalCost()));

		// Gestione linea servizio navetta (condizionale)
		String transportLine = "";
		if (transportService)
		{
			transportLine = "<div class='cost-line'>\n" +
							"    <span>Servizio Navetta:</span>\n" +
							"    <span>€" + String.format("%.2f", getTransportCost()) + "</span>\n" +
							"</div>";
		}
		template = template.replace("[transportLine]", transportLine);

		// Gestione linea costo extra specie (condizionale)
		String extraLine = "";
		double extraCost = getSpeciesExtraCost();
		if (extraCost > 0)
		{
			extraLine = "<div class='cost-line'>\n" +
						"    <span>Extra " + species.getDisplayName() + " (" + species.getExtraCostDescription() + "):</span>\n" +
						"    <span>€" + String.format("%.2f", extraCost) + "</span>\n" +
						"</div>";
		}
		template = template.replace("[extraLine]", extraLine);

		return template;
	}

}
