# 📘 Modulo 7 - Encapsulation (Incapsulamento)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Encapsulation-purple?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Intermedio-orange?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Modulo7Incapsulamento/`

---

## 📋 Panoramica

Esplora l'**Incapsulamento**, uno dei quattro pilastri fondamentali della Programmazione Orientata agli Oggetti (OOP). Impara a proteggere i dati e controllare l'accesso agli attributi della classe.

---

## 💻 Programma Principale

**Main.java** con classe entità **Person.java** migliorata

---

## 🔒 Principi dell'Incapsulamento

### Cos'è l'Incapsulamento?

L'incapsulamento è la pratica di:

<table>
<tr>
<td width="33%">

#### 1️⃣ Nascondere lo Stato
- Campi privati (`private`)
- Nessun accesso diretto esterno
- Protezione dei dati

</td>
<td width="33%">

#### 2️⃣ Accesso Controllato
- Metodi pubblici (getter/setter)
- Controllo sul flusso dati
- Validazione centralizzata

</td>
<td width="33%">

#### 3️⃣ Integrità Dati
- Validazione nel setter
- Protezione da stati invalidi
- Sicurezza null-safe

</td>
</tr>
</table>

---

## 🌟 Vantaggi dell'Incapsulamento

<table>
<tr>
<td width="50%">

### 🔐 Data Hiding
- Previene accesso diretto esterno ai campi
- Protegge l'integrità dei dati
- Controllo totale sui valori

### 🎯 Accesso Controllato
- Metodi pubblici controllano il flusso dati
- Validazione prima dell'assegnazione
- Log e audit quando necessario

</td>
<td width="50%">

### ✅ Validazione
- Setter possono validare prima di salvare
- Prevenzione valori invalidi
- Regole business centralizzate

### 🛡️ Null Safety
- Programmazione difensiva
- Previene `NullPointerException`
- Valori di default sicuri

</td>
</tr>
</table>

---

## 💡 Implementazione Classe Person

### Attributi Privati

```java
private String name;
private String surname;
private String dateOfBirth;
private String gender;
```

**Perché privati?**
- Nessuno può accedervi direttamente da fuori
- Solo i metodi della classe possono modificarli
- Controllo totale sui dati

---

### Costruttori

#### Costruttore Senza Argomenti

```java
public Person() {
    this.name = "";
    this.surname = "";
    this.dateOfBirth = "";
    this.gender = "";
}
```

#### Costruttore Parametrizzato

```java
public Person(String name, String surname, String dateOfBirth, String gender) {
    this.name = name;
    this.surname = surname;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
}
```

---

### Metodi Getter (con Null-Safety)

```java
public String getName() {
    return name == null ? "UNKNOWN" : name;
}

public String getSurname() {
    return surname == null ? "UNKNOWN" : surname;
}

public String getDateOfBirth() {
    return dateOfBirth == null ? "UNKNOWN" : dateOfBirth;
}

public String getGender() {
    return gender == null ? "UNKNOWN" : gender;
}
```

**Perché null-safety?**
- ✅ Previene `NullPointerException`
- ✅ Ritorna "UNKNOWN" invece di `null`
- ✅ Sicuro per operazioni su stringhe
- ✅ Codice più robusto

---

### Metodi Setter (con Validazione)

```java
public void setName(String name) {
    this.name = name == null ? "UNKNOWN" : name;
}

public void setSurname(String surname) {
    this.surname = surname == null ? "UNKNOWN" : surname;
}

public void setDateOfBirth(String dateOfBirth) {
    this.dateOfBirth = dateOfBirth == null ? "UNKNOWN" : dateOfBirth;
}

public void setGender(String gender) {
    this.gender = gender == null ? "UNKNOWN" : gender;
}
```

**Perché validazione?**
- ✅ Gestisce valori `null` in modo difensivo
- ✅ Garantisce integrità dei dati
- ✅ Previene stati invalidi

---

### Metodo toString()

