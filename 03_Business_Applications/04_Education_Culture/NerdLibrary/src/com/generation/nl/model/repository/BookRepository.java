package com.generation.nl.model.repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.generation.library.FileReader;
import com.generation.library.FileWriter;
import com.generation.nl.model.entities.Book;
import com.generation.nl.model.entities.ComicBook;

/**
 * Repository per la gestione della persistenza di libri e fumetti.
 *
 * Responsabilità:
 * - Caricare libri e fumetti dal file system in base all'ISBN
 * - Salvare libri e fumetti su file system
 * - Gestire automaticamente il tipo corretto (Book o ComicBook) durante il caricamento
 *
 * Pattern di persistenza:
 * - Un file per ogni libro/fumetto
 * - Nome file: ISBN.txt
 * - Formato file: prima riga indica il tipo (BOOK o COMICBOOK), righe successive contengono i dati
 * - Path di archivio: archive/books/
 */
public class BookRepository
{
	// ==================== ATTRIBUTI ====================
	final String	archivePath = "archive/books/";

	// ==================== METODI PER LISTA COMPLETA ====================

	/**
	 * Carica tutti i libri e fumetti presenti nell'archivio.
	 * Scansiona la directory archive/books/ e carica ogni file .txt trovato.
	 *
	 * @return array di Book (contenente sia Book che ComicBook)
	 */
	public Book[] loadAll()
	{
		File folder = new File(archivePath);

		// Debug: verifica se la cartella esiste
		if (!folder.exists())
		{
			System.out.println("ERRORE: La cartella " + archivePath + " non esiste!");
			System.out.println("Path assoluto: " + folder.getAbsolutePath());
			return new Book[0];
		}

		File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

		if (files == null || files.length == 0)
		{
			System.out.println("ERRORE: Nessun file trovato in " + archivePath);
			return new Book[0];
		}

		System.out.println("Trovati " + files.length + " file in " + archivePath);

		// Lista temporanea per raccogliere i libri validi
		ArrayList<Book> bookList = new ArrayList<>();

		for (File file : files)
		{
			// Estrae l'ISBN dal nome del file (senza .txt)
			String isbn = file.getName().replace(".txt", "");
			System.out.println("Caricamento: " + isbn);
			Book b = load(isbn);
			if (b != null)
				bookList.add(b);
		}

		System.out.println("Caricati " + bookList.size() + " libri/fumetti");

		// Converte ArrayList in array
		return bookList.toArray(new Book[0]);
	}

	// ==================== METODI DI CARICAMENTO ====================

	/**
	 * Carica un libro o fumetto dal file system dato l'ISBN.
	 * Legge il file corrispondente e ricostruisce l'oggetto appropriato
	 * (Book o ComicBook) in base al tipo specificato nel file.
	 *
	 * Formato file atteso:
	 * - Prima riga: "BOOK" o "COMICBOOK"
	 * - Righe successive: dati del libro/fumetto in ordine specifico
	 *
	 * @param isbn ISBN del libro da caricare (come stringa)
	 * @return oggetto Book o ComicBook se trovato, null se non trovato o in caso di errore
	 */
	public Book load(String isbn)
	{
		String 	filename = archivePath + isbn + ".txt";

		try
		{
			FileReader	reader 		= new FileReader(filename);
			String 		firstRow 	= reader.readString();

			switch (firstRow)
			{
			case "BOOK":
				// Ricostruisce un oggetto Book leggendo i dati in ordine:
				// id, isbn, title, numberOfPages, author, price, stockQuantity, genre
				Book book = new Book(
							reader.readInt(),
							reader.readString(),
							reader.readString(),
							reader.readInt(),
							reader.readString(),
							reader.readDouble(),
							reader.readInt(),
							reader.readString()
							);
				reader.close();
				return book;

			case "COMICBOOK":
				// Ricostruisce un oggetto ComicBook leggendo i dati in ordine:
				// id, isbn, title, numberOfPages, author, artist, price, genre, stockQuantity, isColor
				ComicBook comicBook = new ComicBook(
						reader.readInt(),
						reader.readString(),
						reader.readString(),
						reader.readInt(),
						reader.readString(),
						reader.readString(),
						reader.readDouble(),
						reader.readString(),
						reader.readInt(),
						reader.readInt() == 1  // Converte 1/0 in true/false
						);
				reader.close();
				return comicBook;

			}
		}
		catch (FileNotFoundException e)
		{
			// File non trovato - libro non esiste
			e.printStackTrace();
			return null;
		}
		catch (Exception e)
		{
			// Errore durante la lettura o parsing dei dati
			e.printStackTrace();
			return null;
		}

		// Tipo di libro non riconosciuto
		return null;
	}

	// ==================== METODI DI SALVATAGGIO ====================
	/**
	 * Salva un libro o fumetto su file system.
	 * Utilizza il pattern instanceof per determinare il tipo corretto
	 * e delegare al metodo di salvataggio specifico.
	 *
	 * @param b libro o fumetto da salvare
	 * @return true se il salvataggio è riuscito, false altrimenti
	 */
	public boolean save(Book b)
	{
		if (b == null)
			return false;

		// Delega al metodo specifico in base al tipo runtime
		if (b instanceof ComicBook)
			return saveComicBook((ComicBook)b);
		else
			return saveBook(b);
	}

	/**
	 * Salva un libro standard su file.
	 * Formato file:
	 * - Riga 1: "BOOK"
	 * - Righe successive: id, isbn, title, numberOfPages, author, price, stockQuantity, genre
	 *
	 * @param b libro da salvare
	 * @return true se salvato con successo, false in caso di errore
	 */
	private boolean saveBook(Book b)
	{
		try
		{
			String filename = archivePath + b.getIsbn() + ".txt";
			FileWriter writer = new FileWriter(filename);

			// Scrive il tipo e tutti i dati del libro, uno per riga
			writer.print("BOOK\n");
			writer.print(b.getId() 				+ "\n");
			writer.print(b.getIsbn() 			+ "\n");
			writer.print(b.getTitle() 			+ "\n");
			writer.print(b.getNumberOfPages() 	+ "\n");
			writer.print(b.getAuthor() 			+ "\n");
			writer.print(b.getPrice() 			+ "\n");
			writer.print(b.getStockQuantity() 	+ "\n");
			writer.print(b.getGenre());

			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Salva un fumetto su file.
	 * Formato file:
	 * - Riga 1: "COMICBOOK"
	 * - Righe successive: id, isbn, title, numberOfPages, author, artist, price, genre, stockQuantity, isColor (1/0)
	 *
	 * @param c fumetto da salvare
	 * @return true se salvato con successo, false in caso di errore
	 */
	private boolean saveComicBook(ComicBook c)
	{
		try
		{
			String filename = archivePath + c.getIsbn() + ".txt";
			FileWriter writer = new FileWriter(filename);

			// Scrive il tipo e tutti i dati del fumetto, uno per riga
			writer.print("COMICBOOK\n");
			writer.print(c.getId() 				+ "\n");
			writer.print(c.getIsbn() 			+ "\n");
			writer.print(c.getTitle() 			+ "\n");
			writer.print(c.getNumberOfPages() 	+ "\n");
			writer.print(c.getAuthor() 			+ "\n");
			writer.print(c.getArtist() 			+ "\n");
			writer.print(c.getPrice() 			+ "\n");
			writer.print(c.getGenre() 			+ "\n");
			writer.print(c.getStockQuantity() 	+ "\n");
			writer.print(c.isColor() ? 1 : 0);  // Converte true/false in 1/0

			writer.close();
			return true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
	}

}
