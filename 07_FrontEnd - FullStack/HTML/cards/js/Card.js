class Card
{
	constructor(value, seed)
	{
		this.value = value;
		this.seed = seed;
	}

	/**
	 * Restituisce il valore di gioco della carta.
	 * Valori da 1 a 7 corrispondono al loro numero;
	 * valori da 8 a 10 valgono 0.5.
	 *
	 * @return {number} Il valore di gioco della carta.
	 */
	getGameValue()
	{
		return this.value < 8 ? this.value : 0.5;
	}

	/**
	 * Genera il tag HTML dell'immagine associata al seme della carta.
	 *
	 * @return {string} Tag `<img>` con percorso relativo alla cartella images.
	 */
	image()
	{
		return '<img src="images/'+this.seed+'.jpg" />';
	}

	/**
	 * Genera il markup HTML completo della carta,
	 * composto da una tabella con valore e immagine del seme.
	 *
	 * @return {string} Stringa HTML della carta.
	 */
	render()
	{
		return `<table cellspacing=0 class=card><tr><td class=value>${this.value}</td><td> ${this.image()} </td></tr></table>`;
	}

}

/*
==============================================================
TEORIA - Card.js
==============================================================

1. CLASS
   - La parola chiave 'class' definisce un blueprint (prototipo)
     per creare oggetti.
   - Sintassi: class NomeClasse { ... }

2. CONSTRUCTOR
   - Metodo speciale chiamato automaticamente con 'new'.
   - Inizializza le proprietà dell'istanza.
   - Esempio: new Card(3, "spade") → crea un oggetto Card
     con value=3 e seed="spade".

3. THIS
   - Riferimento all'istanza corrente dell'oggetto.
   - 'this.value' accede alla proprietà 'value' di QUELLA carta.

4. OPERATORE TERNARIO
   - Sintassi: condizione ? valoreSe_True : valoreSe_False
   - Usato in getGameValue():
       this.value < 8 ? this.value : 0.5
     Se il valore è minore di 8 restituisce se stesso,
     altrimenti restituisce 0.5.

5. CONCATENAZIONE DI STRINGHE
   - Unisce stringhe con l'operatore +.
   - Usato in image() per costruire il percorso del file immagine.

6. TEMPLATE LITERALS (backtick `)
   - Stringhe con interpolazione: ${espressione}
   - Usati in render() per inserire this.value e this.image()
     direttamente nella stringa HTML senza usare +.

==============================================================
*/
