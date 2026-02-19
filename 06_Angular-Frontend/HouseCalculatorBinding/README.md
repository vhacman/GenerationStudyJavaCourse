# HouseCalculatorBinding — Calcolatore Immobiliare con Two-way Binding

Calcolatore del prezzo di un immobile basato su metratura e prezzo al metro quadro, reimplementazione di HouseCalculator con **FormsModule e Two-way Binding** al posto dei Signals.

## Funzionalità

- **Inserimento indirizzo**: campo testuale libero
- **Prezzo al mq**: prezzo unitario in EUR
- **Superficie stanza principale**: mq stanza
- **Superficie bagno**: mq bagno
- **Superficie totale**: `smMainRoom + smBath` (calcolata automaticamente)
- **Prezzo totale**: `superficieTotale * prezzoAlMq` (calcolato automaticamente)

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Two-way Binding** | `[(ngModel)]` — sincronizzazione bidirezionale tra input HTML e proprietà TypeScript |
| **FormsModule** | Abilitazione `ngModel` nei Standalone Components |
| **Metodi ordinari** | `smpTotal()` e `totalPrice()` — ricalcolati ad ogni change detection (no Signals) |
| **Interface locale** | `House` dichiarata nel componente, non esportata |

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `App` | Root component, ospita HouseForm |
| `HouseForm` | Form principale con input indirizzo, prezzo/mq, superfici e output calcolati |

## Modello

```typescript
interface House {
    address:    string;
    smPrice:    number;   // prezzo al mq
    smMainRoom: number;   // mq stanza principale
    smBath:     number;   // mq bagno
}
```

## Confronto con HouseCalculator

| Aspetto | HouseCalculator | HouseCalculatorBinding |
|---------|-----------------|------------------------|
| Reattività | `signal()` + `computed()` | `[(ngModel)]` + metodi |
| Ricalcolo | Lazy (solo dipendenze) | Ad ogni change detection |
| FormsModule | No | Sì |
| Campi | Area + prezzo/mq | Indirizzo + prezzo/mq + stanza + bagno |

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
