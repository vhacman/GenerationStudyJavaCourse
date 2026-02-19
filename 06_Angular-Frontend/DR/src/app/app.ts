import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { DishForm } from './dish-form/dish-form';
import { DishFormSignal } from './dish-form-signal/dish-form-signal';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, DishForm, DishFormSignal],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('dr');
}
