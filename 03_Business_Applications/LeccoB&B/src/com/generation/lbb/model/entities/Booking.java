package com.generation.lbb.model.entities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking
{
	public int		id;
	public String firstName;
	public String lastName;
	public LocalDate dob; // DATE OF BIRTH deve essere +18
	public LocalDate checkIn;
	public LocalDate checkOut;
	public RoomType	roomType; // null, BASIC, MIDDLE, SUPERIOR, SUITE
	public MembershipType  membershipType; //null, NONE, SILVER, GOLD

	public long getNights()
	{
		return ChronoUnit.DAYS.between(checkIn, checkOut);
	}
	
	public long getBasicPrice()
	{
		return getNights() * roomType.costPerNight;
	}
	
	public int getDiscount()
	{
		return (int)(membershipType == null ? 0 : getBasicPrice()  * membershipType.discount);
	}
	
	public long getFinalPrice()
	{
		return getBasicPrice() - getDiscount();
	}
	
	public boolean isValid()
	{
		return id != 0								&&
				checkIn != null						&&
				checkOut != null					&&
				checkOut.isAfter(LocalDate.now())	&&
				getNights() > 0						&&
				getNights() <= 30					&&
				roomType != null					&&
				firstName != null					&&
				lastName != null					&&
				!firstName.isBlank()				&&
				!lastName.isBlank()					&&
				dob != null							&&
				getClientAge() > 18					;
				
				
	}

	private int getClientAge()
	{
		return (int) ChronoUnit.YEARS.between(dob,  LocalDate.now());
	}
}

