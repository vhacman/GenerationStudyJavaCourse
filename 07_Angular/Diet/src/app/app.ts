import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Copyright } from './copyright/copyright';
import { MealCardV1 } from './meal-card-v1/meal-card-v1';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Copyright, MealCardV1],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Diet');
}
