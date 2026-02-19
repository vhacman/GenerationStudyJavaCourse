# Ippoterapia — Sistema Ricevute Ippoterapia

Sistema di fatturazione per sedute di ippoterapia (terapia con cavalli), con gestione prezzi e agevolazioni per pazienti fragili.

## Funzionalità

- **Gestione ore**: da 1 a 3 ore per seduta con pulsanti +/-
- **Tariffa**: 30 EUR/ora con calcolo automatico del totale
- **Pazienti fragili**: servizio gratuito (totale = 0) per pazienti con status "fragile"
- **Toggle status**: cambio stato fragile/non fragile in tempo reale
- **Ricevute multiple**: visualizzazione di 3 ricevute simultanee

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Signals** | `WritableSignal` per ore e stato fragile |
| **Computed Signals** | `computed()` per totale con logica condizionale |
| **Interface** | `Receipt` con proprietà reattive |
| **Standalone Components** | Architettura senza NgModules |

## Modello

```typescript
Receipt {
  customer: string
  date: string
  hours: WritableSignal<number>
  frail: WritableSignal<boolean>
}
```

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `ReceiptPreview` | Ricevuta con dettagli servizio e calcolo prezzo |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- Vitest per testing
