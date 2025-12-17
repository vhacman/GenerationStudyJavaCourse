package com.generation.lbb.view;

import com.generation.lbb.model.entities.Booking;
import com.generation.library.Template;

public class BookingView
{
	String filename;
	
	public	BookingView(String filename)
	{
		this.filename = filename;
	}
	
	public String render(Booking b)
	{
		return Template
				.load(filename)
				.replace("[id]", b.id + "")
				.replace("[firstName]", b.firstName.toUpperCase())
				.replace("[lastName]", b.lastName.toUpperCase())
				.replace("[roomType]", b.roomType + "")
				.replace("[membershipType]", b.membershipType + "")
				.replace("[Check In]", b.checkIn + "")
				.replace("[Check Out]", b.checkOut + "")
				.replace("[nights]", b.getNights() + "")
				.replace("[price]", b.getFinalPrice() + "")
				.replace("[discount]", b.getDiscount() + "");								
	}
}
