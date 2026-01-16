/****************************************************************
 * CANCELLARE TUTTE LE RIGHE DI UNA TABELLA
 * 
 * Esistono due modi principali:
 * 
 * 1) DELETE FROM    → Cancella riga per riga
 * 2) TRUNCATE TABLE → Svuota la tabella in blocco
 ****************************************************************/

/****************************************************************
 * METODO 1: DELETE senza WHERE
 * 
 * Elimina tutte le righe una per una.
 * → Più lento su tabelle grandi
 * → Operazione loggata (può fare rollback in una transazione)
 * → NON resetta l'autoincrement
 ****************************************************************/

DELETE FROM guest;

/****************************************************************
 * METODO 2: TRUNCATE TABLE
 * 
 * Svuota la tabella in un'unica operazione.
 * → Molto più veloce
 * → Resetta l'autoincrement a 1
 * → Non può avere WHERE (è tutto o niente)
 * → In alcuni DBMS non è rollbackabile
 ****************************************************************/

TRUNCATE TABLE guest;

/****************************************************************
 * ATTENZIONE: DROP TABLE è diverso!
 * 
 * DROP TABLE guest;  → Elimina la tabella STESSA (struttura + dati)
 *                      La tabella non esiste più.
 * 
 * DELETE / TRUNCATE  → La tabella rimane, solo i dati spariscono.
 ****************************************************************/

/****************************************************************
 * EQUIVALENTE JAVA
 * 
 * // Con JDBC
 * Statement stmt = connection.createStatement();
 * stmt.executeUpdate("DELETE FROM guest");
 * 
 * // Con Spring Data JPA
 * guestRepository.deleteAll();
 ****************************************************************/
