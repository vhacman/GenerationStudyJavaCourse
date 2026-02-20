import { Component, inject } from '@angular/core';
import { LoanService } from '../loan-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-form-money-cost',
  imports: [FormsModule], //libreria per gestire le form in angular.
  templateUrl: './form-money-cost.html',
  styleUrl: './form-money-cost.css',
})
export class FormMoneyCost {

  loanService = inject(LoanService);
  moneyCost = this.loanService.moneyCost;

  newCost:number = this.moneyCost();


  //modifica un signal dentro a un service
  //non so e non voglio sapere chi verrà aggiornato. 
  changeCost():void {
    try{
      //tutti i componenti collegati a quel signal verranno aggiornati
      //verranno aggiornati legati al signal LoanService.moneyCost
      //veranno ridisegnati tutti i componeti che lo usano. 
      this.loanService.setCost(this.newCost);

    }catch(err){
      alert("Invalid value");
    }
  }
}
