package com.generation.ticketGenerator;

import java.time.LocalDateTime;

public class Ticket
{
	private int					id; //id biglietto
	private	Passenger 			passenger; // oggetto passegero
	private LocalDateTime		checkInTime; // data e ora check in biglietto
	private	double				price;
	private String				departureStation; // stazione di partenza
	private LocalDateTime		departureTime; // ora di partenza
	
	public	Ticket() {};
	
	public	Ticket(int id, Passenger passenger, LocalDateTime checkInTime, double price, 
            String departureStation, LocalDateTime departureTime)
	{
		this.id = id;
		this.passenger = passenger;
		this.checkInTime = checkInTime;
		this.price = price;
		this.departureStation = departureStation;
		this.departureTime = departureTime;
	}
	
		// Getter per id
		public int getId() {
			return id;
		}

		// Setter per id
		public void setId(int id) {
			this.id = id;
		}

		// Getter per passenger
		public Passenger getPassenger() {
			return passenger;
		}

		// Setter per passenger
		public void setPassenger(Passenger passenger) {
			this.passenger = passenger;
		}


			// Getter per departureTime
		public LocalDateTime getCheckInTime() {
			return checkInTime;
		}

		// Setter per departureTime
		public void setCheckInTime(LocalDateTime checkInTime) {
			this.checkInTime = checkInTime;
		}

		// Getter per price
		public double getPrice() {
			return price;
		}

		// Setter per price
		public void setPrice(double price) {
			this.price = price;
		}

		// Getter per departureStation
		public String getDepartureStation() {
			return departureStation;
		}

		// Setter per departureStation
		public void setDepartureStation(String departureStation) {
			this.departureStation = departureStation;
		}

		// Getter per departureTime
		public LocalDateTime getDepartureTime() {
			return departureTime;
		}

		// Setter per departureTime
		public void setDepartureTime(LocalDateTime departureTime) {
			this.departureTime = departureTime;
		}
}