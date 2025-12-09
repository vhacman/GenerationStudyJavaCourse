package com.generation.cyphar.cesar;

import com.generation.library.Console;

public class Main {

	public static void main(String[] args) {
		String	 cmd;
		String 	msg;
		int 	k;
		
		do {
// dalla riga 13 alla riga 40 viene detto CORPO DEL CICLO			
			Console.print(" Inserire comando C per cifrare, D per decifrare, Q per uscire");
			cmd = Console.readString();
			switch (cmd)
			{ // switch significa in base di valore di cmd fai un c
				case "C" :
					Console.print("Inserire messaggio da cifrare");
					msg = Console.readString();
					Console.print("Inserire k");
					k = Console.readInt();
					Console.print("Risultato: ");
					Console.print(CaesarCypher.cypher(msg, k));
				break;
				
				case "D":
					Console.print("Inserire messaggio da decifrare ");
					msg = Console.readString();
					Console.print("Inserire k");
					k = Console.readInt();
					Console.print("Risultato: ");
					Console.print(CaesarCypher.decypher(msg, k));
				break;
				
				case "Q" :
					Console.print("Bye bye");
				break;
				
				default:
					Console.print("Comando non riconosciuto");
			}	
		}
		while(!cmd.equals("Q"));
		// la riga 41 è la fine del programma quindi quando esco dal ciclo il programma finisce 
	}
	

}
