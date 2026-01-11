# 📘 Modulo 4 - Esercizi sui Cicli

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Loops](https://img.shields.io/badge/Loops-Exercises-green?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Principiante-green?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Modulo4/`

---

## 📋 Panoramica

Raccolta di esercizi che dimostrano la programmazione iterativa con i cicli. Impara pattern fondamentali di iterazione attraverso esempi pratici e applicazioni reali.

---

## 💻 Programma Principale

**Exercises21to24.java** - Sistema a menu con esercizi multipli

---

## 🎯 Esercizi Implementati

### Esercizio 21 - Massimo e Media

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Funzionalità:**
- Input multipli di numeri interi
- Calcola e visualizza il numero più grande
- Calcola e visualizza la media
- Ciclo do-while con uscita controllata dall'utente

**Concetti Chiave:**
```java
int max = Integer.MIN_VALUE;
int sum = 0;
int count = 0;

do {
    int num = readInt();
    if (num > max) max = num;
    sum += num;
    count++;
} while (continua);

double media = (double) sum / count;
```

**Competenze:**
- Trovare il massimo
- Calcolare la media
- Casting tipi numerici
- Pattern accumulatore

</details>

---

### Esercizio 22 - Gestore Pagamenti Bollette

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Funzionalità:**
- Input numero bollette da pagare
- Per ogni bolletta: raccolta descrizione e importo
- Visualizza lista completa bollette
- Calcola e mostra totale importo dovuto

**Esempio di Output:**
```
Bolletta 1: Luce - €120.50
Bolletta 2: Gas - €85.30
Bolletta 3: Acqua - €45.00
----------------------------
Totale da pagare: €250.80
```

**Competenze:**
- Cicli for con contatore
- Raccolta dati strutturati
- Somma accumulativa
- Formattazione output

</details>

---

### Esercizio 23 - Numeri Pari (For Loop)

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Funzionalità:**
- Visualizza tutti i numeri pari sotto 1000
- Usa ciclo for con operatore modulo

**Implementazione:**
```java
for (int i = 0; i < 1000; i++) {
    if (i % 2 == 0) {
        System.out.println(i);
    }
}
```

**Concetti:**
- Ciclo for
- Operatore modulo (%)
- Rilevamento numeri pari

</details>

---

### Esercizio 023 - Numeri Pari (While Loop)

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Funzionalità:**
- Come Esercizio 23, ma con while loop
- Dimostra approcci diversi ai cicli

**Implementazione:**
```java
int i = 0;
while (i < 1000) {
    if (i % 2 == 0) {
        System.out.println(i);
    }
    i++;
}
```

**Confronto:**
- For: iterazione con contatore integrato
- While: controllo manuale del contatore

</details>

---

### Esercizio 24 - Coppie Dispari-Pari

<details>
<summary><b>Clicca per espandere i dettagli</b></summary>

**Funzionalità:**
- Visualizza coppie di numeri consecutivi dispari-pari sotto 1000
- Formato: 1-2, 3-4, 5-6, etc.
- Usa ciclo con incremento di 2

**Implementazione:**
```java
for (int i = 1; i < 1000; i += 2) {
    System.out.println(i + "-" + (i + 1));
}
```

**Competenze:**
- Incremento personalizzato (i += 2)
- Formattazione coppie
- Pattern di output

</details>

---

## 🔧 Programma Aggiuntivo

### EsercizioLezione.java

<details>
<summary><b>Sommatoria Interattiva</b></summary>

**Funzionalità:**
- Somma numeri interattiva
- Implementazione do-while
- Visualizza somme parziali durante l'input
- Iterazione controllata dall'utente

**Pattern:**
```java
int sum = 0;
do {
    int num = readInt("Inserisci numero: ");
    sum += num;
    System.out.println("Somma parziale: " + sum);
} while (chiediContinua());
```

</details>

---

## 🎓 Concetti Chiave Appresi

<table>
<tr>
<td width="50%">

### 🔄 Tipi di Cicli
- **For loops** - contatore integrato
- **While loops** - controllo esplicito
- **Do-while loops** - esecuzione garantita

### 📊 Pattern di Iterazione
- Contatori
- Accumulatori
- Ricerca massimo/minimo
- Calcolo medie

</td>
<td width="50%">

### 🎯 Tecniche Avanzate
- Operatore modulo (%)
- Incremento personalizzato
- Cicli controllati dall'utente
- Somme parziali

### 💡 Validazione e Input
- Pattern di input ripetuto
- Sentinelle di uscita
- Validazione dati

</td>
</tr>
</table>

---

## 💻 Competenze Praticate

- ✅ Calcolo valore massimo
- ✅ Calcolo media con casting
- ✅ Accumulo somme
- ✅ Rilevamento pari/dispari
- ✅ Pattern incremento loop (i++, i+=2)
- ✅ Iterazione controllata dall'utente
- ✅ Formattazione output

---

## 🚀 Come Eseguire

```bash
# Compila
javac -d bin src/com/generation/modulo4/*.java

# Esegui
java -cp bin com.generation.modulo4.Exercises21to24
```

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

