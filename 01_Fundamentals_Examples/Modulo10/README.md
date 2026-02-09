# 🔄 Modulo10 - Polimorfismo e Interfacce

Modulo dedicato al polimorfismo e alle interfacce in Java.

## Argomenti

- Keyword `implements`
- Interfacce multiple
- Polimorfismo runtime
- Binding dinamico
- Interfacce vs classi astratte

## Struttura

```
Modulo10/
├── src/
└── .settings/
```

## Concetti Chiave

**Interfacce:**
- Contratto di comportamento
- Solo metodi astratti (prima di Java 8)
- Metodi default (Java 8+)
- Metodi statici (Java 8+)
- Una classe può implementare più interfacce

**Polimorfismo:**
- Un oggetto può essere trattato come suo padre
- binding dinamico a runtime
- Flessibilità del codice

**Esempio:**
```java
public interface Forma {
    double calcolaArea();
}

public class Cerchio implements Forma {
    public double calcolaArea() {
        return Math.PI * raggio * raggio;
    }
}
```

---

**Autore:** Hacman Viorica Gabriela  
**Corso:** Generation Italy - Java Full Stack Developer
