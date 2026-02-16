# LezioneExtra — Progetto Didattico Multi-App

Progetto didattico completo che dimostra molteplici funzionalità Angular attraverso 4 sotto-applicazioni: calcolatore BMI, programma fitness, tracker nutrizionale e calcolatore immobiliare con stanze.

## Funzionalità

### BMI Calculator
- Input per nome, peso (kg) e altezza (cm)
- Calcolo BMI automatico: `peso / (altezza² / 10000)`
- Two-way binding con `[(ngModel)]`

### Fitness Program
- Aggiunta esercizi con validazione input non vuoto
- Lista esercizi con rendering `@for` / `@empty`
- Prepend dei nuovi esercizi alla lista

### Food Tracker
- Tracciamento macronutrienti (carboidrati, proteine, grassi) con +/-
- Calcolo calorie: `carbs*4 + proteins*4 + fats*9`
- Aggiornamenti immutabili con spread operator
- Flag vegano per ogni alimento

### House Calculator
- Gestione stanze con tipo, lato1, lato2
- Calcolo area per stanza e totale immobile
- Prezzo calcolato: `area totale * prezzo/mq`
- Form con città, indirizzo, prezzo al mq

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Two-way Binding** | `[(ngModel)]` per form BMI, House, Fitness |
| **FormsModule** | Gestione form con validazione |
| **Control Flow** | Nuova sintassi `@for`, `@empty` |
| **Classi TypeScript** | `Room`, `HouseModel` con metodi calcolati |
| **Interfacce** | `FoodModel` per tipo sicuro |
| **Spread Operator** | Aggiornamenti immutabili dello stato |

## Modelli

```typescript
FoodModel { name, carbs, proteins, fats, vegan, price }
Room { type, side1, side2, area() }
HouseModel { city, address, smp, rooms[], area(), price() }
```

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `BMIComponent` | Calcolatore indice di massa corporea |
| `FoodComponent` | Tracker nutrienti con modello FoodModel |
| `FitnessProgramComponent` | Gestore lista esercizi |
| `HouseComponent` | Calcolatore immobiliare con stanze |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- W3.CSS 4 (layout responsive a griglia)
- FormsModule
- Vitest per testing
