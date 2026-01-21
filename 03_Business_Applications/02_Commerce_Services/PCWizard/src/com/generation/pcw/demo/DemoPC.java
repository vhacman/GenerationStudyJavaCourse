package com.generation.pcw.demo;
import com.generation.pcw.util.Console;
import com.generation.pcw.model.entities.PC;
import com.generation.pcw.model.entities.Processor;
import com.generation.pcw.model.entities.Storage;
/**
 * Classe demo per testare la creazione e configurazione di un PC completo.
 * Il programma crea un'istanza di PC, richiede all'utente di inserire i componenti
 * (processore, storage primario e storage secondario) e calcola il costo totale finale.
 */
public class DemoPC
{
	public static void main(String[] args)
	{
		PC p = new PC();
		p.setProcessor(askProcessor());
		p.setStorage(askStorage());
		
		Console.print("Si vuole un extra storage?");
		if(Console.readString().equalsIgnoreCase("S"));
			p.setExtraStorage(askStorage());
		Console.print("Il costo finale del suo progetto è " + PC.calculateMaterialCost(p));
	}
	/**
	 * Metodo privato e statico che richiede all'utente di inserire uno storage valido.
	 * Il metodo continua a chiedere l'input finché non viene inserito un valore valido dell'enum Storage.
	 * Utilizza un ciclo do-while per garantire almeno un tentativo di input.
	 * In caso di errore (storage non valido), visualizza un messaggio e richiede nuovamente l'input.
	 * Mostra i valori disponibili prima di richiedere l'input.
	 *
	 * @return Lo storage validato inserito dall'utente
	 */
	private static Storage askStorage()
	{
		Storage res = null;
		do
		{
			try
			{
				Console.print("Storage disponibili: SSDBASIC, SSDLARGE, SSDSCAMOSCIUS");
				Console.print("Inserire storage: ");
				res = Storage.valueOf(Console.readString());
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res==null);
		return res;
	}
	/**
	 * Metodo privato e statico che richiede all'utente di inserire un processore valido.
	 * Il metodo continua a chiedere l'input finché non viene inserito un valore valido dell'enum Processor.
	 * Utilizza un ciclo do-while per garantire almeno un tentativo di input.
	 * In caso di errore (processore non valido), visualizza un messaggio e richiede nuovamente l'input.
	 * Mostra i valori disponibili prima di richiedere l'input.
	 *
	 * @return Il processore validato inserito dall'utente
	 */
	private static Processor askProcessor()
	{
		Processor res = null;
		do
		{
			try
			{
				Console.print("Processori disponibili: M1, M4, I714, I515");
				Console.print("Inserire processore: ");
				res = Processor.valueOf(Console.readString());
			}
			catch(Exception e)
			{
				Console.print("Valore non valido");
			}
		}while(res==null);
		return res;
	}
}