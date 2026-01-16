/****************************************************************
 * SELECT * - Lettura Completa della Tabella
 * 
 * Questa è la forma più semplice di SELECT:
 * legge TUTTE le colonne (*) di TUTTE le righe.
 * 
 * Senza la clausola WHERE, non c'è filtro:
 * vengono restituiti tutti i record presenti nella tabella.
 ****************************************************************/

/****************************************************************
 * ANATOMIA DELLA QUERY
 * 
 * SELECT   → Verbo di lettura (operazione READ del CRUD)
 * *        → Wildcard: "tutte le colonne"
 * FROM     → Specifica la sorgente dei dati
 * guest    → Nome della tabella
 * 
 * Manca WHERE → nessun filtro, restituisce tutto
 ****************************************************************/

/****************************************************************
 * EQUIVALENTE JAVA (Repository Pattern)
 * 
 * // Con JDBC
 * String sql = "SELECT * FROM guest";
 * Statement stmt = connection.createStatement();
 * ResultSet rs = stmt.executeQuery(sql);
 * while (rs.next()) {
 *     // leggi ogni riga
 * }
 * 
 * // Con Spring Data JPA
 * List<Guest> tutti = guestRepository.findAll();
 * 
 * Nota: findAll() restituisce una List<Guest> con tutti
 *       gli oggetti persistiti, mappati automaticamente.
 ****************************************************************/

-- Seleziona tutti i guest (nessun filtro)
SELECT * FROM guest;

/****************************************************************
 * BEST PRACTICE
 * 
 * In produzione, evitare SELECT * perché:
 * 1. Trasferisce più dati del necessario
 * 2. Se la tabella cambia, il codice potrebbe rompersi
 * 
 * Meglio specificare le colonne:
 * SELECT id, firstname, lastname, city FROM guest;
 ****************************************************************/
