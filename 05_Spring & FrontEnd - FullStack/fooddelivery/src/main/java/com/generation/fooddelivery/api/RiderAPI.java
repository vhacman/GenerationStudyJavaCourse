package com.generation.fooddelivery.api;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.fooddelivery.api.dto.RiderDTO;
import com.generation.fooddelivery.service.RiderService;
import com.generation.fooddelivery.service.ServiceException;

/**
 * REST Controller per le operazioni sui Rider.
 * Espone endpoint API per la gestione dei rider.
 */
@RestController
@RequestMapping("/fooddelivery/api/riders")
public class RiderAPI
{
    @Autowired
    RiderService riderService;

    /**
     * Endpoint GET per trovare un rider per ID.
     * @param id ID del rider
     * @return RiderDTO oppure 404 se non trovato
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        try
        {
            RiderDTO loaded = riderService.findByID(id);
            return ResponseEntity.ok(loaded);
        }
        catch (ServiceException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint GET per trovare rider attivi tra due date.
     * @param d1 Data inizio
     * @param d2 Data fine
     * @return Lista di rider attivi
     */
    @GetMapping("/activebetween/{d1}/{d2}")
    public ResponseEntity<Object> findActive(
        @PathVariable("d1") LocalDateTime d1,
        @PathVariable("d2") LocalDateTime d2
    )
    {
        return ResponseEntity.ok(riderService.findActiveBetween(d1, d2));
    }
}
