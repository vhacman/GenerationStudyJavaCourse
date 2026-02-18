import { WritableSignal } from '@angular/core';

export interface Dish {
    name: string;
    description: string;
    type: "O" | "V" | "VG";
    price: WritableSignal<number>;
    cost: WritableSignal<number>;
    carbs: WritableSignal<number>;
    proteins: WritableSignal<number>;
    fats: WritableSignal<number>;
}
