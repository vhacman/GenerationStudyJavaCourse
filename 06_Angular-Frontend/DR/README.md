# DR — Dish Register

Progetto didattico che confronta **due approcci Angular** per la gestione di un form di inserimento piatto: metodi ordinari vs Computed Signals.

## Funzionalità

- **Registrazione piatto**: nome, costo, prezzo, tipo dieta, macronutrienti
- **Margine automatico**: differenza prezzo - costo (read-only)
- **Calorie automatiche**: `carbs*4 + proteins*4 + fats*9` (read-only)
- **Tipo dieta**: selezione tra Omnivore / Vegetarian / Vegan

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Two-way Binding** | `[(ngModel)]` — "banana in a box" — sincronizzazione bidirezionale TS ↔ HTML |
| **One-way Binding** | `[value]="metodo()"` — sola lettura da TS verso HTML |
| **FormsModule** | Abilitazione `ngModel` nei componenti Standalone |
| **Computed Signals** | `computed()` — ricalcolo selettivo solo sulle dipendenze cambiate |
| **Metodi vs Computed** | Confronto: metodi ricalcolano ad ogni change detection, `computed()` solo quando le dipendenze cambiano |

## Componenti

| Componente | Approccio | Descrizione |
|------------|-----------|-------------|
| `DishForm` | Metodi ordinari | Form con `margin()` e `calories()` come funzioni — si rieseguono ad ogni evento |
| `DishFormSignal` | Signals + Computed | Form con `margin` e `calories` come `computed()` — ricalcolo lazy sulle dipendenze |

## Modello

```typescript
// DishForm (locale al componente)
interface Dish {
    name: string;
    cost: number;
    price: number;
    description: string;
    type: "O" | "V" | "VG";
    carbs: number;
    proteins: number;
    fats: number;
}

// DishFormSignal (interfaccia esportata)
interface Dish {
    name: string;
    description: string;
    type: "O" | "V" | "VG";
    price: WritableSignal<number>;
    cost: WritableSignal<number>;
    carbs: WritableSignal<number>;
    proteins: WritableSignal<number>;
    fats: WritableSignal<number>;
}
```

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- FormsModule (`ngModel`)
- Vitest per testing

## Avvio

```bash
npm install
ng serve
```
