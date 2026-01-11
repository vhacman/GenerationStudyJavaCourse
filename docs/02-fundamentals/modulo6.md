# 📘 Modulo 6 - OOP Basics (Basi della Programmazione Orientata agli Oggetti)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Basics-yellow?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Intermedio-orange?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Modulo 6/`

---

## 📋 Panoramica

Introduzione alla Programmazione Orientata agli Oggetti con design e implementazione di classi entità. Impara a creare oggetti, definire attributi e metodi, e comprendere i concetti fondamentali della OOP.

---

## 💻 Programmi Principali

<table>
<tr>
<td width="50%">

### 👤 Main.java
Dimostrazione della classe **Person**
- Creazione oggetti Person
- Manipolazione attributi
- Uso dei metodi

</td>
<td width="50%">

### 🏠 MainHouse.java
Dimostrazione della classe **House**
- Calcoli immobiliari
- Metodi business logic
- Gestione proprietà

</td>
</tr>
</table>

---

## 🏗️ Classi Entità

### 👤 Classe Person

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Attributi:**
```java
public class Person {
    String name;           // Nome della persona
    String surname;        // Cognome
    String dateOfBirth;    // Data di nascita (YYYY-MM-DD)
    String gender;         // Identificazione di genere
}
```

**Costruttore:**
```java
public Person() {
    this.name = "";
    this.surname = "";
    this.dateOfBirth = "";
    this.gender = "";
}
```

**Metodo toString():**
```java
@Override
public String toString() {
    return "Person: " + name + " " + surname +
           ", DOB: " + dateOfBirth + ", Gender: " + gender;
}
```

**Esempio di Utilizzo:**
```java
Person p1 = new Person();
p1.name = "Gabriela";
p1.surname = "Hacman";
p1.dateOfBirth = "1998-10-21";
p1.gender = "F";

System.out.println(p1.toString());
// Output: Person: Gabriela Hacman, DOB: 1998-10-21, Gender: F
```

</details>

---

### 🏠 Classe House

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Attributi:**
```java
public class House {
    String address;    // Indirizzo proprietà
    double area;       // Superficie in metri quadri
    double spm;        // Prezzo per metro quadro (€/m²)
}
```

**Costruttore:**
```java
public House() {
    this.address = "";
    this.area = 0.0;
    this.spm = 0.0;
}
```

**Metodo Business Logic:**
```java
public double getPrice() {
    return area * spm;  // Calcola prezzo totale
}
```

**Metodo toString():**
```java
@Override
public String toString() {
    return "House at " + address +
           ", Area: " + area + " m²" +
           ", Price/m²: €" + spm +
           ", Total Price: €" + getPrice();
}
```

**Esempio di Utilizzo:**
```java
House h1 = new House();
h1.address = "Via Roma 123, Milano";
h1.area = 100.0;
h1.spm = 2000.0;

double price = h1.getPrice();  // Ritorna 200000.0
System.out.println(h1.toString());
// Output: House at Via Roma 123, Milano, Area: 100.0 m²,
//         Price/m²: €2000.0, Total Price: €200000.0
```

</details>

---

## 🎯 Concetti Chiave OOP

### 1️⃣ Classi e Oggetti

<table>
<tr>
<td width="50%">

#### 📐 Classe (Blueprint)
- **Template** per creare oggetti
- Definisce attributi e metodi
- Specifica la struttura
- Non occupa memoria (finché non istanziata)

**Esempio:**
```java
public class Person {
    String name;
    String surname;
}
```

</td>
<td width="50%">

#### 🎁 Oggetto (Istanza)
- **Istanza** creata dalla classe
- Ha valori specifici
- Occupa memoria
- Creato con `new`

**Esempio:**
```java
Person p = new Person();
p.name = "Gabriela";
p.surname = "Hacman";
```

</td>
</tr>
</table>

---

### 2️⃣ Attributi e Metodi

<table>
<tr>
<td width="50%">

