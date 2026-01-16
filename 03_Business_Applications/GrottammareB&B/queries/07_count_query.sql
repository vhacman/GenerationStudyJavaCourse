/****************************************************************
 * COUNT(*) - Funzione di Aggregazione
 * 
 * Conta il numero di righe che soddisfano la condizione WHERE.
 * Restituisce un singolo valore intero, non i dati.
 * 
 * L'asterisco (*) significa "conta tutte le righe".
 ****************************************************************/

/****************************************************************
 * EQUIVALENTE JAVA
 * 
 * // Con JDBC
 * String sql = "SELECT COUNT(*) FROM guest WHERE city = ?";
 * PreparedStatement ps = connection.prepareStatement(sql);
 * ps.setString(1, "Roma");
 * ResultSet rs = ps.executeQuery();
 * if (rs.next()) {
 *     int count = rs.getInt(1);
 * }
 * 
 * // Con Spring Data JPA
 * long countByCity(String city);
 ****************************************************************/

-- Quanti guest abitano a Roma?
SELECT COUNT(*) FROM guest WHERE city = 'Roma';

-- Quanti guest ci sono in totale?
SELECT COUNT(*) FROM guest;

-- Quanti guest hanno un indirizzo specificato?
SELECT COUNT(*) FROM guest WHERE address IS NOT NULL;
