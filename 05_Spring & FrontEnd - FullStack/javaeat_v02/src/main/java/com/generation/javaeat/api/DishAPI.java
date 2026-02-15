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

import com.generation.javaeat.service.DishService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.DishDTO;

@RestController
@RequestMapping("/javaeat/api/dishes")
public class DishAPI
{
	@Autowired
	private DishService dishService;

	@PostMapping
	public ResponseEntity<Object> insertDish(@RequestBody DishDTO dishDTO)
	{
		try
		{
			DishDTO savedDto = dishService.insert(dishDTO);
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
	public ResponseEntity<List<DishDTO>> findAll()
	{
		return ResponseEntity.ok(dishService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id)
	{
		try
		{
			DishDTO dish = dishService.findById(id);
			return ResponseEntity.ok(dish);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS faresponse.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@GetMapping("/restaurant/{restaurantId}")
	public ResponseEntity<Object> findByRestaurantId(@PathVariable int restaurantId)
	{
		try
		{
			List<DishDTO> dishes = dishService.findByRestaurantId(restaurantId);
			return ResponseEntity.ok(dishes);
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

	@GetMapping("/delivery/{deliveryId}")
	public ResponseEntity<Object> findByDeliveryId(@PathVariable int deliveryId)
	{
		try
		{
			List<DishDTO> dishes = dishService.findByDeliveryId(deliveryId);
			return ResponseEntity.ok(dishes);
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

	@GetMapping("/search/{name}")
	public ResponseEntity<Object> findByNameContaining(@PathVariable String name)
	{
		try
		{
			List<DishDTO> dishes = dishService.findByNameContaining(name);
			return ResponseEntity.ok(dishes);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			// e il test JS faresponse.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody DishDTO dishDTO)
	{
		try
		{
			return ResponseEntity.ok(dishService.save(dishDTO));
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
			dishService.deleteById(id);
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
