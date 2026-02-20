// un oggetto improvvisato javascript con tutto quello che mi serve per gestire la pagina
// coi metodi per gestire la pagina
// con le proprietÃ  della pagina
let controller = 
{
	checkForm:function()
	{
		let valid = 	form1.firstname.value!=''  		&&
						form1.lastname.value!=''		&&
						form1.gender.value!='' 			&&
						form1.income.value>=0 			;
		// seleziono il bottone dalla pagina tramite il nome
		// non tramite id stavolta	
		form1.savebtn.disabled = !valid;
	},
	sendForm:function()
	{
		// invio i dati della form a una API
		// CORPO DELLA REQUEST, un oggetto Json
		// prendo i dati dalla form e li metto in un oggetto
		let payload = 
		{
			firstName: form1.firstname.value, lastName:form1.lastname.value, 
			gender:form1.gender.value, income:form1.income.value			
		 }
		 
		 fetch(api.insertMembershipRequest.url, {
		     method: api.insertMembershipRequest.method,
		     headers: {
		       'Content-Type': 'application/json'
		     },
		     body: JSON.stringify(payload) // conferto il json in una stringa
		   })
		   .then(response=>response.json())	// prendi la response ed estrai il suo corpo in formato json
		   .then(json=> 
			{
				// ho preso il json della response e ci faccio qualcosa
				form1.reset();			
				alert('Saved with id '+json.id); // mi aspetto di ricevere un oggetto MembershipRequest		
			});
		 
		
	}	
}