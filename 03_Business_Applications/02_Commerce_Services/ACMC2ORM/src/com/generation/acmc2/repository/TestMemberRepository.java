package com.generation.acmc2.repository;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Member;

class TestMemberRepository 
{

	@Test
	void test() throws SQLException
	{
		Connection connection = Context.getDependency(Connection.class);
		MemberRepository repo = 
				new MemberRepositorySQL("member",connection);
		
		Member m = repo.findById(1, true); // NON HO CARICATO SOLO IL MEMBRO MA ANCHE LE SUE DONAZIONI
	
		assert(m.getDonations().size()==3);
		assert(m.getTotalDonations()==3000);
		
	}

}
