package com.generation.gbb.oldView;

import com.generation.gbb.model.entities.Guest;

public class GuestViewTxtBasic extends GuestView
{

	public GuestViewTxtBasic(String template) 
	{
		super(template);
	}

	@Override
	public String render(Guest g) 
	{
		return templateContent
				.replace("[id]", g.getId()+"")
				.replace("[firstname]", g.getFirstName())
				.replace("[lastname]", g.getLastName())
				.replace("[ssn]", g.getSsn())
				.replace("[dob]", g.getDob()+"")
				.replace("[city]", g.getCity())
				.replace("[address]", g.getAddress());		
	}
}
