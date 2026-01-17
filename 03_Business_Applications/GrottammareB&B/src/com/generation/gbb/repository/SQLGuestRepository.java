package com.generation.gbb.repository;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.generation.gbb.model.database.ConnectionFactory;
import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.repository.interfaces.GuestRepository;
import com.generation.library.Console;

/**
 * Implementazione SQL del repository per la gestione degli ospiti.
 * Utilizza JDBC per l'accesso ai dati e implementa il pattern Repository
 * per astrarre la logica di persistenza dal resto dell'applicazione.
 */
public class SQLGuestRepository implements GuestRepository
{
	Connection connection = ConnectionFactory.make();

	
	/**
	 * Verifica se la tabella trip esiste nel database.
	 */
	private void checkTable()
	{
		try
		{
			DatabaseMetaData meta = connection.getMetaData();
			ResultSet rs = meta.getTables(null, null, "guest", null);

			if (!rs.next())
			{
				throw new RuntimeException("Tabella 'guest' non trovata. Eseguire comando 32 (CREATETABLEGUEST)");
			}

			rs.close();
		}
		catch (SQLException e)
		{
			throw new RuntimeException("Errore verifica tabella guest");
		}
	}
	
	/**
	 * Recupera tutti gli ospiti dal database.
	 * 
	 * @return lista contenente tutti gli ospiti presenti nel database
	 */
	@Override
	public List<Guest> findAll() 
	{
		checkTable();

		try
		{
			/*
			 * Relazione tra Repository Pattern e Principi OOP:
			 * 
			 * Astrazione → GuestRepository nasconde dettagli SQL al client
			 * Incapsulamento → Logica JDBC isolata in questa classe
			 * Contratto (Interface) → GuestRepository definisce il comportamento
			 * Implementazione → SQLGuestRepository fornisce logica specifica SQL
			 * 
			 * Mapping Object-Relational:
			 * Riga database (ResultSet) → Oggetto dominio (Guest)
			 * 
			 * PreparedStatement protegge da SQL injection e migliora le performance
			 * grazie alla precompilazione della query. ResultSet mantiene il cursore
			 * sulle righe restituite, permettendo l'iterazione con next().
			 */
			// Query SQL per selezionare tutti i record dalla tabella Guest
			String sql = "select * from Guest";

			// PreparedStatement: oggetto comando che incapsula la query SQL
			// Protegge da SQL injection e permette la precompilazione della query
			PreparedStatement readCmd = connection.prepareStatement(sql);

			// Lista che conterrà tutti gli oggetti Guest mappati dal database
			List<Guest> res = new ArrayList<Guest>();

			// executeQuery(): esegue la SELECT e restituisce un ResultSet
			// ResultSet: cursore che punta alle righe risultanti dalla query
			ResultSet guestRows = readCmd.executeQuery();

			// Ciclo di iterazione sulle righe del ResultSet
			// next(): sposta il cursore alla riga successiva, ritorna false quando non ci sono più righe
			while(guestRows.next())
			{
			    // Crea una nuova istanza Guest per ogni riga del database
			    Guest g = new Guest();
			    
			    // Mapping colonna → proprietà oggetto
			    // getInt/getString: estrae il valore tipizzato dalla colonna specificata
			    g.setId(guestRows.getInt("id"));                                    // Estrae la colonna 'id' come intero
			    g.setFirstName(guestRows.getString("firstname"));                   // Estrae 'firstname' come stringa
			    g.setLastName(guestRows.getString("lastname"));                     // Estrae 'lastname' come stringa
			    g.setDob(LocalDate.parse(guestRows.getString("dob")));             // Converte la stringa 'dob' in LocalDate
			    g.setSsn(guestRows.getString("ssn"));                              // Estrae 'ssn' come stringa
			    g.setCity(guestRows.getString("city"));                            // Estrae 'city' come stringa
			    g.setAddress(guestRows.getString("address"));                      // Estrae 'address' come stringa
			    
			    // Aggiunge l'oggetto Guest popolato alla lista dei risultati
			    res.add(g);
			}

			readCmd.close();
			guestRows.close();
			return res;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error reading");
		}
	}

