<div align="center">

# Modulo 6 — Classi e Oggetti

Introduzione alla programmazione orientata agli oggetti: definizione di classi e creazione di oggetti.

</div>

---

## Argomenti

- Definizione di classi con keyword `class`
- Costruttori (default e parametrici)
- Variabili di istanza
- Metodi di istanza
- Creazione oggetti con `new`
- Riferimenti e lifetime degli oggetti

---

## Concetti Chiave

```java
public class Persona {
    // Variabili di istanza
    private String nome;
    private int eta;

    // Costruttore parametrico
    public Persona(String nome, int eta) {
        this.nome = nome;
        this.eta = eta;
    }
}

// Creazione oggetto
Persona p = new Persona("Mario", 30);
```

---

## Struttura

```
Modulo 6/
└── src/
```

## Tecnologie

- **Java** 17+ | **IDE:** Eclipse

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna ai Fondamenti](../README.md) · [README principale](../../README.md)

</div>
