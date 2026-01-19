package com.generation.library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.generation.library.model.entities.Car;

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: SPECIALIZZAZIONE REPOSITORY
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Estensione di repository generico fornendo implementazione
 *              specifica per un tipo di entità (es. Car).
 *
 * Responsabilità della sottoclasse:
 *   1. Mapping ResultSet → Oggetto (rowToX)
 *   2. Mapping Oggetto → PreparedStatement INSERT (getInsertCmd)
 *   3. Mapping Oggetto → PreparedStatement UPDATE (getUpdateCmd)
 *
 * Vantaggi pattern Template Method + Generics:
 *   - Logica CRUD generica in SQLEntityRepository (findAll, insert, update)
 *   - Dettagli SQL specifici delegati alla sottoclasse
 *   - Type-safety garantita dal parametro generico <Car>
 *
 * Principio Open-Closed:
 *   - Aperto all'estensione: nuove entità → nuovi repository
 *   - Chiuso alla modifica: SQLEntityRepository non cambia
 * ═══════════════════════════════════════════════════════════════════════
 */

public class SQLCarRepository extends SQLEntityRepository<Car>
{
    public SQLCarRepository(String table, Connection connection)
    {
        super(connection, table);
    }

    /*
     * ═══════════════════════════════════════════════════════════════════════
     * TEORIA: ResultSet - Cursore sui risultati query SELECT
     * ═══════════════════════════════════════════════════════════════════════
     * Definizione: Tabella di risultati SQL navigabile tramite cursore.
     *
     * Metodi di accesso dati (per colonna per nome o indice):
     *   - getInt(columnName/index):    recupera INTEGER
     *   - getString(columnName/index): recupera VARCHAR/TEXT
     *   - getDouble(columnName/index): recupera DOUBLE/REAL
     *   - getBoolean, getDate, etc.

     * Pattern: Object-Relational Mapping (ORM) manuale
     *   - Riga database → Oggetto Java
     *   - Ogni colonna mappata su campo oggetto
     * ═══════════════════════════════════════════════════════════════════════
     */
    @Override
    public Car rowToX(ResultSet row) throws SQLException
    {
        Car c = new Car();

        c.setId(row.getInt("id"));
        c.setModel(row.getString("model"));
        c.setPlate(row.getString("plate"));
        c.setPrice(row.getDouble("price"));

        return c;
    }

    /*
     * ═══════════════════════════════════════════════════════════════════════
     * TEORIA: PreparedStatement - Query parametrizzate sicure
     * ═══════════════════════════════════════════════════════════════════════
     * Definizione: Oggetto per esecuzione query SQL con parametri.
     *
     * Placeholder: ? nella stringa SQL sostituito da valori tramite setter
     *
     * Metodi setter (indice parte da 1, NON da 0):
     *   - setString(index, value):  imposta parametro VARCHAR/TEXT
     *   - setInt(index, value):     imposta parametro INTEGER
     *   - setDouble(index, value):  imposta parametro DOUBLE/REAL
     *   - setBoolean, setDate, setNull, etc.
     *
     * Metodi esecuzione:
     *   - execute():       esegue INSERT/UPDATE/DELETE (no risultati)
     *   - executeQuery():  esegue SELECT (restituisce ResultSet)
     *   - executeUpdate(): come execute() ma ritorna row count
     * ═══════════════════════════════════════════════════════════════════════
     */
    @Override
    public PreparedStatement getInsertCmd(Car car) throws SQLException
    {
        String sql = "insert into " + getTable() + " (model, plate, price) values (?, ?, ?)";
        PreparedStatement cmd = connection.prepareStatement(sql);

        cmd.setString(1, car.getModel());
        cmd.setString(2, car.getPlate());
        cmd.setDouble(3, car.getPrice());

        return cmd;
    }

    @Override
    public PreparedStatement getUpdateCmd(Car car) throws SQLException
    {
        String sql = "update " + getTable() + " set model=?, plate=?, price=? where id=?";
        PreparedStatement cmd = connection.prepareStatement(sql);

        cmd.setString(1, car.getModel());
        cmd.setString(2, car.getPlate());
        cmd.setDouble(3, car.getPrice());
        cmd.setInt(4, car.getId());

        return cmd;
    }
}
