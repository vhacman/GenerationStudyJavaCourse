class Hand
{
	constructor()
	{
		this.content = [];
	}

	/**
	 * Aggiunge una carta alla mano.
	 *
	 * @param {Card} card La carta da aggiungere.
	 */
	add(card)
	{
		this.content.push(card);
	}

	/**
	 * Calcola il punteggio totale della mano
	 * sommando i valori di gioco di ogni carta.
	 *
	 * @return {number} Punteggio complessivo della mano.
	 */
	score()
	{
		let res = 0;
		for(let c of this.content)
			res+=c.getGameValue();
		return res;
	}

	/**
	 * Genera il markup HTML della mano, includendo
	 * il rendering di ogni carta e il punteggio corrente.
	 *
	 * @return {string} Stringa HTML della mano completa.
	 */
	render()
	{
		let res = "<div class=hand>";
		for(let c of this.content)
			res+=c.render();
		res+="<br />";
		res+="Score "+this.score();
		res+="</div>";
		return res;
	}

	/**
	 * Determina se la mano è ancora in gioco,
	 * verificando che il punteggio non superi 7.5.
	 *
	 * @return {boolean} True se il punteggio è <= 7.5, altrimenti false.
	 */
	living()
	{
		return this.score()<=7.5;
	}

}

/*
==============================================================
TEORIA - Hand.js
==============================================================

1. ARRAY E PUSH
   - this.content è un array che memorizza le carte della mano.
   - push() aggiunge una carta in coda all'array.

2. FOR...OF (ciclo iterativo)
   - Itera sui valori di un array senza bisogno dell'indice.
   - Usato in score() e render() per scorrere ogni carta
     contenuta nella mano.

3. PATTERN ACCUMULATORE
   - Tecnica in cui una variabile (res) viene inizializzata
     e poi aggiornata iterativamente dentro un ciclo.
   - In score(): res parte da 0 e accumula i valori di gioco.
   - In render(): res parte da una stringa e accumula l'HTML.

4. CONCATENAZIONE DI STRINGHE (+= con stringhe)
   - L'operatore += aggiunge al valore corrente della variabile.
   - Con stringhe: res += "<div>" aggiunge testo alla fine.
   - Con numeri (score): res += c.getGameValue() somma i valori.

5. OPERATORE DI CONFRONTO (<=)
   - Confronta due valori: restituisce true o false.
   - Usato in living(): this.score() <= 7.5
     → verifica se il punteggio non ha superato il limite.

6. RETURN DI BOOLEAN
   - Un confronto come <= produce direttamente un booleano.
   - Non serve un if: si restituisce direttamente il risultato.
   - Esempio: return this.score() <= 7.5;

==============================================================
*/
