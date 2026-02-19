import { Component, computed, signal } from '@angular/core';

@Component({
  selector: 'app-meal-card-v1',
  imports: [],
  templateUrl: './meal-card-v1.html',
  styleUrl: './meal-card-v1.css',
})
export class MealCardV1 {

    description:string = "Pranzo";
    // i valori di carbs, proteins e fats potrebbero cambiare dinamicamente
    // in angular c'è una tecnologia per gestire il cambiamento
    // introdotta nel 2023 che ha mandato al diavolo 10 o 15 anni di sviluppo precendete

    // voi leggete signal ma traducete "valori vivi" o "valori reattivi" o "valori sorvegliati" o
    // "valori osservati"
    // un signal è una funzione che avvolge un valore
    // quando il valore avvolto dalla funzione cambia altri valori vengono ricalcolati
    // un signal che cambia segnala ad angular che alcuni calcoli sono da rifare
    carbs = signal<number>(0);
    proteins = signal<number>(0);
    fats = signal<number>(0);

    // computed signal, un dato di output, un signal calcolato a partire da altri
    // questo signal verrà ricalcolato solo quando i suoi signal riferiti vengono cambiati 
    calories = computed(()=>this.carbs()*4 + this.proteins()*4 + this.fats()*9);
    // un computed è un signal il cui valore viene ricalcolato solo i signal che uso cambiano
    // un computed è una funzione 
    // I => O
    // input => output
    // ricalcolo e ridisegno della pagina sono DUE COSE DIVERSE
    // se cambio description ridisegno la pagina
    // se cambio description NON RICALCOLO calories()
    // calories() è un signal dipendente da carbs(), proteins() e fats()

    // ()=>{}, arrow function
    complete = computed(()=>this.calories()>=600);

    proteinComplete = computed(()=>this.proteins()>=20);


    //TODO 
    // computed per la percentuale di grassi sul totale delle calorie
    // i grassi valgono 9 calorie per grammo, quindi le calorie dai grassi sono fats()*9
    // la percentuale è (calorie dai grassi / calorie totali) * 100
    // se le calorie totali sono 0 ritorniamo 0 per evitare la divisione per zero
    fatPercentage = computed(()=>{
        let result = 0;
        const total = this.calories();
        if(total === 0)
            return 0;
        result = (this.fats() * 9 / total) * 100;
        return result;
    });

    //TODO
    // metodo per impostare tutti i nutrienti a 50
    // usiamo .set() come nel resetNutrients() ma stavolta impostiamo 50 invece di 0
    setAllToFifty():void{
        this.carbs.set(50);
        this.proteins.set(50);
        this.fats.set(50);
    }

    resetNutrients():void{
        this.carbs.set(0);
        this.proteins.set(0);
        this.fats.set(0);
    }

    /**
     * questo metodo riceve il nome di un nutriente e un delta per modificarlo
     * ad esempio
     * "carbs" 1 per aumentare i carboidrati
     * @param nutrient 
     * @param delta 
     */
    updateNutrient(nutrient:string, delta:number):void{

        switch(nutrient) {
            case "carbs":
                this.carbs.update(v=>v+delta);
            break;
            case "proteins":
                this.proteins.update(v=>v+delta);
            break;
            case "fats":
                this.fats.update(v=>v+delta);
            break;
        }
    }


}