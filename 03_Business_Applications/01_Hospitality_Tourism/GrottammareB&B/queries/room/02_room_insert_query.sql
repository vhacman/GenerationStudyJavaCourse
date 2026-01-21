/****************************************************************
 * INSERT INTO ROOM - Operazione di Creazione (Create)
 *
 * TODO: Inserire dati di esempio nella tabella room
 *
 * PSEUDOCODICE:
 * =============
 *
 * 1. Copiare la struttura di queries/02_insert_query.sql
 *
 * 2. Creare INSERT multiplo (batch insert) per 5-10 stanze:
 *    INSERT INTO room (name, description, size, floor, price) VALUES
 *    ('nome1', 'desc1', size1, floor1, price1),
 *    ('nome2', 'desc2', size2, floor2, price2),
 *    ...
 *
 * 3. NON includere id (è AUTOINCREMENT)
 *
 * 4. Dati realistici da usare:
 *    - name: "Camera Standard", "Suite Deluxe", "Camera Vista Mare", ecc.
 *    - description: descrizioni dettagliate e realistiche
 *    - size: valori realistici tra 15.0 e 50.0 (mq)
 *    - floor: valori tra 0 e 6
 *    - price: valori realistici tra 50.00 e 200.00 (euro/notte)
 *
 * 5. Rispettare validazioni di Room.java:
 *    - size > 0
 *    - floor >= 0 && floor <= 6
 *    - price > 0
 *    - name e description NON vuoti
 *
 * 6. Aggiungere commenti esplicativi come nel file guest
 *
 * 7. Includere sezione "EQUIVALENTE JAVA (Repository Pattern)"
 *
 ****************************************************************/

-- TODO: Scrivere qui gli INSERT per room seguendo il template di guest
