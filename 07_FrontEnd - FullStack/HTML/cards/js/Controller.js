// in Javascript non sono obbligato a usare le classi per avere gli oggetti
// controller è un oggetto usa e getta singleton
let controller =
{
	/**
	 * Pesca una carta dal mazzo e la aggiunge
	 * alla mano del giocatore, poi aggiorna la vista.
	 */
	draw:function()
	{
		controller.playerHand.add(controller.deck.draw());
		controller.refresh();
	},
	/**
	 * Gestisce la fine del turno del giocatore.
	 * @todo Da implementare.
	 */
	stop:function()
	{
		// TODO
	},
	playerHand:new Hand(),
	pcHand:new Hand(),
	deck:new Deck(),
	/**
	 * Inizializza il gioco pescando la prima carta
	 * e renderizzando la mano del giocatore.
	 */
	init:function()
	{
		controller.draw();
		controller.refresh();
	},
	/**
	 * Aggiorna il contenuto HTML della mano del giocatore
	 * nel DOM con il rendering corrente.
	 */
	refresh:function()
	{
		document.getElementById('playerhand').innerHTML = controller.playerHand.render();
	}
};

/*
==============================================================
TEORIA - Controller.js
==============================================================

1. OBJECT LITERAL
   - Un oggetto creato direttamente con la sintassi { chiave: valore }.
   - Alternativa alle classi per creare strutture dati con metodi.
   - Sintassi: let obj = { proprietà: valore, metodo: function() {} };

2. PATTERN SINGLETON
   - Un oggetto che esiste in una sola istanza nel programma.
   - Qui 'controller' è un singolo oggetto che gestisce tutto
     il flusso di gioco. Non se ne creare altri.

3. LET (dichiarazione di variabile)
   - Dichiara una variabile con scope a blocco.
   - A differenza di 'var', non è accessibile fuori dal blocco
     in cui è dichiarata.

4. PROPRIETÀ COME FUNZIONI (metodi nell'object literal)
   - Le proprietà possono contenere funzioni:
       draw: function() { ... }
   - Questo le trasforma in metodi dell'oggetto.

5. RIFERIMENTO ALL'OGGETTO STESSO
   - In un object literal 'this' puede essere ambiguo.
   - Qui si usa il nome esplicito 'controller' per riferirsi
     alle proprie proprietà: controller.playerHand, controller.deck.
   - Alternativa più chiara e sicura a this in questo contesto.

6. DOM MANIPULATION
   - Il DOM (Document Object Model) rappresenta la pagina HTML
     come albero di oggetti manipolabili in JavaScript.

7. DOCUMENT.GETELEMENTBYID(id)
   - Restituisce l'elemento HTML che ha l'attributo id specificato.
   - Esempio: document.getElementById('playerhand')
     → seleziona l'elemento con id="playerhand".

8. INNERHTML
   - Proprietà che rappresenta il contenuto HTML interno di un elemento.
   - Assegnandole una stringa, SOSTITUISCE tutto il contenuto
     dell'elemento con il nuovo markup.
   - Usato in refresh() per aggiornare la vista della mano.

==============================================================
*/
