/****************************************************************
 * INSERT INTO - Operazione di Creazione (Create)
 * 
 * Relazione con le operazioni CRUD:
 * 
 * → CREATE    ≡  INSERT INTO    ← siamo qui
 * → READ      ≡  SELECT
 * → UPDATE    ≡  UPDATE
 * → DELETE    ≡  DELETE
 * 
 * L'INSERT crea nuove righe nella tabella, ovvero persiste
 * lo STATO di nuovi oggetti. È l'equivalente di chiamare
 * repository.save(newEntity) su un oggetto appena istanziato.
 ****************************************************************/

/****************************************************************
 * ANATOMIA DELL'INSERT
 * 
 * INSERT INTO [tabella]    → DOVE inserisco
 * (colonne)                → QUALI attributi specifico
 * VALUES (valori)          → I VALORI da inserire
 * 
 * Nota: l'id NON viene specificato perché è AUTOINCREMENT.
 *       Il DBMS genera automaticamente 1, 2, 3, ...
 * 
 * L'ordine dei valori DEVE corrispondere all'ordine delle colonne.
 ****************************************************************/

/****************************************************************
 * INSERT SINGOLO vs MULTIPLO
 * 
 * Singolo:
 * INSERT INTO guest (firstname, lastname) VALUES ('Mario', 'Rossi');
 * 
 * Multiplo (batch insert):
 * INSERT INTO guest (firstname, lastname) VALUES 
 *     ('Mario', 'Rossi'),
 *     ('Giulia', 'Bianchi'),
 *     ('Luca', 'Verdi');
 * 
 * Il batch insert è più efficiente: una sola operazione
 * invece di N operazioni separate.
 ****************************************************************/

/****************************************************************
 * EQUIVALENTE JAVA (Repository Pattern)
 * 
 * // Con JDBC
 * String sql = "INSERT INTO guest (firstname, lastname, dob, ssn, address, city) VALUES (?,?,?,?,?,?)";
 * PreparedStatement ps = connection.prepareStatement(sql);
 * ps.setString(1, guest.getFirstname());
 * ps.setString(2, guest.getLastname());
 * ps.setDate(3, Date.valueOf(guest.getDob()));
 * ps.setString(4, guest.getSsn());
 * ps.setString(5, guest.getAddress());
 * ps.setString(6, guest.getCity());
 * ps.executeUpdate();
 * 
 * // Con Spring Data JPA
 * guestRepository.save(guest);
 ****************************************************************/

-- Inserimento multiplo di guest con tutti i dati
INSERT INTO guest (firstname, lastname, dob, ssn, address, city) VALUES
('Mario', 'Rossi', '1985-06-15', 'RSSMRA85H15H501X', 'Via Roma 10', 'Roma'),
('Giulia', 'Bianchi', '1992-11-22', 'BNCGLI92S62L219Y', 'Corso Italia 5', 'Milano'),
('Luca', 'Verdi', '1978-03-08', 'VRDLCU78C08F839W', 'Piazza Duomo 1', 'Torino'),
('Elena', 'Ferrari', '1988-09-30', 'FRRLNE88P70A944V', 'Via Garibaldi 22', 'Palermo'),
('Marco', 'Esposito', '1995-01-19', 'SPSMRC95A19F839U', 'Via Dante 7', 'Napoli'),
('Sara', 'Colombo', '1983-07-25', 'CLMSRA83L65F205T', 'Viale Europa 33', 'Verona'),
('Andrea', 'Ricci', '1990-12-01', 'RCCNDR90T01H501S', 'Via Mazzini 18', 'Bologna'),
('Chiara', 'Moretti', '1975-04-12', 'MRTCHR75D52G273R', 'Via Cavour 9', 'Firenze'),
('Francesco', 'Barbieri', '1998-08-28', 'BRBFNC98M28L219Q', 'Via Volta 44', 'Genova'),
('Valentina', 'Gallo', '1982-02-14', 'GLLVLN82B54F839P', 'Corso Vittorio 66', 'Roma');
