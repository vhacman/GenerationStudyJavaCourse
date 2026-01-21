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
		//SERVICE CLASS BASIC
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "001";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.BASIC;
		ticket1.membershipType = MembershipType.NONE;		
		double price = ticket1.getPrice();		
		assert (price == 10.0);
		
		
		//SERVICE CLASS BASIC, MEMBERSHIP SILVER
		AirlineTicket ticket2 = new AirlineTicket();
	 	ticket2.id = "002";
        ticket2.km = 100;
        ticket2.serviceClass = ServiceClass.BASIC;
        ticket2.membershipType = MembershipType.SILVER;
        double price1 = ticket2.getPrice();
        assert(price1 == 8);
        
        //SERVICE CLASS BASIC, MEMBERSHIP GOLD
        AirlineTicket ticket3 = new AirlineTicket();
	 	ticket3.id = "003";
        ticket3.km = 100;
        ticket3.serviceClass = ServiceClass.BASIC;
        ticket3.membershipType = MembershipType.GOLD;
        double price2 = ticket3.getPrice();
        assert(price2 == 7);
                
        //ServiceClass SILVER, MembershipType NONE        
        AirlineTicket ticket4 = new AirlineTicket();
	 	ticket4.id = "004";
        ticket4.km = 100;
        ticket4.serviceClass = ServiceClass.SILVER;
        ticket4.membershipType = MembershipType.NONE;
        double price4 = ticket4.getPrice();
        assert(price4 == 20);
        
		//SERVICE CLASS SILVER
		AirlineTicket ticket5 = new AirlineTicket();
		ticket5.id = "003";
		ticket5.km = 100;
		ticket5.serviceClass = ServiceClass.SILVER;
		ticket5.membershipType = MembershipType.SILVER;		
		double price5 = ticket5.getPrice();
		// SILVER service: 100 * 0.2 = 20.0
		// SILVER membership: 20% discount = 20.0 * 0.2 = 4.0
		// Final: 20.0 - 4.0 = 16.0
		assert(price5 == 16.0);
	
		//ServiceClass SILVER, MembershipType GOLD
        
		AirlineTicket ticket6 = new AirlineTicket();
	 	ticket6.id = "006";
        ticket6.km = 100;
        ticket6.serviceClass = ServiceClass.SILVER;
        ticket6.membershipType = MembershipType.GOLD;
        double price6 = ticket6.getPrice();
        assert(price6 == 14);
        
        //ServiceClass GOLD, MembershipType NONE
        
        AirlineTicket ticket7 = new AirlineTicket();
	 	ticket7.id = "007";
        ticket7.km = 100;
        ticket7.serviceClass = ServiceClass.GOLD;
        ticket7.membershipType = MembershipType.NONE;
        double price7 = ticket7.getPrice();
        assert(price7 == 50);
        
        //ServiceClass GOLD, MembershipType SILVER
        
        AirlineTicket ticket8 = new AirlineTicket();
	 	ticket8.id = "008";
        ticket8.km = 100;
        ticket8.serviceClass = ServiceClass.GOLD;
        ticket8.membershipType = MembershipType.SILVER;
        double price8 = ticket8.getPrice();
        assert(price8 == 40);
        
		
		//SERVICE CLASS GOLD
		AirlineTicket ticket9 = new AirlineTicket();
		ticket9.id = "004";
		ticket9.km = 200;
		ticket9.serviceClass = ServiceClass.GOLD;
		ticket9.membershipType = MembershipType.GOLD;
		double price9 = ticket9.getPrice();
		// GOLD service: 200 * 0.5 = 100.0
		// GOLD membership: 30% discount = 100.0 * 0.3 = 30.0
		// Final: 100.0 - 30.0 = 70.0
		assert(price9 == 70.0);
	}
	
	@Test
	void testGetDuration()
	{
		// Test 1: Durata normale (5 ore)
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "001";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.SILVER;
		ticket1.membershipType = MembershipType.SILVER;
		ticket1.date = LocalDate.of(2025, 12, 20);
		ticket1.start = LocalTime.of(10, 0);
		ticket1.end = LocalTime.of(15, 0);
		LocalTime duration1 = ticket1.getDuration();
		assert (duration1.getHour() == 5 && duration1.getMinute() == 0);
		
		// Test 2: Durata con ore e minuti diversi
		AirlineTicket ticket2 = new AirlineTicket();
		ticket2.id = "002";
		ticket2.start = LocalTime.of(18, 20);
		ticket2.end = LocalTime.of(20, 45);
		LocalTime duration2 = ticket2.getDuration();
		assert(duration2.getHour() == 2 && duration2.getMinute() == 25);
		
		// Test 3: Durata di esattamente 1 ora
		AirlineTicket ticket3 = new AirlineTicket();
		ticket3.id = "003";
		ticket3.start = LocalTime.of(18, 0);
		ticket3.end = LocalTime.of(19, 0);
		LocalTime duration3 = ticket3.getDuration();
		assert(duration3.getHour() == 1 && duration3.getMinute() == 0);
		
		// Test 4: Durata di un ora in soli minuti
		AirlineTicket ticket4 = new AirlineTicket();
		ticket4.id = "004";
		ticket4.start = LocalTime.of(18, 15);
		ticket4.end = LocalTime.of(18, 45);
		LocalTime duration4 = ticket4.getDuration();
		assert(duration4.getHour() == 0 && duration4.getMinute() == 30);
		
		// Test 5: Durata lunga di 10+ ore (attraversa la mezzanotte)
		AirlineTicket ticket5 = new AirlineTicket();
		ticket5.id = "005";
		ticket5.start = LocalTime.of(18, 30);
		ticket5.end = LocalTime.of(4, 31);
		LocalTime duration5 = ticket5.getDuration();
		assert(duration5.getHour() == 10 && duration5.getMinute() == 1);
		
		// Test 6: Durata in pochi minuti
		AirlineTicket ticket6 = new AirlineTicket();
		ticket6.id = "006";
		ticket6.start = LocalTime.of(18, 50);
		ticket6.end = LocalTime.of(19, 5);
		LocalTime duration6 = ticket6.getDuration();
		assert(duration6.getHour() == 0 && duration6.getMinute() == 15);
		
		// Test 7: Stesso orario (durata 0, non 24)
		AirlineTicket ticket7 = new AirlineTicket();
		ticket7.id = "007";
		ticket7.start = LocalTime.of(18, 0);
		ticket7.end = LocalTime.of(18, 0);
		LocalTime duration7 = ticket7.getDuration();
		assert(duration7.getHour() == 0 && duration7.getMinute() == 0);
		
		// Test 8: Attraversa la mezzanotte (3 ore)
		AirlineTicket ticket8 = new AirlineTicket();
		ticket8.id = "008";
		ticket8.start = LocalTime.of(23, 0);
		ticket8.end = LocalTime.of(2, 0);
		LocalTime duration8 = ticket8.getDuration();
		assert(duration8.getHour() == 3 && duration8.getMinute() == 0);
		
		// Test 9: Attraversa la mezzanotte con minuti
		AirlineTicket ticket9 = new AirlineTicket();
		ticket9.id = "009";
		ticket9.start = LocalTime.of(9, 30);
		ticket9.end = LocalTime.of(12, 45);
		LocalTime duration9 = ticket9.getDuration();
		assert(duration9.getHour() == 3 && duration9.getMinute() == 15);
	}

	
	@Test 
	void testIsValid()
	{
		//NULL DATE
		AirlineTicket ticket0 = new AirlineTicket();
		ticket0.id = "007";
		ticket0.km = 100;
		ticket0.serviceClass = ServiceClass.BASIC;
		ticket0.membershipType = MembershipType.NONE;
		ticket0.date = null;
		ticket0.start = LocalTime.of(10, 0);
		ticket0.end = LocalTime.of(15, 0);		
		boolean isValid0 = ticket0.isValid();		
		assert(isValid0 == false);
		
		//VALID
		AirlineTicket ticket1 = new AirlineTicket();
		ticket1.id = "002";
		ticket1.km = 100;
		ticket1.serviceClass = ServiceClass.SILVER;
		ticket1.membershipType = MembershipType.SILVER;
		ticket1.date = LocalDate.of(2025, 12, 20);
		ticket1.start = LocalTime.of(10, 0);
		ticket1.end = LocalTime.of(15, 0);
		boolean isValid1 = ticket1.isValid();
		assert(isValid1 == true);		
		
		//NULL START TIME
		AirlineTicket ticket3 = new AirlineTicket();
		ticket3.id = "008";
		ticket3.km = 100;
		ticket3.serviceClass = ServiceClass.BASIC;
		ticket3.membershipType = MembershipType.NONE;
		ticket3.date = LocalDate.of(2025, 12, 20);
		ticket3.start = null;
		ticket3.end = LocalTime.of(15, 0);		
		boolean isValid3 = ticket3.isValid();		
		assert(isValid3 == false);
		
		//NULL END TIME
		AirlineTicket ticket4 = new AirlineTicket();
		ticket4.id = "009";
		ticket4.km = 100;
		ticket4.serviceClass = ServiceClass.BASIC;
		ticket4.membershipType = MembershipType.NONE;
		ticket4.date = LocalDate.of(2025, 12, 20);
		ticket4.start = LocalTime.of(10, 0);
		ticket4.end = null;		
		boolean isValid4 = ticket4.isValid();		
		assert(isValid4 == false);
		
		//END BEFORE START
		AirlineTicket ticket5 = new AirlineTicket();
		ticket5.id = "010";
		ticket5.km = 100;
		ticket5.serviceClass = ServiceClass.BASIC;
		ticket5.membershipType = MembershipType.NONE;
		ticket5.date = LocalDate.of(2025, 12, 20);
		ticket5.start = LocalTime.of(15, 0);
		ticket5.end = LocalTime.of(10, 0);		
		boolean isValid5 = ticket5.isValid();		
		assert(isValid5 == false);
		
		//KM NEGATIVI
		AirlineTicket ticket6 = new AirlineTicket();
		ticket6.id = "012";
		ticket6.km = -100;
		ticket6.serviceClass = ServiceClass.BASIC;
		ticket6.membershipType = MembershipType.NONE;
		ticket6.date = LocalDate.of(2025, 12, 20);
		ticket6.start = LocalTime.of(10, 0);
		ticket6.end = LocalTime.of(15, 0);		
		boolean isValid6 = ticket6.isValid();		
		assert(isValid6 == false);
		
		//KM NULL
		AirlineTicket ticket7 = new AirlineTicket();
		ticket7.id = "012";
		ticket7.km = -100;
		ticket7.serviceClass = ServiceClass.BASIC;
		ticket7.membershipType = MembershipType.NONE;
		ticket7.date = LocalDate.of(2025, 12, 20);
		ticket7.start = LocalTime.of(10, 0);
		ticket7.end = LocalTime.of(15, 0);		
		boolean isValid7 = ticket7.isValid();		
		assert(isValid7 == false);
		
		//ID NULL
		AirlineTicket ticket8 = new AirlineTicket();
		ticket8.id = null;
		ticket8.km = 100;
		ticket8.serviceClass = ServiceClass.BASIC;
		ticket8.membershipType = MembershipType.NONE;
		ticket8.date = LocalDate.of(2025, 12, 20);
		ticket8.start = LocalTime.of(10, 0);
		ticket8.end = LocalTime.of(15, 0);	
		boolean isValid8 = ticket8.isValid();
		assert(isValid8 == false);
		
		//ID VUOTO
		AirlineTicket ticket9 = new AirlineTicket();
		ticket9.id = "";
		ticket9.km = 100;
		ticket9.serviceClass = ServiceClass.BASIC;
		ticket9.membershipType = MembershipType.NONE;
		ticket9.date = LocalDate.of(2025, 12, 20);
		ticket9.start = LocalTime.of(10, 0);
		ticket9.end = LocalTime.of(15, 0);	
		boolean isValid9 = ticket9.isValid();
		assert(isValid9 == false);
	}
}