	/**
	 * Cerca un ospite specifico tramite identificativo.
	 * 
	 * @param id l'identificativo univoco dell'ospite
	 * @return l'oggetto Guest se trovato, null altrimenti
	 */
	@Override
	public Guest findById(int id) 
	{
		checkTable();

		try
		{
			String sql = "select * from Guest where id = ?";
			PreparedStatement readCmd = connection.prepareStatement(sql);
			readCmd.setInt(1, id);

			ResultSet guestRow = readCmd.executeQuery();
			Guest res = null;
			if(guestRow.next())
			{
				res = new Guest();
				res.setId(guestRow.getInt("id"));
				res.setFirstName(guestRow.getString("firstname"));
				res.setLastName(guestRow.getString("lastname"));
				res.setDob(LocalDate.parse(guestRow.getString("dob")));
				res.setSsn(guestRow.getString("ssn"));
				res.setCity(guestRow.getString("city"));
				res.setAddress(guestRow.getString("address"));
			}
			readCmd.close();
			guestRow.close();
			return res;			
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error reading");
		}
	}

	/**
	 * Cerca un ospite tramite codice fiscale.
	 * 
	 * @param ssn il codice fiscale dell'ospite
	 * @return l'oggetto Guest se trovato, null altrimenti
	 */
	@Override
	public Guest findBySSN(String ssn)
	{
		checkTable();

		// VALIDAZIONE: SSN non può essere null o vuoto
		if (ssn == null || ssn.trim().isEmpty())
		{
			throw new IllegalArgumentException("Il codice fiscale non può essere vuoto");
		}

		/*
		 * Trade-off tra Performance e Semplicità:
		 *
		 * Approccio attuale → Filtraggio in-memory con findAll()
		 * Approccio ottimale → Query SQL con WHERE ssn = ?
		 *
		 * Questa implementazione carica tutti i record (N+1 problem),
		 * accettabile per dataset piccoli. Per produzione, preferire
		 * una query diretta con PreparedStatement per ridurre overhead.
		 */
		for(Guest g : findAll())
			if(g.getSsn().equals(ssn))
				return g;

		return null;
	}

	/**
	 * Cerca ospiti il cui cognome contiene una determinata stringa.
	 * 
	 * @param part la porzione di testo da cercare nel cognome
	 * @return lista di ospiti con cognome corrispondente
	 */
	@Override
	public List<Guest> findBySurnameContaining(String part)
	{
		checkTable();

		// VALIDAZIONE: la stringa da cercare non può essere null o vuota
		if (part == null || part.trim().isEmpty())
		{
			throw new IllegalArgumentException("La stringa di ricerca non può essere vuota");
		}

		/*
		 * Pattern Filter in-memory:
		 *
		 * Approccio attuale → Stream implicito (enhanced for) + predicato
		 * Alternativa SQL → WHERE lastname LIKE '%?%'
		 *
		 * L'implementazione corrente privilegia semplicità rispetto a efficienza.
		 * Per dataset grandi (>1000 record), migrare a query SQL con LIKE operator.
		 */
		List<Guest> res = new ArrayList<Guest>();
		for(Guest g : findAll())
			if(g.getLastName().contains(part))
				res.add(g);

		return res;
	}

	/**
	 * Cerca ospiti residenti in una determinata città.
	 * 
	 * @param city il nome della città
	 * @return lista di ospiti residenti nella città specificata
	 */
	@Override
	public List<Guest> findByCity(String city)
	{
		checkTable();

		// VALIDAZIONE: città non può essere null o vuota
		if (city == null || city.trim().isEmpty())
		{
			throw new IllegalArgumentException("La città da cercare non può essere vuota");
		}

		List<Guest> res = new ArrayList<Guest>();
		for(Guest g : findAll())
			if(g.getCity().contains(city))
				res.add(g);
		return res;
	}

