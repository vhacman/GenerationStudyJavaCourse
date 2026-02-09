/**
 * Modello per la gestione delle sanzioni nel frontend.
 * Rappresenta una sanzione con stato e metodi di rendering.
 */
class Sanction
{
    /**
     * Costruttore del modello Sanction.
     * @param {Object} state - Oggetto contenente i dati della sanzione
     */
    constructor(state)
    {
        /*
         * ARCHITETTURA OOP - JAVASCRIPT PROTOTYPAL:
         * 
         *     Constructor Pattern  →  Inizializzazione oggetto
         *     Dynamic Properties  →  Copia dinamica delle proprietà
         *     Incapsulamento    →  Dati e comportamento nello stesso oggetto
         *     This              →  Riferimento all'istanza corrente
         */
        for(let p in state)
            this[p] = state[p];
    }

    /**
     * Verifica se la sanzione è stata pagata.
     * @returns {boolean} true se lo stato è "PAID"
     */
    paid()
    {
        return this.status === 'PAID';
    }

    /**
     * Genera l'HTML per visualizzare la sanzione.
     * @returns {string} Stringa HTML per il rendering
     */
    render()
    {
        /*
         * TEMPLATE LITERALS - RENDERING DINAMICO:
         * 
         *     Template String   →  Interpolazione variabili
         *     Logica Condizionale →  Colore verde/rosso basato su stato
         *     Binding Dati     →  Separazione dati da presentazione
         */
        return `
            <div class="sanction ${this.paid() ? "green": "red"}">
                <div class="header"> ${this.id} - ${this.citizen} - ${this.reason} </div>
                <div class="detail"> ${this.date} - ${this.price} &euro; </div>
                <div class="paymentDetail"> ${this.paidOn ? "Paid on " + this.paidOn : "Still unpaid"} </div>
            </div>
        `;
    }
}
