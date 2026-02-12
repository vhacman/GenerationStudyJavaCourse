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

import com.generation.javaeat.service.CityService;
import com.generation.javaeat.service.MyServiceException;
import com.generation.javaeat.service.dto.CityDTO;

@RestController
@RequestMapping("/javaeat/api/cities")
public class CityAPI
{
	@Autowired
	private CityService cityService;
	
	@PostMapping
	public ResponseEntity<Object> insertCity(@RequestBody CityDTO cityDTO)
	{
		try
		{
			CityDTO savedDto = cityService.insert(cityDTO);
            return ResponseEntity.ok(savedDto);			
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@GetMapping
	public ResponseEntity<List<CityDTO>> findAll()
	{
		return ResponseEntity.ok(cityService.findAll());
	}
	
	@GetMapping("/province/{province}")
	public ResponseEntity<Object> findByProvince(@PathVariable String province)
	{
		try
		{
			CityDTO city = cityService.findByProvince(province);
			return ResponseEntity.ok(city);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/search/{name}")
	public ResponseEntity<Object> findByNameContaining(@PathVariable String name)
	{ 
		try
		{
			CityDTO city = cityService.findByNameContaining(name);
			return ResponseEntity.ok(city);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable int id)
	{
		try
		{
			CityDTO city = cityService.findById(id);
			return ResponseEntity.ok(city);
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping("/save")
	public ResponseEntity<Object> save(@RequestBody CityDTO cityDTO)
	{
		try
		{
			return ResponseEntity.ok(cityService.save(cityDTO));
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteById(@PathVariable int id)
	{
		try
		{
			cityService.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		catch (MyServiceException e)
		{
			Map<String, Object> response = new HashMap<>();
			response.put("errors", e.getErrors());
			return ResponseEntity.badRequest().body(response);
		}
	}
	
}
