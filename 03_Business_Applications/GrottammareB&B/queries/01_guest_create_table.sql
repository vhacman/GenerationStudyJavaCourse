/****************************************************************
 * TABELLA GUEST - Persistenza dello Stato di un'Entità
 * 
 * Relazione OOP → Database Relazionale:
 * 
 * → Classe Java (Guest)    ≡  Tabella SQL (guest)
 * → Attributo              ≡  Colonna
 * → Oggetto (istanza)      ≡  Riga (record/tupla)
 * → Tipo Java              ≡  Tipo SQL
 * 
 * Questa tabella rappresenta la persistenza dello STATO
 * di molteplici oggetti Guest. Ogni riga memorizza i valori
 * degli attributi di una singola istanza.
 * 
 * Principio: Object-Relational Mapping (ORM)
 ****************************************************************/

/****************************************************************
 * TIPI DI DATO UTILIZZATI
 * 
 * INTEGER          → Numeri interi (equivale a int/Integer in Java)
 * VARCHAR(n)       → Stringa a lunghezza variabile, max n caratteri
 *                    (equivale a String in Java)
 * DATE             → Data nel formato YYYY-MM-DD
 *                    (equivale a LocalDate in Java)
 * 
 * PRIMARY KEY      → Vincolo di unicità + NOT NULL
 *                    Identifica univocamente ogni riga (come un ID)
 * AUTOINCREMENT    → Il valore viene generato automaticamente
 *                    dal DBMS ad ogni INSERT (1, 2, 3, ...)
 ****************************************************************/

CREATE TABLE guest
(
    -- Chiave primaria: identificatore univoco auto-generato
    id          INTEGER         PRIMARY KEY AUTOINCREMENT,
    
    -- Dati anagrafici
    firstname   VARCHAR(100),   -- Nome
    lastname    VARCHAR(100),   -- Cognome
    dob         DATE,           -- Data di nascita (Date of Birth)
    ssn         VARCHAR(20),    -- Codice fiscale / Social Security Number
    
    -- Dati di residenza
    address     VARCHAR(100),   -- Indirizzo
    city        VARCHAR(100)    -- Città
);

/****************************************************************
 * EQUIVALENTE JAVA (Entità JPA)
 * 
 * @Entity
 * public class Guest
 * {
 *     @Id
 *     @GeneratedValue(strategy = GenerationType.IDENTITY)
 *     private Integer     id;
 *     
 *     private String      firstname;
 *     private String      lastname;
 *     private LocalDate   dob;
 *     private String      ssn;
 *     private String      address;
 *     private String      city;
 * }
 ****************************************************************/
