package com.generation.nl.view;

import com.generation.library.Console;
import com.generation.library.Template;
import com.generation.nl.model.entities.Book;
import com.generation.nl.model.entities.ComicBook;

/**
 * View per la gestione della visualizzazione di libri e fumetti.
 *
 * Responsabilità:
 * - Renderizzare oggetti Book e ComicBook usando template
 * - Gestire il polimorfismo tra Book e le sue sottoclassi
 * - Separare la logica di presentazione dalla logica di business
 *
 * Pattern MVC: questa classe rappresenta la View, separando la presentazione
 * dalla logica di business (Model) e dal controllo (Controller).
 */
public class LibraryView
{
	// ==================== ATTRIBUTI ====================
	String templateFolder;

	// ==================== COSTRUTTORI ====================

	/**
	 * Costruisce una LibraryView specificando la cartella dei template.
	 *
	 * @param templateFolder path della cartella contenente i template
	 */
	public LibraryView(String templateFolder)
	{
		this.templateFolder = templateFolder;
	}

	// ==================== METODI DI RENDERING ====================

	/**
	 * POLIMORFISMO DI OGGETTO
	 *
	 * Il parametro b è POLIMORFICO - può assumere forme diverse a runtime.
	 *
	 * Entra un Book, ma potrebbe essere:
	 * - un Book "standard"
	 * - un ComicBook
	 * - qualsiasi sottoclasse futura di Book!
	 *
	 * In base al tipo EFFETTIVO dell'oggetto, lo trattiamo in maniera diversa.
	 *
	 * IMPORTANTE:
	 * - Conosciamo solo il tipo FORMALE (Book) - quello della variabile
	 * - NON conosciamo il tipo CONCRETO/EFFETTIVO - quello dell'oggetto
	 * - Usiamo instanceof per scoprire il tipo effettivo a runtime
	 *
	 * Questo è POLIMORFISMO: una variabile, molte forme possibili!
	 *
	 * @param b variabile polimorfica di tipo Book
	 * @return rappresentazione in base al tipo effettivo
	 */
	public String render(Book b)
	{
		// Controllo il tipo EFFETTIVO di b a runtime
		if (b instanceof ComicBook)      // b è davvero un ComicBook?
			return renderComicBook(b);
		return renderBook(b);            // altrimenti è un Book standard
	}

	/**
	 * Renderizza un Book usando il template book.txt
	 * Legge il template e sostituisce i placeholder con i dati effettivi
	 *
	 * @param b libro da renderizzare
	 * @return stringa formattata con i dati del libro
	 */
	private String renderBook(Book b)
	{
		return Template
				.load(templateFolder + "/book.txt")
				.replace("[id]", String.valueOf(b.getId()))
				.replace("[isbn]", b.getIsbn())
				.replace("[title]", b.getTitle())
				.replace("[numberOfPages]", String.valueOf(b.getNumberOfPages()))
				.replace("[author]", b.getAuthor())
				.replace("[price]", String.format("€%.2f", b.getPrice()))
				.replace("[stockQuantity]", String.valueOf(b.getStockQuantity()))
				.replace("[genre]", b.getGenre());
	}

	/**
	 * Renderizza un ComicBook usando il template comicbook.txt
	 * POLIMORFISMO: riceve Book ma fa downcasting a ComicBook
	 *
	 * @param b libro da renderizzare (che è effettivamente un ComicBook)
	 * @return stringa formattata con i dati del fumetto
	 */
	private String renderComicBook(Book b)
	{
		/*
		 * DOWNCASTING: "Cambio pigiamino" all'oggetto puntato da b
		 *
		 * b è di tipo Book (tipo formale), ma punta a un oggetto ComicBook (tipo effettivo).
		 * Con il cast (ComicBook) diciamo al compilatore: "Fidati, so che dentro c'è un ComicBook!"
		 *
		 * Possiamo fare downcasting SOLO perché ComicBook è sottoclasse di Book.
		 * Questo ci permette di accedere ai metodi specifici di ComicBook (getArtist, isColor).
		 *
		 * ATTENZIONE: Se b non fosse davvero un ComicBook, otterremmo ClassCastException a runtime!
		 * Per questo prima usiamo instanceof nel metodo render().
		 */
		ComicBook comic = (ComicBook) b;
		return Template
				.load(templateFolder + "/comicbook.txt")
				.replace("[id]", 				String.valueOf(comic.getId()))
				.replace("[isbn]",				comic.getIsbn())
				.replace("[title]", 			comic.getTitle())
				.replace("[numberOfPages]", 	String.valueOf(comic.getNumberOfPages()))
				.replace("[author]", 			comic.getAuthor())
				.replace("[artist]", 			comic.getArtist())
				.replace("[price]", 			String.format("€%.2f", comic.getPrice()))
				.replace("[stockQuantity]", 	String.valueOf(comic.getStockQuantity()))
				.replace("[genre]", 			comic.getGenre())
				.replace("[isColor]", 			comic.isColor() ? "A colori" : "Bianco e nero")
				.replace("[colorIcon]",			comic.isColor() ? "🎨" : "⬛");
	}

	/**
	 * Stampa a console la rappresentazione di un libro/fumetto
	 *
	 * @param b libro da stampare
	 */
	public void	print(Book b)
	{
		Console.print(render(b));
	}

	/**
	 * Stampa un messaggio generico a console
	 *
	 * @param message messaggio da stampare
	 */
	public void print(String message)
	{
		Console.print(message);
	}

	/**
	 * Stampa il menu principale dell'applicazione caricando il template menu.txt
	 */
	public void	printMenu()
	{
		String 	menu = Template.load(templateFolder + "/menu.txt");
		Console.print(menu);
	}

}
