package com.generation.gbb.repository;


public class GuestRepositoryFactory
{
	
	static GuestRepository dummmyGuestRepo = new DummyGuestRepositoryFactory();

	
	public static GuestRepository make()
	{ 
		return dummmyGuestRepo;
	}
	
}
