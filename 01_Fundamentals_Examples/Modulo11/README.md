# Modulo 11 - Interfacce Funzionali e Lambda Expressions

Modulo dedicato all'apprendimento delle interfacce funzionali, lambda expressions e metodi default in Java 8+.

## Descrizione

Il Modulo 11 introduce concetti avanzati di programmazione funzionale in Java, con particolare focus su lambda expressions, interfacce funzionali, metodi default e il pattern Strategy applicato alle view.

## Argomenti Trattati

### 1. Lambda Expressions
Sintassi compatta per implementare interfacce funzionali con un singolo metodo astratto.

### 2. Interfacce Funzionali
Interfacce con un solo metodo astratto (SAM - Single Abstract Method) che possono essere implementate con lambda.

### 3. Metodi Default nelle Interfacce
Metodi con implementazione nelle interfacce, introdotti in Java 8.

### 4. Pattern Strategy con View
Implementazione del pattern Strategy per il rendering di oggetti in diverse lingue.

## Struttura del Progetto

```
Modulo11/
├── src/com/generation/
│   ├── m11/lesson/                   # Package lezioni Modulo 11
│   │   ├── Person.java               # Classe entità Person
│   │   ├── Student.java              # Classe entità Student
│   │   ├── Teacher.java              # Classe entità Teacher
│   │   ├── TeacherView.java          # Interfaccia view (con default method)
│   │   ├── ItalianTeacherView.java   # Implementazione italiana
│   │   ├── EnglishTeacherView.java   # Implementazione inglese
│   │   ├── Exercise37.java           # Esercizio filtraggio con lambda
│   │   └── Lesson019.java            # Lezione su interfacce e view
│   └── library/                      # Framework Generation Library
│       ├── cache/                    # Sistema caching
│       ├── database/                 # Database utilities
│       ├── repository/               # Repository pattern
│       ├── profiling/                # Performance monitoring
│       ├── Entity.java               # Classe base entità
│       ├── Console.java              # I/O console
│       ├── FileReader.java           # Lettura file
│       └── Template.java             # Template loading
```

## Esercizi e Lezioni

### Exercise37 - Filtraggio con Lambda
Dimostra l'uso di lambda expressions per filtrare una lista di oggetti Person.

**Operazioni:**
1. Crea una lista di 10 persone con dati diversi
2. Filtra la lista rimuovendo le persone di genere maschile usando lambda
3. Stampa il risultato formattato

**Esempio di Lambda Expression:**
```java
people.removeIf(person -> person.getGender().equals("M"));
```

**Concetti dimostrati:**
- Lambda expressions con sintassi `->`
- Metodo `removeIf()` delle Collection
- Predicati funzionali
- Filtraggio dichiarativo vs imperativo

### Lesson019 - Strategy Pattern con View
Implementa il pattern Strategy per il rendering di Teacher in diverse lingue.

**Funzionalità:**
1. Crea una lista di insegnanti
2. Legge la lingua preferita dall'utente (IT/EN)
3. Seleziona dinamicamente la view appropriata
4. Renderizza la lista nella lingua scelta

**Esempio di Strategy Pattern:**
```java
TeacherView view = language.equals("IT")
    ? new ItalianTeacherView()
    : new EnglishTeacherView();

Console.print(view.render(teachers));
```

**Concetti dimostrati:**
- Pattern Strategy per comportamenti intercambiabili
- Operatore ternario per selezione dinamica
- Interfacce con metodi default
- Polimorfismo applicato alle view

## Interfaccia TeacherView

L'interfaccia `TeacherView` dimostra l'uso dei metodi default introdotti in Java 8:

```java
public interface TeacherView {
    String render(Teacher teacher);  // Metodo astratto

    default String render(List<Teacher> teachers) {  // Metodo default
        String res = "";
        for(Teacher teacher : teachers)
            res += render(teacher) + "\n";
        return res;
    }
}
```

**Vantaggi dei metodi default:**
- Permettono di aggiungere metodi alle interfacce senza rompere le implementazioni esistenti
- Forniscono implementazioni riutilizzabili
- Riducono il codice duplicato nelle classi implementatrici
- Mantengono la retrocompatibilità

