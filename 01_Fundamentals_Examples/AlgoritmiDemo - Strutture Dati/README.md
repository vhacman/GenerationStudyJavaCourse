<div align="center">

# AlgoritmiDemo — Strutture Dati

Modulo dedicato all'apprendimento delle strutture dati fondamentali in Java e degli algoritmi di base per la loro manipolazione.

</div>

---

## Descrizione

Questo modulo introduce i concetti fondamentali delle strutture dati in Java, con particolare focus su **array**, **liste**, **set** e **map**. Include esempi pratici e casi d'uso reali per comprendere quando utilizzare ciascuna struttura.

## Argomenti Trattati

### 1. Array (Vettori)
Struttura dati a dimensione fissa per memorizzare elementi dello stesso tipo.

### 2. Liste (ArrayList)
Collezioni dinamiche che permettono inserimento/rimozione di elementi.

### 3. Set (HashSet)
Collezioni che non permettono duplicati, utili per filtraggio.

### 4. Map (HashMap)
Strutture chiave-valore per associazioni e conteggi.

## Struttura del Progetto

```
AlgoritmiDemo - Strutture Dati/
├── src/com/generation/demo/
│   ├── Person.java                      # Classe entità Person
│   ├── Demo001Vettori.java              # Array: iterazione normale e inversa
│   ├── Demo002Vettori.java              # Array: operazioni avanzate
│   ├── Demo003Vettori.java              # Array: ricerca e filtraggio
│   ├── Demo004Liste.java                # ArrayList: operazioni base
│   ├── Demo004ListeEVettori.java        # Conversione Array ↔ List
│   ├── Demo005VettoriESet.java          # Set: rimozione duplicati
│   ├── Demo006ConteggioNomiVirili.java  # Filtraggio con predicati
│   ├── Demo007VettoriESet.java          # Set: operazioni avanzate
│   ├── Demo008ClientelaDivisaPerCitta.java  # Map: raggruppamento
│   └── Demo009MappeAlbergo.java         # Map: conteggio e confronto
└── README.md
```

## Dettaglio delle Demo

### Demo001Vettori - Iterazione Array
Dimostra le operazioni base di iterazione sugli array.

**Operazioni:**
1. Stampa in ordine normale (inizio → fine)
2. Stampa in ordine inverso (fine → inizio)
3. Stampa ultima metà da indice specifico
4. Stampa ultima metà calcolata automaticamente

**Esempio:**
```java
int[] heights = {170, 170, 165, 180, 195, 190};

// Iterazione normale
for(int i = 0; i < heights.length; i++)
    System.out.println(heights[i]);

// Iterazione inversa
for(int i = heights.length - 1; i >= 0; i--)
    System.out.println(heights[i]);

// Seconda metà
for(int i = heights.length / 2; i < heights.length; i++)
    System.out.println(heights[i]);
```

**Concetti dimostrati:**
- Accesso elementi tramite indice
- Proprietà `length` degli array
- Cicli for con contatori
- Iterazione parziale

### Demo002Vettori - Operazioni Avanzate Array
Operazioni più complesse sugli array.

**Concetti dimostrati:**
- Modifica elementi durante iterazione
- Ricerca lineare
- Confronto elementi

### Demo003Vettori - Ricerca e Filtraggio
Algoritmi di ricerca e filtraggio in array.

**Concetti dimostrati:**
- Ricerca sequenziale
- Filtraggio condizionale
- Conteggio elementi

### Demo004Liste - ArrayList Base
Introduzione alle liste dinamiche.

**Operazioni:**
- Aggiunta elementi con `add()`
- Rimozione con `remove()`
- Accesso con `get()`
- Iterazione con for-each

**Esempio:**
```java
ArrayList<String> names = new ArrayList<>();
names.add("Mario");
names.add("Luigi");
names.remove(0);  // Rimuove "Mario"

for(String name : names)
    System.out.println(name);
```

**Concetti dimostrati:**
- Liste dinamiche vs array fissi
- Generics `<T>`
- Metodi delle Collection

### Demo004ListeEVettori - Conversione Array ↔ List
Conversione tra array e liste.

**Esempio:**
```java
// Array → List
String[] arrayNames = {"Mario", "Luigi", "Peach"};
List<String> list = Arrays.asList(arrayNames);

// List → Array
String[] backToArray = list.toArray(new String[0]);
```

**Concetti dimostrati:**
- `Arrays.asList()`
- `toArray()`
- Quando usare array vs liste

### Demo005VettoriESet - Rimozione Duplicati con Set
Utilizzo dei Set per filtrare duplicati.

