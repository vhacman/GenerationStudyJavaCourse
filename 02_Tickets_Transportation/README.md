# 🚆 Tickets & Transportation

Progetti dedicati alla gestione di sistemi di biglietteria e trasporti pubblici, applicando concetti OOP a scenari reali.

## 📁 Progetti

```
02_Tickets_Transportation/
├── BrianzaTaxi/              # Sistema prenotazione taxi
├── BrianzaTaxiService/       # Servizio taxi con tariffazione
├── BrianzaTrains/            # Sistema biglietteria treni
├── DiscotecaTicket/          # Biglietteria eventi (controllo età)
├── MLTrains/                 # Milano-Lecco Treni v1
├── MilanoLeccoTRains2/      # Milano-Lecco Treni v2
├── MilanoLeccoTrains3/       # Milano-Lecco Treni v3
├── MIlanoLeccoTrains4/       # Milano-Lecco Treni v4 (completo)
└── MonzaMetro/              # Biglietteria metropolitana
```

## 📋 Dettaglio Progetti

### BrianzaTaxi
Sistema di prenotazione corse taxi con gestione clienti e tariffe.

**Concetti applicati:**
- Classi per Taxi, Corsa, Cliente
- Liste di oggetti
- Interazione utente base

### BrianzaTaxiService
Evoluzione di BrianzaTaxi con calcolo automatico tariffe.

**Concetti applicati:**
- Ereditarietà
- Polimorfismo
- Metodi di calcolo

### BrianzaTrains
Primo sistema di biglietteria ferroviaria.

**Concetti applicati:**
- Classi Treno, Biglietto, Stazione
- Validazione dati
- Gestione orari

### DiscotecaTicket
Sistema biglietteria discoteca con controllo età e restrizioni.

**Concetti applicati:**
- Validazione date (LocalDate)
- Controllo requisiti (età minima)
- Gestione eventi multipli

### MLTrains / MilanoLeccoTrains*
Serie di progetti per la gestione treni Milano-Lecco con evoluzione progressiva.

| Versione | Caratteristiche |
|----------|------------------|
| MLTrains | Versione base |
| MilanoLeccoTRains2 | Miglioramento struttura classi |
| MilanoLeccoTrains3 | Funzionalità avanzate |
| MIlanoLeccoTrains4 | Completa con abstract classes ed enum |

**Concetti applicati:**
- Abstract classes
- Enum per tipologie
- BigDecimal per prezzi
- Collection avanzate

### MonzaMetro
Biglietteria per metropolitana con sistema di pricing complesso.

**Concetti applicati:**
- BigDecimal per calcoli finanziari
- Enum per tariffe
- Gestione abbonamenti

## 🎯 Obiettivi Didattici

- Applicare OOP a scenari reali
- Comprendere ereditarietà e polimorfismo
- Gestire date e calcoli temporali
- Implementare validazione dati
- Usare BigDecimal per valori monetari

## 🛠️ Tecnologie

- Java 17+
- Eclipse IDE
- Nessuna dipendenza esterna

## 📖 Concetti OOP Applicati

| Progetto | Concetti |
|----------|-----------|
| BrianzaTaxi | Classi base, oggetti, Liste |
| BrianzaTaxiService | Ereditarietà, Override |
| BrianzaTrains | Classi multiple, validazione |
| DiscotecaTicket | LocalDate, validazione condizionale |
| MLTrains* | Abstract classes, Enum |
| MonzaMetro | BigDecimal, Enum, Pattern pricing |

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
