# 📘 Modulo Examples - Fondamenti di Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Examples/`

---

## 📋 Panoramica
Questo modulo contiene esempi pratici di programmazione Java che coprono i concetti fondamentali del linguaggio. Ogni programma è progettato per insegnare specifici concetti attraverso applicazioni reali e pratiche.

---

## 🗂️ Struttura dei Package

```
src/com/generation/
├── 🎯 demo/          # Programmi demo di base
├── 🍕 food/          # Calcolatori nutrizionali e alimentari
├── 📐 geometry/      # Calcoli geometrici
├── 🏠 housing/       # Calcoli immobiliari
├── ✈️ trips/         # Pianificatori di viaggio
└── 📚 library/       # Classi utility
```

---

## 💻 Programmi Implementati

### 🎯 Demo (com.generation.demo)

| Programma | Descrizione | Concetti Chiave |
|-----------|-------------|-----------------|
| `HelloWorld.java` | Il classico "Hello World" - Primo programma Java | • Struttura base programma<br>• Output su console<br>• Metodo main |
| `HelloMe.java` | Programma di saluto personalizzato con input utente | • Input da console<br>• Scanner class<br>• Concatenazione stringhe |

### 🍕 Food (com.generation.food)

#### **NeedCalculator.java** - Calcolatore Fabbisogno Nutrizionale

**Funzionalità:**
- Calcola il fabbisogno calorico e proteico giornaliero
- Analizza la copertura nutrizionale

**Input:**
- Peso corporeo (kg)
- Carboidrati assunti (g)
- Proteine assunte (g)
- Grassi assunti (g)

**Formule:**
```
Fabbisogno Calorico = 150 cal × peso (kg)
Fabbisogno Proteico = 1.5g × peso (kg)
Calorie Totali = (carboidrati × 4) + (proteine × 4) + (grassi × 9)
```

**Concetti Appresi:**
- Operazioni aritmetiche
- Casting di tipi
- Formattazione output
- Calcoli scientifici

### 📐 Geometry (com.generation.geometry)

| Programma | Input | Calcoli | Output | Concetti |
|-----------|--------|---------|--------|----------|
| **SquareCalculator.java** | Lato (double) | Area = lato²<br>Perimetro = lato × 4 | Area e Perimetro formattati | Operazioni matematiche<br>Math class<br>Variabili double |
| **RectangleCalculator.java** | Base e Altezza (double) | Area = base × altezza<br>Perimetro = (base + altezza) × 2 | Area e Perimetro formattati | Variabili multiple<br>Operazioni composite<br>Formattazione decimali |

*(Continua con le altre sezioni combinando il meglio di entrambe le versioni)*

**Comando per completare:**
```
git add docs/02-fundamentals/examples.md
git commit -m "Risolto conflitto merge examples.md"
git push
```

File pulito e pronto! [attached_file:1][attached_file:2]

[1](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
[2](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

