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
import com.generation.javaeat.service.CostumerService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.CostumerDTO;

@RestController
@RequestMapping("/javaeat/api/costumers")
public class CostumerAPI
{
	@Autowired
	private CostumerService costumerService;

	@PostMapping
	public ResponseEntity<Object> insertCostumer(@RequestBody CostumerDTO costumerDTO)
	{
		try
		{
			CostumerDTO savedDto = costumerService.insert(costumerDTO);
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
	public ResponseEntity<List<CostumerDTO>> findAll()
	{
		return ResponseEntity.ok(costumerService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id)
	{
		try
		{
			CostumerDTO costumer = costumerService.findById(id);
			return ResponseEntity.ok(costumer);
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
	public ResponseEntity<Object> findByCityId(@PathVariable int cityId)
	{
		try
		{
			List<CostumerDTO> costumers = costumerService.findByCityId(cityId);
			return ResponseEntity.ok(costumers);
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

	@GetMapping("/search/{legalName}")
	public ResponseEntity<Object> findByLegalNameContaining(@PathVariable String legalName)
	{
		try
		{
			List<CostumerDTO> costumers = costumerService.findByLegalNameContaining(legalName);
			return ResponseEntity.ok(costumers);
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

	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody CostumerDTO costumerDTO)
	{
		try
		{
			return ResponseEntity.ok(costumerService.save(costumerDTO));
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
			costumerService.deleteById(id);
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