#### 📊 Attributi (Stato dell'Oggetto)
- Variabili che definiscono lo stato
- Dati/campi dell'oggetto
- Cosa l'oggetto SA

**Esempio:**
```java
String name;      // Attributo
double area;      // Attributo
int age;          // Attributo
```

</td>
<td width="50%">

#### ⚙️ Metodi (Comportamento)
- Funzioni che definiscono il comportamento
- Cosa l'oggetto PUÒ FARE
- Possono calcolare valori

**Esempio:**
```java
public double getPrice() {
    return area * spm;
}

public String toString() {
    return "Data...";
}
```

</td>
</tr>
</table>

---

### 3️⃣ Costruttori

```java
public Person() {
    this.name = "";
    this.surname = "";
}
```

**Caratteristiche:**
- ✅ Metodo speciale per inizializzare lo stato
- ✅ Chiamato automaticamente con `new`
- ✅ Stesso nome della classe
- ✅ Nessun tipo di ritorno
- ✅ Può avere parametri (overloading)

---

### 4️⃣ Metodo toString()

```java
@Override
public String toString() {
    return "Person: " + name + " " + surname;
}
```

**Scopo:**
- Rappresentazione testuale personalizzata dell'oggetto
- Chiamato automaticamente quando si stampa l'oggetto
- Override del metodo `Object.toString()` di default

---

## 💡 Codice Dimostrativo

### Main.java - Demo Person

```java
public class Main {
    public static void main(String[] args) {
        // Creazione oggetti Person
        Person p1 = new Person();
        p1.name = "Gabriela";
        p1.surname = "Hacman";
        p1.dateOfBirth = "1998-10-21";
        p1.gender = "F";

        Person p2 = new Person();
        p2.name = "Valerio";
        p2.surname = "Corallini";
        p2.dateOfBirth = "1999-02-23";
        p2.gender = "M";

        // Stampa oggetti
        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
```

### MainHouse.java - Demo House

```java
public class MainHouse {
    public static void main(String[] args) {
        House h1 = new House();
        h1.address = "Via Manzoni 45, Como";
        h1.area = 85.0;
        h1.spm = 2500.0;

        House h2 = new House();
        h2.address = "Corso Italia 12, Lecco";
        h2.area = 120.0;
        h2.spm = 1800.0;

        System.out.println(h1.toString());
        System.out.println("Price: €" + h1.getPrice());

        System.out.println(h2.toString());
        System.out.println("Price: €" + h2.getPrice());
    }
}
```

---

## 🎓 Competenze Acquisite

Dopo aver completato questo modulo, saprai:

- ✅ Definire classi
- ✅ Creare oggetti con `new`
- ✅ Accedere agli attributi direttamente
- ✅ Implementare metodi
- ✅ Usare costruttori
- ✅ Creare proprietà calcolate vs memorizzate
- ✅ Creare oggetti multipli dalla stessa classe
- ✅ Override del metodo `toString()`

---

## 📊 Proprietà Calcolate vs Memorizzate

<table>
<tr>
<td width="50%">

### 💾 Proprietà Memorizzate
```java
public class House {
    double area;  // Memorizzata
    double spm;   // Memorizzata
}
```
**Caratteristiche:**
- Valore salvato in memoria
- Accesso diretto
- Può cambiare nel tempo

</td>
<td width="50%">

### 🧮 Proprietà Calcolate
```java
public double getPrice() {
    return area * spm;  // Calcolata
}
```
**Caratteristiche:**
- Valore calcolato al momento
- Non occupa memoria extra
- Sempre aggiornata

</td>
</tr>
</table>

---

## 🚀 Prossimi Passi

Dopo aver padroneggiato le basi OOP:

1. **Modulo 7 - Encapsulation** → Campi privati con getter/setter
2. **Modulo 8 - Inheritance** → Ereditarietà delle classi
3. **Modulo 9 - Abstraction** → Classi astratte e interfacce

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato le basi OOP? Passa all'Encapsulation!

**Ricorda:** Gli oggetti sono la base della programmazione moderna!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
