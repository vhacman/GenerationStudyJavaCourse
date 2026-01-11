# 📘 Modulo 9 - Abstraction (Astrazione)

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![OOP](https://img.shields.io/badge/OOP-Abstraction-red?style=for-the-badge)
![Livello](https://img.shields.io/badge/Livello-Avanzato-red?style=for-the-badge)

**Percorso:** `01_Fundamentals_Examples/Modulo9Astrazione/`

---

## 📋 Panoramica

Esplora l'**Astrazione**, il terzo pilastro della Programmazione Orientata agli Oggetti (OOP). Impara a nascondere i dettagli implementativi complessi e mostrare solo le funzionalità essenziali.

---

## 🎯 Cos'è l'Astrazione?

L'astrazione è il processo di nascondere i dettagli implementativi complessi e mostrare solo le funzionalità essenziali dell'oggetto.

**Esempio del Mondo Reale:**
- Quando guidi un'auto, usi sterzo, freni e acceleratore
- Non hai bisogno di sapere come funziona il motore internamente
- L'implementazione è nascosta, l'interfaccia è semplice

---

## 🔑 Concetti Chiave

### 📦 Classi Astratte

<table>
<tr>
<td width="50%">

#### Caratteristiche
- **Non possono essere istanziate** direttamente
- Possono contenere metodi astratti (senza implementazione)
- Possono contenere metodi concreti (con implementazione)
- Usate come classi base per l'ereditarietà

</td>
<td width="50%">

#### Esempio
```java
public abstract class Animal {
    // Metodo astratto
    abstract void makeSound();

    // Metodo concreto
    void sleep() {
        System.out.println("Sleeping...");
    }
}
```

</td>
</tr>
</table>

---

### ⚡ Metodi Astratti

**Dichiarati senza implementazione:**

```java
public abstract class Animal {
    // Solo la firma, nessun corpo
    abstract void makeSound();

    // Le sottoclassi DEVONO implementarlo
}
```

**Implementazione nelle sottoclassi:**

```java
public class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof!");
    }
}

public class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Meow!");
    }
}
```

---

### 🎭 Interfacce

<table>
<tr>
<td width="50%">

#### Caratteristiche Java 7 e precedenti
- 100% astratte
- Solo metodi astratti
- Nessuna implementazione
- Ereditarietà multipla possibile

</td>
<td width="50%">

#### Esempio
```java
public interface Flyable {
    void fly();
    void land();
}

public interface Swimmable {
    void swim();
}
```

</td>
</tr>
</table>

**Implementazione:**

```java
public class Duck implements Flyable, Swimmable {
    @Override
    public void fly() {
        System.out.println("Duck flying");
    }

    @Override
    public void land() {
        System.out.println("Duck landing");
    }

    @Override
    public void swim() {
        System.out.println("Duck swimming");
    }
}
```

---

## 🌟 Vantaggi dell'Astrazione

<table>
<tr>
<td width="50%">

### 🏗️ Struttura Forzata
- Forza le sottoclassi a implementare metodi specifici
- Garantisce un'interfaccia coerente
- Previene implementazioni incomplete

### 🔗 Loose Coupling
- Riduce le dipendenze tra componenti
- Facilita la manutenzione
- Migliora la testabilità

</td>
<td width="50%">

### 🎭 Polimorfismo
- Supporta il polimorfismo
- Programma all'interfaccia, non all'implementazione
- Flessibilità nel design

### 📜 Contratti Chiari
- Definisce contratti chiari
- Documentazione attraverso la struttura
- Aspettative esplicite

</td>
</tr>
</table>

---

## 💡 Esempio Pratico Completo

### Classe Astratta: Animal

```java
public abstract class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    // Metodo astratto - implementazione obbligatoria
    public abstract void makeSound();

    // Metodo concreto - implementazione opzionale
    public void sleep() {
        System.out.println(name + " is sleeping");
    }

    public void eat() {
        System.out.println(name + " is eating");
    }
}
```

### Sottoclassi Concrete

```java
public class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Woof!");
    }
}

public class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println(name + " says: Meow!");
    }
}
```

### Utilizzo

```java
public class Main {
    public static void main(String[] args) {
        // Animal a = new Animal("Test");  // ERRORE! Non si può istanziare

        Animal dog = new Dog("Buddy");
        Animal cat = new Cat("Whiskers");

        dog.makeSound();  // Output: Buddy says: Woof!
        cat.makeSound();  // Output: Whiskers says: Meow!

        dog.sleep();      // Output: Buddy is sleeping
        cat.eat();        // Output: Whiskers is eating
    }
}
```

---

## 📊 Confronto: Classi Astratte vs Interfacce

| Aspetto | Classe Astratta | Interfaccia (Java 7) |
|---------|----------------|---------------------|
| **Metodi** | Astratti + Concreti | Solo Astratti |
| **Campi** | Può avere campi normali | Solo costanti (public static final) |
| **Costruttori** | Può avere costruttori | Nessun costruttore |
| **Ereditarietà** | Singola (extends) | Multipla (implements) |
| **Modificatori** | Qualsiasi | Solo public |
| **Quando usare** | Relazione IS-A forte | Comportamento CAN-DO |

---

## 🎓 Competenze Acquisite

Dopo aver completato questo modulo, saprai:

- ✅ Creare classi astratte
- ✅ Definire metodi astratti
- ✅ Implementare metodi astratti nelle sottoclassi
- ✅ Usare interfacce
- ✅ Comprendere i principi dell'astrazione
- ✅ Scegliere tra classi astratte e interfacce
- ✅ Applicare il polimorfismo con astrazione

---

## 🚀 Prossimi Passi

Dopo aver padroneggiato l'astrazione, applica tutti e quattro i pilastri OOP in progetti reali:

1. **Business Applications** → Applicazioni aziendali complete
2. **Advanced Projects** → Progetti complessi
3. **Database Integration** → Persistenza dati

---

## 💡 Best Practices

### ✅ Fai Sempre

- Usa classi astratte per relazioni IS-A con codice condiviso
- Usa interfacce per definire capacità (CAN-DO)
- Rendi astratti solo i metodi che variano tra sottoclassi
- Documenta i contratti delle interfacce

### ❌ Evita

- Classi astratte troppo generiche
- Interfacce con troppi metodi
- Metodi astratti senza chiara necessità
- Implementazioni parziali

---

## 👨‍💻 Autore

**Hacman Viorica Gabriela**
- 🎓 Generation Italy - Java Full Stack Developer
- 📧 hacmanvioricagabriela@gmail.com
- 🐙 GitHub: [@vhacman](https://github.com/vhacman)

---

<div align="center">

### ⭐ Hai completato tutti i 4 pilastri OOP!

**I 4 Pilastri OOP:**
1. **Encapsulation** (Incapsulamento)
2. **Inheritance** (Ereditarietà)
3. **Abstraction** (Astrazione)
4. **Polymorphism** (Polimorfismo)

![Java](https://img.shields.io/badge/Keep_Coding-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)

</div>
