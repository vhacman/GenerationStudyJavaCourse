package com.generation.acmc2.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Donation;
import com.generation.acmc2.model.entities.Member;
import com.generation.acmc2.model.entities.MemberStatus;
import com.generation.library.repository.SQLEntityRepository;

public class MemberRepositorySQL extends SQLEntityRepository<Member> implements MemberRepository{

	public MemberRepositorySQL(String table, Connection connection) 
	{
		super(table, connection);
	}

	@Override
	public Member findById(int id, boolean complete) throws SQLException
	{
		// siccome findById richiama findWhere
		// e findWhere richiama le donazioni
		// e le donazioni richiamano findByID
		// abbiamo un ciclo
		return !complete ? findByIdNaked(id) : findById(id);
	}

	private Member findByIdNaked(int id) throws SQLException
	{
		Statement readSQL = 
				connection.createStatement();
		ResultSet rows = readSQL.executeQuery("select * from Member where id="+id);
		Member res = rows.next() ? rowToX(rows) : null;
		readSQL.close();
		rows.close();
		return res;
	}

	@Override
	public Member rowToX(ResultSet rows) throws SQLException 
	{
		Member m = new Member();
		m.setId(rows.getInt("id"));
		m.setFirstName(rows.getString("firstname"));
		m.setLastName(rows.getString("lastname"));
		m.setDob(LocalDate.parse(rows.getString("dob")));
		m.setSsn(rows.getString("ssn"));
		m.setStatus(MemberStatus.valueOf(rows.getString("status")));
		return m;
	}
	

	
	// per ora non implemento i metodi di scrittura
	// perché sono uguali
	@Override
	protected PreparedStatement getUpdateCmd(Member newVersion) throws SQLException {
		// Assumendo che la tabella si chiami 'member'
	    String sql = "UPDATE member SET firstName=?, lastName=?, ssn=?, dob=?, status=? WHERE id=?";
	    
	    PreparedStatement ps = connection.prepareStatement(sql);
	    
	    ps.setString(1, newVersion.getFirstName());
	    ps.setString(2, newVersion.getLastName());
	    ps.setString(3, newVersion.getSsn());
	    
	    // Gestione LocalDate: lo convertiamo in stringa (formato ISO-8601 YYYY-MM-DD)
	    // SQLite e la maggior parte dei DB lo accettano correttamente così
	    ps.setString(4, newVersion.getDob() != null ? newVersion.getDob().toString() : null);
	    
	    // Gestione Enum MemberStatus
	    ps.setString(5, newVersion.getStatus() != null ? newVersion.getStatus().name() : null);
	    
	    // Il parametro per il WHERE id=?
	    ps.setInt(6, newVersion.getId());
	    
	    return ps;
	}

	@Override
	protected PreparedStatement getInsertCmd(Member x) throws SQLException 
	{
		// L'ID non viene inserito manualmente perché è auto-incrementante
	    String sql = "INSERT INTO member (firstName, lastName, ssn, dob, status) VALUES (?, ?, ?, ?, ?)";
	    
	    PreparedStatement ps = connection.prepareStatement(sql);
	    
	    ps.setString(1, x.getFirstName());
	    ps.setString(2, x.getLastName());
	    ps.setString(3, x.getSsn());
	    
	    // Salviamo la data di nascita come stringa ISO (YYYY-MM-DD)
	    ps.setString(4, x.getDob() != null ? x.getDob().toString() : null);
	    
	    // Salviamo lo stato come stringa (nome dell'enum)
	    ps.setString(5, x.getStatus() != null ? x.getStatus().name() : null);
	    
	    return ps;
	   }


	@Override
	public List<Member> findWhere(String condition) throws SQLException
	{
		DonationRepository donationRepo = 
				Context.getDependency(DonationRepository.class);
		
		// nudi
		List<Member> res = super.findWhere(condition);
		List<Donation> allDonations = donationRepo.findAll();
		
		// ricollego i membri alle loro donazioni
		// sub ottimale ma non orrenda come prima
		for(Member m:res)
			for(Donation d:allDonations)
				if(d.getMember().getId()==m.getId())
					m.addDonation(d);

		return res;
	}
	
	

}
