# NerdLibrary

Sistema di gestione di una libreria specializzata in fumetti e libri.

## Descrizione

NerdLibrary è un'applicazione che permette di gestire l'inventario di una libreria che vende sia libri tradizionali che fumetti. Utilizza l'ereditarietà OOP con Book come classe base e ComicBook come sottoclasse specializzata.

## Struttura del Progetto

```
NerdLibrary/
├── src/
│   └── com/generation/nl/
│       ├── controller/
│       │   └── Main.java
│       ├── model/
│       │   ├── entities/
│       │   │   ├── Book.java
│       │   │   └── ComicBook.java
│       │   └── repository/
│       │       └── BookRepository.java
│       └── view/
│           └── LibraryView.java
└── archive/
└── print/
```

## Classi Principali

### Model (Entità con Ereditarietà)
- `Book.java` - Classe base rappresentante un libro
  - Attributi: id, isbn, titolo, pagine, autore, prezzo, quantità
  - Metodo `isValid()` per validazione

- `ComicBook.java` - Sottoclasse di Book per fumetti
  - Attributi aggiuntivi: artist, isColor
  - Override di metodi base

### Repository
- `BookRepository.java` - Gestione persistenza libri/fumetti su file
  - Supporta polimorficamente Book e ComicBook
  - Metodi `save()` e `load()` gestiscono entrambi i tipi

### View
- `LibraryView.java` - Renderizzazione template per visualizzazione dati

### Controller
- `Main.java` - Controller principale con menu interattivo

## Funzionalità Implementate

1. **Gestione Libri**
   - Caricamento libro da ISBN
   - Aggiunta nuovo libro con validazione automatica
   - Vendita libro con decremento quantità

2. **Gestione Fumetti**
   - Aggiunta nuovo fumetto (libro + artist + colore)
   - Salvataggio con distinzione tipo (BOOK/COMICBOOK)

3. **Repository Polimorfico**
   - Supporto polimorfico: `save()` e `load()` gestiscono automaticamente Book e ComicBook
   - Caricamento completo di tutti i libri da directory
   - Metodi utilizzano `instanceof` per gestire entrambi i tipi

4. **Validazione**
   - Validazione costruttore: lancia `IllegalArgumentException` se dati non validi
   - Controllo ISBN, titolo, autore, prezzo, quantità

## Pattern Architetturali Utilizzati

- **MVC (Model-View-Controller)**: Main come controller, Book/ComicBook come model, LibraryView come view
- **Inheritance (Ereditarietà)**: ComicBook estende Book aggiungendo comportamenti specifici
- **Polymorphism (Polimorfismo)**: `save()` e `load()` utilizzano instanceof per gestire entrambi i tipi
- **Repository Pattern**: BookRepository per accesso dati
- **Factory Method**: `save()` determina il tipo corretto e chiama il metodo appropriato
- **Validazione nel Costruttore**: Validazione forzata nel costruttore, lancia eccezione

## Esecuzione

```bash
cd NerdLibrary
javac -d bin src/com/generation/nl/**/*.java
java -cp bin com.generation.nl.controller.Main
```

## Concetti Java Dimostrati

### Ereditarietà e Polimorfismo
```java
// Book è la classe base
public class Book {
    protected String isbn;
    protected String title;
    // ...
}

// ComicBook estende Book
public class ComicBook extends Book {
    private String artist;
    private boolean isColor;
    // ...
}

// Polimorfismo nel repository
public void save(Book book) {
    if (book instanceof ComicBook) {
        // salva come fumetto
    } else {
        // salva come libro
    }
}
```

### Validazione nel Costruttore
```java
public Book(String isbn, String title, ...) {
    if (!isValidIsbn(isbn)) {
        throw new IllegalArgumentException("ISBN non valido");
    }
    this.isbn = isbn;
    // ...
}
```

## File Chiave

- `Book.java:1` - Classe base con validazione
- `ComicBook.java:1` - Sottoclasse specializzata
- `BookRepository.java:1` - Repository polimorfico
- `Main.java:1` - Controller con menu

## Relazione IS-A

```
Book (classe base)
  ├── Attributi comuni: isbn, title, pages, author, price, quantity
  └── ComicBook (sottoclasse)
      └── Attributi specifici: artist, isColor
```

ComicBook IS-A Book: un fumetto è un tipo di libro con caratteristiche aggiuntive.
