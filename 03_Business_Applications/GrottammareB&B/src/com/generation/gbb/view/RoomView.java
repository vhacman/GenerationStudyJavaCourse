package com.generation.gbb.view;

import java.util.List;

import com.generation.gbb.model.entities.Room;
import com.generation.library.Template;

/**
 * Abstract base class for room views.
 * Defines the contract for rendering room entities in different formats.
 * Provides a partial implementation with template loading and list rendering.
 */
public abstract class RoomView
{
	protected String template;
	protected String templateContent;

	public RoomView(String template)
	{
		this.template = template;
		this.templateContent = Template.load(template);
	}

	/**
	 * Renders a single room entity.
	 * Must be implemented by concrete subclasses.
	 * @param r The room to render
	 * @return Formatted string representation of the room
	 */
	public abstract String render(Room r);

	/**
	 * Renders a list of rooms by calling render() on each element.
	 * @param rooms List of rooms to render
	 * @return Formatted string with all rooms
	 */
	public String render(List<Room> rooms)
	{
		String res = "";
		for(Room room : rooms)
			res += render(room) + "\n";
		return res;
	}
}
