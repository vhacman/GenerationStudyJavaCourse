import { Component, computed, signal } from '@angular/core';
import { DecimalPipe } from '@angular/common';
import { BmiData } from '../model/BmiData';

// decoratore che definisce il componente Angular
@Component({
    selector: 'app-bmi-form',          // tag HTML per usare questo componente
    imports: [DecimalPipe],             // importa la pipe per formattare i numeri nel template
    templateUrl: './bmi-form.html',     // file HTML del template
    styleUrl: './bmi-form.css',         // file CSS degli stili
})
export class BmiForm {

    // oggetto che contiene i dati del BMI
    // i signal sono variabili reattive: quando cambiano, il template si aggiorna automaticamente
    bmiData: BmiData = {
        weight: signal<number>(70),     // peso iniziale: 70 kg
        height: signal<number>(170),    // altezza iniziale: 170 cm
    };

    // computed: valore calcolato che si aggiorna automaticamente quando peso o altezza cambiano
    // formula BMI = peso(kg) / altezza(m)^2
    bmi = computed(() => {
        let heightInMeters = this.bmiData.height() / 100;   // converte cm in metri
        if (heightInMeters <= 0)                             // evita divisione per zero
            return 0;
        return this.bmiData.weight() / (heightInMeters * heightInMeters);
    });

    // versione con ternario
    // category = computed(() => this.bmi() < 18.5 ? 'SOTTOPESO' : this.bmi() < 25 ? 'NORMOPESO' : this.bmi() < 30 ? 'SOVRAPPESO' : 'OBESO');

    // computed che restituisce la categoria in base al valore del BMI
    category = computed(() => {
        let value = this.bmi();         // legge il valore corrente del BMI
        if (value < 18.5)
            return 'SOTTOPESO';
        else if (value < 25)
            return 'NORMOPESO';
        else if (value < 30)
            return 'SOVRAPPESO';
        else
            return 'OBESO';
    });

    // aggiunge 1 kg al peso
    // update() riceve il valore corrente e restituisce il nuovo valore
    addWeight(): void {
        this.bmiData.weight.update((v: number) => v + 1);
    }

    // sottrae 1 kg al peso, ma non scende sotto 1
    // set() imposta direttamente il nuovo valore
    subtractWeight(): void {
        let current = this.bmiData.weight();    // legge il valore corrente
        if (current > 1)                        // controlla che non vada sotto 1
            this.bmiData.weight.set(current - 1);
    }

    // aggiunge 1 cm all'altezza
    addHeight(): void {
        this.bmiData.height.update((v: number) => v + 1);
    }

    // sottrae 1 cm all'altezza, ma non scende sotto 1
    subtractHeight(): void {
        let current = this.bmiData.height();    // legge il valore corrente
        if (current > 1)                        // controlla che non vada sotto 1
            this.bmiData.height.set(current - 1);
    }
}
