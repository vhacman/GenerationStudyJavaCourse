-- Esercizio 7: Self join e alias di tabella

-- ========================================================================
-- Creazione dello schema
-- ========================================================================

DROP TABLE IF EXISTS PERSON;

CREATE TABLE PERSON (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL,
    JOB VARCHAR(100),
    SALARY DECIMAL(10,2),
    CITY VARCHAR(100),
    GENDER VARCHAR(20),
    FATHER_ID INT
);

-- ========================================================================
-- Inserimento dei dati di esempio
-- ========================================================================

INSERT INTO PERSON (NAME, SURNAME, JOB, SALARY, CITY, GENDER, FATHER_ID) VALUES
-- Persone con lavori e stipendi diversi (per query 1)
('Mario', 'Rossi', 'Ingegnere', 50000.00, 'Roma', 'M', NULL),
('Luigi', 'Rossi', 'Ingegnere', 35000.00, 'Roma', 'M', 1),    -- figlio di Mario
('Anna', 'Bianchi', 'Medico', 60000.00, 'Milano', 'F', NULL),
('Laura', 'Bianchi', 'Medico', 45000.00, 'Milano', 'F', 3),    -- figlia di Anna
('Paolo', 'Verdi', 'Insegnante', 40000.00, 'Napoli', 'M', NULL),
('Giovanni', 'Verdi', 'Insegnante', 30000.00, 'Napoli', 'M', 5), -- figlio di Paolo
('Sara', 'Neri', 'Medico', 55000.00, 'Milano', 'F', 3),        -- figlia di Anna
('Marco', 'Gialli', 'Ingegnere', 42000.00, 'Roma', 'M', 1),    -- figlio di Mario

-- Persone per la stessa città (per query 2)
('Luca', 'Marroni', 'Avvocato', 55000.00, 'Torino', 'M', NULL),
('Chiara', 'Marroni', 'Avvocato', 48000.00, 'Torino', 'F', 9),  -- figlia di Luca
('Alessandro', 'Esposito', 'Chef', 38000.00, 'Napoli', 'M', NULL),
('Francesca', 'Esposito', 'Studentessa', 0.00, 'Napoli', 'F', 11);

-- ========================================================================
-- QUERY 1: Associare ogni persona a tutte le persone che fanno lo stesso 
-- lavoro ma guadagnano meno di lei. Stampare nome, cognome e stipendio 
-- di entrambe le persone.
-- ========================================================================

SELECT 
    P1.NAME AS P1_NAME,
    P1.SURNAME AS P1_SURNAME,
    P1.JOB AS LAVORO,
    P1.SALARY AS P1_SALARY,
    P2.NAME AS P2_NAME,
    P2.SURNAME AS P2_SURNAME,
    P2.SALARY AS P2_SALARY
FROM PERSON P1, PERSON P2
WHERE P1.JOB = P2.JOB 
  AND P2.SALARY < P1.SALARY
  AND P1.ID != P2.ID;

/*
SPIEGAZIONE:
- P1 rappresenta la persona con stipendio più alto
- P2 rappresenta la persona con lo stesso lavoro ma stipendio più basso
- P1.JOB = P2.JOB assicura che abbiano lo stesso lavoro
- P2.SALARY < P1.SALARY filtra solo chi guadagna meno
- P1.ID != P2.ID evita di abbinare una persona con se stessa

RISULTATO ATTESO:
| P1_NAME | P1_SURNAME | LAVORO     | P1_SALARY | P2_NAME | P2_SURNAME | P2_SALARY |
|---------|------------|------------|-----------|---------|------------|-----------|
| Mario   | Rossi      | Ingegnere  | 50000     | Luigi   | Rossi      | 35000     |
| Mario   | Rossi      | Ingegnere  | 50000     | Marco   | Gialli     | 42000     |
| Luigi   | Rossi      | Ingegnere  | 35000     | (nessuno con stipendio minore) |
| Anna    | Bianchi    | Medico     | 60000     | Laura   | Bianchi    | 45000     |
| Anna    | Bianchi    | Medico     | 60000     | Sara    | Neri       | 55000     |
| Laura   | Bianchi    | Medico     | 45000     | (nessuno con stipendio minore) |
| Sara    | Neri       | Medico     | 55000     | Laura   | Bianchi    | 45000     |
| Paolo   | Verdi      | Insegnante | 40000     | Giovanni| Verdi      | 30000     |
| Marco   | Gialli     | Ingegnere  | 42000     | Luigi   | Rossi      | 35000     |
*/

-- ========================================================================
-- QUERY 2: Associare ogni persona a ogni altra persona della sua stessa 
-- città, ma che non sia lui stesso. Stampare nome, cognome e genere 
-- di ambo le persone.
-- ========================================================================

SELECT 
    P1.NAME AS P1_NAME,
    P1.SURNAME AS P1_SURNAME,
    P1.GENDER AS P1_GENDER,
    P2.NAME AS P2_NAME,
    P2.SURNAME AS P2_SURNAME,
    P2.GENDER AS P2_GENDER,
    P1.CITY
FROM PERSON P1, PERSON P2
WHERE P1.CITY = P2.CITY 
  AND P1.ID != P2.ID;

