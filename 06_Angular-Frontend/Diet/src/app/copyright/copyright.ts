import { Component } from '@angular/core';

@Component({
  selector: 'app-copyright',
  imports: [],
  templateUrl: './copyright.html',
  styleUrl: './copyright.css',
})
export class Copyright {

    // La logica del componente è definita nella classe Typescript
    // le sue proprietà e i suoi metodi sono disponibili
    // nell'html

    company:string  =   "Evil Diet Inc";
    year:number     =   new Date().getFullYear(); 


}
