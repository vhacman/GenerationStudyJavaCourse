package com.generation.jia.demo;

import java.time.LocalDate;
import java.time.LocalTime;

import com.generation.jia.model.entities.AirlineTicket;
import com.generation.jia.model.entities.MembershipType;
import com.generation.jia.model.entities.ServiceClass;
import com.generation.library.Console;

public class JavaitaAirplaneDemo
{
	public static void main(String[] args)
	{
		//CREAZIONE BIGLIETTO AEREO PER PROVARE LOCALTIME
		
		AirlineTicket ticket1 = new AirlineTicket();
		Console.print("Inserire ID: ");
		ticket1.id = Console.readString();
		
		Console.print("Inserire km: ");
		ticket1.km = Console.readInt();
		
		Console.print("Inserire Service Class tra BASIC, SILVER, GOLD: ");
		ticket1.serviceClass = ServiceClass.valueOf(Console.readString());
		
		Console.print("Inserire Membership Type tra NONE, SILVER, GOLD: ");
		ticket1.membershipType = MembershipType.valueOf(Console.readString());
		
		Console.print("Inserire Data partenza in formato YYYY-MM-DD: ");
		ticket1.date = LocalDate.parse(Console.readString());
		
		Console.print("Inserire Orario partenza (formato HH:MM): ");
		ticket1.start = LocalTime.parse(Console.readString());
		
		Console.print("Inserire Orario arrivo (formato HH:MM): ");
		ticket1.end = LocalTime.parse(Console.readString());

	}
}