## Classi Entità

### Person
Classe base per persone con attributi:
- Nome e cognome
- Data di nascita
- Genere
- Calcolo età automatico

### Student
Estende Person aggiungendo:
- Informazioni studente specifiche

### Teacher
Estende Person aggiungendo:
- Materia insegnata
- Stipendio

## Lambda Expressions - Sintassi

### Sintassi Base
```java
// Sintassi completa
(parametro) -> { return espressione; }

// Sintassi compatta (singola espressione)
parametro -> espressione

// Multipli parametri
(param1, param2) -> espressione

// Con blocco di codice
(param) -> {
    istruzione1;
    istruzione2;
    return risultato;
}
```

### Esempi Pratici nel Progetto

**Filtraggio:**
```java
people.removeIf(person -> person.getGender().equals("M"));
```

**Ordinamento:**
```java
people.sort((p1, p2) -> p1.getName().compareTo(p2.getName()));
```

**Iterazione:**
```java
people.forEach(person -> Console.print(person));
```

## Pattern Implementati

### 1. Strategy Pattern
Le view (ItalianTeacherView, EnglishTeacherView) sono strategie intercambiabili per il rendering:
- Definiscono algoritmi alternativi
- Sono selezionabili a runtime
- Condividono interfaccia comune

### 2. Template Method (nei Default Methods)
Il metodo default `render(List<Teacher>)` definisce lo scheletro dell'algoritmo, delegando il rendering singolo al metodo astratto.

## Concetti di Programmazione Funzionale

### Interfacce Funzionali
Un'interfaccia con un solo metodo astratto può essere implementata con lambda:
```java
@FunctionalInterface
public interface Predicate<T> {
    boolean test(T t);
}

// Implementazione con lambda
Predicate<Person> isMale = person -> person.getGender().equals("M");
```

### Predicati
Espressioni booleane usate per filtrare collezioni:
```java
people.removeIf(person -> person.getGender().equals("M"));  // Predicato
```

### Riferimenti ai Metodi
Alternativa alle lambda quando si richiama un metodo esistente:
```java
// Con lambda
people.forEach(person -> Console.print(person));

// Con method reference
people.forEach(Console::print);
```

## Integration con Generation Library

Il modulo utilizza componenti dalla Generation Library:
- **Console**: I/O utente
- **Entity**: Classe base per validazione
- **Repository Pattern**: Gestione persistenza
- **Cache**: Ottimizzazione performance
- **Template**: Loading template file

## Vantaggi della Programmazione Funzionale

1. **Codice più conciso**: Lambda expressions riducono il boilerplate
2. **Leggibilità**: Espressioni dichiarative vs imperative
3. **Immutabilità**: Favorisce stili di programmazione immutabili
4. **Parallelizzazione**: Facilita l'uso di stream paralleli
5. **Composizione**: Le funzioni possono essere facilmente composte

## Esercizi Proposti

1. Implementare SpanishTeacherView per aggiungere supporto spagnolo
2. Creare un filtro con lambda per persone sopra una certa età
3. Ordinare la lista usando lambda e Comparator
4. Implementare map/filter/reduce usando stream e lambda
5. Creare interfacce funzionali personalizzate

## Competenze Acquisite

- Lambda expressions e sintassi
- Interfacce funzionali
- Metodi default nelle interfacce
- Pattern Strategy applicato
- Predicati e filtraggio dichiarativo
- Programmazione funzionale in Java
- Collection API con lambda
- Polimorfismo e interfacce
- Design patterns applicati

## Risorse Aggiuntive

- Java 8 Functional Interfaces: `java.util.function.*`
- Stream API per operazioni bulk
- Optional per gestione null-safety
- Metodi di riferimento (Method References)

## Note Tecniche

- Le lambda richiedono Java 8 o superiore
- Le interfacce funzionali possono avere solo un metodo astratto
- I metodi default permettono evoluzione delle interfacce
- Le lambda catturano variabili effectively final
- L'operator ternario è utile per Strategy Pattern compatto
