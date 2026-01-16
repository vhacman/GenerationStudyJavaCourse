/****************************************************************
 * TABELLA EXPENSE - Persistenza dello Stato di un'Entità
 *
 * TODO: Creare tabella expense seguendo la struttura di guest
 *
 * PSEUDOCODICE:
 * =============
 *
 * 1. Copiare la struttura di queries/01_guest_create_table.sql
 *
 * 2. Sostituire "guest" con "expense"
 *
 * 3. Definire colonne mappando da Expense.java:
 *    - id          INTEGER PRIMARY KEY AUTOINCREMENT
 *    - date        DATE            -- Data della spesa (formato YYYY-MM-DD)
 *    - description VARCHAR(200)    -- Descrizione della spesa
 *    - value       REAL            -- Valore monetario (tipo decimale)
 *    - category    VARCHAR(50)     -- Categoria: FOOD, SERVICES, SALARIES
 *
 * 4. NOTA IMPORTANTE su category:
 *    - In Java è ExpenseCategory (ENUM)
 *    - In SQL si memorizza come VARCHAR con valori:
 *      * "FOOD"
 *      * "SERVICES"
 *      * "SALARIES"
 *
 * 5. Aggiungere commenti per spiegare:
 *    - Relazione OOP → Database Relazionale
 *    - Mappatura ENUM Java → VARCHAR SQL
 *    - Tipi di dato SQL e loro equivalente Java
 *
 * 6. Includere sezione "EQUIVALENTE JAVA (Entità JPA)" alla fine
 *
 ****************************************************************/

-- TODO: Scrivere qui il CREATE TABLE expense seguendo il template di guest
