package com.generation.rs.model.entities;

/**
 * Classe modello Repair (Riparazione)
 * 
 * Questa classe rappresenta una riparazione effettuata in un magazzino.
 * Contiene tutte le informazioni relative a una riparazione: dati del cliente,
 * descrizione del problema, costi dei materiali, ore di lavoro e prezzo finale.
 * 
 * Serve per creare oggetti di tipo Repair che verranno utilizzati
 * per gestire le riparazioni nel sistema.
 */
public class Repair {
	
	//STATO: insieme dei valori delle proprietà in un dato momento
	//il prezzo è quello praticato non quello stimato. price è quello finale. 	
	public String id, client, phone, fix;
	public double materialPartsCost, hour, price;
	public String totalRepairsDone;
	
	
	//COMPORTAMENTO
	/**
	 * Calcola il prezzo stimato della riparazione
	 * 
	 * La stima si basa su:
	 * - Costo dei materiali e parti
	 * - Ore di manodopera moltiplicate per la tariffa oraria (50€/ora)
	 * 
	 * Formula: materialPartsCost + (labour * 50)
	 * 
	 * @return il prezzo stimato della riparazione in euro
	 */
	public double getExtimatedPrice() {
		return materialPartsCost + hour * 50;
	}
	/**
	 * Verifica se l'oggetto Repair contiene tutti i dati necessari
	 * e se questi sono validi
	 * 
	 * Controlla che:
	 * - client, phone e fix non siano null
	 * - client, phone e fix non siano stringhe vuote o contenenti solo spazi
	 * - materialPartsCost sia maggiore o uguale a 0
	 * - labour sia almeno 1 ora
	 * 
	 * @return true se tutti i dati sono validi, false altrimenti
	 */
	public boolean isValid() {
		return client != null         && 
			   phone != null          &&
			   fix != null            &&
			   !client.isBlank()      &&
			   !phone.isBlank()       &&
			   !fix.isBlank()         && 
			   materialPartsCost >= 0 &&
			   hour >= 1;
	}
}
