# HouseCalculator — Calcolatore Immobiliare

Calcolatore per il prezzo di un immobile basato su metratura e prezzo al metro quadro, con qualificazione automatica "open space".

## Funzionalità

- **Calcolo prezzo**: `area * prezzo al mq` con aggiornamento in tempo reale
- **Gestione area**: incremento/decremento di 1 mq tramite pulsanti
- **Gestione prezzo/mq**: incremento/decremento di 50 EUR tramite pulsanti
- **Open Space**: qualificazione automatica per immobili con area > 60 mq

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Signals** | `signal()` per area (default 40) e prezzo/mq (default 1000) |
| **Computed Signals** | `computed()` per prezzo totale e qualificazione open space |
| **Standalone Components** | Architettura senza NgModules |

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `HousePreview` | Calcolatore principale con controlli area e prezzo |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- Vitest per testing
