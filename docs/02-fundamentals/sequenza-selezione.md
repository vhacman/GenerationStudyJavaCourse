# 📘 Sequenza e Selezione

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Logic](https://img.shields.io/badge/Logic-Selection-blue?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/SequenzaESelezione/`

---

## 📋 Panoramica

Sistema di validazione età e altezza per il controllo accessi a giostre o attività. Dimostra l'uso di istruzioni condizionali e logica AND per validazioni multiple.

---

## 💻 Programma Principale

**AgeAndHeightChecker.java** - Validatore età e altezza

---

## 🎯 Funzionalità

<table>
<tr>
<td width="50%">

### 📏 Requisiti

- **Età minima:** 13 anni
- **Altezza minima:** 120 cm
- Entrambe le condizioni devono essere soddisfatte
- Accesso concesso solo se ENTRAMBI i requisiti sono OK

</td>
<td width="50%">

### ✅ Validazione Duale

- Validazione età AND altezza
- If annidati per controlli combinati
- Messaggi di errore specifici
- Feedback chiaro all'utente

</td>
</tr>
</table>

---

## 💡 Logica di Esempio

### Implementazione con If Annidati

```java
Scanner scanner = new Scanner(System.in);

System.out.print("Inserisci la tua età: ");
int age = scanner.nextInt();

System.out.print("Inserisci la tua altezza (cm): ");
int height = scanner.nextInt();

// Validazione età
if (age >= 13) {
    // Se età OK, controlla altezza
    if (height >= 120) {
        System.out.println("✅ Accesso consentito!");
    } else {
        System.out.println("❌ Altezza insufficiente (minimo 120 cm)");
    }
} else {
    System.out.println("❌ Età insufficiente (minimo 13 anni)");
}
```

### Implementazione Alternativa con AND Logico

```java
if (age >= 13 && height >= 120) {
    System.out.println("✅ Accesso consentito!");
} else if (age < 13) {
    System.out.println("❌ Età insufficiente (minimo 13 anni)");
} else {
    System.out.println("❌ Altezza insufficiente (minimo 120 cm)");
}
```

---

## 🔑 Concetti Chiave

### 📝 Esecuzione Sequenziale

```java
// Passo 1: Input età
int age = scanner.nextInt();

// Passo 2: Input altezza
int height = scanner.nextInt();

// Passo 3: Validazione
if (age >= 13 && height >= 120) { ... }
```

**Caratteristiche:**
- Le istruzioni vengono eseguite in ordine
- Una dopo l'altra
- Dall'alto verso il basso

---

### 🔀 Istruzioni di Selezione (If/Else)

```java
if (condizione) {
    // Esegue se condizione è true
} else {
    // Esegue se condizione è false
}
```

---

### 🔗 If Annidati (Nested If)

```java
if (primaCondizione) {
    if (secondaCondizione) {
        // Esegue solo se ENTRAMBE sono true
    } else {
        // Prima true, seconda false
    }
} else {
    // Prima condizione false
}
```

---

### ➕ Operatori Logici

<table>
<tr>
<td width="33%">

#### AND (`&&`)
```java
if (age >= 13 && height >= 120)
```
**Entrambe devono essere true**

</td>
<td width="33%">

#### OR (`||`)
```java
if (age < 13 || height < 120)
```
**Almeno una deve essere true**

</td>
<td width="33%">

#### NOT (`!`)
```java
if (!(age >= 13))
```
**Inverte il valore booleano**

</td>
</tr>
</table>

---

## 🎨 Applicazioni nel Mondo Reale

Questo tipo di logica è utilizzato in:

- 🎢 **Parchi a tema** - Restrizioni per giostre
- 🏃 **Attività sportive** - Requisiti di sicurezza
- 🎫 **Sistemi di biglietteria** - Controlli accesso
- 🚪 **Controllo accessi** - Sistemi di sicurezza
- 🎓 **Iscrizioni scolastiche** - Requisiti di ammissione

---

## 🎓 Competenze Acquisite

Dopo questo modulo, saprai:

- ✅ Implementare esecuzione sequenziale
- ✅ Usare istruzioni di selezione (if/else)
- ✅ Creare condizioni annidate
- ✅ Applicare logica AND
- ✅ Validare input multipli
- ✅ Combinare condizioni booleane
- ✅ Fornire feedback specifico all'utente

---

## 💡 Esempi di Output

### Caso 1: Accesso Consentito
```
Inserisci la tua età: 15
Inserisci la tua altezza (cm): 130
✅ Accesso consentito!
```

### Caso 2: Età Insufficiente
```
Inserisci la tua età: 10
Inserisci la tua altezza (cm): 130
❌ Età insufficiente (minimo 13 anni)
```

### Caso 3: Altezza Insufficiente
```
Inserisci la tua età: 15
Inserisci la tua altezza (cm): 110
❌ Altezza insufficiente (minimo 120 cm)
```

### Caso 4: Entrambi Insufficienti
```
Inserisci la tua età: 10
Inserisci la tua altezza (cm): 110
❌ Età insufficiente (minimo 13 anni)
```

---

## 🚀 Esercizi Proposti

1. **Modifica i limiti** - Cambia età minima a 18 e altezza a 150
2. **Aggiungi peso massimo** - Implementa anche controllo peso
3. **Crea categorie** - Bambini, ragazzi, adulti
4. **Aggiungi eccezioni** - Permetti accessi speciali con autorizzazione

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato questo modulo? Passa al successivo!

**Ricorda:** La logica condizionale è fondamentale per il controllo del flusso!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
