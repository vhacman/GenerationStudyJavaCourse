import { Component, computed, inject, signal } from '@angular/core';
import { LoanService } from '../loan-service';

@Component({
  selector: 'app-house-loan',
  imports: [],
  templateUrl: './house-loan.html',
  styleUrl: './house-loan.css',
})
export class HouseLoan {

  // Inietto il servizio LoanService per accedere alle logiche di calcolo e dati comuni.
  // L’injection permette di riutilizzare istanze già gestite da Angular (Dependency Injection).
  loanService = inject(LoanService);

  // === Dati reattivi di base ===

  // Costo della casa. È un segnale (signal) di tipo number: reattivo e tracciato dal framework.
  houseCost = signal<number>(40000);

  // Durata del mutuo in anni. Ogni volta che cambia, tutti i calcoli dipendenti si aggiornano.
  years = signal<number>(10);

  // Tasso d’interesse (costo del denaro) fornito dal servizio.
  // È un segnale readonly: può essere letto ma non modificato dal componente.
  moneyCost = this.loanService.moneyCost;

  // === Calcolo reattivo del costo finale ===

  // computed() crea un segnale derivato che ricalcola automaticamente il suo valore
  // ogni volta che cambia uno dei segnali da cui dipende.
  // Qui, finalCost dipende da houseCost, years e moneyCost.
  finalCost = computed(
		() => this.houseCost() + (this.houseCost() * this.years() * this.moneyCost())
  );

}
