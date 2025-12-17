package com.generation.jia.test;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

import com.generation.jia.model.entities.AirlineTicket;
import com.generation.jia.model.entities.MembershipType;
import com.generation.jia.model.entities.ServiceClass;

public class JavaitaTest
{
	@Test
	void	testGetPrice()
	{
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "001";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.BASIC;
		ticket1.membershipType = MembershipType.NONE;

		
		double price = ticket1.getPrice();
		
		assert (price == 10.0);
	}
	
	@Test
	void testGetDuration()
	{
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "002";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.SILVER;
		ticket1.membershipType = MembershipType.SILVER;
		ticket1.date = LocalDate.of(2025, 12, 20);
		ticket1.start = LocalTime.of(10, 0);
		ticket1.end = LocalTime.of(15, 0);
		
		LocalTime duration = ticket1.getDuration();
		assert (duration.getHour() == 5 && duration.getMinute() == 0);
	}
	
	@Test 
	void testIsValid()
	{
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "002";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.SILVER;
		ticket1.membershipType = MembershipType.SILVER;
		ticket1.date = LocalDate.of(2025, 12, 20);
		ticket1.start = LocalTime.of(10, 0);
		ticket1.end = LocalTime.of(15, 0);
		
		boolean isValid = ticket1.isValid();
		
		assert(isValid == true);
	}
}
