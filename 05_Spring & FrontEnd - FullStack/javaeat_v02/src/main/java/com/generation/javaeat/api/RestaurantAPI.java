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
import com.generation.javaeat.service.RestaurantService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.RestaurantDTO;

@RestController
@RequestMapping("/javaeat/api/restaurants")
public class RestaurantAPI
{
	@Autowired
	private RestaurantService 	restaurantService;
	
	@PostMapping
	public ResponseEntity<Object> insertRestaurant(@RequestBody RestaurantDTO restaurantDTO)
	{
		try
		{
			RestaurantDTO savedDto = restaurantService.insert(restaurantDTO);
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
	public ResponseEntity<List<RestaurantDTO>> 	findAll()
	{
		return ResponseEntity.ok(restaurantService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> 	findById(@PathVariable int id)
	{
		try
		{
			RestaurantDTO restaurant = restaurantService.findById(id);
			return ResponseEntity.ok(restaurant);
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
	public ResponseEntity<Object> 	findByNameContaining(@PathVariable String name)
	{ 
		try
		{
			List<RestaurantDTO> restaurants = restaurantService.findByNameContaining(name);
			return ResponseEntity.ok(restaurants);
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
	
	@GetMapping("/city/{cityId}")
	public ResponseEntity<Object> 		findByCityId(@PathVariable int cityId)
	{ 
		try
		{
			List<RestaurantDTO> restaurants = restaurantService.findByCityId(cityId);
			return ResponseEntity.ok(restaurants);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			// Non uso notFound().build() perche' ritorna una risposta SENZA body,
			//e il test JS fa response.json() che fallisce su body vuoto.
			// Con status(404).body(response) ritorno comunque un JSON con il messaggio di errore.
			return ResponseEntity.status(404).body(response);
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> 		save(@RequestBody RestaurantDTO restaurantDTO)
	{
		try
		{
			return ResponseEntity.ok(restaurantService.save(restaurantDTO));
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getMessage());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> 		deleteById(@PathVariable int id)
	{
		try
		{
			restaurantService.deleteById(id);
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
