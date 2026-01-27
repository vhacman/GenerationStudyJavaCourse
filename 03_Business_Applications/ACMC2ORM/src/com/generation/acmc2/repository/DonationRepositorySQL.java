package com.generation.acmc2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Donation;
import com.generation.acmc2.model.entities.Member;
import com.generation.library.repository.SQLEntityRepository;

public class DonationRepositorySQL extends SQLEntityRepository<Donation> implements DonationRepository 
{

	public DonationRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected PreparedStatement getUpdateCmd(Donation newVersion) throws SQLException 
	{
		String sql = "UPDATE donation SET date=?, amount=?, memberid=? WHERE id=?";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    
	    ps.setString(1, newVersion.getDate() != null ? newVersion.getDate().toString() : null);
	    ps.setInt(2, newVersion.getAmount());
	    
	    // Prendiamo l'ID dal riferimento all'oggetto Member
	    if (newVersion.getMember() != null) {
	        ps.setInt(3, newVersion.getMember().getId());
	    } else {
	        ps.setNull(3, java.sql.Types.INTEGER);
	    }
	    
	    ps.setInt(4, newVersion.getId());
	    return ps;
	}

	@Override
	protected PreparedStatement getInsertCmd(Donation x) throws SQLException 
	{
		String sql = "INSERT INTO donation (date, amount, memberid) VALUES (?, ?, ?)";
	    PreparedStatement ps = connection.prepareStatement(sql);
	    
	    ps.setString(1, x.getDate() != null ? x.getDate().toString() : null);
	    ps.setInt(2, x.getAmount());
	    
	    // Colleghiamo l'id del Member
	    if (x.getMember() != null) {
	        ps.setInt(3, x.getMember().getId());
	    } else {
	        ps.setNull(3, java.sql.Types.INTEGER);
	    }
	    
	    return ps;
	}

	@Override
	public Donation rowToX(ResultSet rows) throws SQLException 
	{
		// sto caricando un figlio
		// devo caricare il padre
		MemberRepository memberRepo = Context.getDependency(MemberRepository.class);
		Donation d = new Donation();
	    d.setId(rows.getInt("id"));
	    d.setAmount(rows.getInt("amount"));
	    // Parsing della data
	    String dateStr = rows.getString("date");
	    if (dateStr != null) {
	        d.setDate(LocalDate.parse(dateStr));
	    }
	    // dal memberid devo ricavare il Member
	    int memberId = rows.getInt("memberid");
	    
	    // false perché non voglio che carichi gli altri figli
	    // e che vada in loop
	    Member m = memberRepo.findById(memberId, false);
	    
	    d.setMember(m);
	    
	    return d;
	}
	
	

}
