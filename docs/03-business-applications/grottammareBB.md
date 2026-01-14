# Grottammare B&B - Sistema Gestionale

Sistema console-based per la gestione di un Bed & Breakfast che implementa architettura MVC, Repository Pattern e Factory per Dependency Injection. Include validazione dati con regex per Codice Fiscale italiano e controllo età maggiorenne.[1][2][11]

## Architettura Software

L'applicazione segue il paradigma MVC con separazione chiara dei ruoli:

```
Main (Controller)
   ↓ Dependency Injection (Factory Pattern)
GuestRepository ←→ Guest (Entity + Validazione)
RoomRepository  ←→ Room  (Entity + Validazione)
   ↑ In-Memory Implementation (Dummy Repository)
```

**Livelli di astrazione:**
- **Controller** (`Main.java`): Orchestrazione menu interattivo e I/O console
- **Model** (`Guest.java`, `Room.java`): Entità con validazione incorporata 
- **Repository** (Interfacce + Dummy impl.): Astrazione persistenza
- **Factory** (`*RepositoryFactory.java`): Creazione oggetti configurabile[5][6][8][9][1]

## Design Patterns Implementati

### 1. Repository Pattern
Interfacce `GuestRepository` e `RoomRepository` astraggono l'accesso dati:

```java
public interface GuestRepository {
    List<Guest> findAll();
    Guest findById(int id);
    Guest findBySSN(String ssn);     // Codice Fiscale
    List<Guest> findBySurnameContaining(String part);
    List<Guest> findByCity(String city);
    // CRUD operations (insert, update, delete)
}
```

**Vantaggi**: Testability (mock), estendibilità (JDBC, JPA), inversione dipendenze.[6]

### 2. Factory Pattern
```java
public class GuestRepositoryFactory {
    public static GuestRepository make() {
        return new DummyGuestRepository();  // Configurabile
    }
}
```
Permette sostituzione implementazioni senza modificare `Main`.[8]

### 3. Template Method (Validazione)
Classe base `Entity` (ereditata) definisce algoritmo validazione:

```java
@Override
public List<String> getErrors() {
    List<String> errors = new ArrayList<>();
    // Template steps: mandatory fields → format validation → business rules
    if (isMissing(firstName)) errors.add("Nome mancante");
    if (!hasValidSSN()) errors.add("Codice Fiscale non valido");
    if (getAge() < 18) errors.add("L'ospite deve essere maggiorenne");
    return errors;
}
```
`isValid()` = `getErrors().isEmpty()`[2]

## Validazione Dati

### Codice Fiscale Italiano
Regex pattern: `[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]`
- 6 lettere (cognome+nome)
- 2 cifre (anno nascita)
- 1 lettera (mese: A=Gennaio...L=Dicembre)  
- 2 cifre (giorno + genere)
- 4 caratteri (codice catasto + controllo)[10][2]

### Regole Business
```
OBBLIGATORI: nome, cognome, CF, data nascita, indirizzo, città
ETÀ: ≥ 18 anni (LocalDate.now() - DOB)
SSN: 16 caratteri, formato italiano valido
```

## Repository Implementations

**Dummy Repository** (in-memory):
- 13 ospiti con CF validi reali italiani
- 4 stanze con pricing progressivo (Economy → Suite)
- Ricerca case-insensitive su cognome/città e nome/descrizione[4][7]

**Metodi di Ricerca Implementati:**
```
Guest: findAll(), findById(), findBySSN(), findBySurnameContaining(), findByCity()
Room:  findAll(), findById(), findByNameOrDescriptionContaining(), findByPriceLesserThan()
```

## Flusso Applicativo

1. **Bootstrap**: Factory crea repository, validazione dipendenze
2. **Menu Loop**: `do-while` fino a "Q", switch-case comandi multipli (numeri + testo)
3. **Ricerca**: Chiamata repository → rendering console formattato
4. **Output**: Conteggi risultati + dettagli rilevanti[1]

## Test Unitari

`GuestTest.java` (JUnit 5) verifica:
```java
@Test void testValidazioneSSN()     // Regex CF italiano
@Test void testValidazioneEta()      // Calcolo età ≥18y  
@Test void testValidazioneCompleta() // Tutti i controlli
```
Coverage: validazione incrementale campi + edge cases (minorenne, CF malformato)[10]

## Estendibilità

Il design permette sostituzione `DummyRepository` con:
- **JDBC**: Database relazionale
- **JPA/Hibernate**: ORM
- **File CSV/JSON**: Persistenza seriale
- **REST API**: Integrazione esterna

Senza modifiche al Controller `Main`.[5][6]

## Compilazione ed Esecuzione

```bash
javac -d . com/generation/gbb/**/*.java
java com.generation.gbb.controller.Main
```

**Progetto didattico Generation Italy** - Dimostrazione Repository Pattern, Dependency Injection, MVC e validazione enterprise in Java.[11][1]

