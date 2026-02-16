# Diet — Meal Nutrient Tracker

Applicazione per il tracciamento dei macronutrienti di un pasto con calcolo automatico delle calorie e metriche nutrizionali.

## Funzionalità

- **Tracciamento macronutrienti**: gestione di carboidrati, proteine e grassi tramite pulsanti +/-
- **Calorie calcolate**: formula `carbs*4 + proteins*4 + fats*9`
- **Completezza pasto**: verifica automatica se il pasto raggiunge 600+ calorie
- **Completezza proteine**: controllo soglia minima 20g di proteine
- **Percentuale grassi**: calcolo dinamico con gestione divisione per zero
- **Reset e preset**: pulsanti RESET e SET ALL TO 50 per test rapido

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Signals** | `signal()` per stato reattivo (carbs, proteins, fats) |
| **Computed Signals** | `computed()` per calorie, completezza, percentuale grassi |
| **Standalone Components** | Architettura senza NgModules |

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `MealCardV1` | Card principale con gestione nutrienti e calcoli |
| `Copyright` | Footer con nome azienda e anno |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- Vitest per testing
