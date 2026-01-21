package com.generation.gbb.test;

import org.junit.jupiter.api.Test;

import com.generation.gbb.model.entities.Room;
import com.generation.gbb.profiling.ProfilingMonitor;
import com.generation.gbb.repository.SQLRoomRepository;
import com.generation.gbb.repository.interfaces.RoomRepository;

class SQLRoomRepositoryTest
{
	
	@Test
	void test()
	{
		ProfilingMonitor.queryNumber = 0;
		RoomRepository repo = new SQLRoomRepository();
		Room r = repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findById(1);
		repo.findAll();
		repo.findAll();
		repo.findAll();
		repo.findAll();
		repo.findAll();

		
		
		
		assert(r.getName().equals("Camera Standard"));
		assert(ProfilingMonitor.queryNumber==1);
		
	}
	
}
