import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { FoodComponent } from './food/food.component';
import { BMIComponent } from "./bmi/bmi.component";
import { FitnessProgramComponent } from "./fitness-program/fitness-program.component";
import { HouseComponent } from "./house/house.component";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [BMIComponent, FitnessProgramComponent, HouseComponent ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {

}
