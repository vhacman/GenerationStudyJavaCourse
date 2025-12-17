package com.generation.lbb.demo;

import java.time.LocalDate;
import com.generation.lbb.model.entities.Booking;
import com.generation.lbb.model.entities.MembershipType;
import com.generation.lbb.model.entities.RoomType;
import com.generation.library.Console;

public class DemoBooking
{
	public static void main(String[] args)
	{
		// Crea oggetto prenotazione vuoto
		Booking b = new Booking();
		
		// INPUT Nome
		Console.print("Inserire Nome: ");
		b.firstName = Console.readString();
		
		// INPUT Cognome
		Console.print("Inserire Cognome: ");
		b.lastName = Console.readString();
		
		// INPUT CHECK-IN
		Console.print("Inserire Check In in formato YYYY-MM-DD: ");
		// Converte stringa in LocalDate (es: "2025-12-20")
		b.checkIn = LocalDate.parse(Console.readString());
		
		// INPUT CHECK-IN
		Console.print("Inserire età in formato YYYY-MM-DD: ");
		// Converte stringa in LocalDate (es: "2025-12-20")
		b.dob = LocalDate.parse(Console.readString());
		
		// INPUT CHECK-OUT
		Console.print("Inserire Check Out in formato YYYY-MM-DD: ");
		b.checkOut = LocalDate.parse(Console.readString());
		
		// INPUT TIPO CAMERA (enum, non stringa!)
		Console.print("Inserire tipo di stanza fra BASIC, MIDDLE, SUPERIOR, SUITE: ");
		// valueOf() converte la stringa nell'enum corrispondente
		b.roomType = RoomType.valueOf(Console.readString());
		
		// Mostra prezzo base (notti × costo camera)
		Console.print("Costo senza sconti applicati: " + b.getBasicPrice() + " €\n");

		// INPUT MEMBERSHIP (livello abbonamento)
		Console.print("Inserire livello di Membership (NONE, SILVER, GOLD): ");
		b.membershipType = MembershipType.valueOf(Console.readString());
		
		// Mostra percentuale di sconto
		Console.print("Lei ha uno sconto di: " + b.getDiscount() + "€\n");

		// Calcola e mostra numero di notti
		System.out.println("Le resterà: " + b.getNights() + " notti");
		
		// Mostra prezzo finale con sconto applicato
		Console.print("Lei paga: " + b.getFinalPrice() + " €");
	}
}

// ESEMPIO ESECUZIONE:
// Input:
//   Check In: 2025-12-20
//   Check Out: 2025-12-23
//   Tipo stanza: SUPERIOR
//   Membership: GOLD
//
// Output:
//   Costo base: 300€ (3 notti × 100€)
//   Sconto: 20%
//   Notti: 3
//   Prezzo finale: 240€