// configurazione di un PC
// richiesta dell'utente

// esempio: processore, ram, hd... => prezzo

class Configuration
{
	// non ho bisogno di dichiarare le proprietà, mi basta impostarle...

	constructor()
	{
		this.firstName = "";
		this.lastName = "";
		this.processor = "";
		this.ram = "";
		this.ssd = "";
	}

	getErrors()
	{
		let errors = []; // vettore vuoto
		// controllo di esistenza.
		if(!this.firstName) // questo è un controllo su un valore truthy, circa vero. una stringa piena è letta come true, una vuota è letta come false
			errors.push("Missing first name of the customer");

		if(!this.lastName)
			errors.push("Missing last name for the customer");

		if(!this.processor)
			errors.push("Missing processor");

		if(!this.ram || this.ram<0)
			errors.push("Invalid ram value");

		if(!this.ssd || this.ssd<0)
			errors.push("Invalid ssd value");

		return errors;
	}

	isValid()
	{
		return this.getErrors().length==0;
	}


	getPrice()
	{
		if(!this.isValid())
			return 0;

		let res = 0;
		switch(this.processor)
		{
			case "i3":
				res+=100;
			break;
			case "i5":
				res+=150;
			break;
			case "i7":
				res+=300;
			break;
		}

		res+=(this.ram * 10); // 10 euro x gb

		res+=(this.ssd * 0.1);

		return res;
	}

}
