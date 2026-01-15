package com.generation.gbb.view;

/**
 * Scelgo quale SOTTOCLASSE, SOTTOTIPO
 * di GuestView voglio produrre
 */
public class GuestViewFactory 
{
	// in questo caso quello che voglio non è un sottotipo dell'interfaccia
	// ma è un sottotipo della classe astratta GuestView
	// ma funziona allo stesso modo
	// la factory decide quale tipo creare e, in certi casi, quale oggetto restituirmi
	static GuestView summaryView = new GuestViewTxtBasic("template/guestsummary.txt");
	static GuestView fullView	 = new GuestViewTxtBasic("template/fullguestview.txt");
	
	public static GuestView make(String type)
	{
		return type.equals("full") ? fullView : summaryView;
	}
	
	
}
