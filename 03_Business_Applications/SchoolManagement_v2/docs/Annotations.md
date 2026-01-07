# Annotations in Java

## Cos'è un'Annotation?

Un'**annotation** è un'etichetta speciale che metti sul codice con il simbolo `@`. Serve per dare istruzioni al compilatore o ai framework (come Spring e JPA).

**Sintassi base**:
```java
@NomeAnnotation
public class MiaClasse { }
```

Pensa alle annotations come a dei **cartellini** che attacchi sul codice per dire "Ehi, tratta questo pezzo di codice in modo speciale!".

---

## Le 3 Annotations Base di Java

### 1. @Override
**Cosa fa**: Dice "Questo metodo sovrascrive un metodo della superclasse"

**Esempio**:
```java
public class Person {
    @Override
    public String toString() {
        return "Person: " + name;
    }
}
```

**Perché è utile**:
- Se scrivi male il nome del metodo, il compilatore ti avverte subito
- Senza `@Override`, un typo crea un nuovo metodo inutile invece di sovrascrivere quello giusto

```java
// SENZA @Override - compila ma NON sovrascrive
public String tostring() { }  // typo: 's' minuscola

// CON @Override - ERRORE DI COMPILAZIONE (ti salva dal bug!)
@Override
public String tostring() { }  // Il compilatore ti avverte dell'errore
```