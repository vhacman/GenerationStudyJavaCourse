package com.generation.oc.view;

import com.generation.library.Console;
import com.generation.library.Template;

// classe statica con le stampe classiche
// e le voci di menù, per comodità
// 

public class Menu 
{
	
	public final static String LOGO = Template.load("template/txt/logo.txt");
	public final static String MENU = Template.load("template/txt/menu.txt");
	public final static String QUITCOMMAND 							= "Q";
	public final static String INSERTPRESTATIONCOMMAND 				= "1";
	public static final String INSERTEMERGENCYPRESTATIONCOMMAND 	= "2";
	public static final String PRINTPRESTATIONSBYDATE 				= "3";
	public static final String PRINTPRESTATIONSFORTODAY 			= "4";
	public static final String PRINTINCOMEBYPERIOD 					= "5";
	public static final String PRINTINCOMEBYYEAR 					= "6";
	public static final String PRINTPATIENTFULL 					= "7" ;
	public static final String PRINTPRESTATIONBYID 					= "8" ;
	public static final String PRINTINCOMEDAYBYDAY 					= "9" ;
	public static final String PRINTINCOMEBYSERVICETYPE 			= "10";
	public static final String PRINTDISTINCTPATIENTSFORPERIOD  		= "11";
	public static final String PRINTDISTINCTSERVICESFORPERIOD 		= "12";
	public static final String INSERTHEALTHSERVICE 					= "13";
	public static final String UPDATESERVICEPRICE 					= "14";
	public static final String INSERTPATIENT 						= "15";
	public static final String DELETEPRESTATION 					= "16";
	public static final String PRINTPATIENTHTML 					= "17";
	public static final String PRINTPRESTATIONHTML 					= "18";
	public static final String PRINTTODAYPRESTATIONSHTML 			= "19";


	
	public static void printLogo()
	{
		Console.print(LOGO);
	}
	
	public static void printMenu()
	{
		Console.print(MENU);
	}

}
