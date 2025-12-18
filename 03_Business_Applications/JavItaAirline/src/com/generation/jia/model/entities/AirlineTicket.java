package com.generation.jia.model.entities;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class AirlineTicket
{
	public	String	id;
	public	int		km;
	public	ServiceClass	serviceClass;
	public	MembershipType  membershipType;
	public	LocalDate		date;
	public	LocalTime		start;
	public	LocalTime		end;
	
	public	double	getPrice()
	{
		double	basePrice = km * serviceClass.pricePerKm;
		double	discountAmount = basePrice * membershipType.discount;
		return basePrice - discountAmount;
	}
	
	public	LocalTime getDuration()
	{
		Duration duration = Duration.between(start, end);
		if (duration.isNegative()) {
                duration = duration.plusDays(1);
        }
		long	hour = duration.toHours();
		long	minutes = duration.toMinutesPart();
		return LocalTime.of((int)hour, (int)minutes);
	}
	
	public boolean isValid()
	{
		return date != null &&
				start != null &&
				end != null && 
				end.isAfter(start) &&
				km > 0 && 
				id != null &&
				!id.isEmpty();
	}
	
}
