# 📘 While Loops - Cicli While

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Loops](https://img.shields.io/badge/Loops-While-green?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/While/`

---

## 📋 Panoramica

Progetto utility con classi di libreria condivise per esercizi basati sui cicli. Questo modulo fornisce le basi per comprendere e utilizzare i cicli `while` in Java.

---

## 🎯 Scopo del Progetto

Questo progetto fornisce classi utility comuni utilizzate in molteplici esercizi ed esempi sui cicli, facilitando l'apprendimento dei pattern di iterazione fondamentali.

---

## 🔑 Concetti Chiave

<table>
<tr>
<td width="50%">

### 🔄 Struttura del While Loop
- Condizione di controllo
- Variabili di controllo del ciclo
- Corpo del ciclo
- Aggiornamento della condizione

</td>
<td width="50%">

### ⚡ Caratteristiche Importanti
- Valori sentinella
- Prevenzione loop infiniti
- Pattern di iterazione comuni
- Controllo del flusso

</td>
</tr>
</table>

---

## 💻 Struttura del While Loop

### Sintassi Base

```java
while (condition) {
    // Codice da ripetere
    // Aggiorna la variabile di condizione
}
```

**Caratteristiche:**
- La condizione viene valutata **prima** di ogni iterazione
- Se la condizione è `false` all'inizio, il corpo non viene mai eseguito
- Il ciclo continua finché la condizione è `true`

---

## 🎨 Pattern Comuni

### 1️⃣ Counter-Controlled Loop (Ciclo Controllato da Contatore)

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;  // IMPORTANTE: incrementa il contatore!
}
```

**Quando usarlo:**
- Quando sai quante volte ripetere
- Per contare fino a un valore specifico
- Per iterare su range numerici

---

### 2️⃣ Sentinel-Controlled Loop (Ciclo Controllato da Sentinella)

```java
String input = "";
while (!input.equals("quit")) {
    input = Console.readString("Inserisci comando (quit per uscire): ");
    // Processa il comando
}
```

**Quando usarlo:**
- Per menu interattivi
- Quando l'utente controlla quando fermarsi
- Per input ripetuti fino a un valore speciale

---

### 3️⃣ Validation Loop (Ciclo di Validazione)

```java
int age = -1;
while (age < 0 || age > 120) {
    age = Console.readInt("Inserisci età valida (0-120): ");
    if (age < 0 || age > 120) {
        System.out.println("Età non valida! Riprova.");
    }
}
```

**Quando usarlo:**
- Per validare input utente
- Per garantire dati corretti
- Per ripetere finché l'input è valido

---

## 🎯 Confronto: While vs Do-While

<table>
<tr>
<td width="50%">

### While Loop
```java
while (condition) {
    // codice
}
```

**Caratteristiche:**
- Controlla la condizione PRIMA
- Può eseguire 0 volte
- Usa quando: potresti non dover eseguire

</td>
<td width="50%">

### Do-While Loop
```java
do {
    // codice
} while (condition);
```

**Caratteristiche:**
- Controlla la condizione DOPO
- Esegue almeno 1 volta
- Usa quando: devi eseguire almeno una volta

</td>
</tr>
</table>

---

## ⚠️ Prevenzione Loop Infiniti

### ❌ Errore Comune: Loop Infinito

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    // ERRORE: Dimentichi di incrementare i!
    // Il ciclo non si ferma mai!
}
```

### ✅ Soluzione Corretta

```java
int i = 0;
while (i < 10) {
    System.out.println(i);
    i++;  // Incrementa per evitare loop infinito!
}
```

---

## 💡 Esempi Pratici

### Esempio 1: Somma Numeri fino a N

```java
int n = 10;
int sum = 0;
int i = 1;

while (i <= n) {
    sum += i;
    i++;
}

System.out.println("Somma: " + sum);  // Output: 55
```

### Esempio 2: Menu Interattivo

```java
String choice = "";
while (!choice.equals("x")) {
    System.out.println("Menu:");
    System.out.println("1. Opzione A");
    System.out.println("2. Opzione B");
    System.out.println("x. Esci");

    choice = Console.readString("Scelta: ");

    switch(choice) {
        case "1":
            System.out.println("Hai scelto A");
            break;
        case "2":
            System.out.println("Hai scelto B");
            break;
    }
}
```

---

## 🔗 Progetti Correlati

- **Modulo 4** - Contiene esercizi sui cicli while
- **Esercizi di confronto loop** - For vs While

---

## 🎓 Competenze Acquisite

Dopo questo modulo, saprai:

- ✅ Comprendere la struttura del while loop
- ✅ Implementare counter-controlled loops
- ✅ Creare sentinel-controlled loops
- ✅ Validare input con while loops
- ✅ Prevenire loop infiniti
- ✅ Scegliere tra while e do-while
- ✅ Applicare pattern comuni di iterazione

---

## 📚 Best Practices

### ✅ Fai Sempre

1. **Inizializza** la variabile di controllo prima del loop
2. **Aggiorna** la variabile nel corpo del loop
3. **Verifica** che la condizione diventi false
4. **Usa** nomi di variabili significativi

### ❌ Evita

1. Loop infiniti (sempre aggiorna la condizione!)
2. Condizioni troppo complesse
3. Modifiche multiple della variabile di controllo
4. Dipendenze esterne non controllate

---

## 🚀 Prossimi Passi

Dopo aver padroneggiato i while loops, procedi con:

1. **Do-While Loops** → Cicli con esecuzione garantita
2. **For Loops** → Cicli con contatore integrato
3. **Nested Loops** → Cicli annidati

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato questo modulo? Passa al successivo!

**Ricorda:** I while loops sono perfetti quando non sai esattamente quante iterazioni servono!

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
