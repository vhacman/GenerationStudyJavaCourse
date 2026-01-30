package com.generation.oc.controller;
import com.generation.library.Console;
import com.generation.oc.view.Menu;

/**
 * il vostro esercizio master
 * i casi sono in ordine. Per ogni caso che scriverò io ce ne sarà uno che dovrete implementare voi
 * i casi possono costringervi a creare metodi nuovi nei repository
 * ogni caso sottende a un algoritmo e a una logica particolari
 * tutte le eccezioni sono gestite correttamente tranne quelle da dipendenze mancanti
 * che per definizione devono mandare in crash il programma
 * per divertimento ho anche fatto un po' di grafica... ho creato una classe menu per la stampa dei menù
 *
 * TODO sistemare ed estendere i casi nel menu
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
		}
		while (!cmd.equals(Menu.QUITCOMMAND));
	}

	private static void execute(String cmd)
	{
		switch (cmd)
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
				// metodo 3: fatto da me
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
				// metodo 7: fatto da me
				PatientController.printPatientFull();
			break;
			case Menu.PRINTPRESTATIONBYID:
				// metodo 8: fatto da voi
				PrestationController.printPrestationById();
			break;
			case Menu.PRINTINCOMEDAYBYDAY:
				// metodo 9: fatto da me
				PrestationController.printEarningDayByDay();
			break;
			case Menu.PRINTINCOMEBYSERVICETYPE:
				// metodo 10: fatto da voi
				PrestationController.printEarningByServiceName();
			break;
			case Menu.PRINTDISTINCTPATIENTSFORPERIOD:
				// metodo 11: fatto da me
				PrestationController.printDistinctPatientsForPeriod();
			break;
			case Menu.PRINTDISTINCTSERVICESFORPERIOD:
				// metodo 12: fatto da voi
				PrestationController.printDistinctServiceForPeriod();
			break;
			case Menu.INSERTHEALTHSERVICE:
				// metodo 13: inserimento nuovo servizio (no duplicati per nome)
				HealthServiceController.insertHealthService();
			break;
			case Menu.UPDATESERVICEPRICE:
				// metodo 14: modifica del prezzo di un servizio (selezione da lista e salvataggio)
				HealthServiceController.updateServicePrice();
			break;
			case Menu.INSERTPATIENT:
				// metodo 15: inserimento nuovo paziente (no duplicati per codice fiscale)
				PatientController.insertPatient();
			break;
			case Menu.DELETEPRESTATION:
				// metodo 16: cancellazione prestazione per id (no cancellazione passato, chiedere conferma)
				PrestationController.deletePrestation();
			break;
			case Menu.PRINTPATIENTHTML:
				// metodo 17: stampa scheda HTML di un paziente (opzionale includere prestazioni)
				PatientController.printPatientHTML();
			break;
			case Menu.PRINTPRESTATIONHTML:
				// metodo 18: stampa scheda HTML di una prestazione (includere dati paziente e servizio)
				PrestationController.printPrestationHTML();
			break;
			case Menu.PRINTTODAYPRESTATIONSHTML:
				// metodo 19: stampa HTML prestazioni previste per oggi (con logo Omega Clinic in cima)
				PrestationController.printTodayPrestationsHTML();
			break;
			default:
				Console.print("Bad command");
		}
	}
}
