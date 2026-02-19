/**
 * Rappresenta una lezione associata a uno studente specifico, con durata e materia.
 * Fornisce validazione dei dati attraverso controllo di esistenza dei campi obbligatori.
 *
 * 
 */
class Lesson 
{
	constructor()
	{
		this.student = "";
		this.hours = 0;
		this.subject = "";
	}
	
	/**
	   * Raccoglie tutti gli errori di validazione presenti nell'oggetto.
	   * 
	   * @returns {Array<string>} array di messaggi di errore (vuoto se valido)
	*/
	getErrors()
	{
		let errors = [];
		if(!this.student)
			errors.push("Missing student name");
		if(!this.hours || this.hours <= 0)
		    errors.push("Invalid hours value (must be > 0)");
		if(!this.subject)
			errors.push("Missing subject");
		return errors;
	}
	
	/**
    * Valida l'integrità dell'oggetto verificando presenza di tutti i campi obbligatori.
    * 
    * @returns {boolean} true se studente, ore e materia sono valorizzati, false altrimenti
    */
   isValid()
   { 
       return this.getErrors().length === 0;
   }
   
   /**
    * Calcola il costo totale della lezione basandosi sulle ore.
    * 
    * @returns {number} prezzo in euro (0 se la lezione non è valida)
    */
   getPrice()
   {
		if(!this.isValid())
			return 0;
		let hourlyRate = 0;
		switch(this.subject)
		{
			case "JAVA":
				hourlyRate = 50;
				break;
			case "SQL":
				hourlyRate = 30;
				break;
			case "ITALIAN":
				hourlyRate = 20;
				break;
		}
		return this.hours * hourlyRate;
   }
}