	/**
	 * Inserisce un nuovo ospite nel database.
	 * 
	 * @param newGuest l'oggetto Guest da persistere
	 * @return l'oggetto Guest con l'id generato dal database
	 * @throws RuntimeException se l'ospite non è valido o ha già un id
	 */
	@Override
	public Guest insert(Guest newGuest)
	{
		checkTable();

		// VALIDAZIONE: nome non può essere vuoto
		if (newGuest.getFirstName() == null || newGuest.getFirstName().trim().isEmpty())
		{
			throw new IllegalArgumentException("Il nome non può essere vuoto");
		}

		// VALIDAZIONE: cognome non può essere vuoto
		if (newGuest.getLastName() == null || newGuest.getLastName().trim().isEmpty())
		{
			throw new IllegalArgumentException("Il cognome non può essere vuoto");
		}

		// VALIDAZIONE: codice fiscale non può essere vuoto
		if (newGuest.getSsn() == null || newGuest.getSsn().trim().isEmpty())
		{
			throw new IllegalArgumentException("Il codice fiscale non può essere vuoto");
		}

		// VALIDAZIONE: data di nascita non può essere nel futuro
		if (newGuest.getDob() != null && newGuest.getDob().isAfter(LocalDate.now()))
		{
			throw new IllegalArgumentException("La data di nascita non può essere nel futuro");
		}

		if(!newGuest.isValid())
			throw new RuntimeException("Invalid guest");

		if(newGuest.getId() != 0)
			throw new RuntimeException("Cannot save a guest with a previous id");

		try
		{
			/*
			 * Pattern Command per operazioni DML:
			 * 
			 * PreparedStatement → Command Object (incapsula richiesta SQL)
			 * setString/setInt → Binding parametri (Type-safe)
			 * execute() → Invocazione comando
			 * 
			 * Flusso Persist:
			 * 1. Validazione entità → Fail-fast se dati inconsistenti
			 * 2. Mapping Object → SQL parameters
			 * 3. Execute INSERT → Persistenza su DB
			 * 4. Recupero ID generato → Sincronizzazione stato oggetto
			 * 
			 * Il metodo getNewId() recupera l'ultimo ID assegnato,
			 * necessario per mantenere coerenza tra oggetto e record DB.
			 */
			String sql = 
				"insert into Guest (firstname, lastname, ssn, dob, city, address) values(?,?,?,?,?,?)";
			
			PreparedStatement insertCmd = connection.prepareStatement(sql);
			insertCmd.setString(1, newGuest.getFirstName());
			insertCmd.setString(2, newGuest.getLastName());
			insertCmd.setString(3, newGuest.getSsn());
			insertCmd.setString(4, newGuest.getDob() + "");
			insertCmd.setString(5, newGuest.getCity());
			insertCmd.setString(6, newGuest.getAddress());
			insertCmd.execute();
			insertCmd.close();
			int res = getNewId();
			newGuest.setId(res);
			return newGuest;
		}		
		catch(Exception e)
		{
			throw new RuntimeException("Error saving");
		}	
	}

	/**
	 * Recupera l'ultimo ID generato dal database.
	 * Metodo di supporto per sincronizzare l'id dopo un inserimento.
	 * 
	 * @return l'ID massimo presente nella tabella Guest
	 */
	private int getNewId()
	{
		try
		{
			/*
			 * Strategia ID Generation:
			 * 
			 * MAX(id) → Recupero ultimo identificativo assegnato
		
			 * Soluzione migliore:
			 * - SQLite: last_insert_rowid()
			 * - JDBC: Statement.RETURN_GENERATED_KEYS
			 */
			PreparedStatement readCmd = connection.prepareStatement("select max(id) as m from Guest");
			ResultSet rs = readCmd.executeQuery();
			int res = rs.getInt("m");
			readCmd.close();
			rs.close();
			return res;
		}
		catch(Exception e)
		{
			throw new RuntimeException("Error reading");
		}
	}
	