**Esempio:**
```java
String[] names = {"Mario", "Luigi", "Mario", "Peach"};
Set<String> uniqueNames = new HashSet<>(Arrays.asList(names));
// uniqueNames contiene: {"Mario", "Luigi", "Peach"}
```

**Concetti dimostrati:**
- Set non permette duplicati
- HashSet per filtraggio rapido
- Conversione Array → Set

### Demo006ConteggioNomiVirili - Filtraggio Condizionale
Conta elementi che soddisfano un predicato.

**Esempio:**
```java
Person[] people = {
    new Person("Mario", "M"),
    new Person("Luigi", "M"),
    new Person("Peach", "F")
};

int countMale = 0;
for(Person p : people)
    if(p.gender.equals("M"))
        countMale++;
```

**Concetti dimostrati:**
- Predicati booleani
- Filtraggio con if
- Conteggio condizionale

### Demo007VettoriESet - Set Avanzato
Operazioni avanzate con Set.

**Concetti dimostrati:**
- Operazioni insiemistiche
- Verifica contenimento con `contains()`
- Differenze Set vs List

### Demo008ClientelaDivisaPerCitta - Raggruppamento con Map
Raggruppa persone per città usando Map.

**Esempio:**
```java
Person[] customers = {
    new Person("Mario", "Roma"),
    new Person("Luigi", "Milano"),
    new Person("Peach", "Roma")
};

Map<String, List<Person>> byCity = new HashMap<>();

for(Person p : customers) {
    byCity.computeIfAbsent(p.city, k -> new ArrayList<>()).add(p);
}

// byCity: {"Roma" => [Mario, Peach], "Milano" => [Luigi]}
```

**Concetti dimostrati:**
- Map chiave-valore
- `computeIfAbsent()` per raggruppamento
- Strutture dati annidate

### Demo009MappeAlbergo - Gestione Hotel Transilvania
Sistema completo di gestione disponibilità stanze per un hotel fantasy.

**Scenario:**
Hotel Transilvania gestisce stanze per diverse specie (vampiri, lycan, umani, ghost, zombi).

**Funzionalità:**
1. Mappa stanze disponibili per specie
2. Mappa stanze richieste (conteggio ospiti per specie)
3. Confronto disponibilità vs richieste
4. Report generazione automatica

**Esempio:**
```java
// Stanze disponibili
Map<String, Integer> available = new HashMap<>();
available.put("vampiro", 3);
available.put("umano", 3);
available.put("lycan", 1);
available.put("ghost", 10);

// Conta richieste
Map<String, Integer> requested = new HashMap<>();
for(Person guest : guests) {
    String species = guest.species;
    requested.put(species, requested.getOrDefault(species, 0) + 1);
}

// Confronta
for(String species : requested.keySet()) {
    int req = requested.get(species);
    int avail = available.getOrDefault(species, 0);

    if(req <= avail)
        System.out.println("OK: " + species);
    else
        System.out.println("MANCANO " + (req - avail) + " stanze per " + species);
}
```

**Concetti dimostrati:**
- Map per conteggio occorrenze
- Pattern: `getOrDefault(key, 0) + 1`
- `containsKey()` per verifica presenza
- Confronto chiavi tra Map diverse
- Business logic con strutture dati

## Classi Entità

### Person
Classe semplice per rappresentare persone con attributi variabili a seconda della demo.

**Attributi comuni:**
- Nome (firstName)
- Cognome (lastName)
- Altri attributi specifici (gender, city, species, etc.)

**Utilizzo:**
```java
Person p = new Person("Mario", "Rossi", "M");
```

## Quando Usare Quale Struttura Dati

| Struttura | Quando Usarla | Vantaggi | Svantaggi |
|-----------|---------------|----------|-----------|
| **Array** | Dimensione fissa nota a priori | Veloce accesso O(1), basso overhead | Non ridimensionabile |
| **ArrayList** | Dimensione variabile | Flessibile, facile da usare | Più lenta di array per accesso |
| **HashSet** | Rimuovere duplicati, verifica appartenenza | Nessun duplicato, contains() veloce | Non ordinato, no accesso per indice |
| **HashMap** | Associazioni chiave-valore, conteggi | Accesso O(1) per chiave | Usa più memoria |

## Algoritmi e Complessità

### Ricerca Lineare in Array
```java
for(int i = 0; i < arr.length; i++) {
    if(arr[i] == target) return i;
}
```
**Complessità:** O(n)

### Conteggio con Map
```java
map.put(key, map.getOrDefault(key, 0) + 1);
```
**Complessità:** O(1) per singola operazione

