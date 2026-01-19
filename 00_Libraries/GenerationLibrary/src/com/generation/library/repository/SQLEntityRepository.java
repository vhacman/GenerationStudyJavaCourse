package com.generation.library.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.generation.library.Entity;

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: GENERICS (Tipi Parametrizzati)
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Meccanismo per creare classi/metodi che operano su tipi
 *              specificati al momento dell'uso, garantendo type-safety a
 *              compile-time ed eliminando cast espliciti.
 *
 * Sintassi: `public class NomeClasse<T> { ... }`
 *           `public <T> T nomeMetodo(T param) { ... }`
 *
 * Bounded Type Parameter: `<T extends SuperClass>`
 *   - Restringe T a sottoclassi di SuperClass
 *   - Permette accesso ai metodi di SuperClass su oggetti di tipo T
 * ═══════════════════════════════════════════════════════════════════════
 */

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: JDBC (Java Database Connectivity)
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: API standard Java per interazione con database relazionali.
 *              Fornisce interfacce uniformi indipendenti dal DBMS specifico.
 *
 * Componenti principali:
 *   1. Connection       → Connessione al database
 *   2. Statement        → Esecuzione query SQL statiche
 *   3. PreparedStatement→ Esecuzione query parametrizzate (SQL injection safe)
 *   4. ResultSet        → Cursore sui risultati di una SELECT
 *   5. SQLException     → Eccezione per errori database
 *
 * Operazioni CRUD:
 *   - CREATE: INSERT con PreparedStatement.execute()
 *   - READ:   SELECT con Statement.executeQuery() → ResultSet
 *   - UPDATE: UPDATE con PreparedStatement.execute()
 *   - DELETE: DELETE con Statement.execute()
 * ═══════════════════════════════════════════════════════════════════════
 */

/*
 * ═══════════════════════════════════════════════════════════════════════
 * TEORIA: PATTERN REPOSITORY
 * ═══════════════════════════════════════════════════════════════════════
 * Definizione: Pattern architetturale che incapsula logica di accesso ai dati,
 *              fornendo un'astrazione collection-like per entità persistenti.
 *
 * Scopo: Disaccoppiare logica di business da dettagli di persistenza.
 *        Il codice client lavora con repository come se fossero collezioni
 *        in-memory, ignorando SQL/JDBC/ORM sottostanti.
 * ═══════════════════════════════════════════════════════════════════════
 */

/**
 * Repository generico per la gestione delle operazioni CRUD su entità persistite
 * in database SQL tramite JDBC
 * Questa interfaccia implementa il pattern Repository, fornendo un'astrazione
 * tra la logica di business e il livello di accesso ai dati.
 *
 * @param <X> Il tipo di entità gestita dal repository, deve estendere Entity
 */
public abstract class SQLEntityRepository<X extends Entity>
{

    protected 	Connection      connection;
    private 	String         	table;

    public SQLEntityRepository(Connection connection, String table)
    {
         super();
         this.connection = connection;
         this.table      = table;
    }


    /**
     * Recupera entità dalla tabella che soddisfano una condizione WHERE specificata.
     * Esegue una query SELECT con la condizione fornita e converte ogni riga
     * nell'entità corrispondente.
     *
     * @param condition la condizione WHERE (senza la parola chiave WHERE)
     * @return lista delle entità che soddisfano la condizione, lista vuota se nessuna trovata
     */
    public List<X>		findWhere(String condition)
    {
        List<X> res = new ArrayList<X>();
        try
        {
            String      sql     = "select * from " + table + " where " + condition;
            Statement   readCmd = getConnection().createStatement();
            ResultSet   rows    = readCmd.executeQuery(sql);
            while(rows.next())
                res.add(rowToX(rows));
            return res;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return res;
        }
    }

    /**
     * Recupera un'entità dalla tabella identificata dall'ID fornito.
     * Utilizza il metodo findWhere per filtrare per l'ID specificato.
     *
     * @param id l'identificativo univoco dell'entità da recuperare
     * @return l'entità trovata, null se non esiste
     */
    public X findById(int id)
    {
        List<X> results = findWhere("id=" + id);
        if(results.isEmpty())
            return null;
        return results.get(0);
    }

    /**
     * Inserisce una nuova entità nella tabella del database.
     * Valida l'entità prima dell'inserimento e utilizza PreparedStatement per sicurezza.
     *
     * @param x l'entità da inserire
     * @return l'entità inserita con l'ID assegnato dal database
     * @throws SQLException se l'entità non è valida, ha già un ID o si verifica un errore durante l'inserimento
     */
    public X insert(X x) throws SQLException
    {
        if(!x.isValid())
            throw new SQLException("Invalid entity");
        if(x.getId() != 0)
            throw new SQLException("Entity already has an id");
        PreparedStatement insertCmd = getInsertCmd(x); // ASTRATTO - passa l'entità
        insertCmd.execute(); // inserisco
        insertCmd.close();
        x.setId(getNewId());
        return x;
    }

