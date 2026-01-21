/****************************************************************
 * INSERT INTO EXPENSE - Operazione di Creazione (Create)
 *
 * TODO: Inserire dati di esempio nella tabella expense
 *
 * PSEUDOCODICE:
 * =============
 *
 * 1. Copiare la struttura di queries/02_insert_query.sql
 *
 * 2. Creare INSERT multiplo (batch insert) per 8-12 spese:
 *    INSERT INTO expense (date, description, value, category) VALUES
 *    ('2025-01-10', 'desc1', value1, 'FOOD'),
 *    ('2025-01-11', 'desc2', value2, 'SERVICES'),
 *    ...
 *
 * 3. NON includere id (è AUTOINCREMENT)
 *
 * 4. Dati realistici da usare:
 *    - date: date recenti in formato 'YYYY-MM-DD'
 *    - description: descrizioni specifiche per categoria
 *    - value: valori realistici (50.00 - 2000.00)
 *    - category: distribuire tra le 3 categorie:
 *
 *      FOOD: "Acquisto forniture cucina", "Spesa supermercato", ecc.
 *            Valori: 100.00 - 500.00
 *
 *      SERVICES: "Bolletta elettrica", "Pulizie", "Manutenzione", ecc.
 *                Valori: 50.00 - 300.00
 *
 *      SALARIES: "Stipendio receptionist", "Stipendio cuoco", ecc.
 *                Valori: 1200.00 - 2000.00
 *
 * 5. Rispettare validazioni di Expense.java:
 *    - date NON null
 *    - description NON vuota
 *    - value > 0
 *    - category deve essere esattamente: 'FOOD', 'SERVICES' o 'SALARIES'
 *
 * 6. Aggiungere commenti esplicativi come nel file guest
 *
 * 7. Includere sezione "EQUIVALENTE JAVA (Repository Pattern)"
 *
 ****************************************************************/

-- TODO: Scrivere qui gli INSERT per expense seguendo il template di guest
