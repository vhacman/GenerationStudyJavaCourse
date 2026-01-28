package com.generation.acmc2.repository;

import org.junit.jupiter.api.Test;

import com.generation.acmc2.context.Context;
import com.generation.library.Console;

class TestDonationRepository 
{

	@Test
	void test() throws Exception
	{
		DonationRepository repo = Context.getDependency(DonationRepository.class);
		
		Console.print(repo.findById(1));
		
		Console.print(repo.findById(1).getMember().getFirstName());
		
		assert(repo.findById(1).getMember().getFirstName().equals("Ferdinando"));
	
	}

}
