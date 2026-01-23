package com.generation.acmc.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

import com.generation.acmc.model.entities.Member;
import com.generation.acmc.model.entities.MembershipLevel;
import com.generation.library.repository.PartialCacheSQLEntityRepository;

public class SQLMemberRepository extends PartialCacheSQLEntityRepository<Member> implements MemberRepository
{
    public static final Comparator<Member> BY_FIRST_NAME  = (a, b) -> a.getFirstName().compareTo(b.getFirstName());
    public static final Comparator<Member> BY_LAST_NAME   = (a, b) -> a.getLastName().compareTo(b.getLastName());
    public static final Comparator<Member> BY_LEVEL       = (a, b) -> a.getLevel().compareTo(b.getLevel());
    public static final Comparator<Member> BY_INCOME_EST  = (a, b) -> a.getIncomeEst().compareTo(b.getIncomeEst());
    public static final Comparator<Member> BY_DOB         = (a, b) -> a.getDob().compareTo(b.getDob());

    public SQLMemberRepository(String table, Connection connection, int maxSize)
    {
        super(table, connection, maxSize);
    }

    /**
     * Cerca un membro per cognome esatto.
     *
     * @param lastName Il cognome da cercare.
     * @return Il membro trovato, oppure null se non esiste.
     */
    @Override
    public Member findByLastName(String lastName)
    {
        List<Member> members = findWhere("lastname = '" + lastName + "'");
        return members.isEmpty() ? null : members.get(0);
    }

    /**
     * Cerca membri il cui cognome contiene la sottostringa fornita.
     *
     * @param lastName La sottostringa da cercare nel cognome.
     * @return Lista dei membri che soddisfano il criterio.
     */
    @Override
    public List<Member> findByLastNameContaining(String lastName)
    {
        return findWhere("lastname LIKE '%" + lastName + "%'");
    }

    /**
     * Cerca membri per livello di membership.
     *
     * @param level Il livello di membership da cercare.
     * @return Lista dei membri che hanno il livello specificato.
     */
    @Override
    public List<Member> findByLevel(MembershipLevel level)
    {
        return findWhere("level = '" + level.name() + "'");
    }

    /**
     * Prepara il comando SQL per l'inserimento di un membro nel database.
     *
     * @param x Il membro da inserire.
     * @return PreparedStatement configurato per l'inserimento.
     * @throws SQLException Se si verifica un errore durante la preparazione.
     */
    @Override
    public PreparedStatement getInsertCmd(Member x) throws SQLException
    {
        String               sql = "INSERT INTO member(firstName, lastname, gender, dob, incomeEst, level) VALUES(?,?,?,?,?,?)";
        PreparedStatement    ps  = connection.prepareStatement(sql);
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getGender());
        ps.setString(4, x.getDob().toString()); // SQLite salva date come stringhe YYYY-MM-DD
        ps.setBigDecimal(5, x.getIncomeEst());
        ps.setString(6, x.getLevel().name()); // Usa name() per il DB, non toString()
        return ps;
    }

    /**
     * Prepara il comando SQL per l'aggiornamento di un membro nel database.
     *
     * @param x Il membro da aggiornare.
     * @return PreparedStatement configurato per l'aggiornamento.
     * @throws SQLException Se si verifica un errore durante la preparazione.
     */
    @Override
	public PreparedStatement getUpdateCmd(Member x) throws SQLException
    {
        String               sql = "UPDATE member SET firstName=?, lastname=?, gender=?, dob=?, incomeEst=?, level=? WHERE id=?";
        PreparedStatement    ps  = connection.prepareStatement(sql);
        ps.setString(1, x.getFirstName());
        ps.setString(2, x.getLastName());
        ps.setString(3, x.getGender());
        ps.setString(4, x.getDob().toString()); // SQLite salva date come stringhe YYYY-MM-DD
        ps.setBigDecimal(5, x.getIncomeEst());
        ps.setString(6, x.getLevel().name()); // Usa name() per il DB, non toString()
        ps.setInt(7, x.getId());
        return ps;
    }

    /**
     * Converte una riga del ResultSet in un oggetto Member.
     *
     * @param rows Il ResultSet contenente i dati della riga.
     * @return Un nuovo oggetto Member costruito dai dati della riga.
     * @throws SQLException Se si verifica un errore durante la lettura del ResultSet.
     */
    @Override
    public Member rowToX(ResultSet rows) throws SQLException
    {
        return new Member(
                rows.getInt("id"),
                rows.getString("firstName"),
                rows.getString("lastname"),
                rows.getString("gender"),
                LocalDate.parse(rows.getString("dob")), // Converte stringa YYYY-MM-DD a LocalDate
                rows.getBigDecimal("incomeEst"),
                MembershipLevel.valueOf(rows.getString("level"))
        );
    }
}
