// ricevuta. Modello per una ricevuta
// una entity ma in typescript si preferiscono le interface alle classi

import { WritableSignal } from "@angular/core";

// poi vi dirò perché

export interface Receipt {

    customer:string;
    date:string;
    hours:WritableSignal<number>;
    frail:WritableSignal<boolean>;

}