-- Esercizio 5: Interrogazioni su più tabelle con LEFT JOIN
-- Schema di esempio per l'esercizio

-- Creazione delle tabelle
CREATE TABLE CITY (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL
);

CREATE TABLE PERSON (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL,
    CITY_ID INT,
    FOREIGN KEY (CITY_ID) REFERENCES CITY(ID) ON DELETE SET NULL
);

CREATE TABLE AUTO (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PLATE VARCHAR(20) NOT NULL,
    MODEL VARCHAR(100) NOT NULL,
    COST DECIMAL(10,2),
    OWNER_ID INT,
    FOREIGN KEY (OWNER_ID) REFERENCES PERSON(ID) ON DELETE SET NULL
);

-- Inserimento delle città
INSERT INTO CITY (NAME) VALUES
('Lisbona'),
('Porto'),
('Faro'),
('Coimbra');

-- Inserimento delle persone (inserite per mostrare tutti i casi)
INSERT INTO PERSON (NAME, SURNAME, CITY_ID) VALUES
('Mario', 'Rossi', 1),      -- Lisbona, possiede auto
('Luigi', 'Bianchi', 1),    -- Lisbona, possiede auto costosa
('Anna', 'Verdi', 1),       -- Lisbona, possiede auto economica
('Paolo', 'Neri', 1),       -- Lisbona, SENZA auto
('Maria', 'Gialli', 2),     -- Porto, possiede auto
('Giovanni', 'Blu', 2),     -- Porto, SENZA auto
('Sofia', 'Rossi', 3),      -- Faro, possiede auto
('Marco', 'Bianchi', NULL); -- Città non specificata, SENZA auto

-- Inserimento delle auto
INSERT INTO AUTO (PLATE, MODEL, COST, OWNER_ID) VALUES
('AA-111-AA', 'Fiat Panda', 15000.00, 1),      -- Mario Rossi
('BB-222-BB', 'BMW Serie 3', 45000.00, 2),     -- Luigi Bianchi
('CC-333-CC', 'Ford Fiesta', 18000.00, 3),     -- Anna Verdi
('DD-444-DD', 'Audi A4', 25000.00, 5),         -- Maria Gialli
('EE-555-EE', 'Toyota Yaris', 22000.00, 7);   -- Sofia Rossi

/*
===========================================================================
QUERY 1: Nome e cognome delle persone e targa e modello delle auto
         per tutte le persone del database, che abbiano o meno auto.
         Uso LEFT JOIN per preservare tutte le persone.
===========================================================================
*/
SELECT 
    PERSON.NAME,
    PERSON.SURNAME,
    AUTO.PLATE,
    AUTO.MODEL,
    AUTO.COST
FROM PERSON 
LEFT JOIN AUTO ON PERSON.ID = AUTO.OWNER_ID;

/*
Risultato atteso:
- Mario Rossi, AA-111-AA, Fiat Panda, 15000
- Luigi Bianchi, BB-222-BB, BMW Serie 3, 45000
- Anna Verdi, CC-333-CC, Ford Fiesta, 18000
- Paolo Neri, NULL, NULL, NULL          (persona SENZA auto)
- Maria Gialli, DD-444-DD, Toyota Yaris, 22000
- Giovanni Blu, NULL, NULL, NULL        (persona SENZA auto)
- Sofia Rossi, EE-555-EE, Toyota Yaris, 22000
- Marco Bianchi, NULL, NULL, NULL     (persona SENZA auto)
===========================================================================
*/

/*
===========================================================================
QUERY 2: Persone di Lisbona con relative auto
         Uso INNER JOIN con City per filtrare per città.
===========================================================================
*/
SELECT 
    PERSON.NAME,
    PERSON.SURNAME,
    AUTO.PLATE,
    AUTO.MODEL,
    AUTO.COST
FROM PERSON 
LEFT JOIN AUTO ON PERSON.ID = AUTO.OWNER_ID
INNER JOIN CITY ON CITY.ID = PERSON.CITY_ID
WHERE CITY.NAME = 'Lisbona';

/*
Risultato atteso:
- Mario Rossi, AA-111-AA, Fiat Panda, 15000
- Luigi Bianchi, BB-222-BB, BMW Serie 3, 45000
- Anna Verdi, CC-333-CC, Ford Fiesta, 18000
- Paolo Neri, NULL, NULL, NULL          (persona SENZA auto, ma è di Lisbona)
===========================================================================
*/

/*
===========================================================================
QUERY 3: Solo auto con costo superiore a 20.000 euro
         Devo includere OR AUTO.COST IS NULL per non perdere 
         le persone senza auto.
===========================================================================
*/
SELECT 
    PERSON.NAME,
    PERSON.SURNAME,
    AUTO.PLATE,
    AUTO.MODEL,
    AUTO.COST
FROM PERSON 
LEFT JOIN AUTO ON PERSON.ID = AUTO.OWNER_ID
WHERE AUTO.COST > 20000 OR AUTO.COST IS NULL;

/*
Risultato atteso:
- Luigi Bianchi, BB-222-BB, BMW Serie 3, 45000    (>20000)
- Maria Gialli, DD-444-DD, Toyota Yaris, 22000   (>20000)
- Paolo Neri, NULL, NULL, NULL                    (SENZA auto)
- Giovanni Blu, NULL, NULL, NULL                  (SENZA auto)
- Marco Bianchi, NULL, NULL, NULL                (SENZA auto)

Nota: Le auto con costo <= 20000 (Fiat Panda e Ford Fiesta) 
      e le rispettive persone (Mario Rossi e Anna Verdi) 
      non appaiono nel risultato.
===========================================================================
*/
