# BMICalculator — Calcolatore Indice di Massa Corporea

Calcolatore BMI che computa dinamicamente l'indice di massa corporea in base a peso e altezza, mostrando il valore e la categoria di salute corrispondente.

## Funzionalità

- **Calcolo BMI**: formula `peso / (altezza in metri)²` con protezione divisione per zero
- **Categorie salute**: classificazione WHO in italiano
  - BMI < 18.5 → SOTTOPESO
  - BMI 18.5–25 → NORMOPESO
  - BMI 25–30 → SOVRAPPESO
  - BMI ≥ 30 → OBESO
- **Gestione peso**: pulsanti +/- (minimo 1 kg)
- **Gestione altezza**: pulsanti +/- (minimo 1 cm)
- **Formattazione**: BMI arrotondato a 1 decimale con `DecimalPipe`

## Concetti Angular

| Concetto | Dettaglio |
|----------|-----------|
| **Signals** | `WritableSignal` per peso (70 kg) e altezza (170 cm) |
| **Computed Signals** | `computed()` per BMI e categoria salute |
| **DecimalPipe** | Formattazione output `number:'1.1-1'` |
| **Interface Model** | `BmiData` con proprietà reattive |
| **Standalone Components** | Architettura senza NgModules |

## Modello

```typescript
BmiData {
    weight: WritableSignal<number>   // peso in kg
    height: WritableSignal<number>   // altezza in cm
}
```

## Componenti

| Componente | Descrizione |
|------------|-------------|
| `App` | Root component, ospita il BmiForm |
| `BmiForm` | Calcolatore principale con input peso/altezza e output BMI/categoria |

## Stack

- Angular 21 (Standalone Components)
- TypeScript 5.9
- Vitest per testing
