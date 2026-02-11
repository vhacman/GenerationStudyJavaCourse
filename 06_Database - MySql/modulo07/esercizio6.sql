-- Esercizio 6: Integrità Referenziale
-- Due scenari: ON DELETE CASCADE e ON DELETE RESTRICT

-- ========================================================================
-- PARTE 1: ON DELETE CASCADE
-- ========================================================================

-- Drop delle tabelle se esistono (per ripartire da zero)
DROP TABLE IF EXISTS AUTO_CASCADE;
DROP TABLE IF EXISTS PERSON_CASCADE;

-- Creazione della tabella PERSON con cascade
CREATE TABLE PERSON_CASCADE (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL
);

-- Creazione della tabella AUTO con ON DELETE CASCADE
-- Quando si cancella una persona, le sue auto vengono automaticamente cancellate
CREATE TABLE AUTO_CASCADE (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PLATE VARCHAR(20) NOT NULL,
    MODEL VARCHAR(100) NOT NULL,
    COST DECIMAL(10,2),
    OWNER_ID INT,
    FOREIGN KEY (OWNER_ID) REFERENCES PERSON_CASCADE(ID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- Inserimento di dati di esempio
INSERT INTO PERSON_CASCADE (NAME, SURNAME) VALUES
('Mario', 'Rossi'),
('Luigi', 'Bianchi'),
('Anna', 'Verdi');

INSERT INTO AUTO_CASCADE (PLATE, MODEL, COST, OWNER_ID) VALUES
('AA-111-AA', 'Fiat Panda', 15000.00, 1),   -- Auto di Mario
('BB-222-BB', 'BMW Serie 3', 45000.00, 1),  -- Seconda auto di Mario
('CC-333-CC', 'Ford Fiesta', 18000.00, 2),  -- Auto di Luigi
('DD-444-DD', 'Toyota Yaris', 22000.00, 3);  -- Auto di Anna

-- Verifica dei dati PRIMA della cancellazione
SELECT 'PRIMA DELLA CANCELLAZIONE - PERSONE:' AS STATO;
SELECT * FROM PERSON_CASCADE;

SELECT 'PRIMA DELLA CANCELLAZIONE - AUTO:' AS STATO;
SELECT * FROM AUTO_CASCADE;

-- =========================================================================
-- PROVA: Cancellare Mario Rossi (che possiede 2 auto)
-- =========================================================================
SELECT 'CANCELLAZIONE DI MARIO ROSSI (ID=1) CON ON DELETE CASCADE' AS OPERAZIONE;

DELETE FROM PERSON_CASCADE WHERE ID = 1;

-- Verifica DOPO la cancellazione
SELECT 'DOPO LA CANCELLAZIONE - PERSONE:' AS STATO;
SELECT * FROM PERSON_CASCADE;

SELECT 'DOPO LA CANCELLAZIONE - AUTO:' AS STATO;
SELECT * FROM AUTO_CASCADE;

-- RISULTATO ATTESO:
-- Le auto con OWNER_ID = 1 (AA-111-AA e BB-222-BB) sono state
-- automaticamente cancellate insieme a Mario Rossi.


-- ========================================================================
-- PARTE 2: ON DELETE RESTRICT
-- ========================================================================

DROP TABLE IF EXISTS AUTO_RESTRICT;
DROP TABLE IF EXISTS PERSON_RESTRICT;

-- Creazione della tabella PERSON
CREATE TABLE PERSON_RESTRICT (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(100) NOT NULL,
    SURNAME VARCHAR(100) NOT NULL
);

-- Creazione della tabella AUTO con ON DELETE RESTRICT
-- Impedisce la cancellazione di una persona se ha auto associate
CREATE TABLE AUTO_RESTRICT (
    ID INT PRIMARY KEY AUTO_INCREMENT,
    PLATE VARCHAR(20) NOT NULL,
    MODEL VARCHAR(100) NOT NULL,
    COST DECIMAL(10,2),
    OWNER_ID INT,
    FOREIGN KEY (OWNER_ID) REFERENCES PERSON_RESTRICT(ID)
        ON DELETE RESTRICT
        ON UPDATE CASCADE
);

-- Inserimento di dati di esempio
INSERT INTO PERSON_RESTRICT (NAME, SURNAME) VALUES
('Paolo', 'Neri'),
('Sara', 'Marrone');

INSERT INTO AUTO_RESTRICT (PLATE, MODEL, COST, OWNER_ID) VALUES
('EE-555-EE', 'Audi A4', 38000.00, 4),   -- Auto di Paolo
('FF-666-FF', 'Renault Clio', 12000.00, 5); -- Auto di Sara

-- =========================================================================
-- PROVA: Tentare di cancellare Paolo Neri (che possiede 1 auto)
-- =========================================================================
SELECT 'TENTATIVO DI CANCELLAZIONE DI PAOLO NERI (ID=4) CON ON DELETE RESTRICT' AS OPERAZIONE;

-- Questa cancellazione fallirà con un errore di integrità referenziale
DELETE FROM PERSON_RESTRICT WHERE ID = 4;

-- Se il delete fosse riuscito (ma non riuscirà), vedremmo i dati aggiornati
SELECT 'OPERAZIONE COMPLETATA (se nessun errore è stato mostrato)' AS RISULTATO;

-- =========================================================================
-- MESSAGGIO DI ERRORE ATTESO:
-- Error Code: 1451. Cannot delete or update a parent row: 
-- a foreign key constraint fails
-- (`database`.`auto_restrict`, CONSTRAINT `auto_restrict_ibfk_1` 
-- FOREIGN KEY (`owner_id`) REFERENCES `person_restrict` (`id`) 
-- ON DELETE RESTRICT)
--
-- Questo errore indica che esistono righe figlie (auto) associate
-- alla persona che si tenta di cancellare, e il vincolo RESTRICT
-- impedisce l'operazione per mantenere l'integrità referenziale.
-- =========================================================================

-- =========================================================================
-- PROVA ALTERNATIVA: Cancellare una persona SENZA auto (funzionerà)
-- =========================================================================
SELECT 'CANCELLAZIONE DI UNA PERSONA SENZA AUTO (funzionerà)' AS OPERAZIONE;

-- Creiamo prima una persona senza auto
INSERT INTO PERSON_RESTRICT (NAME, SURNAME) VALUES
('Luca', 'Verdi');

SELECT 'PERSONE PRIMA DELLA CANCELLAZIONE:' AS STATO;
SELECT * FROM PERSON_RESTRICT;

-- Questa cancellazione riuscirà perché Luca non possiede auto
DELETE FROM PERSON_RESTRICT WHERE NAME = 'Luca';

SELECT 'PERSONE DOPO LA CANCELLAZIONE:' AS STATO;
SELECT * FROM PERSON_RESTRICT;

SELECT 'Luca Verdi è stato cancellato con successo perché non possedeva auto.' AS NOTE;
