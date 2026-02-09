package com.generation.sanctionweb.api;

import com.generation.sanctionweb.dto.SanctionDTO;
import com.generation.sanctionweb.dto.SanctionMapperDTO;
import com.generation.sanctionweb.model.entities.Sanction;
import com.generation.sanctionweb.model.repository.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST Controller per la gestione delle API relative alle sanzioni.
 * Espone endpoint CRUD attraverso il protocollo HTTP.
 */
@RestController
@RequestMapping("/sanction/api/sanctions")
public class SanctionAPI
{
    /*
     * ARCHITETTURA OOP - PATTERN E PRINCIPI:
     * 
     *     REST Controller  →  Gestione richieste HTTP
     *     Layer Arch.     →  Presentation Layer (API)
     *     @Autowired      →  Iniezione dipendenze
     *     Stream API      →  Elaborazione declarativa dati
     *     Repository      →  Accesso al database
     */

    /**
     * Repository per l'accesso ai dati delle sanzioni.
     */
    @Autowired
    private SanctionRepository repo;

    /**
     * Mapper per la conversione Entity ↔ DTO.
     */
    @Autowired
    private SanctionMapperDTO mapper;

    /**
     * Recupera tutte le sanzioni dal database.
     * 
     * @return lista di DTO contenenti i dati delle sanzioni
     */
    @GetMapping
    public List<SanctionDTO> getAll()
    {
        /**
         * Recupera tutte le entità dal DB, le converte in oggetti DTO
         * tramite il mapper e restituisce la lista completa.
         */
        return repo.findAll().stream()
                .map(s -> mapper.toDTO(s)) // Trasforma ogni entità in DTO
                .collect(Collectors.toList()); // Raccoglie i risultati in una lista
    }
}
