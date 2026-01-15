package com.generation.gbb.view;

import com.generation.gbb.model.entities.Room;

/**
 * Concrete implementation of RoomView for text-based rendering.
 * Uses template placeholders to format room data.
 */
public class RoomViewTxtBasic extends RoomView
{
	public RoomViewTxtBasic(String template)
	{
		super(template);
	}

	@Override
	public String render(Room r)
	{
		return templateContent
				.replace("[id]", r.getId() + "")
				.replace("[name]", r.getName())
				.replace("[description]", r.getDescription())
				.replace("[size]", r.getSize() + "")
				.replace("[floor]", r.getFloor() + "")
				.replace("[price]", r.getPrice() + "");
	}
}
