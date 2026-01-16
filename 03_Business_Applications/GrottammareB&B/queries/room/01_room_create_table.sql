/****************************************************************
 * TABELLA ROOM - Persistenza dello Stato di un'Entità
 *
 * TODO: Creare tabella room seguendo la struttura di guest
 *
 * PSEUDOCODICE:
 * =============
 *
 * 1. Copiare la struttura di queries/01_guest_create_table.sql
 *
 * 2. Sostituire "guest" con "room"
 *
 * 3. Definire colonne mappando da Room.java:
 *    - id          INTEGER PRIMARY KEY AUTOINCREMENT
 *    - name        VARCHAR(100)    -- Nome stanza
 *    - description VARCHAR(200)    -- Descrizione
 *    - size        REAL            -- Dimensione in mq (tipo decimale)
 *    - floor       INTEGER         -- Piano (0-6)
 *    - price       REAL            -- Prezzo per notte (tipo decimale)
 *
 * 4. Aggiungere commenti per spiegare:
 *    - Relazione OOP → Database Relazionale
 *    - Mappatura Classe Java ≡ Tabella SQL
 *    - Mappatura Attributo ≡ Colonna
 *    - Tipi di dato SQL e loro equivalente Java
 *
 * 5. Includere sezione "EQUIVALENTE JAVA (Entità JPA)" alla fine
 *
 ****************************************************************/

-- TODO: Scrivere qui il CREATE TABLE room seguendo il template di guest
