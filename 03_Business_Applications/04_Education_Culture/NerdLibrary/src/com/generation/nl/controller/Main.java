package com.generation.nl.controller;

import com.generation.library.Console;
import com.generation.nl.model.entities.Book;
import com.generation.nl.model.entities.ComicBook;
import com.generation.nl.model.repository.BookRepository;
import com.generation.nl.view.LibraryView;

/**
 * Controller principale dell'applicazione Nerd Library.
 *
 * Gestisce l'interfaccia utente a console e coordina le operazioni
 * tra l'utente e il repository dei libri/fumetti.
 *
 * Pattern MVC:
 * - Model: Book, ComicBook, BookRepository
 * - View: LibraryView
 * - Controller: Main (questa classe)
 *
 * Comandi disponibili:
 * - CARICA: Carica e visualizza un libro/fumetto per ISBN
 * - AGGIUNGI_LIBRO: Aggiunge un nuovo libro all'archivio
 * - AGGIUNGI_FUMETTO: Aggiunge un nuovo fumetto all'archivio
 * - VENDI: Vende una copia di un libro (decrementa quantità)
 * - Q: Esce dal programma
 */
public class Main
{
	// ==================== ATTRIBUTI ====================
	private static BookRepository 	bookRepo 	= new BookRepository();
	private static LibraryView 		view 		= new LibraryView("templates");

	// ==================== MAIN ====================

	/**
	 * Metodo principale che avvia l'applicazione.
	 * Implementa un ciclo di lettura-esecuzione-stampa (REPL)
	 * che continua finché l'utente non inserisce il comando "Q".
	 *
	 * @param args argomenti da linea di comando (non utilizzati)
	 */
	public static void main(String[] args)
	{
		// Benvenuto all'utente
		view.print("Hey ciao! Tu non sembri avere una vita!");
		view.print("Benvenuto a Nerd Library!");
		String cmd;

		// Loop principale del programma
		do
		{
			view.printMenu();
			cmd = Console.readString();

			// Dispatch dei comandi alle funzioni appropriate
			switch(cmd)
			{
				case "CARICA":
					loadBook();
				break;
				case "AGGIUNGI_LIBRO":
					addBook();
				break;
				case "AGGIUNGI_FUMETTO":
					addComicBook();
				break;
				case "VENDI":
					sellBook();
				break;
			}

		}while(!cmd.equals("Q"));

	}
	/**
	 * METODO FORNITO DAL DOCENTE - NON MODIFICABILE
	 *
	 * Gestisce la vendita di un libro.
	 * Operazioni:
	 * 1. Chiede l'ISBN all'utente
	 * 2. Carica il libro dal repository
	 * 3. Verifica che esista e che ci siano copie disponibili
	 * 4. Decrementa la quantità di 1
	 * 5. Salva le modifiche su file
	 */
	private static void sellBook()
	{
		view.print("Inserire isbn");
		String 	isbn = Console.readString();
		Book	b = bookRepo.load(isbn);

		if(b == null)
		{
			view.print("Non trovato");
			return;
		}

		if(b.getQuantity()>0)
		{
			b.decreaseQuantity(1); 	// Modifica in RAM
			bookRepo.save(b);		// Persiste su file
			view.print("Vendita completata con successo!");
			view.print(b);
		}
		else
			view.print("Non ci sono copie in magazzino");
	}

	/**
	 * Carica e visualizza un libro o fumetto dal repository.
	 * Chiede l'ISBN all'utente, tenta di caricare il libro
	 * e ne mostra i dettagli se trovato.
	 */
	private static void loadBook()
	{
		view.print("Inserire isbn");
		String 		isbn 	= Console.readString();
		Book 		b 		= bookRepo.load(isbn);

		if(b == null)
		{
			view.print("Libro non trovato");
			return;
		}

		// Mostra i dettagli del libro usando il template
		view.print(b);
	}

	/**
	 * Aggiunge un nuovo libro all'archivio.
	 * Procedura:
	 * 1. Raccoglie tutti i dati necessari dall'utente
	 * 2. Crea un oggetto Book (con validazione automatica)
	 * 3. Salva il libro su file tramite il repository
	 * 4. Gestisce eventuali errori di validazione
	 */
	private static void addBook()
	{
		// Raccolta dati dall'utente
		view.print("Inserire id");
		int 		id 				= Console.readInt();
		view.print("Inserire isbn");
		String 		isbn 			= Console.readString();
		view.print("Inserire titolo");
		String 		title 			= Console.readString();
		view.print("Inserire numero di pagine");
		int 		numberOfPages 	= Console.readInt();
		view.print("Inserire autore");
		String 		author 			= Console.readString();
		view.print("Inserire prezzo");
		double 		price 			= Console.readDouble();
		view.print("Inserire quantità in magazzino");
		int 		stockQuantity 	= Console.readInt();
		view.print("Inserire genere");
		String 		genre 			= Console.readString();

		try
		{
			// Crea e salva il libro (il costruttore valida automaticamente)
			Book b = new Book(
								id,
								isbn,
								title,
								numberOfPages,
								author,
								price,
								stockQuantity,
								genre
							);
			if(bookRepo.save(b))
			{
				view.print("Libro salvato con successo");
				view.print(b);
			}
			else
				view.print("Errore durante il salvataggio");
		}
		catch(IllegalArgumentException e)
		{
			// Gestisce errori di validazione
			view.print("Dati non validi: " + e.getMessage());
		}
	}

	/**
	 * Aggiunge un nuovo fumetto all'archivio.
	 * Procedura:
	 * 1. Raccoglie tutti i dati necessari dall'utente (inclusi artist e isColor)
	 * 2. Crea un oggetto ComicBook (con validazione automatica)
	 * 3. Salva il fumetto su file tramite il repository
	 * 4. Gestisce eventuali errori di validazione
	 */
	private static void addComicBook()
	{
		// Raccolta dati dall'utente
		view.print("Inserire id");
		int 		id 				= Console.readInt();
		view.print("Inserire isbn");
		String 		isbn 			= Console.readString();
		view.print("Inserire titolo");
		String 		title 			= Console.readString();
		view.print("Inserire numero di pagine");
		int 		numberOfPages 	= Console.readInt();
		view.print("Inserire autore");
		String 		author 			= Console.readString();
		view.print("Inserire disegnatore");
		String 		artist 			= Console.readString();
		view.print("Inserire prezzo");
		double 		price 			= Console.readDouble();
		view.print("Inserire genere");
		String 		genre 			= Console.readString();
		view.print("Inserire quantità in magazzino");
		int 		stockQuantity 	= Console.readInt();
		view.print("A colori? (S/N)");
		String 		colorInput 		= Console.readString();
		boolean 	isColor 		= colorInput.equalsIgnoreCase("S");

		try
		{
			// Crea e salva il fumetto (il costruttore valida automaticamente)
			ComicBook c = new ComicBook(
											id, isbn, title, numberOfPages,
											author, artist, price, genre,
											stockQuantity, isColor
										);
			if(bookRepo.save(c))
			{
				view.print("Fumetto salvato con successo");
				view.print(c);
			}
			else
				view.print("Errore durante il salvataggio");
		}
		catch(IllegalArgumentException e)
		{
			// Gestisce errori di validazione
			view.print("Dati non validi: " + e.getMessage());
		}
	}

}
