import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HouseLoan } from './house-loan/house-loan';
import { FormSubmittedEvent } from '@angular/forms';
import { FormMoneyCost } from './form-money-cost/form-money-cost';
import { PeopleList } from './people-list/people-list';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HouseLoan, FormMoneyCost, PeopleList],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('Example');
}
