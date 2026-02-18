import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HouseForm } from './house-form/house-form';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HouseForm],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('HouseCalculatorBinding');
}
