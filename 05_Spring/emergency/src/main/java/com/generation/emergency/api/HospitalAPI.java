package com.generation.emergency.api;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}