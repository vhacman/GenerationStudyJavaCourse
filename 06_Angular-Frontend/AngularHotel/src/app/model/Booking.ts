// modello per una prenotazione alberghiera
// si preferisce l'interfaccia alla classe in TypeScript

import { WritableSignal } from "@angular/core";

export interface Booking
{
	date:		string;
	nights:		WritableSignal<number>;
	fullBoard:	WritableSignal<boolean>;
}
