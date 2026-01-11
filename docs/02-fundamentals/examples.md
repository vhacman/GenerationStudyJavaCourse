# 📘 Modulo Examples - Fondamenti di Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Examples/`

---

## 📋 Panoramica

<<<<<<< HEAD
=======
Questo modulo contiene esempi pratici di programmazione Java che coprono i concetti fondamentali del linguaggio. Ogni programma è progettato per insegnare specifici concetti attraverso applicazioni reali e pratiche.
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8

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

<table>
<tr>
<td width="30%"><b>Programma</b></td>
<td width="40%"><b>Descrizione</b></td>
<td width="30%"><b>Concetti Chiave</b></td>
</tr>
<tr>
<td><code>HelloWorld.java</code></td>
<td>Il classico "Hello World" - Primo programma Java</td>
<td>
• Struttura base programma<br>
• Output su console<br>
• Metodo main
</td>
</tr>
<tr>
<td><code>HelloMe.java</code></td>
<td>Programma di saluto personalizzato con input utente</td>
<td>
• Input da console<br>
• Scanner class<br>
• Concatenazione stringhe
</td>
</tr>
</table>

---

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

**Formule Implementate:**
```java
Fabbisogno Calorico = 150 cal × peso (kg)
Fabbisogno Proteico = 1.5g × peso (kg)
Calorie Totali = (carboidrati × 4) + (proteine × 4) + (grassi × 9)
```

**Output:**
- Percentuale copertura calorica
- Percentuale copertura proteica
- Report nutrizionale completo

<<<<<<< HEAD
---

### 📐 Geometry (com.generation.geometry)

<table>
<tr>
<td width="50%">

#### **SquareCalculator.java**
**Calcoli sul Quadrato**

**Input:**
- Lato del quadrato (double)

=======
**Concetti Appresi:**
- Operazioni aritmetiche
- Casting di tipi
- Formattazione output
- Calcoli scientifici

---

#### **PrintFoodLabel.java** - Generatore Etichette Alimentari

**Funzionalità:**
- Creazione etichette alimentari formattate
- Generazione HTML per visualizzazione

**Concetti Appresi:**
- String formatting
- HTML embedding in Java
- Template design

---

#### **FoodAnalyzer.java** - Analizzatore Alimenti

**Funzionalità:**
- Analisi composizione alimenti
- Strumenti di valutazione nutrizionale

**Concetti Appresi:**
- Analisi dati
- Logica condizionale

---

### 📐 Geometry (com.generation.geometry)

<table>
<tr>
<td width="50%">

#### **SquareCalculator.java**
**Calcoli sul Quadrato**

**Input:**
- Lato del quadrato (double)

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
**Calcoli:**
- Area = lato²
- Perimetro = lato × 4

<<<<<<< HEAD
=======
**Output:**
- Area formattata
- Perimetro formattato

**Concetti:**
- Operazioni matematiche
- Math class
- Variabili double

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
</td>
<td width="50%">

#### **RectangleCalculator.java**
**Calcoli sul Rettangolo**

**Input:**
- Base (double)
- Altezza (double)

**Calcoli:**
- Area = base × altezza
- Perimetro = (base + altezza) × 2

<<<<<<< HEAD
=======
**Output:**
- Area formattata
- Perimetro formattato

**Concetti:**
- Variabili multiple
- Operazioni composite
- Formattazione decimali

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
</td>
</tr>
</table>

---

### 🏠 Housing (com.generation.housing)

#### **PrintHouseLabel.java** - Calcolatore Immobiliare

**Funzionalità:**
- Calcolo superficie totale immobile
- Calcolo prezzo finale con valutazione balcone

<<<<<<< HEAD
=======
**Input Richiesti:**
```java
• Dimensioni stanze (lunghezza × larghezza)
• Numero bagni
• Presenza balcone (sì/no)
• Dimensioni balcone (se presente)
• Prezzo al metro quadro (€/m²)
```

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
**Logica di Calcolo:**
```java
Superficie Totale = Σ(stanze) + bagni
Se balcone presente:
    Superficie Balcone = lunghezza × larghezza
    Valore Balcone = Superficie × Prezzo/m² × 0.5
Prezzo Finale = (Superficie Totale × Prezzo/m²) + Valore Balcone
```

<<<<<<< HEAD
=======
**Output Formattato:**
- Dettagli proprietà
- Superficie totale
- Prezzo finale
- Prezzo al metro quadro

**Concetti Avanzati:**
- Cicli for per input multipli
- Calcoli condizionali
- Accumulo valori
- Formattazione currency

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
---

### ✈️ Trips (com.generation.trips)

#### **FamilyTripCalculator.java** - Pianificatore Viaggio Famiglia

<<<<<<< HEAD
=======
**Funzionalità:**
- Calcolo costo totale vacanza famiglia
- Analisi costo per persona

**Input:**
```java
• Numero viaggiatori
• Costo voli (andata + ritorno per persona)
• Numero notti
• Budget giornaliero per persona
```

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
**Calcoli:**
```java
Costo Voli Totale = costo_volo × numero_viaggiatori
Costo Soggiorno = budget_giornaliero × notti × viaggiatori
Costo Totale = Costo Voli + Costo Soggiorno
Media per Persona = Costo Totale / viaggiatori
```

