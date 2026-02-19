package com.generation.emergency.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.generation.emergency.model.repository.FakeQuoteRepository;

/**
 * REST CONTROLLER: Endpoint API che restituisce dati grezzi (non pagine HTML)
 */
@RestController
public class QuoteAPI
{
    /**
     * DEPENDENCY INJECTION: @Autowired inietta automaticamente la dipendenza
     * Spring cerca nel Context un oggetto di tipo FakeQuoteRepository
     * e lo assegna automaticamente a questa variabile
     * Equivalente manuale: quoteRepo = Context.getDependency(FakeQuoteRepository.class);
     */
    @Autowired
    FakeQuoteRepository quoteRepo;

    /**
     * ENDPOINT GET: Restituisce una citazione casuale
     * @GetMapping: Mappa questo metodo a una richiesta HTTP GET
     * URL: http://localhost:8080/emergency/api/quotes/random
     * Risposta: Stringa JSON contenente una citazione casuale
     */
    @GetMapping("/emergency/api/quotes/random")
    public String getQuote()
    {
        return quoteRepo.getRandomQuote();
    }
}