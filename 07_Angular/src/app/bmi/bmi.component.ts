import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-bmi',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './bmi.component.html',
  styleUrl: './bmi.component.css'
})
export class BMIComponent {

    name:string='';
    height:number=170;
    weight:number=70;

    bmi():number{
        return this.weight / (this.height * this.height / 10000);
    }

}