<<<<<<< HEAD
=======
**Output:**
- Breakdown dettagliato costi
- Costo totale viaggio
- Media per persona
- Suggerimenti budget

**Concetti:**
- Variabili multiple
- Calcoli finanziari
- Media aritmetica
- Formattazione valute

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
---

## 🎯 Concetti Chiave Appresi

<<<<<<< HEAD
=======
### Fondamenti Java

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
<table>
<tr>
<td width="50%">

#### 📝 Variabili e Tipi di Dati
- `int`, `double`, `String`, `boolean`
- Dichiarazione e inizializzazione
- Casting e conversioni
<<<<<<< HEAD
=======
- Costanti (`final`)
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8

#### ⌨️ Input/Output
- `Scanner` per input utente
- `System.out.print/println`
<<<<<<< HEAD
- Formattazione output
=======
- Formattazione output (`printf`)
- Buffer management
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8

</td>
<td width="50%">

#### ➕ Operazioni Aritmetiche
- Operatori base (+, -, *, /, %)
- Precedenza operatori
<<<<<<< HEAD
=======
- Operatori incremento/decremento
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
- Math class methods

#### 🏗️ Struttura Programma
- Package organization
- Import statements
- Main method
<<<<<<< HEAD
=======
- Commenti e documentazione
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8

</td>
</tr>
</table>

---

## 🚀 Come Eseguire i Programmi

<<<<<<< HEAD
### Da Terminale
=======
### Metodo 1: Da IDE (IntelliJ/Eclipse)

```bash
1. Apri il progetto nell'IDE
2. Naviga al file .java desiderato
3. Click destro sul file
4. Seleziona "Run As" → "Java Application"
5. Segui le istruzioni nella console
```

### Metodo 2: Da Terminale
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8

```bash
# Compila il programma
javac -d bin src/com/generation/[package]/[NomeFile].java

# Esegui il programma
java -cp bin com.generation.[package].[NomeFile]
```

<<<<<<< HEAD
=======
### Esempio Pratico:
```bash
# Compilare NeedCalculator
javac -d bin src/com/generation/food/NeedCalculator.java

# Eseguire NeedCalculator
java -cp bin com.generation.food.NeedCalculator
```

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
---

## 📚 Percorso di Apprendimento Consigliato

<<<<<<< HEAD
=======
```mermaid
graph LR
    A[1. HelloWorld] --> B[2. HelloMe]
    B --> C[3. SquareCalculator]
    C --> D[4. RectangleCalculator]
    D --> E[5. NeedCalculator]
    E --> F[6. FamilyTripCalculator]
    F --> G[7. PrintHouseLabel]
```

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
### 🎓 Livello Principiante
1. **HelloWorld.java** - Comprendi la struttura base
2. **HelloMe.java** - Impara l'input utente
3. **SquareCalculator.java** - Calcoli semplici

### 🎓 Livello Intermedio
4. **RectangleCalculator.java** - Multiple variabili
5. **NeedCalculator.java** - Formule complesse
6. **FamilyTripCalculator.java** - Logica business

<<<<<<< HEAD
=======
### 🎓 Livello Avanzato (per questo modulo)
7. **PrintHouseLabel.java** - Cicli e condizioni
8. **PrintFoodLabel.java** - String formatting avanzato

---

## 💡 Suggerimenti per lo Studio

### ✅ Best Practices

1. **Leggi il codice prima di eseguirlo**
   - Cerca di capire cosa fa ogni riga
   - Identifica variabili e tipi

2. **Esegui e sperimenta**
   - Prova diversi input
   - Osserva gli output

3. **Modifica il codice**
   - Cambia le formule
   - Aggiungi nuove funzionalità
   - Migliora l'output

4. **Commenta il tuo codice**
   - Spiega la logica
   - Documenta le formule

### 🎯 Esercizi Proposti

**Per ogni programma:**
- [ ] Eseguilo e comprendine il funzionamento
- [ ] Modifica le formule di calcolo
- [ ] Aggiungi validazione input
- [ ] Migliora la formattazione output
- [ ] Crea una versione con menu

---

## 🐛 Problemi Comuni e Soluzioni

| Problema | Causa | Soluzione |
|----------|-------|-----------|
| `ClassNotFoundException` | Classe non trovata | Verifica il path e il nome della classe |
| `InputMismatchException` | Input tipo sbagliato | Usa `try-catch` o valida l'input |
| `ArithmeticException` | Divisione per zero | Controlla i valori prima di dividere |
| Output formattato male | Printf format errato | Verifica i format specifiers (`%d`, `%f`, `%s`) |

---

## 📖 Risorse Aggiuntive

### 📚 Documentazione
- [Java SE Documentation](https://docs.oracle.com/javase/)
- [Java Tutorials - Oracle](https://docs.oracle.com/javase/tutorial/)

### 🎥 Tutorial Consigliati
- Java Basics for Beginners
- Understanding Variables and Data Types
- Input/Output in Java

>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---
<<<<<<< HEAD

<div align="center">

### ⭐ Hai completato questo modulo? Passa al successivo!

**Ricorda:** La pratica è la chiave per padroneggiare Java!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
=======
>>>>>>> cbf0a2b90a8ac87e90f1503e722ceca9defd49b8
