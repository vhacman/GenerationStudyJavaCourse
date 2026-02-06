package com.generation.ticket.model.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Ticket 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String room;
	private String opening;
	private String closure;

	private LocalDateTime openOn;
	private LocalDateTime closedOn;

	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOpening() {
		return opening;
	}

	public void setOpening(String opening) {
		this.opening = opening;
	}

	public String getClosure() {
		return closure;
	}

	public void setClosure(String closure) {
		this.closure = closure;
	}

	public LocalDateTime getOpenOn() {
		return openOn;
	}

	public void setOpenOn(LocalDateTime openOn) {
		this.openOn = openOn;
	}

	public LocalDateTime getClosedOn() {
		return closedOn;
	}

	public void setClosedOn(LocalDateTime closedOn) {
		this.closedOn = closedOn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}
	
	
	

}