	/**
	 * Aggiorna i dati di un ospite esistente.
	 * 
	 * @param newVersion l'oggetto Guest con i dati aggiornati
	 * @return l'oggetto Guest aggiornato
	 */
	@Override
	public Guest update(Guest newVersion)
	{
		checkTable();

		// VALIDAZIONE: il guest deve essere valido
		if (!newVersion.isValid())
		{
			throw new IllegalArgumentException("Invalid guest data");
		}

		// VALIDAZIONE: deve avere un ID valido per l'update
		if (newVersion.getId() <= 0)
		{
			throw new IllegalArgumentException("Cannot update a guest without a valid id");
		}

		// VALIDAZIONE: verifica che l'ID esista nel database
		Guest existingGuest = findById(newVersion.getId());
		if (existingGuest == null)
		{
			throw new IllegalArgumentException("Guest con ID " + newVersion.getId() + " non trovato nel database");
		}

		try
		{
			String sql = 
				"update guest set firstname=?, lastname=?, ssn=?, dob=?, city=?, address=? where id=?";
			
			PreparedStatement updateCmd = connection.prepareStatement(sql);
			updateCmd.setString(1, newVersion.getFirstName());
			updateCmd.setString(2, newVersion.getLastName());
			updateCmd.setString(3, newVersion.getSsn());
			updateCmd.setString(4, newVersion.getDob() + "");
			updateCmd.setString(5, newVersion.getCity());
			updateCmd.setString(6, newVersion.getAddress());
			updateCmd.setInt(7, newVersion.getId());
			updateCmd.execute();
			updateCmd.close();
			return newVersion;
		}		
		catch(Exception e)
		{
			throw new RuntimeException("Error saving");
		}	
	}

	/**
	 * Elimina un ospite dal database.
	 * 
	 * @param id l'identificativo dell'ospite da eliminare
	 * @return true se l'eliminazione ha successo, false altrimenti
	 */
	@Override
	public boolean delete(int id)
	{
		checkTable();

		// VALIDAZIONE: ID deve essere positivo
		if (id <= 0)
		{
			throw new IllegalArgumentException("ID non valido: " + id);
		}

		// VALIDAZIONE: verifica che l'ID esista
		Guest existingGuest = findById(id);
		if (existingGuest == null)
		{
			throw new IllegalArgumentException("Guest con ID " + id + " non trovato nel database");
		}

		try
		{
			/*
			 * Pattern Delete Command:
			 * 
			 * DELETE SQL → Rimozione fisica del record
			 * 
			 * Considerazioni:
			 * - Hard delete → Record rimosso permanentemente
			 * - Soft delete (alternativa) → Flag "deleted" per audit trail
			 * 
			 * Il metodo non verifica se l'ID esiste, restituisce true anche
			 * per ID inesistenti. Usare executeUpdate() per contare righe
			 * affette renderebbe il feedback più preciso.
			 */
			PreparedStatement deleteCmd = connection.prepareStatement("delete from Guest where id = ?");
			deleteCmd.setInt(1, id);
			deleteCmd.execute();
			deleteCmd.close();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}

	/**
	 * Crea la tabella guest nel database se non esiste già.
	 * Utilizza la connessione esistente del repository.
	 */
	public void initTable()
	{
		try
		{
			String createTableSQL = "CREATE TABLE IF NOT EXISTS guest (" +
									"id INTEGER PRIMARY KEY AUTOINCREMENT, " +
									"firstname VARCHAR(100) NOT NULL, " +
									"lastname VARCHAR(100) NOT NULL, " +
									"fiscalcode VARCHAR(16) NOT NULL UNIQUE, " +
									"email VARCHAR(100) NOT NULL, " +
									"phone VARCHAR(20))";

			PreparedStatement statement = connection.prepareStatement(createTableSQL);
			statement.executeUpdate();
			statement.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Errore nella creazione della tabella guest");
		}
	}
}
