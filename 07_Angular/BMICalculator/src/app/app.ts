// importa il decoratore Component da Angular
import { Component } from '@angular/core';
// importa il componente BmiForm che abbiamo creato
import { BmiForm } from './bmi-form/bmi-form';

// decoratore che definisce il componente principale dell'applicazione
@Component({
  selector: 'app-root',        // tag HTML usato in index.html per caricare l'app
  imports: [BmiForm],           // lista dei componenti figli usati nel template
  templateUrl: './app.html',    // file HTML del template
  styleUrl: './app.css'         // file CSS degli stili
})
// componente radice: è il punto di partenza dell'applicazione
// contiene solo il componente BmiForm, quindi non ha bisogno di logica
export class App {
}
