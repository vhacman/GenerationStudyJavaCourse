let controller = 
{
    configuration: new Configuration(),
    synch:function()
    {
        console.log("Sincronizzo");
        // fase di input. Prendo i dati dalla form
        // li metto nel model, li metto in configuration
        // copio i dati dalla form al model, alla entità
        controller.configuration.processor = pcForm.processor.value;
        controller.configuration.ram = pcForm.ram.value;
        controller.configuration.ssd = pcForm.ssd.value;
        controller.configuration.firstName = pcForm.firstName.value;
        controller.configuration.lastName = pcForm.lastName.value;

        // fase di output. Dal model alla form
        pcForm.price.value = controller.configuration.getPrice();
        pcForm.saveBtn.disabled = !controller.configuration.isValid();
    },
    callAPI:function()
    {
		let apiURL = '/pcconfigurator/api/configurations';
		let payload = 
		{
				firstName:controller.configuration.firstName,
				lastName:controller.configuration.lastName,
				ssd:controller.configuration.ssd,
				ram:controller.configuration.ram,
				processor:controller.configuration.processor 
		}; // gli mando la mia entity
		// il frontend manda lo stato della propria entity al backend
		// che la ricostruisce nella propria entity
		
		
		fetch(apiURL, 
		{
		     method: "POST",
		     headers: {
		       'Content-Type': 'application/json'
		     },
		     body: JSON.stringify(payload) // conferto il json in una stringa
		})
		.then(response=>
		{
			if(!response.ok) // il codice non è 200
				throw new Error("Invalid data");
			return response.json(); // estraggo il  json dalla response
		})
		.then(json=>
		{
			pcForm.reset();
			pcForm.operationstatus.value = 'Saved with id '+json.id;
		})
		.catch(err=>
		{
			pcForm.operationstatus.value = 'Error saving';
		});
		
	}
};

/*
    gestire gli eventi
    fare fetch
    sincronizzare vista e model

*/