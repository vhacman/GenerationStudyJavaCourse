package com.generation.gbb.test;

import org.junit.jupiter.api.Test;

import com.generation.gbb.model.entities.Guest;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.SQLGuestRepository;

class SQLGuestRepositoryTest
{
	@Test
	void test()
	{
		SQLGuestRepository	repo	= new SQLGuestRepository();

		ProfilingMonitor.queryNumber	= 0;
		ProfilingMonitor.rowsLoaded		= 0;

		Guest	g	= repo.findById(1);
		g = repo.findById(1);
		g = repo.findById(1);
		g = repo.findById(1);
		g = repo.findById(1);
		g = repo.findById(1);

		// Con PartialCache: prima chiamata fa query, le successive usano la cache
		assert(ProfilingMonitor.queryNumber == 1);
		assert(ProfilingMonitor.rowsLoaded == 1);
	}
	
}
