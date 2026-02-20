import { Injectable, signal } from '@angular/core';

// Il decoratore @Injectable indica che questa classe può essere "iniettata" come servizio
// e che Angular gestirà la sua istanza tramite il sistema di dependency injection.
@Injectable({
  providedIn: 'root', // Il servizio è disponibile a livello globale dell’app
})
export class LoanService {

  // Valore di riferimento dell’inflazione (2%), non esposto all’esterno
  // Variabile privata perché non deve essere modificata da altri componenti
  private _inflation: number = 0.02;

  // Crea un segnale reattivo (signal) che contiene il costo del denaro.
  // Il segnale è modificabile solo all’interno del servizio.
  private _moneyCost = signal<number>(0.1);

  // Versione readonly del segnale da esporre ai componenti.
  // In questo modo i componenti possono leggerne il valore ma non modificarlo direttamente.
  public moneyCost = this._moneyCost.asReadonly();

  // Metodo pubblico per aggiornare il costo del denaro.
  // Se il valore proposto è inferiore all’inflazione, solleva un’eccezione.
  public setCost(cost: number): void {
    if (cost < this._inflation)
      throw { "err": 'Cost is lower than inflation'};
    else
      this._moneyCost.set(cost); // Aggiorna il valore del segnale
  }

  // Metodo getter per leggere l’inflazione corrente.
  // I getter separano la logica di accesso dai dati interni.
  public getInflation(): number {
    return this._inflation;
  }
}
