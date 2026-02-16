import { Component, computed, signal } from '@angular/core';

@Component({
  selector: 'app-house-preview',
  imports: [],
  templateUrl: './house-preview.html',
  styleUrl: './house-preview.css',
})
export class HousePreview {

    // String description;
    description:string = "Monolocale costruito sul Cimitero Indiano";

    area = signal<number>(40);
    smp = signal<number>(1000);

    price = computed<number>(
        // i -> o
        // arrow function, sintassi di funzione abbreviata
        // è come dire function(){}
        // quando altri signal cambieranno verrà eseguita questa funzione
        ()=> this.area() * this.smp()
    );

    openspace = computed<boolean>(
        ()=>this.area() > 60
    );

    modifyValue(value:string, delta:number):void{
        
        switch(value){
            case "area":
                this.area.update(v=>v+delta);
            break;
            case "smp":
                this.smp.update(v=>v+delta);
            break;
        }
    }



}