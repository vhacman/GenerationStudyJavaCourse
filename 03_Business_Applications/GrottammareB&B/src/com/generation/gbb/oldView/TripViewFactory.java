package com.generation.gbb.oldView;

/**
 * Factory class for creating TripView instances.
 * Provides pre-configured view objects for different display formats.
 */
public class TripViewFactory
{
	static TripView summaryView = new TripViewTxtBasic("template/tripsummary.txt");
	static TripView fullView    = new TripViewTxtBasic("template/fulltripview.txt");

	/**
	 * Creates a TripView instance based on the requested type.
	 * @param type The view type ("full" or "summary")
	 * @return TripView instance (full view if type is "full", summary otherwise)
	 */
	public static TripView make(String type)
	{
		return type.equals("full") ? fullView : summaryView;
	}
}
