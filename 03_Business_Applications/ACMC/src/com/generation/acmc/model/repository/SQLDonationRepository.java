package com.generation.acmc.model.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import com.generation.acmc.context.Context;
import com.generation.acmc.model.entities.Donation;
import com.generation.library.repository.PartialCacheSQLEntityRepository;

/**
 * Classe repository per gestire le donazioni nel database tramite JDBC.
 * Estende PartialCacheSQLEntityRepository, quindi eredita caching e metodi base
 * come save(), delete(), findById() ecc.
 *
 * Il repository mappa l’oggetto Donation sulla tabella donation:
 *   - donation.id       → Donation.id
 *   - donation.member_id → Donation.member.getId()
 *   - donation.amount    → Donation.amount
 *   - donation.date      → Donation.date
 *   - donation.notes     → Donation.notes
 */
public class SQLDonationRepository extends PartialCacheSQLEntityRepository<Donation> implements DonationRepository
{

    public SQLDonationRepository(String table, Connection connection, int maxSize)
    {
        super(table, connection, maxSize);
    }


    /**
     * Prepara il comando SQL per l’inserimento di una donazione nel database.
     *
     * Il metodo:
     *   - crea una PreparedStatement con INSERT INTO donation(...) VALUES(?, ?, ?, ?)
     *   - associa i parametri in ordine tramite setInt, setBigDecimal, setDate, setString
     *   - non chiude la connessione: è responsabilità di chi chiama
     *
     * Nota sulla data: Donation usa LocalDate, mentre JDBC per il set richiede Date.
     * Per questo si usa Date.valueOf(donation.getDate()) per convertire LocalDate → Date.
     */
    @Override
    public PreparedStatement getInsertCmd(Donation donation) throws SQLException
    {
        // INSERT INTO donation (member_id, amount, date, notes) VALUES (?, ?, ?, ?)
        String sql = "INSERT INTO " + table + " (member_id, amount, date, notes) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, donation.getMember().getId());       // member_id
        ps.setBigDecimal(2, donation.getAmount());         // amount
        ps.setString(3, donation.getDate().toString());   // date come stringa YYYY-MM-DD per SQLite
        ps.setString(4, donation.getNotes());             // notes

        return ps;
    }

    /**
     * Prepara il comando SQL per l’aggiornamento di una donazione nel database.
     *
     * Il metodo:
     *   - crea una PreparedStatement con UPDATE donation SET ... WHERE id = ?
     *   - imposta i parametri nel giusto ordine (set* prima della WHERE)
     *   - restituisce il PreparedStatement pronto per essere eseguito
     */
    @Override
	public PreparedStatement getUpdateCmd(Donation donation) throws SQLException
    {
        // UPDATE donation SET member_id=?, amount=?, date=?, notes=? WHERE id = ?
        String sql = "UPDATE " + table + " SET member_id=?, amount=?, date=?, notes=? WHERE id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        // SET: imposta i campi della donazione (ordine delle colonne nel SET)
        ps.setInt(1, donation.getMember().getId());       // member_id
        ps.setBigDecimal(2, donation.getAmount());         // amount
        ps.setString(3, donation.getDate().toString());   // date come stringa YYYY-MM-DD per SQLite
        ps.setString(4, donation.getNotes());             // notes

        // WHERE: filtra per id
        ps.setInt(5, donation.getId()); // id

        return ps;
    }

    /**
     * Converte una riga del ResultSet (una donazione dal DB) in un oggetto Donation.
     *
     * Il metodo:
     *   - legge i valori dalle colonne con getInt, getBigDecimal, getDate, getString
     *   - usa id, amount, date e notes per costruire un oggetto Donation
     *   - per la data: restituisce un Date, che tramite toLocalDate() torna a LocalDate
     *
     * Attenzione:
     *   - Il riferimento a Member (donor) non viene letto qui.
     *   - Solitamente Member verrà poi caricato da un SQLMemberRepository separato.
     *   - Qui si può lasciare solo l'id del membro (o passare un Member con solo l'id).
     */
    @Override
    public Donation rowToX(ResultSet rows) throws SQLException
    {
        return new Donation(
        	rows.getInt("id"),
            Context.getDependency(MemberRepository.class).findById(rows.getInt("member_id")),
            rows.getBigDecimal("amount"),
            rows.getString("date") != null
                ? LocalDate.parse(rows.getString("date"))
                : null,
            rows.getString("notes")
        );
    }

    /**
     * Restituisce tutte le donazioni effettuate da un socio specifico.
     *
     * @param memberId identificativo del socio
     * @return lista delle donazioni del socio, mai null (può essere vuota)
     */
    @Override
    public List<Donation> findByMemberId(int memberId) throws SQLException
    {
        return findWhere("member_id = " + memberId);
    }
    /**
     * Restituisce le donazioni effettuate da un socio nell'ultimo anno.
     * L'ultimo anno è calcolato dalla data odierna meno 365 giorni.
     *
     * @param memberId identificativo del socio
     * @return lista delle donazioni del socio nell'ultimo anno, mai null (può essere vuota)
     */
    @Override
    public List<Donation> findByMemberIdLastYear(int memberId) throws SQLException
    {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        String whereClause = "member_id = " + memberId + " AND date >= '" + oneYearAgo.toString() + "'";
        return findWhere(whereClause);
    }


	@Override
	public List<Donation> findDateBetween(LocalDate d1, LocalDate d2)
	{
		return findWhere("date between '" +d1+"' and '"+d2+"'");
	}



}
