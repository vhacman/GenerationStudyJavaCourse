package com.generation.javaeat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.javaeat.service.DeliveryService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.DeliveryDTO;

@RestController
@RequestMapping("/javaeat/api/deliveries")
public class DeliveryAPI
{
	@Autowired
	private DeliveryService deliveryService;

	@PostMapping
	public ResponseEntity<Object> insertDelivery(@RequestBody DeliveryDTO deliveryDTO)
	{
		try
		{
			DeliveryDTO savedDto = deliveryService.insert(deliveryDTO);
			return ResponseEntity.ok(savedDto);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping
	public ResponseEntity<List<DeliveryDTO>> findAll()
	{
		return ResponseEntity.ok(deliveryService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id)
	{
		try
		{
			DeliveryDTO delivery = deliveryService.findById(id);
			return ResponseEntity.ok(delivery);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> findByRestaurantId(@PathVariable int restaurantId)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByRestaurantId(restaurantId);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/costumer/{costumerId}")
	public ResponseEntity<Object> findByCostumerId(@PathVariable int costumerId)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByCostumerId(costumerId);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/rider/{riderId}")
	public ResponseEntity<Object> findByRiderId(@PathVariable int riderId)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByRiderId(riderId);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/costumer/{costumerId}/status/{status}")
	public ResponseEntity<Object> findByCostumerIdAndStatus(@PathVariable int costumerId, @PathVariable String status)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByCostumerIdAndStatus(costumerId, status);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il frontend (o i test JS) fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/restaurant/{restaurantId}/status/{status}")
	public ResponseEntity<Object> findByRestaurantIdAndStatus(@PathVariable int restaurantId, @PathVariable String status)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByRestaurantIdAndStatus(restaurantId, status);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il frontend (o i test JS) fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/rider/{riderId}/status/{status}")
	public ResponseEntity<Object> findByRiderIdAndStatus(@PathVariable int riderId, @PathVariable String status)
	{
		try
		{
			List<DeliveryDTO> deliveries = deliveryService.findByRiderIdAndStatus(riderId, status);
			return ResponseEntity.ok(deliveries);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il frontend (o i test JS) fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/count/costumer/{costumerId}")
	public ResponseEntity<Integer> countByCostumerId(@PathVariable int costumerId)
	{
		return ResponseEntity.ok(deliveryService.countByCostumerId(costumerId));
	}

	@GetMapping("/count/restaurant/{restaurantId}")
	public ResponseEntity<Integer> countByRestaurantId(@PathVariable int restaurantId)
	{
		return ResponseEntity.ok(deliveryService.countByRestaurantId(restaurantId));
	}

	@GetMapping("/count/rider/{riderId}")
	public ResponseEntity<Integer> countByRiderId(@PathVariable int riderId)
	{
		return ResponseEntity.ok(deliveryService.countByRiderId(riderId));
	}

	@GetMapping("/count/status/{status}")
	public ResponseEntity<Integer> countByStatus(@PathVariable String status)
	{
		return ResponseEntity.ok(deliveryService.countByStatus(status));
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody DeliveryDTO deliveryDTO)
	{
		try
		{
			return ResponseEntity.ok(deliveryService.save(deliveryDTO));
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable int id)
	{
		try
		{
			deliveryService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
}
