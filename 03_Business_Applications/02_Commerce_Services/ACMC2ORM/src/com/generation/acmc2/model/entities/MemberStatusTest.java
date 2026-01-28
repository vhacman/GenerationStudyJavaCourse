package com.generation.acmc2.model.entities;

import org.junit.jupiter.api.Test;

class MemberStatusTest 
{

	@Test
	void test() 
	{
		assert(MemberStatus.getByThreshold(5100)==MemberStatus.SILVER);
	
	}

}
