package com.generation.acmc2.controller;

import java.sql.SQLException;
import java.util.List;

import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Member;
import com.generation.acmc2.repository.MemberRepository;
import com.generation.library.Console;

public class RiepilogoVersamentiUltimoAnnoSocioPerSocio 
{

	private final static MemberRepository memberRepo = 
			Context.getDependency(MemberRepository.class);

	
	public static void main(String[] args) 
	{

		try
		{
			List<Member> all = memberRepo.findAll();
			for(Member m:all)
				Console.print(m.getFirstName()+" "+m.getLastName()+" "+m.getLastYearDonations());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}		
		
	}

}
