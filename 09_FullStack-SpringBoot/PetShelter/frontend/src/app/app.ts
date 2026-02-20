import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { PetList } from './pet-list/pet-list';

@Component({
  selector: 'app-root',
  imports: [PetList],  
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