### Rimozione Duplicati con Set
```java
Set<T> unique = new HashSet<>(Arrays.asList(array));
```
**Complessità:** O(n)

## Pattern Comuni Dimostrati

### 1. Pattern Conteggio (Counting Pattern)
```java
Map<String, Integer> counts = new HashMap<>();
for(Item item : items) {
    String key = item.getCategory();
    counts.put(key, counts.getOrDefault(key, 0) + 1);
}
```

### 2. Pattern Raggruppamento (Grouping Pattern)
```java
Map<String, List<Item>> groups = new HashMap<>();
for(Item item : items) {
    groups.computeIfAbsent(item.getCategory(), k -> new ArrayList<>()).add(item);
}
```

### 3. Pattern Filtraggio (Filtering Pattern)
```java
List<Item> filtered = new ArrayList<>();
for(Item item : items) {
    if(item.matches(criteria))
        filtered.add(item);
}
```

### 4. Pattern Rimozione Duplicati (Deduplication Pattern)
```java
Set<Item> unique = new HashSet<>(items);
```

## Esercizi Proposti

1. Implementare ricerca binaria in array ordinato
2. Creare una Map che conta occorrenze di parole in un testo
3. Raggruppare studenti per voto usando Map
4. Trovare intersezione di due Set
5. Ordinare ArrayList con `Collections.sort()`
6. Implementare stack usando ArrayList
7. Creare rubrica telefonica con HashMap

## Competenze Acquisite

- Comprensione array e limiti strutture fisse
- Utilizzo Collection Framework Java
- Scelta struttura dati appropriata al problema
- Pattern conteggio e raggruppamento con Map
- Rimozione duplicati con Set
- Iterazione collezioni (for, for-each)
- Conversione tra array e Collection
- Algoritmi di ricerca e filtraggio
- Business logic con strutture dati complesse

## Risorse Aggiuntive

### Java Collections Framework
- `java.util.ArrayList`
- `java.util.HashSet`
- `java.util.HashMap`
- `java.util.Arrays`

### Metodi Utili
```java
// ArrayList
list.add(element)
list.remove(index)
list.get(index)
list.size()
list.contains(element)

// Set
set.add(element)
set.contains(element)
set.remove(element)

// Map
map.put(key, value)
map.get(key)
map.getOrDefault(key, defaultValue)
map.containsKey(key)
map.keySet()
map.values()
map.computeIfAbsent(key, mappingFunction)
```

## Note Tecniche

- Gli array hanno dimensione fissa definita alla creazione
- ArrayList ridimensiona automaticamente il backing array
- HashSet usa HashMap internamente
- HashMap non garantisce ordine (usa LinkedHashMap per mantenere ordine inserimento)
- `Arrays.asList()` restituisce lista fixed-size
- Le Collection usano generics per type safety

## Differenze Fondamentali

### Array vs ArrayList
```java
// Array - dimensione fissa
String[] arr = new String[10];
arr[0] = "Hello";

// ArrayList - dimensione dinamica
ArrayList<String> list = new ArrayList<>();
list.add("Hello");
```

### List vs Set
```java
// List - permette duplicati, ordine garantito
List<String> list = Arrays.asList("A", "B", "A");  // ["A", "B", "A"]

// Set - no duplicati, ordine non garantito
Set<String> set = new HashSet<>(list);  // {"A", "B"}
```

### Array vs Map
```java
// Array - accesso per indice numerico
String[] arr = {"Mario", "Luigi"};
System.out.println(arr[0]);  // "Mario"

// Map - accesso per chiave custom
Map<String, String> map = new HashMap<>();
map.put("player1", "Mario");
System.out.println(map.get("player1"));  // "Mario"
```

## Caso d'Uso Reale: Hotel Transilvania

La Demo009 rappresenta un caso d'uso completo che integra tutti i concetti:

1. **Map per disponibilità** -- Struttura statica iniziale
2. **Array per ospiti** -- Input dati
3. **Map per richieste** -- Aggregazione dinamica con conteggio
4. **Confronto Map** -- Business logic complessa
5. **Report** -- Iterazione e output

Questo pattern è comune in applicazioni reali:
- E-commerce (inventario vs carrelli)
- Hotel (stanze vs prenotazioni)
- Eventi (posti vs registrazioni)
- Risorse (disponibilità vs richieste)

---

<div align="center">

**Hacman Viorica Gabriela** | Generation Italy — Java Full Stack Developer

[Torna a Fundamentals Examples](../README.md) · [README principale](../../README.md)

</div>
