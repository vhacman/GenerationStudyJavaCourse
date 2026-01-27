package com.generation.acmc2.controller;

import java.sql.SQLException;

import com.generation.acmc2.context.Context;
import com.generation.acmc2.model.entities.Member;
import com.generation.acmc2.repository.MemberRepository;
import com.generation.library.Console;

public class ProceduraAggiornamentoStatoAutomatico 
{

	private final static MemberRepository memberRepo = 
			Context.getDependency(MemberRepository.class);
	
			
	
	public static void main(String[] args)
	{
		try
		{
			Console.print("Inserire id membro");
			int id = Console.readInt();					// CALDA, mi manda al secondo catch se da eccezione
			Member m = memberRepo.findById(id);	// riga calda, mi può mandare al primo catch
			if(m!=null)
			{
				Console.print(m.getFirstName()+" "+m.getLastName());
				if(m.getStatus()!=m.getCalculatedStatus())
				{
					 Console.print("Lo stato attuale differisce da quello registrato");
					 Console.print("Stato calcolato "+m.getCalculatedStatus());
					 Console.print("Donazioni ultimo anno "+m.getLastYearDonations());
					 Console.print("Stato registrato "+m.getStatus());
					 Console.print("Si vuole correggere?");
					 if(Console.readString().equals("S"))
					 {
						 m.setStatus(m.getCalculatedStatus());
						 memberRepo.update(m);
						 Console.print("Aggiornato");
					 }
				}
			}
			else
				Console.print("Non trovato");
		}
		catch(SQLException e)
		{
			// c'è stata una eccezione in lettura dal d
			// grave, gravissimo
			// perché non mi aspettavo che il db desse errore
			e.printStackTrace(); // dettaglio dell'eccezione
			// con elenco di tutte le chiamate a metodo che la hanno causata
		}
		catch(NumberFormatException e)
		{
			Console.print("Valore numerico non valido");
		}
	}
	
}
