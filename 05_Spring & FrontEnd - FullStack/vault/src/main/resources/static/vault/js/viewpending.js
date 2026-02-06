let controller = 
{
	loadPending:function()
	{
		// il database non è sul client, è sul server
		fetch(api.viewPending.url)
		.then(response=>response.json())
		.then(json=>
		{
			// mi è arrivato un vettore di oggetti membership request
			// li devo graficare
			let res = "";
			for(let o of json)
				res+=controller.render(o);
			// document => intera pagina 
			// seleziono un elemento tramite id
			document.getElementById('pending').innerHTML = res;
			
		});
	},
	render:function(o)
	{
		return `<div class=request> Richiesta di ${o.firstName} ${o.lastName} </div>`
	}
}