```java
@Override
public String toString() {
    return "Person: " + getName() + " " + getSurname() +
           ", DOB: " + getDateOfBirth() + ", Gender: " + getGender();
}
```

**Nota:** Usa i getter per null-safety automatica!

---

## 🔍 Codice Dimostrativo

### Main.java

```java
Person p1 = new Person("Gabriela", "Hacman", "21-10-1998", "F");
Person p2 = new Person("Valerio", "Corallini", "02-23-1999", "M");
Person p3 = new Person("Pippo", "Disney", null, "M");  // null dateOfBirth
Person p4 = new Person("Pluto", "Disney", null, "M");

// Accesso attraverso getter
String name = p1.getName();  // Ritorna "Gabriela"
String dob = p3.getDateOfBirth(); // Ritorna "UNKNOWN"

// Modifica attraverso setter
p1.setName("Gaby");
```

---

## 🎯 Concetti Chiave

### Modificatori di Accesso

<table>
<tr>
<td width="50%">

#### `private`
- Accessibile **solo** dentro la classe
- Usa per: attributi, metodi helper
- Massima protezione

</td>
<td width="50%">

#### `public`
- Accessibile da **ovunque**
- Usa per: getter, setter, metodi API
- Interfaccia pubblica

</td>
</tr>
</table>

---

### Programmazione Difensiva

Usa operatori ternari per controlli null:

```java
return name == null ? "UNKNOWN" : name;
```

**Equivalente a:**
```java
if (name == null) {
    return "UNKNOWN";
} else {
    return name;
}
```

---

### Overloading dei Costruttori

Costruttori multipli con parametri diversi:

```java
// Costruttore vuoto
public Person() { }

// Costruttore con tutti i campi
public Person(String name, String surname, String dateOfBirth, String gender) { }
```

---

### Parola Chiave `this`

Riferisce all'istanza corrente dell'oggetto:

```java
this.name = name;  // Assegna il parametro al campo
```

**Quando usare `this`:**
- Quando parametro e campo hanno lo stesso nome
- Per chiarezza nel codice
- Per chiamare altri costruttori: `this()`

---

## 📊 Confronto: Prima vs Dopo l'Incapsulamento

### ❌ Modulo 6 (Accesso Diretto)

```java
Person p = new Person();
p.name = "Gabriela";      // Accesso diretto al campo
String n = p.name;        // Lettura diretta del campo

// PROBLEMI:
// - Nessuna validazione
// - Possibili valori null
// - Nessun controllo
```

### ✅ Modulo 7 (Incapsulato)

```java
Person p = new Person();
p.setName("Gabriela");    // Controllo attraverso setter
String n = p.getName();   // Controllo attraverso getter

// VANTAGGI:
// - Validazione automatica
// - Null-safety integrata
// - Controllo completo
```

---

## 🎓 Competenze Acquisite

Dopo aver completato questo modulo, saprai:

- ✅ Dichiarare campi privati
- ✅ Creare metodi getter/setter pubblici
- ✅ Implementare tecniche null-safety
- ✅ Usare operatori ternari per validazione
- ✅ Fare overloading dei costruttori
- ✅ Applicare programmazione difensiva
- ✅ Comprendere i principi del data hiding
- ✅ Implementare pattern di accesso controllato

---

## 🚀 Prossimi Passi

Dopo aver padroneggiato l'incapsulamento:

1. **Modulo 8 - Inheritance** → Estensione delle classi
2. **Modulo 9 - Abstraction** → Classi astratte e interfacce
3. **Business Applications** → Applicazioni pratiche

---

## 💡 Best Practices

### ✅ Fai Sempre

- Rendi i campi `private`
- Fornisci getter/setter `public`
- Valida nei setter
- Gestisci null nei getter
- Usa `this` quando necessario

### ❌ Evita

- Campi `public` (eccetto costanti)
- Getter/setter senza validazione
- Accesso diretto ai campi
- Valori null non gestiti

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato questo modulo? Passa al successivo!

**Ricorda:** L'incapsulamento è la base per codice sicuro e manutenibile!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
