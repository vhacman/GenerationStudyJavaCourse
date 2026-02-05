package com.generation.dinner.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.dinner.model.entities.Dinner;
import com.generation.dinner.model.repository.DinnerRepository;

/**
 * REST Controller per la gestione degli ordini di cena.
 * @RequestMapping sulla classe definisce il prefisso comune per tutti gli endpoint.
 * I metodi GET, POST, PUT poi completano l'URL relativo a quello di base.
 * I prefissi jr, js, gr sono diversi per endpoint, quindi il prefisso comune
 * sulla classe è solo "dinnerservice/api".
 *
 * Endpoint esposti:
 *   POST   /dinnerservice/api/jr/dinners          - crea un nuovo ordine
 *   GET    /dinnerservice/api/jr/dinners/{id}     - recupera un ordine per id
 *   GET    /dinnerservice/api/js/dinners/pending      - ordini pending
 *   GET    /dinnerservice/api/gr/dinners/delivered    - ordini delivered
 *   PUT    /dinnerservice/api/jr/dinners          - aggiorna un ordine
 */
@RestController
@RequestMapping("dinnerservice/api")
public class DinnerAPI
{
    @Autowired
    private DinnerRepository repo;

    /**
     * POST: Crea un nuovo ordine di cena.
     * URL: POST /dinnerservice/api/jr/dinners
     * @RequestBody: il corpo della request contiene un oggetto Dinner in JSON.
     * Il metodo imposta automaticamente lo stato a "pending" e la mancia a 0.
     */
    @PostMapping("/jr/dinners")
    public Dinner create(@RequestBody Dinner dinner)
    {
        if (dinner.getCost() < 0) throw new RuntimeException("Costo negativo non ammesso");
        // quando si crea un nuovo ordine lo stato parte sempre da "pending"
        dinner.setStatus("pending");
        // la mancia si aggiunge solo dopo, quando l'ordine è stato consegnato
        dinner.setTip(0);

        return repo.save(dinner);
    }

    /**
     * GET: Recupera un singolo ordine per ID.
     * URL: GET /dinnerservice/api/jr/dinners/{id}
     * @PathVariable collega il valore nell'URL alla variabile id.
     * Funziona esattamente come il findTicket nel progetto Ticket.
     */
    @GetMapping("/jr/dinners/{id}")
    public Dinner findById(@PathVariable("id") int id)
    {
        return repo.findById(id).get();
    }

    /**
     * GET: Recupera tutti gli ordini con stato "pending".
     * URL: GET /dinnerservice/api/js/dinners/pending
     * Interroga il repository sfruttando la Query Method 'findByStatus'.
     */
    @GetMapping("/js/dinners/pending")
    public List<Dinner> findPending()
    {
        return repo.findByStatus("pending");
    }

    /**
     * GET: Recupera tutti gli ordini con stato "delivered".
     * URL: GET /dinnerservice/api/gr/dinners/delivered
     * Interroga il repository sfruttando la Query Method 'findByStatus'.
     */
    @GetMapping("/gr/dinners/delivered")
    public List<Dinner> findDelivered()
    {
        return repo.findByStatus("delivered");
    }

    /**
     * PUT: Aggiorna un ordine di cena esistente.
     * URL: PUT /dinnerservice/api/jr/dinners
     * Il metodo recupera l'entità dal database tramite ID e applica validazioni:
     * - costo e mancia non possono essere negativi
     * - lo stato deve essere uno tra: pending, delivered, paid
     * - non si può lasciare una mancia se l'ordine è ancora pending
     * Al termine i campi vengono sovrascritti e l'oggetto viene salvato.
     */
    @PutMapping("/jr/dinners")
    public Dinner updateDinner(@RequestBody Dinner newVersion)
    {
        // recupero la versione attuale dal database
        Optional<Dinner>    oldVersionOpt = repo.findById(newVersion.getId());
        Dinner              oldVersion    = oldVersionOpt.get();

        // validazione: costo e mancia non negativi
        if (newVersion.getCost() < 0 || newVersion.getTip() < 0)
            throw new RuntimeException("Valori numerici non validi");

        // validazione: lo stato deve essere uno di quelli ammessi
        List<String>        validStatuses = List.of("pending", "delivered", "paid");
        if (!validStatuses.contains(newVersion.getStatus()))
            throw new RuntimeException("Stato non riconosciuto");

        // validazione: non si può lasciare mancia se ancora pending
        if (newVersion.getTip() > 0 && newVersion.getStatus().equals("pending"))
            throw new RuntimeException("Impossibile lasciare mancia per un ordine ancora pending");

        // aggiornamento dei campi
        oldVersion.setDescription(newVersion.getDescription());
        oldVersion.setCost(newVersion.getCost());
        oldVersion.setStatus(newVersion.getStatus());
        oldVersion.setTip(newVersion.getTip());

        return repo.save(oldVersion);
    }
}