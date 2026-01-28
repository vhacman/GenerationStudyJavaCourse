package com.generation.oc.controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.generation.library.Console;
import com.generation.oc.context.Context;
import com.generation.oc.model.entities.HealthService;
import com.generation.oc.model.entities.Patient;
import com.generation.oc.model.entities.Prestation;
import com.generation.oc.model.repository.HealthServiceRepository;
import com.generation.oc.model.repository.PatientRepository;
import com.generation.oc.model.repository.PrestationRepository;
import com.generation.oc.view.Menu;
import com.generation.oc.view.Views;

/**
 * il vostro esercizio master
 * i casi sono in ordine. Per ogni caso che scriverò io ce ne sarà uno che dovrete implementare voi
 * i casi possono costringervi a creare metodi nuovi nei repository
 * ogni caso sottende a un algoritmo e a una logica particolari
 * tutte le eccezioni sono gestite correttamente tranne quelle da dipendenze mancanti
 * che per definizione devono mandare in crash il programma
 * per divertimento ho anche fatto un po' di grafica... ho creato una classe menu per la stampa dei menù
 * 
 */

public class Main 
{
	
	
	
	public static void main(String[] args) 
	{
		Menu.printLogo();
		String cmd;
		do
		{
			Menu.printMenu();
			cmd = Console.readString();
			execute(cmd);
						
		}while(!cmd.equals(Menu.QUITCOMMAND));
	}


	private static void execute(String cmd) 
	{
		switch(cmd)
		{
			case Menu.INSERTPRESTATIONCOMMAND:
				// metodo 1: fatto da me
				PrestationController.insertPrestation();
			break;		
			case Menu.INSERTEMERGENCYPRESTATIONCOMMAND:
				// metodo 2: vostro
				PrestationController.insertEmergencyPrestation();
			break;
			case Menu.PRINTPRESTATIONSBYDATE:
				// metodo 3: fatto da me:
				PrestationController.printByDate();
			break;
			case Menu.PRINTPRESTATIONSFORTODAY:
				// metodo 4: vostro
				PrestationController.printPrestationsForToday();
			break;
			case Menu.PRINTINCOMEBYPERIOD:
				// metodo 5: fatto da me
				PrestationController.printIncomeByPeriod();
			break;
			case Menu.PRINTINCOMEBYYEAR:
				// metodo 6: fatto da voi
				PrestationController.printIncomeByYear();
			break;
			case Menu.PRINTPATIENTFULL:
				// metodo 7: Fatto da me
				PatientController.printPatientFull();
			break;
			case Menu.PRINTPRESTATIONBYID:
				// metodo 8, fatto da voi
				PrestationController.printPrestationById();
			break;
			case Menu.PRINTINCOMEDAYBYDAY:
				// metodo 9, fatto da me
				PrestationController.printEarningDayByDay();
			break;
			case Menu.PRINTINCOMEBYSERVICETYPE:
				// metodo 10, fatto da voi
				PrestationController.printEarningByServiceName();
			break;
			case Menu.PRINTDISTINCTPATIENTSFORPERIOD:
				// metodo 11 fatto da me
				PrestationController.printDistinctPatientsForPeriod();
			break;
			case Menu.PRINTDISTINCTSERVICESFORPERIOD:
				// metodo 12, fatto da voi
				PrestationController.printDistinctServiceForPeriod();
			break;
			// da qui in avanti completate voi
			// mi servono i seguenti casi:
			/*
			 * inserimento nuovo servizio. Se c'è già un servizio con quel nome l'inserimento va annullato
			 
			 * modifica del prezzo di un servizio. Selezionarlo da una lista, chiedere all'utente il nuovo prezzo, poi salvarlo
			 
			 * inserimento di un nuovo paziente. Se c'è già quel codice fiscale il paziente non va salvato
			 
			 * cancellazione di una prestazione dato il suo id. Le prestazioni registrate nel passato non possono essere cancellate. Chiedere conferma prima di cancellare
			 
			 * stampa della scheda html di un paziente. 
			 * Il paziente va selezionato al solito modo, e poi bisogna produrre una stampa html su file. A seconda della scelta dell'utente, bisognerà stampare o meno le prestazioni da lui ricevute 
			 * 
			 * stampa della scheda di una prestazione in html. La scheda deve riportare i dati di paziente e servizio
			 * 
			 * stampa in html delle prestazioni previste per oggi, col logo di Omega Clinic in cima
			 * 
			 * tempo a vostra disposizione: sei ore
			 * 
			 * NON DEVE MAI CRASHARE.
			 * 
			 */
			default:
				Console.print("Bad command");
		}
	}


	
	
	

	
	

	
	

}
