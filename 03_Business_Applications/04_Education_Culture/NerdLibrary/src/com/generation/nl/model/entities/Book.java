package com.generation.nl.model.entities;

/**
 * Classe che rappresenta un libro nella libreria Nerd Library.
 * Contiene tutte le informazioni necessarie per gestire un libro:
 * identificativo, dati bibliografici, prezzo e disponibilità in magazzino.
 *
 * La classe implementa validazione dei dati per garantire l'integrità
 * delle informazioni memorizzate.
 */
public class Book
{
	// ==================== ATTRIBUTI ====================

	private int			id;
	private	String		isbn;
	private String		title;
	private	int			numberOfPages;
	private	String		author;
	private	double		price;
	private	int			stockQuantity;
	private	String		genre;

	// ==================== COSTRUTTORI ====================

	/**
	 * Costruttore vuoto necessario per la deserializzazione
	 */
	public		Book() {}

	/**
	 * Costruttore completo che crea un libro con tutti i dati necessari.
	 * Valida automaticamente i parametri prima di creare l'oggetto.
	 *
	 * @param id Identificativo univoco
	 * @param isbn Codice ISBN (come stringa)
	 * @param title Titolo del libro
	 * @param numberOfPages Numero di pagine
	 * @param author Autore
	 * @param price Prezzo
	 * @param stockQuantity Quantità in magazzino
	 * @param genre Genere letterario
	 * @throws IllegalArgumentException se i dati non sono validi
	 */
	public		Book(int id, String isbn,
					String title, int numberOfPages,
					String author, double price,
					int stockQuantity, String genre)
	{
		// Validazione dei dati prima della creazione
		if (!isValid(isbn, title, numberOfPages, author, price, stockQuantity, genre))
			throw new IllegalArgumentException("Invalid book data: cannot create book");

		this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.author = author;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.genre = genre;
	}

	// ==================== GETTERS ====================

	public int 			getId() 				{ return id; }
	public String 		getIsbn() 				{ return isbn; }
	public String 		getTitle() 				{ return title; }
	public int 			getNumberOfPages() 		{ return numberOfPages; }
	public String 		getAuthor() 			{ return author; }
	public double 		getPrice() 				{ return price; }
	public int 			getStockQuantity() 		{ return stockQuantity; }
	public String 		getGenre() 				{ return genre; }

	// ==================== SETTERS ====================

	public void 		setId(int id) 						{ this.id = id; }
	public void 		setIsbn(String isbn) 				{ this.isbn = isbn; }
	public void 		setTitle(String title) 				{ this.title = title; }
	public void 		setNumberOfPages(int numberOfPages) { this.numberOfPages = numberOfPages; }
	public void 		setAuthor(String author) 			{ this.author = author; }
	public void 		setPrice(double price) 				{ this.price = price; }
	public void 		setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
	public void 		setGenre(String genre) 				{ this.genre = genre; }

	/**
	 * Metodo alias per getStockQuantity().
	 * Fornito per compatibilità con il codice del Main.
	 *
	 * @return la quantità disponibile in magazzino
	 */
	public int 			getQuantity() 						{ return getStockQuantity(); }

	/**
	 * Diminuisce la quantità in magazzino del valore specificato.
	 * Utilizzato durante la vendita di un libro.
	 *
	 * @param amount quantità da sottrarre
	 */
	public void 		decreaseQuantity(int amount) 		{ this.stockQuantity -= amount; }

	// ==================== METODI DI VALIDAZIONE ====================

	/**
	 * Valida tutti i dati di un libro prima della creazione.
	 * Verifica che:
	 * - L'ISBN non sia nullo o vuoto
	 * - Il titolo non sia nullo o vuoto
	 * - Il numero di pagine sia positivo
	 * - L'autore non sia nullo o vuoto
	 * - Il prezzo sia non negativo
	 * - La quantità in stock sia non negativa
	 * - Il genere non sia nullo o vuoto
	 *
	 * @param isbn Codice ISBN da validare
	 * @param title Titolo da validare
	 * @param numberOfPages Numero di pagine da validare
	 * @param author Autore da validare
	 * @param price Prezzo da validare
	 * @param stockQuantity Quantità da validare
	 * @param genre Genere da validare
	 * @return true se tutti i dati sono validi, false altrimenti
	 */
	private boolean isValid(String isbn, String title, int numberOfPages,
							String author, double price, int stockQuantity, String genre)
	{
		return isbn != null					&&
				!isbn.trim().isEmpty()		&&
				title != null 				&&
				!title.trim().isEmpty()		&&
				numberOfPages > 0			&&
				author != null				&&
				!author.trim().isEmpty() 	&&
				price >= 0					&&
				stockQuantity >= 0			&&
				genre != null				&&
				!genre.trim().isEmpty();

	}

	// ==================== METODI EREDITATI ====================

	/**
	 * Restituisce una rappresentazione testuale del libro.
	 * Formato: {id=1, isbn=123456, title='...', numberOfPages=200, ...}
	 *
	 * @return stringa che rappresenta il libro
	 */
	@Override
	public String	toString()
	{
		return
			"{" 		+
            "id=" 			+ id		+
            ", isbn=" 		+ isbn 		+
            ", title='" 	+ title 	+ '\'' +
            ", numberOfPages=" 			+ numberOfPages +
            ", author='" 	+ author 	+ '\'' +
            ", price="	 	+ price 	+
            ", stockQuantity=" 			+ stockQuantity +
            ", genre='" 				+ genre + '\'' +
            '}';

	}
}
