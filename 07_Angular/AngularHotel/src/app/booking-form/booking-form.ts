import { Component, computed, signal } from '@angular/core';
import { Booking } from '../model/Booking';

@Component({
	selector: 'app-booking-form',
	imports: [],
	templateUrl: './booking-form.html',
	styleUrl: './booking-form.css',
})
export class BookingForm {

	booking: Booking = {
		date: new Date().toLocaleDateString('it-IT'),
		nights: signal<number>(1),
		fullBoard: signal<boolean>(false),
	};

	// versione con ternario
	// total = computed(() => this.booking.nights() * (this.booking.fullBoard() ? 70 : 50));

	total = computed(() => {
		if (this.booking.fullBoard())
			return this.booking.nights() * 70;
		else
			return this.booking.nights() * 50;
	});

	// versione con ternario
	// freeTransport = computed(() => this.total() > 700);

	freeTransport = computed(() => {
		if (this.total() > 700)
			return true;
		else
			return false;
	});

	addNight(): void {
		this.booking.nights.update((v: number) => v + 1);
	}

	subtractNight(): void {
		let current = this.booking.nights();
		if (current > 1)
			this.booking.nights.set(current - 1);
	}

	changeFullBoard(): void {
		this.booking.fullBoard.update((v: boolean) => !v);
	}
}
