-- Creazione della tabella PEOPLE
--
-- Campi:
--   id       : chiave primaria auto-incrementale
--   firstname: nome
--   lastname : cognome
--   dob      : data di nascita
--   city     : città di appartenenza
--            -> valori ammessi: RACCOON VILLE | LISBON | NEW YORK
--   role     : ruolo della persona
--            -> valori ammessi: RESEARCHER | FOREST GUARDIAN | EXPLORER
--   salary   : stipendio mensile in euro (0 se minorenne)
--   gender   : genere della persona
--            -> valori ammessi: M | F
use ex00;

-- Prima di tutto se la tabella esiste già la elimino,
-- così posso ricominciare da zero senza errori
DROP TABLE IF EXISTS people;

-- Creo la tabella PEOPLE con tutti i campi necessari
CREATE TABLE people (
    -- id: chiave primaria che si incrementa da sola ad ogni inserimento
    id        INTEGER        PRIMARY KEY AUTO_INCREMENT,

    -- nome e cognome: stringhe obbligatorie, 100 caratteri bastano
    firstname VARCHAR(100)   NOT NULL,
    lastname  VARCHAR(100)   NOT NULL,

    -- data di nascita: campo DATE obbligatorio, mi serve per calcolare l'età dopo
    dob       DATE           NOT NULL,

    -- città: solo tre valori ammessi, uso CHECK per impedire errori di battitura
    city      VARCHAR(50)    NOT NULL
                             CHECK (city IN ('RACCOON VILLE', 'LISBON', 'NEW YORK')),

    -- ruolo: default EXPLORER così non devo specificarlo sempre nell'INSERT;
    -- lo aggiorno dopo nel file 03
    role      VARCHAR(50)    NOT NULL DEFAULT 'EXPLORER'
                             CHECK (role IN ('RESEARCHER', 'FOREST GUARDIAN', 'EXPLORER')),

    -- stipendio: uso DECIMAL per i soldi (evito problemi di arrotondamento con FLOAT);
    -- default 0 per i minorenni che non guadagnano
    salary    DECIMAL(10,2)  NOT NULL DEFAULT 0.00,

    -- genere: solo M o F, un carattere basta
    gender    VARCHAR(1)     NOT NULL
                             CHECK (gender IN ('M', 'F'))
);
