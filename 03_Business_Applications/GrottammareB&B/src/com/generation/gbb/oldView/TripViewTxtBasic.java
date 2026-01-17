package com.generation.gbb.oldView;

import com.generation.gbb.model.entities.Trip;

/**
 * Concrete implementation of TripView for text-based rendering.
 * Uses template placeholders to format trip data.
 */
public class TripViewTxtBasic extends TripView
{
	public TripViewTxtBasic(String template)
	{
		super(template);
	}

	@Override
	public String render(Trip t)
	{
		return templateContent
				.replace("[id]", t.getId() + "")
				.replace("[city]", t.getCity())
				.replace("[date]", t.getDate().toString())
				.replace("[review]", t.getReview())
				.replace("[score]", t.getScore() + "");
	}
}
