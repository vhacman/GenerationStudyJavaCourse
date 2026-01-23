package com.generation.acmc.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDate;

import com.generation.acmc.model.entities.Expense;
import com.generation.library.repository.PartialCacheSQLEntityRepository;

/**
 * Classe repository per gestire le spese nel database tramite JDBC.
 * Estende PartialCacheSQLEntityRepository, quindi eredita il caching e i metodi base
 * come save(), delete(), findById(), ecc.
 *
 * Tabella tipica (esempio):
 *   expense(
 *     id,
 *     reason,         → varchar
 *     date,           → date (mappato a LocalDate)
 *     cost            → decimal (mappato a BigDecimal)
 *   )
 *
 * Nota:
 *   - I campi cost e date usano BigDecimal e LocalDate.
 */
public class SQLExpenseRepository extends PartialCacheSQLEntityRepository<Expense>
    implements ExpenseRepository
{

    public SQLExpenseRepository(String table, Connection connection, int maxSize)
    {
        super(table, connection, maxSize);
    }

    /**
     * Prepara il comando SQL per l’inserimento di una spesa.
     *
     * Teoria:
     *   - Il metodo costruisce un INSERT INTO ... VALUES(?, ?, ?, ?, ?)
     *   - Usa PreparedStatement per parametri posizionali (non concatenazione di stringhe)
     *     per evitare SQL injection e per prestazioni.
     *   - I metodi setXXX (setInt, setBigDecimal, setDate, setString) associano i valori
     *     ai parametri ? nel giusto ordine.
     *   - Per la data: Expense usa LocalDate, mentre JDBC per il set richiede Date.
     *     Quindi si usa Date.valueOf(expense.getDate()) per convertire.
     *
     * @param expense La spesa da inserire.
     * @return PreparedStatement pronto per essere eseguito.
     * @throws SQLException Se si verifica un errore durante la creazione del comando.
     */
    @Override
	public PreparedStatement getInsertCmd(Expense expense) throws SQLException
    {
        String sql = "INSERT INTO " + table + " (reason, date, cost) VALUES (?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, expense.getReason());

        // Gestione date: verifica se è nulle e salva come stringa YYYY-MM-DD per SQLite
        if (expense.getDate() != null)
		{
			ps.setString(2, expense.getDate().toString());
		} else
		{
			ps.setNull(2, Types.VARCHAR);
		}

        ps.setBigDecimal(3, expense.getCost());

        return ps;
    }

    /**
     * Prepara il comando SQL per l’aggiornamento di una spesa.
     *
     * Teoria:
     *   - Il metodo costruisce un UPDATE expense SET ... WHERE id = ?
     *   - I parametri di SET (il valore che cambia) vanno prima,
     *     il parametro di WHERE (l’id) va per ultimo.
     *   - Anche qui si usa Date.valueOf(expense.getDate()) per la data.
     *
     * @param expense La spesa da aggiornare.
     * @return PreparedStatement pronto per essere eseguito.
     * @throws SQLException Se si verifica un errore durante la creazione del comando.
     */
    @Override
	public PreparedStatement getUpdateCmd(Expense expense) throws SQLException
    {
        String sql = "UPDATE " + table + " SET reason=?, date=?, cost=? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);

        ps.setString(1, expense.getReason());
       /**
       * Il campo date può essere null (perché la spesa è ancora senza data, o perché è un dato opzionale).
       * Salviamo come stringa YYYY-MM-DD per SQLite.
       */
        if (expense.getDate() != null)
		{
			ps.setString(2, expense.getDate().toString());
		} else
		{
			ps.setNull(2, Types.VARCHAR);
		}
        ps.setBigDecimal(3, expense.getCost());
        ps.setInt(4, expense.getId());
        return ps;
    }

    /**
     * Converte una riga del ResultSet (una spesa dal DB) in un oggetto Expense.
     *
     * Teoria:
     *   - Il metodo rowToX è il “ponte” dal mondo relazionale (tabella DB) a quello oggetti (Java).
     *   - Usa il ResultSet per leggere ogni colonna con getInt, getString, getBigDecimal, getDate.
     *   - Per la data: getDate() restituisce un oggetto Date, che viene convertito in LocalDate con .toLocalDate().
     *   - Il membro (member) non viene letto qui: si può passare un riferimento a Member caricato separatamente
     *     (es. tramite un SQLMemberRepository) oppure si crea un oggetto Member con solo l’id.
     *
     * @param rows Il ResultSet posizionato sulla riga della spesa.
     * @return Un nuovo oggetto Expense costruito con i dati del ResultSet.
     * @throws SQLException Se si verifica un errore durante la lettura del ResultSet.
     */
    @Override
    public Expense rowToX(ResultSet rows) throws SQLException
    {
        // Gestione date: verifica se è null prima della conversione
        String 		dateStr 	= rows.getString("date");
        LocalDate 	localDate 	= (dateStr != null) ? LocalDate.parse(dateStr) : null;
        // Crea un Expense con i dati della riga
        return new Expense(
            rows.getInt("id"),
            rows.getString("reason"),
            localDate,
            rows.getBigDecimal("cost")
        );
    }
}
