# PCWizard

Applicazione wizard interattiva per la configurazione personalizzata di PC.

## Descrizione

PCWizard è un configuratore interattivo che permette di costruire PC personalizzati selezionando componenti singoli oppure scegliendo configurazioni pre-built (ECONOMY, STANDARD, DELUXE). Calcola automaticamente costi, benchmark e genera preventivi HTML.

## Struttura del Progetto

```
PCWizard/
├── src/
│   └── com/generation/pcw/
│       ├── controller/
│       │   └── PcWizard.java
│       ├── model/
│       │   └── entities/
│       │       ├── PC.java
│       │       ├── Processor.java
│       │       ├── Gpu.java
│       │       ├── Ram.java
│       │       ├── Storage.java
│       │       └── PcTier.java
│       └── view/
│           └── PcView.java
└── print/
└── template/
```

## Classi Principali

### Model - Entità Principale
**PC.java** - Entità principale rappresentante un PC configurato
- Componenti: processor, gpu, ram, primaryStorage, secondaryStorage
- Dati cliente: nome, cognome, telefono, numero preventivo
- Metodi: `isValid()`, `calculateMaterialCost()`, calcolo benchmark

### Model - Componenti (Enum)

**Processor.java** - Enum processori disponibili
- Valori: M1, M4, I714, I515
- Attributi: benchmark, prezzo, nome, marca

**Gpu.java** - Enum GPU disponibili
- Attributi: benchmark, prezzo, modello, marca

**Ram.java** - Enum RAM disponibili
- Attributi: capacità (GB), velocità (MHz), prezzo

**Storage.java** - Enum storage (SSD)
- Attributi: dimensione (GB), benchmark, prezzo, tipo

**PcTier.java** - Enum configurazioni pre-build
- ECONOMY: configurazione base economica
- STANDARD: configurazione bilanciata
- DELUXE: configurazione high-end

### View
- `PcView.java` - Visualizzazione menu e preview PC

### Controller
- `PcWizard.java` - Controller principale con menu wizard step-by-step

## Funzionalità Implementate

### 1. Configurazione Manuale
- Selezione processore con validazione e conferma
- Selezione GPU con visualizzazione dettagli
- Selezione RAM con opzioni multiple capacità
- Selezione storage primario e secondario opzionale

### 2. Configurazioni Pre-Build
- ECONOMY: componenti entry-level per budget limitato
- STANDARD: componenti mid-range bilanciati
- DELUXE: componenti high-end per performance massime
- Creazione automatica tramite `PC.createFromTier()`

### 3. Dati Cliente e Preventivo
- Inserimento dati cliente (nome, cognome, telefono)
- Numero preventivo univoco
- Inserimento costo manodopera
- Validazione prezzo finale non inferiore a costo totale

### 4. Calcoli Automatici
- Calcolo automatico costo materiali (somma prezzi componenti)
- Calcolo benchmark totale (somma benchmark componenti)
- Validazione completezza configurazione

### 5. Output
- Preview configurazione PC con tutti i dettagli
- Export HTML preventivo completo
- Validazione PC completo prima dell'export

## Pattern Architetturali Utilizzati

- **Enum Pattern**: Processor, Gpu, Ram, Storage, PcTier implementati come enum con attributi e metodi
- **Factory Method**: `PC.createFromTier()` crea PC pre-configurati
- **Wizard Pattern**: Procedura step-by-step guidata da menu
- **Strategy Pattern**: Diversi percorsi configurazione (manuale vs pre-build)
- **Singleton Logic**: Enum garantisce istanza unica per ogni valore
- **Validazione**: `isValid()` verifica completezza configurazione
- **Static Method**: `calculateMaterialCost()` per calcoli centralizzati

## Esecuzione

```bash
cd PCWizard
javac -d bin src/com/generation/pcw/**/*.java
java -cp bin com.generation.pcw.controller.PcWizard
```

## Concetti Java Dimostrati

### Enum Avanzati con Attributi e Metodi
```java
public enum Processor {
    M1("Apple M1", "Apple", 8500, 299.99),
    I714("Intel Core i7-14700K", "Intel", 12000, 419.99);

    private final String name;
    private final String brand;
    private final int benchmark;
    private final double price;

    Processor(String name, String brand, int benchmark, double price) {
        this.name = name;
        this.brand = brand;
        this.benchmark = benchmark;
        this.price = price;
    }

    public double getPrice() { return price; }
    public int getBenchmark() { return benchmark; }
}
```

### Factory Method per Configurazioni Pre-Build
```java
public static PC createFromTier(PcTier tier) {
    PC pc = new PC();
    switch(tier) {
        case ECONOMY:
            pc.setProcessor(Processor.I515);
            pc.setGpu(Gpu.RTX3060);
            // ...
            break;
        case DELUXE:
            pc.setProcessor(Processor.M4);
            pc.setGpu(Gpu.RTX4090);
            // ...
            break;
    }
    return pc;
}
```

### Validazione Configurazione
```java
public boolean isValid() {
    return processor != null
        && gpu != null
        && ram != null
        && primaryStorage != null
        && firstName != null && !firstName.isEmpty()
        && lastName != null && !lastName.isEmpty();
}
```

### Calcolo Aggregato da Enum
```java
public static double calculateMaterialCost(PC pc) {
    double total = 0;
    total += pc.getProcessor().getPrice();
    total += pc.getGpu().getPrice();
    total += pc.getRam().getPrice();
    total += pc.getPrimaryStorage().getPrice();
    if (pc.getSecondaryStorage() != null) {
        total += pc.getSecondaryStorage().getPrice();
    }
    return total;
}
```

## File Chiave

- `PcWizard.java:1` - Controller wizard step-by-step
- `PC.java:1` - Entità principale con factory method
- `Processor.java:1` - Enum processori con attributi
- `PcTier.java:1` - Enum configurazioni pre-build

## Configurazioni Pre-Build

| Tier | Processor | GPU | RAM | Storage | Prezzo Indicativo |
|------|-----------|-----|-----|---------|-------------------|
| ECONOMY | Intel i5-15 | RTX 3060 | 16GB 3200MHz | 512GB SSD | ~1000€ |
| STANDARD | Intel i7-14 | RTX 4070 | 32GB 3600MHz | 1TB SSD | ~1800€ |
| DELUXE | Apple M4 | RTX 4090 | 64GB 4800MHz | 2TB SSD + 4TB HDD | ~4000€ |

## Menu Wizard

1. Configurazione Manuale o Pre-Build?
2. Se Manuale:
   - Selezione Processore
   - Selezione GPU
   - Selezione RAM
   - Selezione Storage Primario
   - Selezione Storage Secondario (opzionale)
3. Se Pre-Build:
   - Selezione Tier (ECONOMY/STANDARD/DELUXE)
4. Inserimento Dati Cliente
5. Inserimento Costo Manodopera
6. Preview Configurazione
7. Export Preventivo HTML
