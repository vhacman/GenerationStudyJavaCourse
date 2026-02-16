import { Component, signal } from '@angular/core';
import { BookingForm } from './booking-form/booking-form';

@Component({
  selector: 'app-root',
  imports: [BookingForm],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('AngularHotel');
}
