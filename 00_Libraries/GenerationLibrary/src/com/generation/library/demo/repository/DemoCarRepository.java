package com.generation.library.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

import com.generation.library.model.entities.Car;
import com.generation.library.repository.SQLCarRepository;

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: CONNESSIONE DATABASE JDBC
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Processo di stabilire connessione tra applicazione Java e DBMS.
 *
 * Step connessione JDBC:
 *   1. Class.forName("driver.class.Name") → Carica driver JDBC
 *   2. DriverManager.getConnection(url)   → Ottiene Connection
 *   3. Usa Connection per creare Statement/PreparedStatement
 *   4. Esegui query, elabora risultati
 *   5. Chiudi risorse (best practice: try-with-resources)
 *
 * URL formato: "jdbc:subprotocol:subname"
 *   - SQLite:     jdbc:sqlite:path/to/database.db
 *   - MySQL:      jdbc:mysql://localhost:3306/database
 *   - PostgreSQL: jdbc:postgresql://localhost:5432/database
 * ═══════════════════════════════════════════════════════════════════════
 */

public class DemoCarRepository
{

	public static void main(String[] args) throws Exception
	{
		// Carica il driver JDBC per SQLite in memoria
		Class.forName("org.sqlite.JDBC");

		// Stabilisce connessione al database SQLite (file car.db)
		Connection connection = DriverManager.getConnection("jdbc:sqlite:car.db");	

		// Istanzia repository passando nome tabella e connessione
		SQLCarRepository carRepo = new SQLCarRepository("car", connection);

		/*
		 * ═══════════════════════════════════════════════════════════════════════
		 * OPERAZIONE CREATE: Inserimento nuova entità
		 * ═══════════════════════════════════════════════════════════════════════
		 * 1. Crea oggetto entità
		 * 2. Imposta proprietà tramite setter
		 * 3. Repository valida (isValid()) e inserisce nel DB
		 * 4. Database genera ID auto-increment
		 * 5. Repository assegna ID all'oggetto e lo restituisce
		 * ═══════════════════════════════════════════════════════════════════════
		 */
		Car c = new Car();
		c.setPlate("FER666");
		c.setModel("Smart");
		c.setPrice(2000);
		carRepo.insert(c);  // INSERT INTO car (model, plate, price) VALUES (?, ?, ?)

		/*
		 * ═══════════════════════════════════════════════════════════════════════
		 * OPERAZIONE READ: Recupero entità
		 * ═══════════════════════════════════════════════════════════════════════
		 * - findAll():         SELECT * FROM car WHERE 1=1
		 * - findWhere(cond):   SELECT * FROM car WHERE condition
		 * - findById(id):      SELECT * FROM car WHERE id=?
		 *
		 * Ogni riga ResultSet trasformata in oggetto tramite rowToX()
		 * ═══════════════════════════════════════════════════════════════════════
		 */
		System.out.println(carRepo.findAll());               // Tutte le auto
		System.out.println(carRepo.findWhere("model='Smart'")); // Filtro per model

		/*
		 * ═══════════════════════════════════════════════════════════════════════
		 * OPERAZIONE UPDATE: Modifica entità esistente
		 * ═══════════════════════════════════════════════════════════════════════
		 * 1. Recupera entità dal DB tramite findById()
		 * 2. Modifica proprietà tramite setter
		 * 3. Repository valida e aggiorna record nel DB
		 * 4. SQL: UPDATE car SET ... WHERE id=?
		 * ═══════════════════════════════════════════════════════════════════════
		 */
		Car l = carRepo.findById(1);
		l.setPrice(l.getPrice()+100);  // Incrementa prezzo di 100
		carRepo.update(l);             // UPDATE car SET model=?, plate=?, price=? WHERE id=?
		System.out.println(carRepo.findAll());
		
	}

}
