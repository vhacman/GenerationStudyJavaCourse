package com.generation.fooddelivery.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.fooddelivery.api.dto.DeliveryDTO;
import com.generation.fooddelivery.service.DeliveryService;
import com.generation.fooddelivery.service.ServiceException;

/**
 * REST Controller per le operazioni sulle Delivery (consegne).
 */
@RestController
@RequestMapping("fooddelivery/api/deliveries")
public class DeliveryAPI
{
    @Autowired
    DeliveryService deliveryService;

    /**
     * Endpoint GET per trovare una delivery per ID.
     * @param id ID della delivery
     * @return DeliveryDTO oppure 404
     */
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable("id") int id)
    {
        try
        {
            DeliveryDTO dto = deliveryService.findById(id);
            return ResponseEntity.ok(dto);
        }
        catch (ServiceException e)
        {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint POST per creare una nuova delivery.
     * @param dto DTO della delivery da creare
     * @return DeliveryDTO creata oppure messaggio di errore
     */
    @PostMapping
    public ResponseEntity<Object> newDelivery(@RequestBody DeliveryDTO dto)
    {
        try
        {
            DeliveryDTO saved = deliveryService.insert(dto);
            return ResponseEntity.status(203).body(saved);
        }
        catch (ServiceException e)
        {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
