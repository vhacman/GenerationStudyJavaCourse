import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { ReceiptPreview } from "./receipt-preview/receipt-preview";

@Component({
  selector: 'app-root',
  imports: [ReceiptPreview],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('HC');
}