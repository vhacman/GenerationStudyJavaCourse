// io sono il controller della pagina newTicket.html
// io governo la pagina
// io contengo la LOGICA della pagina

// questo è un oggetto usa e getta di Javascript
// in Javascript non siete obbligati a usare le classi per creare gli oggetti
// in javascript se voglio un oggetto mi basta mettere le graffe
// e cominciare a riempirlo con proprietà e metodi
let controller = 
{
	// checkForm è un campo di tipo function dentro l'oggetto controller
	// in pratica, un metodo dell'oggetto senzaclasse controller
	insertAPIURL:'/ticketservice/api/tickets',
	checkForm:function()
	{
		// il metodo checkForm
		// analizza la form Form1
		// e decide se abilitare o meno il pulsante
		// il pulsante si chiama savebtn
		
		// 1 - prendere il pulsante. Javascript ha accesso alla pagina
		// si dice in gergo, Javascript ha accesso al DOM
		// Document Object Model, alla pagina
		
		// dalla pagina prendi l'elemento che ha id savebtn, il nostro pulsante
		let btn = document.getElementById('savebtn');
		
		let disabled = form1.opening.value=='' || form1.room.value=='';
		// aggiorno il bottone
		btn.disabled = disabled;		
		// non c'è tipo di ritorno nelle funzioni
		// una function può ritornare qualcosa o non ritornare nulla
		// a seconda di come le gira
	},
	callAPI:function()
	{
		// un oggetto temporaneo javascript creato sul momento con due proprietà
		// opening e room
		// prendo i dati dalla form, vale a dire
		// dall'utente
		let json = {opening:form1.opening.value, room:form1.room.value};		
		// ora devo mandare questo oggetto alla API
		// questo è il corpo della request
		// che deve essere post		
		let jsonString = JSON.stringify(json);
		
		//		indirizzo a cui sto mandando i dati, indirizzo della API
		//		fetch esegue una request http a una API
		fetch(
			controller.insertAPIURL, 
		  	{
		  		method: 'POST',
		  		headers: 
				{
		    		'Content-Type': 'application/json'
		  		},
		  		body: jsonString
		})
		// arriva la risposta, .then, quando è arriva estraggo il JSON
		.then(response=>response.json())
		.then(json=>alert('Ticket registrato con id '+json.id));
				
		
	}
};