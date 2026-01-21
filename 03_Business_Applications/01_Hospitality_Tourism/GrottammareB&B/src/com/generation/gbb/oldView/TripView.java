package com.generation.gbb.oldView;

import java.util.List;

import com.generation.gbb.model.entities.Trip;
import com.generation.library.Template;

/**
 * Abstract base class for trip views.
 * Defines the contract for rendering trip entities in different formats.
 * Provides a partial implementation with template loading and list rendering.
 */
public abstract class TripView
{
	protected String template;
	protected String templateContent;

	public TripView(String template)
	{
		this.template = template;
		this.templateContent = Template.load(template);
	}

	/**
	 * Renders a single trip entity.
	 * Must be implemented by concrete subclasses.
	 * @param t The trip to render
	 * @return Formatted string representation of the trip
	 */
	public abstract String render(Trip t);

	/**
	 * Renders a list of trips by calling render() on each element.
	 * @param trips List of trips to render
	 * @return Formatted string with all trips
	 */
	public String render(List<Trip> trips)
	{
		String res = "";
		for(Trip trip : trips)
			res += render(trip) + "\n";
		return res;
	}
}
