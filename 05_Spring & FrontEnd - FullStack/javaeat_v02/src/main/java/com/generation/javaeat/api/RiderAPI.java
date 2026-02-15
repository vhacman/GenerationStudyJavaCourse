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

import com.generation.javaeat.service.RiderService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.RiderDTO;

@RestController
@RequestMapping("/javaeat/api/riders")
public class RiderAPI
{
	@Autowired
	private RiderService riderService;

	@PostMapping
	public ResponseEntity<Object> insertRider(@RequestBody RiderDTO riderDTO)
	{
		try
		{
			RiderDTO savedDto = riderService.insert(riderDTO);
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
	public ResponseEntity<List<RiderDTO>> findAll()
	{
		return ResponseEntity.ok(riderService.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id)
	{
		try
		{
			RiderDTO rider = riderService.findById(id);
			return ResponseEntity.ok(rider);
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

	@GetMapping("/status/{status}")
	public ResponseEntity<Object> findByStatus(@PathVariable String status)
	{
		try
		{
			List<RiderDTO> riders = riderService.findByStatus(status);
			return ResponseEntity.ok(riders);
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
			List<RiderDTO> riders = riderService.findByLegalNameContaining(legalName);
			return ResponseEntity.ok(riders);
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
	public ResponseEntity<Object> save(@RequestBody RiderDTO riderDTO)
	{
		try
		{
			return ResponseEntity.ok(riderService.save(riderDTO));
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
			riderService.deleteById(id);
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
