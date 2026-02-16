# AngularHotel — Hotel Booking Calculator

Applicazione per il calcolo del costo di una prenotazione alberghiera con gestione automatica del prezzo, full board e servizio navetta.

## Funzionalità

- **Calcolo prezzo**: formula `notti × 50` (senza full board) o `notti × 70` (con full board)
- **Full board**: toggle per attivare/disattivare il trattamento all-inclusive
- **Contatore notti**: pulsanti +/- per incrementare o decrementare le notti (minimo 1)
- **Navetta gratuita**: servizio navetta incluso automaticamente se il totale supera 700€
- **Data prenotazione**: visualizzazione automatica della data odierna in formato italiano

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Signals** | `signal()` per stato reattivo (nights, fullBoard) |
| **Computed Signals** | `computed()` per totale e navetta gratuita |
| **Standalone Components** | Architettura senza NgModules |
| **Component Composition** | Composizione gerarchica App → BookingForm |
| **Interface Model** | `Booking` con mix di tipi primitivi e `WritableSignal` |

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `App` | Root component, ospita il BookingForm |
| `BookingForm` | Card principale con gestione prenotazione e calcoli |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- Vitest per testing
