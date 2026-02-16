import { Component } from '@angular/core';
import { HousePreview } from './house-preview/house-preview';

@Component({
  selector: 'app-root',
  imports: [HousePreview],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
}
