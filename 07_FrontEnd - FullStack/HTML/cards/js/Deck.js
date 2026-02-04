class Deck
{
	constructor()
	{
		this.cards = [];
		this.seeds = ["denari", "coppe", "spade", "bastoni"];

		for(let i=1;i<=10;i++)
			for(let s of this.seeds)
				this.cards.push(new Card(i, s));
	}

	/**
	 * Ripristina il mazzo allo stato iniziale,
	 * ricreando tutte le 40 carte con i quattro semi.
	 */
	shuffle()
	{
		this.cards = [];
		this.seeds = ["denari", "coppe", "spade", "bastoni"];
		for(let i=1;i<=10;i++)
			for(let s of this.seeds)
				this.cards.push(new Card(i, s));
	}

	/**
	 * Estrae una carta casuale dal mazzo e la rimuove.
	 *
	 * @return {Card} La carta estratta.
	 */
	draw()
	{
		let pos = parseInt(Math.random() * this.cards.length);
		return this.cards.splice(pos,1)[0];
	}

	/**
	 * Restituisce il numero di carte ancora presenti nel mazzo.
	 *
	 * @return {number} Quantità di carte rimanenti.
	 */
	left()
	{
		return this.cards.size;
	}

}

/*
==============================================================
TEORIA - Deck.js
==============================================================

1. ARRAY
   - Struttura dati ordinata che contiene elementi.
   - Dichiarazione con letterale: let arr = [];
   - Gli elementi sono accessibili tramite indice (base 0).

2. ARRAY.push()
   - Aggiunge un elemento alla fine dell'array.
   - Esempio: this.cards.push(new Card(i, s))
     → aggiunge una nuova carta alla fine del mazzo.

3. ARRAY.splice(pos, count)
   - Rimuove 'count' elementi dalla posizione 'pos'
     e restituisce un nuovo array con gli elementi rimossi.
   - Usato in draw(): splice(pos, 1)[0]
     → rimuove 1 carta dalla posizione casuale e la restituisce.

4. FOR LOOP (classico)
   - Sintassi: for(let i = 0; i < limite; i++) { ... }
   - Usato nel constructor e in shuffle() per iterare
     sui valori da 1 a 10.

5. FOR...OF
   - Itera direttamente sugli VALORI di un array (o iterable).
   - Sintassi: for(let elemento of array) { ... }
   - Usato per iterare sui semi: for(let s of this.seeds)

6. MATH.random()
   - Restituisce un numero decimale casuale tra 0 (incluso)
     e 1 (escluso).
   - Moltiplicato per this.cards.length genera un indice
     casuale nell'intervallo valido dell'array.

7. parseInt()
   - Converte una stringa (o decimale) in un intero.
   - Usato per trasformare il risultato di Math.random() * length
     in un indice intero valido.

==============================================================
*/
