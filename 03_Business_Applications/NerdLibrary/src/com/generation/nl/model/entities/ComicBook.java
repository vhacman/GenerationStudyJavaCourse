package com.generation.nl.model.entities;

/**
 * Classe che rappresenta un fumetto (comic book) nella libreria Nerd Library.
 * Estende la classe Book aggiungendo attributi specifici per i fumetti:
 * - disegnatore (artist)
 * - informazione se è a colori o in bianco e nero
 *
 * Eredita tutti gli attributi e i metodi della classe padre Book,
 * implementando validazione specifica per i dati aggiuntivi.
 */
public class ComicBook extends Book
{
	// ==================== ATTRIBUTI SPECIFICI ====================
	private	boolean	isColor;
	private String 	artist;

	// ==================== GETTERS E SETTERS ====================
	public String 	getArtist() 				{return artist;}
	public void 	setArtist(String artist) 	{this.artist = artist;}
	public boolean 	isColor() 					{return isColor;}
	public void 	setColor(boolean isColor) 	{this.isColor = isColor;}

	// ==================== COSTRUTTORI ====================

	/**
	 * Costruttore vuoto necessario per la deserializzazione
	 */
	public 	ComicBook() {}

	/**
	 * Costruttore completo per creare un fumetto con tutti i dati necessari.
	 * Valida automaticamente i parametri specifici del fumetto.
	 *
	 * @param id Identificativo univoco
	 * @param isbn Codice ISBN (come stringa)
	 * @param title Titolo del fumetto
	 * @param numberOfPages Numero di pagine
	 * @param author Autore/sceneggiatore
	 * @param artist Disegnatore
	 * @param price Prezzo
	 * @param genre Genere (es. Supereroi, Manga, Fantasy)
	 * @param stockQuantity Quantità in magazzino
	 * @param isColor true se a colori, false se b/n
	 * @throws IllegalArgumentException se i dati non sono validi
	 */
	public	ComicBook(int id, String isbn, String title, int numberOfPages,
					String author, String artist, double price, String genre,
					int stockQuantity, boolean isColor)
	{
		// Chiama il costruttore della classe padre per validare e inizializzare i dati comuni
		super(id, isbn, title, numberOfPages, author, price, stockQuantity, genre);

		// Validazione specifica per il fumetto
		if (!isValidArtist(artist))
			throw new IllegalArgumentException("Invalid comic book data");

		this.artist = artist;
		this.isColor = isColor;
	}

	// ==================== METODI EREDITATI ====================

	/**
	 * Restituisce una rappresentazione testuale del fumetto.
	 * Riutilizza il toString() della classe padre aggiungendo i campi specifici.
	 *
	 * Formato: ComicBook{id=1, isbn=123456, ..., artist='...', isColor=true}
	 *
	 * @return stringa che rappresenta il fumetto
	 */
	@Override
	public String	toString() {
		return super.toString().replace("Book{", "ComicBook{").replace("}",
										", artist='" + artist + '\'' +
										", isColor=" + isColor + '}');
	}

	// ==================== METODI DI VALIDAZIONE ====================

	/**
	 * Valida il nome del disegnatore.
	 * Verifica che non sia nullo e non sia una stringa vuota.
	 *
	 * @param artist nome del disegnatore da validare
	 * @return true se valido, false altrimenti
	 */
	private boolean isValidArtist(String artist) {
		return artist != null && !artist.trim().isEmpty();
	}

}
