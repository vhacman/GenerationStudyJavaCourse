package com.generation.emergency.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.generation.emergency.model.entities.Hospital;
import com.generation.emergency.model.repository.HospitalRepository;

/**
 * REST CONTROLLER: API per gestire gli ospedali
 * Un REST Controller:
 * - Risponde a richieste HTTP (GET, POST, PUT, DELETE)
 * - Restituisce dati grezzi (JSON, XML) invece di pagine HTML
 * - Viene usato per creare API consumabili da frontend o app esterne
 */
@RestController
public class HospitalAPI
{
    /**
     * DEPENDENCY INJECTION: Repository degli ospedali
     * @Autowired: Spring inietta automaticamente l'implementazione del Repository
     * Equivalente manuale: repo = Context.getDependency(HospitalRepository.class)
     */
    @Autowired
    HospitalRepository repo;

    /**
     * ENDPOINT 1: Recupera TUTTI gli ospedali dal database
     * @GetMapping: Mappa questo metodo a una richiesta HTTP GET
     * URL: http://localhost:8080/emergency/api/hospitals
     * Risposta: Array JSON di tutti gli ospedali presenti nel database
     */
    @GetMapping("/emergency/api/hospitals")
    public List<Hospital> findHospitals()
    {
        /** findAll() è un metodo standard di JpaRepository
         *  Esegue: SELECT * FROM hospital */
        return repo.findAll();
    }

    /**
     * ENDPOINT 2: Trova l'ospedale con la coda più breve
     * URL: http://localhost:8080/emergency/api/hospitals/fastest
     * Risposta: Oggetto JSON dell'ospedale con il minor numero di persone in coda
     */
    @GetMapping("/emergency/api/hospitals/fastest")
    public Hospital findFastest()
    {
        /** findFirstByOrderByQueueAsc() è un Query Method di Spring Data JPA
         *  Esegue: SELECT * FROM hospital ORDER BY queue ASC LIMIT 1 */
        return repo.findFirstByOrderByQueueAsc();
    }

    /**
     * ENDPOINT 3: Recupera un singolo ospedale per ID
     * URL: http://localhost:8080/emergency/api/hospitals/{id}
     * @PathVariable collega il valore nell'URL alla variabile id
     * Risposta: Oggetto JSON dell'ospedale trovato
     */
    @GetMapping("/emergency/api/hospitals/{id}")
    public Hospital findById(@PathVariable("id") int id)
    {
        return repo.findById(id).get();
    }

    /**
     * ENDPOINT 4: Cerca ospedali per città
     * URL: http://localhost:8080/emergency/api/hospitals/bycity?city=Milano
     * @RequestParam prende il parametro dalla query string (dopo il ?)
     * Risposta: Array JSON degli ospedali nella città specificata
     */
    @GetMapping("/emergency/api/hospitals/bycity")
    public List<Hospital> findByCity(@RequestParam("city") String city)
    {
        /** findByCity è un Query Method: Spring genera la query automaticamente
         *  Esegue: SELECT * FROM hospital WHERE city = ? */
        return repo.findByCity(city);
    }

    /**
     * ENDPOINT 5: Cerca ospedali per indirizzo (ricerca parziale)
     * URL: http://localhost:8080/emergency/api/hospitals/byaddress?address=corso
     * Risposta: Array JSON degli ospedali il cui indirizzo contiene la stringa
     */
    @GetMapping("/emergency/api/hospitals/byaddress")
    public List<Hospital> findByAddress(@RequestParam("address") String address)
    {
        /** findByAddressContaining genera: SELECT * FROM hospital WHERE address LIKE %?% */
        return repo.findByAddressContaining(address);
    }

    /**
     * ENDPOINT 6: Aggiorna la coda di un ospedale
     * URL: http://localhost:8080/emergency/api/hospitals (verbo PUT)
     * @RequestBody: il corpo della request HTTP contiene un oggetto Hospital in formato JSON
     * Il metodo recupera l'ospedale dal database tramite ID, aggiorna solo la coda
     * e lo salva. Così gli altri campi (nome, indirizzo, città) non vengono modificati
     * anche se il client non li manda nel body.
     */
    @PutMapping("/emergency/api/hospitals")
    public Hospital updateQueue(@RequestBody Hospital newVersion)
    {
        // recupero la versione attuale dal database
        Optional<Hospital> oldVersionOpt = repo.findById(newVersion.getId());
        Hospital oldVersion = oldVersionOpt.get();

        // aggiorno solo il campo queue (numero persone in coda)
        oldVersion.setQueue(newVersion.getQueue());

        // salvo nel database e restituisco l'oggetto aggiornato
        return repo.save(oldVersion);
    }
}