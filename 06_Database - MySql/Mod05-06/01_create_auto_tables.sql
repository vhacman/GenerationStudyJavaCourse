-- Creazione del database e delle tabelle per l'esercizio sulle auto
--
-- Struttura:
--   PROPRIETARI: informazioni sui proprietari delle auto
--   AUTO: informazioni sulle auto possedute
--
-- Relazione: un proprietario può avere più auto (1:N)

USE ex00;

-- Elimino le tabelle se esistono già (in ordine inverso per le foreign key)
DROP TABLE IF EXISTS auto;
DROP TABLE IF EXISTS proprietari;

-- Tabella PROPRIETARI
CREATE TABLE proprietari (
    id        INTEGER       PRIMARY KEY AUTO_INCREMENT,
    nome      VARCHAR(100)  NOT NULL,
    cognome   VARCHAR(100)  NOT NULL,
    citta     VARCHAR(50)   NOT NULL
);

-- Tabella AUTO
CREATE TABLE auto (
    id              INTEGER        PRIMARY KEY AUTO_INCREMENT,
    modello         VARCHAR(100)   NOT NULL,
    targa           VARCHAR(20)    NOT NULL UNIQUE,
    prezzo          DECIMAL(10,2)  NOT NULL,
    proprietario_id INTEGER        NOT NULL,

    -- Foreign key che collega ogni auto al suo proprietario
    CONSTRAINT fk_proprietario
        FOREIGN KEY (proprietario_id)
        REFERENCES proprietari(id)
        ON DELETE CASCADE
);