    /**
     * Aggiorna un'entità esistente nella tabella del database.
     * Valida l'entità e verifica che esista prima dell'aggiornamento.
     *
     * @param newVersion la nuova versione dell'entità da aggiornare
     * @return l'entità aggiornata
     * @throws SQLException se l'entità non è valida, non ha un ID o non esiste nel database
     */
    public X update(X newVersion) throws SQLException
    {
        if(!newVersion.isValid())
            throw new SQLException("Invalid entity");

        if(newVersion.getId() == 0)
            throw new SQLException("Invalid id");

        if(findById(newVersion.getId()) == null)
            throw new SQLException("Unknown entity");

        PreparedStatement updateCmd = getUpdateCmd(newVersion);
        updateCmd.execute();
        updateCmd.close();

        return newVersion;
    }

    /**
     * Recupera tutte le entità presenti nella tabella del database.
     * Utilizza il metodo findWhere con una condizione sempre vera (1=1).
     *
     * @return lista di tutte le entità trovate, lista vuota se non ci sono record
     */
    public List<X> findAll()
    {
        return findWhere("1=1");
    }


    /**
     * Cancella un record dalla tabella del database identificato dall'ID fornito.
     * Esegue una query SQL DELETE utilizzando java.sql.Statement.
     * L'operazione è sincrona e restituisce l'esito dell'eliminazione.
     *
     * @param id l'identificativo univoco del record da eliminare
     * @return true se l'eliminazione è avvenuta con successo, false in caso di errore
     */
    public boolean delete(int id)
    {
        try
        {
            String sql = "delete from " + getTable() + " where id=" + id;
            // Creazione dello Statement per l'esecuzione della query
            Statement deleteCmd = getConnection().createStatement();
            // Esecuzione della query DELETE
            deleteCmd.execute(sql);
            deleteCmd.close();
            return true;
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    // ═══════════════════════════════════════════════════════════════════════
    // METODI HELPER
    // ═══════════════════════════════════════════════════════════════════════

    /**
     * Recupera l'ultimo ID generato dal database dopo un'operazione di inserimento.
     * Utilizza la funzione SQL last_insert_rowid() specifica per SQLite.
     *
     * @return l'ID dell'ultima riga inserita
     * @throws SQLException se si verifica un errore durante il recupero dell'ID
     */
    private int getNewId() throws SQLException
    {
        String      sql     = "select last_insert_rowid() as id";
        Statement   readCmd = getConnection().createStatement();
        ResultSet   rows    = readCmd.executeQuery(sql);

        if(rows.next())
            return rows.getInt("id");
        throw new SQLException("Failed to retrieve new ID");
    }

    // ═══════════════════════════════════════════════════════════════════════
    // METODI ASTRATTI (da implementare nelle sottoclassi)
    // ═══════════════════════════════════════════════════════════════════════

    /**
     * Converte una riga del ResultSet nell'entità di tipo X.
     * Questo metodo astratto deve essere implementato dalle sottoclassi concrete
     * per gestire la logica di mapping specifica dell'entità.
     *
     * @param rs il ResultSet posizionato sulla riga da convertire
     * @return l'istanza dell'entità creata dai dati della riga
     * @throws SQLException se si verifica un errore durante l'accesso ai dati
     */
    public abstract X rowToX(ResultSet rs) throws SQLException;

    /**
     * Crea e restituisce un PreparedStatement configurato per l'inserimento di una nuova entità.
     * Questo metodo astratto deve essere implementato dalle sottoclassi concrete
     * per definire la query SQL specifica e i parametri dell'entità.
     *
     * @param x l'entità da inserire
     * @return il PreparedStatement pronto per l'esecuzione
     * @throws SQLException se si verifica un errore durante la creazione dello statement
     */
    public abstract PreparedStatement getInsertCmd(X x) throws SQLException;

    /**
     * Crea e restituisce un PreparedStatement configurato per l'aggiornamento di un'entità esistente.
     * Questo metodo astratto deve essere implementato dalle sottoclassi concrete
     * per definire la query SQL UPDATE specifica e i parametri dell'entità.
     *
     * @param x l'entità con i nuovi valori da aggiornare
     * @return il PreparedStatement pronto per l'esecuzione
     * @throws SQLException se si verifica un errore durante la creazione dello statement
     */
    public abstract PreparedStatement getUpdateCmd(X x) throws SQLException;


    // ═══════════════════════════════════════════════════════════════════════
    // GETTER E SETTER
    // ═══════════════════════════════════════════════════════════════════════

    public Connection   getConnection()                         { return connection;            }
    public String       getTable()                              { return table;                 }
    
    public void         setConnection(Connection connection)    { this.connection = connection; }
    public void         setTable(String table)                  { this.table = table;           }
}
