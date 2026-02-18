import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

interface House {
	address:				string;
	smPrice:				number;
	smMainRoom:			number;
	smBath:					number; 
}


@Component({
	selector: 'app-house-form',
	imports: [FormsModule],
	templateUrl: './house-form.html',
	styleUrl: './house-form.css',
})

export class HouseForm {
	house:House = {
		address: "",
		smPrice: 0,
		smMainRoom: 0,
		smBath: 0,
	}

//Io non sono computed, vengo ricalcolato a ogni cambiamento

smpTotal(): number {
	console.log("Sto ricalcolando la superficie totale");
	return this.house.smMainRoom + this.house.smBath;
}

totalPrice(): number {
	console.log("Sto ricalcolando il prezzo finale");
	return this.smpTotal() * this.house.smPrice;
}

}
