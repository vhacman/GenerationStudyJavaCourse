package com.generation.gbb.etl;

/*
 * TEORIA: Factory Pattern
 * ========================
 * 
 * DEFINIZIONE:
 * Factory Method è un design pattern creazionale che fornisce un'interfaccia
 * per creare oggetti in una superclasse, delegando alle sottoclassi la decisione
 * su quale classe concreta istanziare.
 * 
 * VANTAGGI:
 * - Disaccoppiamento: client non conosce implementazioni concrete
 * - Centralizzazione: logica di creazione in un unico punto
 * - Estensibilità: aggiungere nuove implementazioni senza modificare client
 * - Testabilità: facile mock delle dipendenze
 * 
 * PATTERN APPLICATO:
 * GuestExtractorFactory decide quale implementazione di GuestExtractor
 * istanziare in base all'estensione del file (csv, json, xml).
 * 
 * STRATEGIA:
 * - Controllo estensione file con endsWith()
 * - Restituzione implementazione specifica (Strategy Pattern)
 * - Null se formato non supportato
 */

/**
 * Factory class for creating appropriate GuestExtractor implementations.
 * Selects extractor based on file extension (csv, json, xml).
 * Implements Factory Method pattern for extractor instantiation.
 */
public class GuestExtractorFactory
{
    /** Singleton CSV extractor instance for reuse. */
    static GuestExtractor extractorCSV = new GuestExtractorCSV();

    /**
     * Creates appropriate GuestExtractor based on filename extension.
     * Determines file type from extension and returns matching extractor.
     * 
     * @param filename Name of file to extract from (must include extension)
     * @return GuestExtractor implementation for file type, null if unsupported format
     */
    public static GuestExtractor make(String filename)
    {
        if (filename.endsWith(".csv"))
            return extractorCSV;
        
        return null;
    }
}
