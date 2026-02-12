package com.generation.javaeat.service.dto.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import com.generation.javaeat.model.entities.Restaurant;
import com.generation.javaeat.service.dto.RestaurantDTO;

@Component
public class RestaurantDTOMapper
{
	CityDTOMapper 		cityDTOMapper;
	DeliveryDTOMapper 	deliveryDTOMapper;
	
	public RestaurantDTO toDTO(Restaurant restaurant)
	{
		if (restaurant == null)
			return null;
		RestaurantDTO dto = new RestaurantDTO();
		dto.setId		(restaurant.getId());
		dto.setName		(restaurant.getName());
		dto.setEmail	(restaurant.getEmail());
		dto.setPw		(restaurant.getPw());
		dto.setAddress	(restaurant.getAddress());
		dto.setCapacity	(restaurant.getCapacity());
		if (restaurant.getCity() != null)
			dto.setCity(cityDTOMapper.toDTO(restaurant.getCity()));
		if (restaurant.getDeliveries() != null)
			dto.setDeliveries(restaurant.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}
	
	public RestaurantDTO toDTONoCity(Restaurant restaurant)
	{
		if (restaurant == null)
			return null;
		RestaurantDTO dto = new RestaurantDTO();
		dto.setId		(restaurant.getId());
		dto.setName		(restaurant.getName());
		dto.setEmail	(restaurant.getEmail());
		dto.setPw		(restaurant.getPw());
		dto.setAddress	(restaurant.getAddress());
		dto.setCapacity	(restaurant.getCapacity());
		if (restaurant.getDeliveries() != null)
			dto.setDeliveries(restaurant.getDeliveries().stream().map(deliveryDTOMapper::toDTO).toList());
		return dto;
	}
	
	public RestaurantDTO toDTONoRelations(Restaurant restaurant)
	{
		if (restaurant == null)
			return null;
		RestaurantDTO dto = new RestaurantDTO();
		dto.setId		(restaurant.getId());
		dto.setName		(restaurant.getName());
		dto.setEmail	(restaurant.getEmail());
		dto.setPw		(restaurant.getPw());
		dto.setAddress	(restaurant.getAddress());
		dto.setCapacity	(restaurant.getCapacity());
		return dto;
	}
	
	public Restaurant toEntity(RestaurantDTO dto)
	{
		if (dto == null)
			return null;
		Restaurant restaurant = new Restaurant();
		restaurant.setId		(dto.getId());
		restaurant.setName		(dto.getName());
		restaurant.setEmail		(dto.getEmail());
		restaurant.setPw		(dto.getPw());
		restaurant.setAddress	(dto.getAddress());
		restaurant.setCapacity	(dto.getCapacity());
		if (dto.getCity() != null)
			restaurant.setCity(cityDTOMapper.toEntityNoRelations(dto.getCity()));
		if (dto.getDeliveries() != null)
			restaurant.setDeliveries(dto.getDeliveries().stream().map(deliveryDTOMapper::toEntityNoRelations).toList());
		return restaurant;
	}
	
	public Restaurant toEntityNoRelations(RestaurantDTO dto)
	{
		if (dto == null)
			return null;
		Restaurant restaurant = new Restaurant();
		restaurant.setId		(dto.getId());
		restaurant.setName		(dto.getName());
		restaurant.setEmail		(dto.getEmail());
		restaurant.setPw		(dto.getPw());
		restaurant.setAddress	(dto.getAddress());
		restaurant.setCapacity	(dto.getCapacity());
		return restaurant;
	}
	
	public List<RestaurantDTO> toDTOList(List<Restaurant> restaurants)
	{
		return restaurants.stream().map(this::toDTO).toList();
	}
	
	public List<Restaurant> toEntityList(List<RestaurantDTO> dtos)
	{
		return dtos.stream().map(this::toEntity).toList();
	}
	
}
