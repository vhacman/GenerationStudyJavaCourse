package com.generation.emergency.model.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * ANNOTAZIONE @Service:
 * - Crea un oggetto SINGLETON di questa classe (una sola istanza)
 * - Lo registra nel Context di Spring (contenitore di dipendenze)
 * - Può essere iniettato automaticamente con @Autowired
 */
@Service
public class FakeQuoteRepository
{
    /** Lista di citazioni memorizzate in memoria */
    List<String> quotes = new ArrayList<String>();

    /**
     * COSTRUTTORE: Inizializza la lista con citazioni predefinite
     * Viene chiamato UNA SOLA VOLTA quando Spring crea il Singleton
     */
    public FakeQuoteRepository()
    {
        quotes.add("Fa' niente - Leonardo Scrima");
        quotes.add("Non ce l'ha - Veronica Perrella");
        quotes.add("Boh, non lo so - Chiara Foniciello");
        quotes.add("L'ossessione batte il talento e lo batterà sempre - Giovanni Pacchiarotti");
        quotes.add("Non me ne frega - Carlo M. Ianna");
        quotes.add("Quella di prima - Gabriela");
        quotes.add("No so neanche... mi sente? - Mousum");
        quotes.add("Sono veramente content* che non avrò mai figli - Jojo");
        quotes.add("Inutile! Inutile! Inutile! - Dio Brando");
        quotes.add("Siamo vivi per usarci - Bryton Junior Kengne Kamte");
        quotes.add("Speravo de morì prima - Ferdinando");
    }

    /**
     * Restituisce una citazione casuale dalla lista
     * Math.random() genera un numero decimale tra 0.0 e 0.999...
     * Moltiplicato per quotes.size() otteniamo un indice valido
     * Il cast (int) tronca la parte decimale
     */
    public String getRandomQuote()
    {
        int n = (int)(Math.random() * quotes.size());
        return quotes.get(n);
    }
}