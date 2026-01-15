package com.generation.gbb.view;

/**
 * Factory class for creating RoomView instances.
 * Provides pre-configured view objects for different display formats.
 */
public class RoomViewFactory
{
	static RoomView summaryView = new RoomViewTxtBasic("template/roomsummary.txt");
	static RoomView fullView    = new RoomViewTxtBasic("template/fullroomview.txt");

	/**
	 * Creates a RoomView instance based on the requested type.
	 * @param type The view type ("full" or "summary")
	 * @return RoomView instance (full view if type is "full", summary otherwise)
	 */
	public static RoomView make(String type)
	{
		return type.equals("full") ? fullView : summaryView;
	}
}
