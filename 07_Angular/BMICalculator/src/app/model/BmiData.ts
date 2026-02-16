// modello per il calcolo del BMI
// si preferisce l'interfaccia alla classe in TypeScript
// perché serve solo a definire la struttura dei dati, senza logica

// WritableSignal è un tipo di Angular che rappresenta una variabile reattiva
// "reattiva" significa che quando il suo valore cambia, il template si aggiorna automaticamente
import { WritableSignal } from "@angular/core";

// export: rende l'interfaccia utilizzabile in altri file tramite import
// interface: definisce la forma dell'oggetto (quali campi ha e di che tipo sono)
export interface BmiData
{
    weight:     WritableSignal<number>;   // peso in kg - WritableSignal perché deve essere modificabile
    height:     WritableSignal<number>;   // altezza in cm - WritableSignal perché deve essere modificabile
}
