<div align="center">

# Modulo 10 — Polimorfismo e Interfacce

Keyword `implements`, interfacce multiple e binding dinamico.

</div>

---

## Argomenti

- Keyword `implements`
- Interfacce multiple
- Polimorfismo runtime e binding dinamico
- Metodi `default` e `static` nelle interfacce (Java 8+)
- Interfacce vs classi astratte

---

## Concetti Chiave

### Interfacce
- Contratto di comportamento puro
- Una classe puo implementare **piu interfacce**
- Metodi `default` (Java 8+) per implementazioni comuni

### Polimorfismo
- Un oggetto puo essere trattato come il suo tipo padre
- Binding dinamico a runtime
- Massima flessibilità del codice

### Esempio

```java
public interface Forma {
    double calcolaArea();
}

public class Cerchio implements Forma {
    private double raggio;

    public double calcolaArea() {
        return Math.PI * raggio * raggio;
    }
}
```

---

## Struttura

```
Modulo10/
└── src/
```

## Tecnologie

- **Java** 17+ | **IDE:** Eclipse

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna ai Fondamenti](../README.md) · [README principale](../../README.md)

</div>
