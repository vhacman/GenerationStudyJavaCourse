import { Component, computed, signal } from '@angular/core';
import { Receipt } from '../model/Receipt';

@Component({
  selector: 'app-receipt-preview',
  imports: [],
  templateUrl: './receipt-preview.html',
  styleUrl: './receipt-preview.css',
})
export class ReceiptPreview {

    receipt:Receipt = {
        customer:"Anonymous",
        date:new Date().toString(),
        hours:signal<number>(1),
        frail:signal<boolean>(false)        
    };

    pricePerHour:number = 30;
    total = computed(()=>this.receipt.frail()  ? 0 : this.receipt.hours() * this.pricePerHour);

    addHour():void{
        this.receipt.hours.update((v:number)=>v+1<=3 ? v+1: 3);
    }

    subtractHour():void {
        this.receipt.hours.update((v:number)=>v-1>0 ? v-1: 1);
    }

    changeStatus():void {
        this.receipt.frail.update((v:boolean)=>!v);
    }


}