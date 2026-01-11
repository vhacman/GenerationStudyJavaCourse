# 📘 Modulo Examples - Fondamenti di Java

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Examples/`

---

## 📋 Panoramica

Esempi pratici di programmazione Java che coprono i concetti fondamentali del linguaggio. Ogni programma è progettato per insegnare specifici concetti attraverso applicazioni reali e pratiche.

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

---

### 📐 Geometry (com.generation.geometry)

<table>
<tr>
<td width="50%">

#### **SquareCalculator.java**
**Calcoli sul Quadrato**

**Input:**
- Lato del quadrato (double)

**Calcoli:**
- Area = lato²
- Perimetro = lato × 4

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

</td>
</tr>
</table>

---

### 🏠 Housing (com.generation.housing)

#### **PrintHouseLabel.java** - Calcolatore Immobiliare

**Funzionalità:**
- Calcolo superficie totale immobile
- Calcolo prezzo finale con valutazione balcone

**Logica di Calcolo:**
```java
Superficie Totale = Σ(stanze) + bagni
Se balcone presente:
    Superficie Balcone = lunghezza × larghezza
    Valore Balcone = Superficie × Prezzo/m² × 0.5
Prezzo Finale = (Superficie Totale × Prezzo/m²) + Valore Balcone
```

---

### ✈️ Trips (com.generation.trips)

#### **FamilyTripCalculator.java** - Pianificatore Viaggio Famiglia

**Calcoli:**
```java
Costo Voli Totale = costo_volo × numero_viaggiatori
Costo Soggiorno = budget_giornaliero × notti × viaggiatori
Costo Totale = Costo Voli + Costo Soggiorno
Media per Persona = Costo Totale / viaggiatori
```

---

## 🎯 Concetti Chiave Appresi

<table>
<tr>
<td width="50%">

#### 📝 Variabili e Tipi di Dati
- `int`, `double`, `String`, `boolean`
- Dichiarazione e inizializzazione
- Casting e conversioni

#### ⌨️ Input/Output
- `Scanner` per input utente
- `System.out.print/println`
- Formattazione output

</td>
<td width="50%">

#### ➕ Operazioni Aritmetiche
- Operatori base (+, -, *, /, %)
- Precedenza operatori
- Math class methods

#### 🏗️ Struttura Programma
- Package organization
- Import statements
- Main method

</td>
</tr>
</table>

---

## 🚀 Come Eseguire i Programmi

### Da Terminale

```bash
# Compila il programma
javac -d bin src/com/generation/[package]/[NomeFile].java

# Esegui il programma
java -cp bin com.generation.[package].[NomeFile]
```

---

## 📚 Percorso di Apprendimento Consigliato

### 🎓 Livello Principiante
1. **HelloWorld.java** - Comprendi la struttura base
2. **HelloMe.java** - Impara l'input utente
3. **SquareCalculator.java** - Calcoli semplici

### 🎓 Livello Intermedio
4. **RectangleCalculator.java** - Multiple variabili
5. **NeedCalculator.java** - Formule complesse
6. **FamilyTripCalculator.java** - Logica business

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato questo modulo? Passa al successivo!

**Ricorda:** La pratica è la chiave per padroneggiare Java!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