/*
SPIEGAZIONE:
- P1 e P2 rappresentano due persone diverse
- P1.CITY = P2.CITY assicura che siano della stessa città
- P1.ID != P2.ID evita di abbinare una persona con se stessa

RISULTATO ATTESO (città Roma per esempio):
| P1_NAME | P1_SURNAME | P1_GENDER | P2_NAME | P2_SURNAME | P2_GENDER | CITY |
|---------|------------|-----------|---------|------------|-----------|------|
| Mario   | Rossi      | M         | Luigi   | Rossi      | M         | Roma |
| Mario   | Rossi      | M         | Marco   | Gialli     | M         | Roma |
| Luigi   | Rossi      | M         | Mario   | Rossi      | M         | Roma |
| Luigi   | Rossi      | M         | Marco   | Gialli     | M         | Roma |
| Marco   | Gialli     | M         | Mario   | Rossi      | M         | Roma |
| Marco   | Gialli     | M         | Luigi   | Rossi      | M         | Roma |

Nota: Ogni coppia appare due volte (P1,P2 e P2,P1). Se si desidera 
una sola copia, si può aggiungere una condizione P1.ID < P2.ID.
*/

-- Versione alternativa con una sola direzione per ogni coppia:
SELECT 
    P1.NAME AS P1_NAME,
    P1.SURNAME AS P1_SURNAME,
    P1.GENDER AS P1_GENDER,
    P2.NAME AS P2_NAME,
    P2.SURNAME AS P2_SURNAME,
    P2.GENDER AS P2_GENDER,
    P1.CITY
FROM PERSON P1, PERSON P2
WHERE P1.CITY = P2.CITY 
  AND P1.ID < P2.ID  -- Solo una direzione per coppia
ORDER BY P1.CITY, P1.SURNAME;

-- ========================================================================
-- QUERY 3: Stampare nome e cognome di ogni persona con a fianco nome 
-- e cognome del padre, ove disponibile.
-- ========================================================================

SELECT 
    FIGLIO.NAME AS FIGLIO_NAME,
    FIGLIO.SURNAME AS FIGLIO_SURNAME,
    PADRE.NAME AS PADRE_NAME,
    PADRE.SURNAME AS PADRE_SURNAME
FROM PERSON FIGLIO, PERSON PADRE
WHERE FIGLIO.FATHER_ID = PADRE.ID;

/*
SPIEGAZIONE:
- FIGLIO è la tabella con tutti i dati della persona
- PADRE è la tabella con i dati del padre (stessa tabella!)
- FIGLIO.FATHER_ID = PADRE.ID stabilisce la relazione padre-figlio
- Se FATHER_ID è NULL, la riga non appare (LEFT JOIN potrebbe essere usato)

RISULTATO ATTESO:
| FIGLIO_NAME | FIGLIO_SURNAME | PADRE_NAME | PADRE_SURNAME |
|-------------|----------------|------------|---------------|
| Luigi       | Rossi          | Mario      | Rossi         |
| Laura       | Bianchi        | Anna       | Bianchi       |
| Giovanni    | Verdi          | Paolo      | Verdi         |
| Sara        | Neri           | Anna       | Bianchi       |
| Marco       | Gialli         | Mario      | Rossi         |
| Chiara      | Marroni        | Luca       | Marroni       |
| Francesca   | Esposito       | Alessandro | Esposito      |
*/

-- ========================================================================
-- QUERY 3 CON LEFT JOIN: Include anche chi non ha padre registrato
-- ========================================================================

SELECT 
    FIGLIO.NAME AS FIGLIO_NAME,
    FIGLIO.SURNAME AS FIGLIO_SURNAME,
    PADRE.NAME AS PADRE_NAME,
    PADRE.SURNAME AS PADRE_SURNAME
FROM PERSON FIGLIO
LEFT JOIN PERSON PADRE ON FIGLIO.FATHER_ID = PADRE.ID;

/*
RISULTATO ATTESO (con LEFT JOIN):
| FIGLIO_NAME | FIGLIO_SURNAME | PADRE_NAME | PADRE_SURNAME |
|-------------|----------------|------------|---------------|
| Mario       | Rossi          | NULL       | NULL          |  (non ha padre registrato)
| Luigi       | Rossi          | Mario      | Rossi         |
| Anna        | Bianchi        | NULL       | NULL          |  (non ha padre registrato)
| Laura       | Bianchi        | Anna       | Bianchi       |
| Paolo       | Verdi          | NULL       | NULL          |  (non ha padre registrato)
| Giovanni    | Verdi          | Paolo      | Verdi         |
| Sara        | Neri           | Anna       | Bianchi       |
| Marco       | Gialli         | Mario      | Rossi         |
| Luca        | Marroni        | NULL       | NULL          |  (non ha padre registrato)
| Chiara      | Marroni        | Luca       | Marroni       |
| Alessandro  | Esposito       | NULL       | NULL          |  (non ha padre registrato)
| Francesca   | Esposito       | Alessandro | Esposito      |

Nota: Con LEFT JOIN appaiono anche le persone senza padre registrato
(come Mario Rossi, Anna Bianchi, Paolo Verdi, Luca Marroni, 
Alessandro Esposito).
*/
